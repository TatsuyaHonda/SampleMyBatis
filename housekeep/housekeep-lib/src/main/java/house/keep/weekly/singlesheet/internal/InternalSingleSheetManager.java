package house.keep.weekly.singlesheet.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import house.keep.mybatis.MyBatisController;
import house.keep.mybatis.model.housekeeper.ActualValueResultEntity;
import house.keep.mybatis.model.housekeeper.OAccount241;
import house.keep.mybatis.model.housekeeper.OOther;
import house.keep.weekly.model.WeeklySheetModel;
import house.keep.weekly.singlesheet.SingleSheetManager;
import house.keep.weekly.singlesheet.model.SingleSheetModel;

/**
 * 単票マネージャ
 *
 * @author Tatsuya
 *
 */
@RequestScoped
public class InternalSingleSheetManager implements SingleSheetManager {

	@Inject
	private MyBatisController myBatisController;

	/** 基準日（給与日） */
	private static final int PAYMENT_DAY = 25;

	/** 週票の開始曜日 */
	private static final int WEEK_START = Calendar.MONDAY;

	/**
	 * 対象月の単票を取得する。
	 *
	 * @param targetMonth 対象月
	 * @return 単票Model
	 */
	@Override
	public SingleSheetModel getTargetSingleSheet(Date targetMonth) {
		SingleSheetModel baseSingleSheetModel = new SingleSheetModel();

		OAccount241 targetMonthAccount241Entity = myBatisController.selectAccount241WherePaymentdate(targetMonth);
		Date baseDate = targetMonthAccount241Entity.getPaymentDate();

		BigDecimal paymentAmount = targetMonthAccount241Entity.getInAmount();
		baseSingleSheetModel.setPayday(baseDate);
		baseSingleSheetModel.setPaymentAmount(paymentAmount);

		this.calculateSingleSheetFromTo(baseSingleSheetModel, baseDate);
		this.calculateWeeklySheetFromTo(baseSingleSheetModel);
		this.calculateSingleSheetAmount(baseSingleSheetModel);
		this.calculateWeeklySheetAmount(baseSingleSheetModel);

		this.calculateActualResult(baseSingleSheetModel, baseDate);

		this.validateSingleSheetModel(baseSingleSheetModel);
		return baseSingleSheetModel;
	};

	/**
	 * 単票リストを取得する。
	 *
	 * @return 単票Modelリスト
	 */
	@Override
	public List<SingleSheetModel> getSingleSheetList() {
		return null;
	};

	/**
	 * 対象期間（月）を算出する。
	 *
	 * @param baseSingleSheetModel 単票Model
	 * @param baseDate 基準日
	 * @param nextBaseDate 次回基準日
	 * @return 単票Model
	 */
	private void calculateSingleSheetFromTo(SingleSheetModel baseSingleSheetModel, Date baseDate) {
		this.calculateSingleSheetFromTo(baseSingleSheetModel, baseDate, -1);
	}

	/**
	 * 対象期間（月）を算出する。
	 *
	 * @param baseSingleSheetModel 単票Model
	 * @param baseDate 基準日
	 * @param nextBaseDate 次回基準日
	 * @return 単票Model
	 */
	private void calculateSingleSheetFromTo(SingleSheetModel baseSingleSheetModel, Date baseDate, int graceDate) {
		// 1. 対象期間の算出
		Calendar singleSheetFromCalendar = Calendar.getInstance();
		Calendar singleSheetToCalendar = Calendar.getInstance();
		singleSheetToCalendar.setTime(baseDate);
		singleSheetToCalendar.add(Calendar.MONTH, 1);

		OAccount241 targetMonthAccount241Entity = myBatisController
				.selectAccount241WherePaymentdate(singleSheetToCalendar.getTime());

		if (baseDate == null) {
			throw new RuntimeException("[単月の計算時エラー] 基準日がnullのため、単票の計算ができません。");
		} else if (targetMonthAccount241Entity == null) {
			singleSheetFromCalendar.setTime(baseDate);
			singleSheetToCalendar.setTime(baseDate);
			singleSheetToCalendar.add(Calendar.MONTH, 1);
			singleSheetToCalendar.add(Calendar.DATE, graceDate);
		} else {
			Date nextBaseDate = targetMonthAccount241Entity.getPaymentDate();
			singleSheetFromCalendar.setTime(baseDate);
			singleSheetToCalendar.setTime(nextBaseDate);
			singleSheetToCalendar.add(Calendar.DATE, graceDate);
		}

		clearHourAndMinuteAndSecondAndMillisecond(singleSheetFromCalendar);
		clearHourAndMinuteAndSecondAndMillisecond(singleSheetToCalendar);

		baseSingleSheetModel.setSingleSheetFrom(singleSheetFromCalendar.getTime());
		baseSingleSheetModel.setSingleSheetTo(singleSheetToCalendar.getTime());
	}

