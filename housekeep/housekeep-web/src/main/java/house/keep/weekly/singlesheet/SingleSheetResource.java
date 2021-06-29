package house.keep.weekly.singlesheet;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

import house.keep.mybatis.model.housekeeper.OOther;
import house.keep.weekly.model.WeeklySheetModel;
import house.keep.weekly.singlesheet.model.SingleSheetModel;

@Path("SingleSheet")
@RequestScoped
public class SingleSheetResource {

	@Inject
	private SingleSheetManager singleSheetManager;

	@Context
	private HttpServletRequest httpServletRequest;

	/**
	 * 単票を返す。
	 *
	 * @param targetMonthString 対象月
	 * @return 単票
	 */
	@GET
	@Path("/getSingleSheet")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getSingleSheet(@QueryParam("targetMonth") String targetMonthString) {
		Date targetMonthDate = null;

		if (targetMonthString == null || targetMonthString.isEmpty()) {
			targetMonthDate = new Date(System.currentTimeMillis());
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
			try {
				targetMonthDate = dateFormat.parse(targetMonthString);
			} catch (ParseException e) {
				throw new RuntimeException("QueryParamのパースに失敗しました。（QueryParam:" + targetMonthString + "）", e);
			}
		}

		SingleSheetModel singleSheetModel = singleSheetManager.getTargetSingleSheet(targetMonthDate);
		List<Map<Object, Object>> singleSheetWeeklyModel = getSingleSheetWeeklyModel(singleSheetModel);

		httpServletRequest.setAttribute("singleSheetModel", singleSheetModel);
		httpServletRequest.setAttribute("singleSheetWeeklyModel", singleSheetWeeklyModel);

		return new Viewable("/SingleSheet.jsp", singleSheetModel);
	}

	/**
	 * 週単位の明細MapListを返す。
	 *
	 * @param singleSheetModel 単票Model
	 * @return 週単位の明細MapList
	 */
	private List<Map<Object, Object>> getSingleSheetWeeklyModel(SingleSheetModel singleSheetModel) {
		List<Map<Object, Object>> SingleSheetWeeklyModel = new ArrayList<>();

		List<WeeklySheetModel> weeklySheetModelSortedList = singleSheetModel.getWeeklySheetModelSortedList();

		Map<Object, Object> rowTitleMap = new HashMap<>();
		weeklySheetModelSortedList.stream().forEach(weeklySheetModel -> {
			weeklySheetModel.getOtherList().stream().forEach(otherModel -> {
				rowTitleMap.put(otherModel.getExpense(), null);
			});
		});
		rowTitleMap.put("週計", BigDecimal.ZERO);
		Map<Object, Object> sortedRowTitleMap = new LinkedHashMap<>();
		sortMapUsingByTitleSortOrder(rowTitleMap, sortedRowTitleMap);

		for (int i = 0; i < weeklySheetModelSortedList.size(); i++) {
			WeeklySheetModel weeklySheetModel = weeklySheetModelSortedList.get(i);

			Map<Object, Object> rowMap = new HashMap<>();
			BigDecimal weeklySumAmount = BigDecimal.ZERO;

			for (OOther otherModel : weeklySheetModel.getOtherList()) {
				String expense = otherModel.getExpense();
				BigDecimal amount = otherModel.getAmount();
				if (BigDecimal.ZERO.compareTo(amount) == 0) {
					continue;
				}
				weeklySumAmount = weeklySumAmount.add(amount);

				if(rowMap.containsKey(expense)) {
					BigDecimal amountContains = (BigDecimal) rowMap.get(expense);
					amountContains = amountContains.add(amount);
					rowMap.put(expense, amountContains);
				} else {
					rowMap.put(expense, amount);
				}
			}
			if (BigDecimal.ZERO.compareTo(weeklySumAmount) == 0) {
				rowMap.remove("週計");
			} else {
				rowMap.put("週計", weeklySumAmount);
			}

			//			modifyTitleInTheMap(rowMap, sortedRowTitleMap);
			Map<Object, Object> sortedRowMap = getSortedMapUsingByTitleSortOrder(rowMap, sortedRowTitleMap);

			// タイトル
			String incrementedInt = String.valueOf(i + 1);
			sortedRowMap.put("", "week" + incrementedInt);
			sortedRowMap.put("期間", getFormattedStringMMdd(weeklySheetModel.getWeeklySheetFrom()) + " - "
					+ getFormattedStringMMdd(weeklySheetModel.getWeeklySheetTo()));

			Map<Object, Object> finalSortedRowMap = new LinkedHashMap<>();
			sortMapUsingByTitleSortOrder(sortedRowMap, finalSortedRowMap);

			SingleSheetWeeklyModel.add(finalSortedRowMap);
		}

		return SingleSheetWeeklyModel;
	}

	private Map<Object, Object> getSortedMapUsingByTitleSortOrder(Map<Object, Object> rowMap,
			Map<Object, Object> sortedRowTitleMap) {
		Map<Object, Object> sortedRowMap = new LinkedHashMap<>();
		sortedRowTitleMap.entrySet().stream().forEach(entry -> {
			Object value = (rowMap.containsKey(entry.getKey()))
					? rowMap.get(entry.getKey())
					: "";
			sortedRowMap.put(entry.getKey(), value);
		});

		return sortedRowMap;
	}

	/**
	 * タイトルの平仄を取る。
	 *
	 * @param rowMap 対象
	 * @param titleSortOrderMap 期待値
	 */
	//	private void modifyTitleInTheMap(Map<Object, Object> rowMap, Map<Object, Object> titleSortOrderMap) {
	//		titleSortOrderMap.entrySet().stream().forEach(entry -> {
	//			if (!rowMap.containsKey(entry.getKey())) {
	//				rowMap.put(entry.getKey(), "");
	//			}
	//		});
	//	}

	/**
	 * 費目のソート順位でMapをソートする。
	 *
	 * @param rowMap
	 * @param sortedMap
	 */
	private void sortMapUsingByTitleSortOrder(Map<Object, Object> rowMap, Map<Object, Object> sortedMap) {
		rowMap.entrySet().stream()
				.sorted((entry1, entry2) -> Integer.compare(
						getTitleSortOrder(entry1.getKey()),
						getTitleSortOrder(entry2.getKey())))
				.forEach(entry -> {
					sortedMap.put(entry.getKey(), entry.getValue());
				});
	}

	/**
	 * 費目のソート順位を返す。
	 *
	 * @param titleObject 費目
	 * @return ソート順位
	 */
	private int getTitleSortOrder(Object titleObject) {
		String titleString = (String) titleObject;
		if (titleString.equals("")) {
			return 1;
		}
		switch (titleString) {
		case "変動費":
			return 1;
		case "期間":
			return 2;
		case "週計":
			return 3;
		case "食費":
			return 4;
		case "生活用品費":
			return 5;
		case "娯楽費":
			return 6;
		case "交際費":
			return 7;
		case "物品購入費":
			return 8;
		case "交通系チャージ費":
			return 9;
		default:
			return 99;
		}
	}

	/**
	 * DateにMMddのフォーマットを適用した結果文字列を返す。
	 *
	 * @param targetDate 日付
	 * @return フォーマット文字列
	 */
	private String getFormattedStringMMdd(Date targetDate) {
		SimpleDateFormat dateFormatterPlain = new SimpleDateFormat("MMdd");
		String formattedDate = dateFormatterPlain.format(targetDate);
		return formattedDate;
	}

}