	/**
	 * 対象期間（週）を算出する。
	 *
	 * @param baseSingleSheetModel 単票Model
	 */
	private void calculateWeeklySheetFromTo(SingleSheetModel baseSingleSheetModel) {
		List<WeeklySheetModel> weeklySheetModelSortedList = new ArrayList<>();

		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(baseSingleSheetModel.getSingleSheetFrom());
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(baseSingleSheetModel.getSingleSheetTo());

		while (1 > fromCalendar.compareTo(toCalendar)) {

			if (WEEK_START == fromCalendar.get(Calendar.DAY_OF_WEEK)) {
				WeeklySheetModel newWeeklySheet = new WeeklySheetModel();
				newWeeklySheet.setWeeklySheetFrom(fromCalendar.getTime());

				Calendar weeklySheetFromToCalendar = Calendar.getInstance();
				weeklySheetFromToCalendar.setTime(newWeeklySheet.getWeeklySheetFrom());
				weeklySheetFromToCalendar.add(Calendar.DATE, 6);
				newWeeklySheet.setWeeklySheetTo(weeklySheetFromToCalendar.getTime());

				weeklySheetModelSortedList.add(newWeeklySheet);
			}

			fromCalendar.add(Calendar.DATE, 1);
		}

		baseSingleSheetModel.setWeeklySheetModelSortedList(weeklySheetModelSortedList);
	}

	/**
	 * 単票の金額を計算する。
	 *
	 * @param targetSingleSheetModel
	 */
	private void calculateSingleSheetAmount(SingleSheetModel targetSingleSheetModel) {
		Date singleSheetFrom = targetSingleSheetModel.getSingleSheetFrom();
		Date singleSheetTo = targetSingleSheetModel.getSingleSheetTo();

		List<OAccount241> account241EntityList = myBatisController.selectGroupByExpenseFromAccount241(singleSheetFrom,
				singleSheetTo);
		for (OAccount241 account241Entity : account241EntityList) {
			switch (account241Entity.getExpense()) {
			case "家賃":
				targetSingleSheetModel.setHouseRentCost(account241Entity.getOutAmount());
				break;
			case "レックテニス":
				targetSingleSheetModel.setTennisCost(account241Entity.getOutAmount());
				break;
			case "クレジット":
				targetSingleSheetModel.setCreditPayment(account241Entity.getOutAmount());
				break;
			}
		}

		List<OOther> otherEntityList = myBatisController.selectGroupByExpenseWhereFromToAndDetails(singleSheetFrom,
				singleSheetTo);
		BigDecimal regularTrainFee = BigDecimal.ZERO;
		List<OOther> trainFeeOtherEntityList = otherEntityList.stream()
				.filter(otherEntity -> "定期代".equals(otherEntity.getExpense()))
				.collect(Collectors.toList());
		for (OOther other : trainFeeOtherEntityList) {
			regularTrainFee = regularTrainFee.add(other.getAmount());
		}
		targetSingleSheetModel.setRegularTrainFee(regularTrainFee);
	}

	/**
	 * 週票の金額を計算する。
	 *
	 * @param targetSingleSheetModel
	 */
	private void calculateWeeklySheetAmount(SingleSheetModel targetSingleSheetModel) {
		List<WeeklySheetModel> weeklySheetModelList = targetSingleSheetModel.getWeeklySheetModelSortedList();
		for (WeeklySheetModel weeklySheetModel : weeklySheetModelList) {
			Date weeklySheetFrom = weeklySheetModel.getWeeklySheetFrom();
			Date weeklySheetTo = weeklySheetModel.getWeeklySheetTo();

			List<OOther> otherEntityList = myBatisController.selectGroupByExpenseWhereFromToAndDetails(weeklySheetFrom,
					weeklySheetTo);
			otherEntityList = otherEntityList.stream()
					.filter(otherEntity -> !("定期代".equals(otherEntity.getExpense())))
					.collect(Collectors.toList());
			weeklySheetModel.setOtherList(otherEntityList);
		}
	}

	/**
	 * Calendar の時分秒を切り捨てる。
	 *
	 * @param targetCalendar
	 */
	private void clearHourAndMinuteAndSecondAndMillisecond(Calendar targetCalendar) {
		targetCalendar.clear(Calendar.MINUTE);
		targetCalendar.clear(Calendar.SECOND);
		targetCalendar.clear(Calendar.MILLISECOND);
		// 時の部分をクリアするには、setで入れないといけない。
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
	}

	/**
	 * SingleSheetModel の null チェックを実施する。
	 * null の場合はデフォルト値をセットする。
	 *
	 * @param targetCalendar
	 */
	private void validateSingleSheetModel(SingleSheetModel targetSingleSheetModel) {
		if (targetSingleSheetModel.getPayday() == null
				|| targetSingleSheetModel.getPaymentAmount() == null
				|| targetSingleSheetModel.getSingleSheetFrom() == null
				|| targetSingleSheetModel.getSingleSheetTo() == null) {
			throw new RuntimeException("給与、単票期間(From)、単票期間(To)のいずれかがnull");
		}

		if (targetSingleSheetModel.getHouseRentCost() == null) {
			targetSingleSheetModel.setHouseRentCost(BigDecimal.ZERO);
		}
		if (targetSingleSheetModel.getTennisCost() == null) {
			targetSingleSheetModel.setTennisCost(BigDecimal.ZERO);
		}
		if (targetSingleSheetModel.getCreditPayment() == null) {
			targetSingleSheetModel.setCreditPayment(BigDecimal.ZERO);
		}
		if (targetSingleSheetModel.getRegularTrainFee() == null) {
			targetSingleSheetModel.setRegularTrainFee(BigDecimal.ZERO);
		}

		// 週票リスト
		for (WeeklySheetModel weeklySheetModel : targetSingleSheetModel.getWeeklySheetModelSortedList()) {
			if (weeklySheetModel.getWeeklySheetFrom() == null
					|| weeklySheetModel.getWeeklySheetTo() == null) {
				throw new RuntimeException("週票期間(From)、週票期間(To)のいずれかがnull");
			}

			for (OOther other : weeklySheetModel.getOtherList()) {
				if (other.getAmount() == null) {
					other.setAmount(BigDecimal.ZERO);
				}

			}
		}

		ActualValueResultEntity actual241ValueResultEntity = targetSingleSheetModel.getActualValue241Result();
		targetSingleSheetModel.setActualValue241Result(setActualValueResultEntity(actual241ValueResultEntity));
		ActualValueResultEntity actual461ValueResultEntity = targetSingleSheetModel.getActualValue461Result();
		targetSingleSheetModel.setActualValue461Result(setActualValueResultEntity(actual461ValueResultEntity));
	}

	private ActualValueResultEntity setActualValueResultEntity(ActualValueResultEntity actualValueResultEntity) {
		if (actualValueResultEntity == null) {
			actualValueResultEntity = new ActualValueResultEntity();
		}

		if (actualValueResultEntity.getBudget() == null) {
			actualValueResultEntity.setBudget(BigDecimal.ZERO);
		}
		if (actualValueResultEntity.getIncome() == null) {
			actualValueResultEntity.setIncome(BigDecimal.ZERO);
		}
		if (actualValueResultEntity.getMonthlyBalance() == null) {
			actualValueResultEntity.setMonthlyBalance(BigDecimal.ZERO);
		}
		if (actualValueResultEntity.getMonthlyUsage() == null) {
			actualValueResultEntity.setMonthlyUsage(BigDecimal.ZERO);
		}
		if (actualValueResultEntity.getRemittance() == null) {
			actualValueResultEntity.setRemittance(BigDecimal.ZERO);
		}
		if (actualValueResultEntity.getTargetMonthlyBalance() == null) {
			actualValueResultEntity.setTargetMonthlyBalance(BigDecimal.ZERO);
		}
		return actualValueResultEntity;
	}

	private void calculateActualResult(SingleSheetModel baseSingleSheetModel, Date baseDate) {
		SingleSheetModel tempSingleSheetModel = new SingleSheetModel();
		this.calculateSingleSheetFromTo(tempSingleSheetModel, baseDate, 0);

		Date singleSheetFrom = tempSingleSheetModel.getSingleSheetFrom();
		Date singleSheetTo = tempSingleSheetModel.getSingleSheetTo();

		ActualValueResultEntity actualValue241Result = myBatisController.selectActuaResults(singleSheetFrom,
				singleSheetTo);
		ActualValueResultEntity actualValue461Result = myBatisController.selectActuaResults461(singleSheetFrom,
				singleSheetTo);

		baseSingleSheetModel.setActualValue241Result(actualValue241Result);
		baseSingleSheetModel.setActualValue461Result(actualValue461Result);
	}
}
