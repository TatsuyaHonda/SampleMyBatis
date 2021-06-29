package house.keep.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import house.keep.model.MonthWeekModel;
import house.keep.model.MonthWeekModelAsWeek;
import house.keep.model.account241.Account241AsMonthModel;
import house.keep.model.account241.DateBigDecimalEntry;
import house.keep.mybatis.dao.housekeeper.MoneyMapper;
import house.keep.mybatis.dao.housekeeper.OAccount241Mapper;
import house.keep.mybatis.dao.housekeeper.OAccount461Mapper;
import house.keep.mybatis.dao.housekeeper.OAccountconstMapper;
import house.keep.mybatis.dao.housekeeper.OCreditMapper;
import house.keep.mybatis.dao.housekeeper.OEntertainmentMapper;
import house.keep.mybatis.dao.housekeeper.OFoodMapper;
import house.keep.mybatis.dao.housekeeper.OGoodsMapper;
import house.keep.mybatis.dao.housekeeper.OLiveMapper;
import house.keep.mybatis.dao.housekeeper.OOtherMapper;
import house.keep.mybatis.dao.housekeeper.OWasteMapper;
import house.keep.mybatis.model.housekeeper.ActualValueResultEntity;
import house.keep.mybatis.model.housekeeper.Money;
import house.keep.mybatis.model.housekeeper.OAccount241;
import house.keep.mybatis.model.housekeeper.OAccount461;
import house.keep.mybatis.model.housekeeper.OAccountconst;
import house.keep.mybatis.model.housekeeper.OCredit;
import house.keep.mybatis.model.housekeeper.OEntertainment;
import house.keep.mybatis.model.housekeeper.OFood;
import house.keep.mybatis.model.housekeeper.OGoods;
import house.keep.mybatis.model.housekeeper.OLive;
import house.keep.mybatis.model.housekeeper.OOther;
import house.keep.mybatis.model.housekeeper.OWaste;

/**
 * めんどくさいことは全部この人に頼もう。
 * 何がしたいのか注文しよう。
 *
 */
@Named
@RequestScoped
public class MyBatisController {

	// 定数
	private static final String ACCOUNT241_PAYMENT = "MF（給与）";
	private static final String ACCOUNT241_BONUS = "MF（賞与）";
	private static final String ACCOUNT241_CREDIT = "クレジット";
	private static final String ACCOUNT241_TENNIS = "レックテニス";
	private static final String ACCOUNT241_RENT = "家賃";
	private static final String ACCOUNT241_WITHDRAWALLIST = "出金";

	private OAccount241Mapper oAccount241Mapper;
	private OAccount461Mapper oAccount461Mapper;
	private OAccountconstMapper oAccountConstMapper;
	private OCreditMapper oCreditMapper;
	private OEntertainmentMapper oEntertainmentMapper;
	private OFoodMapper oFoodMapper;
	private OGoodsMapper oGoodsMapper;
	private OLiveMapper oLiveMapper;
	private OOtherMapper oOtherMapper;
	private OWasteMapper oWasteMapper;
	private MoneyMapper moneyMapper;

	@Inject
	private MyBatisControllerModel myBatisModel;

	/**
	 * コンストラクタ。
	 *
	 * @throws IOException
	 */
	public MyBatisController() throws IOException {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();

			// Mapperの初回登録。
			oAccount241Mapper = session.getMapper(OAccount241Mapper.class);
			oAccount461Mapper = session.getMapper(OAccount461Mapper.class);
			oAccountConstMapper = session.getMapper(OAccountconstMapper.class);
			oCreditMapper = session.getMapper(OCreditMapper.class);
			oEntertainmentMapper = session.getMapper(OEntertainmentMapper.class);
			oFoodMapper = session.getMapper(OFoodMapper.class);
			oGoodsMapper = session.getMapper(OGoodsMapper.class);
			oLiveMapper = session.getMapper(OLiveMapper.class);
			oWasteMapper = session.getMapper(OWasteMapper.class);
			oOtherMapper = session.getMapper(OOtherMapper.class);
			moneyMapper = session.getMapper(MoneyMapper.class);

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * リクエストスコープへの登録。
	 */
	private void setRequestScoped() {
		// Modelへの登録。
		// IN：給与
		List<OAccount241> oAccount241List = oAccount241Mapper.selectAsMonth();
		// IN：給与
		List<OAccount461> oAccount461List = oAccount461Mapper.selectAsMonth();
		// IN：給与
		List<OAccountconst> oAccountConstList = oAccountConstMapper.selectAsMonth();
		// OUT：クレジットカード
		List<OCredit> oCreditList = oCreditMapper.selectAsMonth();
		// OUT：食費
		List<OFood> oFoodList = oFoodMapper.selectAsMonth();
		// OUT：生活用品費
		List<OLive> oLiveList = oLiveMapper.selectAsMonth();
		// OUT：その他費目
		List<OOther> oOtherList = oOtherMapper.selectAsMonth();
		// OUT：娯楽費
		List<OWaste> oWasteList = oWasteMapper.selectAsMonth();
		// OUT：交際費
		List<OEntertainment> oEntertainmentList = oEntertainmentMapper.selectAsMonth();
		// OUT：常駐家庭用品費
		List<OGoods> oGoodsList = oGoodsMapper.selectAsMonth();
		// OUT：現金
		List<Money> moneyList = moneyMapper.selectAsMonth();

		myBatisModel.setoAccount241List(oAccount241List);
		myBatisModel.setoAccount461List(oAccount461List);
		myBatisModel.setoAccountConstList(oAccountConstList);
		myBatisModel.setoCreditList(oCreditList);
		myBatisModel.setoEntertainmentList(oEntertainmentList);
		myBatisModel.setoFoodList(oFoodList);
		myBatisModel.setoGoodsList(oGoodsList);
		myBatisModel.setoLiveList(oLiveList);
		myBatisModel.setoOtherList(oOtherList);
		myBatisModel.setoWasteList(oWasteList);
		myBatisModel.setMoneyList(moneyList);
	}

	public OAccount241 selectAccount241WherePaymentdate(Date targetDate) {
		OAccount241 account241Entity = new OAccount241();
		account241Entity.setPaymentDate(targetDate);

		return oAccount241Mapper.selectWherePaymentdateByStringDateYYMM(account241Entity);
	}

	public OAccount241 selectAccount241NextPaymentdate(Date targetDate) {
		OAccount241 account241Entity = new OAccount241();
		account241Entity.setPaymentDate(targetDate);

		return oAccount241Mapper.selectNextPaymentdate(account241Entity);
	}

	public List<OAccount241> selectGroupByExpenseFromAccount241(Date singleSheetFrom, Date singleSheetTo) {
		OAccount241 account241Entity = new OAccount241();
		account241Entity.setSingleSheetFrom(singleSheetFrom);
		account241Entity.setSingleSheetTo(singleSheetTo);

		return oAccount241Mapper.selectGroupByExpenseWhereFromTo(account241Entity);
	}

	public List<OOther> selectGroupByExpenseWhereFromToAndDetails(Date singleSheetFrom, Date singleSheetTo) {
		OAccount241 account241Entity = new OAccount241();
		account241Entity.setSingleSheetFrom(singleSheetFrom);
		account241Entity.setSingleSheetTo(singleSheetTo);

		return oAccount241Mapper.selectGroupByExpenseWhereFromToAndDetails(account241Entity);
	}

	public ActualValueResultEntity selectActuaResults(Date singleSheetFrom, Date singleSheetTo) {
		OAccount241 account241Entity = new OAccount241();
		account241Entity.setSingleSheetFrom(singleSheetFrom);
		account241Entity.setSingleSheetTo(singleSheetTo);

		return oAccount241Mapper.selectActuaResults(account241Entity);
	}

	public ActualValueResultEntity selectActuaResults461(Date singleSheetFrom, Date singleSheetTo) {
		OAccount241 account241Entity = new OAccount241();
		account241Entity.setSingleSheetFrom(singleSheetFrom);
		account241Entity.setSingleSheetTo(singleSheetTo);

		return oAccount241Mapper.selectActuaResults461(account241Entity);
	}



	public List<OOther> getOtherList(Date fromDate) {
		OOther entity = new OOther();
		entity.setPaymentDate(fromDate);
		return oOtherMapper.selectDetail(entity);
	}

	public Map<Account241AsMonthModel, HouseKeepModel> getMonthMap(int getMonth) {
		Map<Account241AsMonthModel, HouseKeepModel> resultMap = new HashMap<>();

		// 1. 直近月の通帳241の明細を取得する。
		List<Account241AsMonthModel> account241AsMonthModelList = getAccount241AsMonthModelList();
		int size = account241AsMonthModelList.size();
		Account241AsMonthModel account241AsMonthModel = account241AsMonthModelList.get(size - getMonth);

		// 2. 明細を取得する。
		Date fromDate = account241AsMonthModel.getPaymentDate();
		Date toDate = new GregorianCalendar(2099, 4, 1).getTime();

		BigDecimal sumWithdrawalAmount = BigDecimal.ZERO;
		for (DateBigDecimalEntry sumWithdrawalAmountEntry : account241AsMonthModel.getWithdrawalEntryList()) {
			sumWithdrawalAmount = sumWithdrawalAmount.add(sumWithdrawalAmountEntry.getAmount());
		}

		HouseKeepModel houseKeepModel = getBetweenDetailModel(
				sumWithdrawalAmount,
				fromDate,
				toDate,
				BigDecimal.ZERO);

		// 3. 戻り値を設定する。
		resultMap.put(account241AsMonthModel, houseKeepModel);

		return resultMap;
	}

	public Map<OAccount241, HouseKeepModel> getDetailMap(int getMonth) {
		Map<OAccount241, HouseKeepModel> resultMap = new HashMap<>();

		// 1. 直近月の通帳241の明細を取得する。
		List<Account241AsMonthModel> account241AsMonthModelList = getAccount241AsMonthModelList();
		int size = account241AsMonthModelList.size();
		Account241AsMonthModel account241AsMonthModel = account241AsMonthModelList.get(size - getMonth);

		// 2. 「出金」明細リストのループ
		List<DateBigDecimalEntry> dateBigDecimalEntryList = account241AsMonthModel.getWithdrawalEntryList();
		BigDecimal stayAmount = BigDecimal.ZERO;
		for (int i = 0; i < dateBigDecimalEntryList.size(); i++) {
			Date fromDate = dateBigDecimalEntryList.get(i).getDate();
			Date toDate = ((i + 1) < dateBigDecimalEntryList.size())
					? dateBigDecimalEntryList.get(i + 1).getDate()
					: new GregorianCalendar(2099, 4, 1).getTime();

			OAccount241 account241 = new OAccount241();
			account241.setPaymentDate(dateBigDecimalEntryList.get(i).getDate());
			account241.setOutAmount(dateBigDecimalEntryList.get(i).getAmount().add(stayAmount));

			HouseKeepModel houseKeepModel = getBetweenDetailModel(
					dateBigDecimalEntryList.get(i).getAmount(),
					fromDate,
					toDate,
					stayAmount);

			stayAmount = houseKeepModel.getStayAmount();

			resultMap.put(account241, houseKeepModel);
		}

		return resultMap;
	}

	private HouseKeepModel getBetweenDetailModel(BigDecimal sumAmount, Date fromDate, Date toDate,
			BigDecimal stayAmount) {
		HouseKeepModel houseKeepModel = new HouseKeepModel();

		// 食費
		myBatisModel.getoFoodList().stream()
				.filter(food -> (0 <= food.getPaymentDate().compareTo(fromDate))
						&& (food.getPaymentDate().compareTo(toDate) < 0))
				.forEach(food -> {
					BigDecimal tempAmount = houseKeepModel.getoFood();
					tempAmount = tempAmount.add(food.getAmount());
					houseKeepModel.setoFood(tempAmount);
				});

		// 生活用品費
		myBatisModel.getoLiveList().stream()
				.filter(live -> (0 <= live.getPaymentDate().compareTo(fromDate))
						&& (live.getPaymentDate().compareTo(toDate) < 0))
				.forEach(live -> {
					BigDecimal tempAmount = houseKeepModel.getoLive();
					tempAmount = tempAmount.add(live.getAmount());
					houseKeepModel.setoLive(tempAmount);
				});

		// 娯楽費
		myBatisModel.getoWasteList().stream()
				.filter(waste -> (0 <= waste.getPaymentDate().compareTo(fromDate))
						&& (waste.getPaymentDate().compareTo(toDate) < 0))
				.forEach(waste -> {
					BigDecimal tempAmount = houseKeepModel.getoWaste();
					tempAmount = tempAmount.add(waste.getAmount());
					houseKeepModel.setoWaste(tempAmount);
				});

		// その他費目
		myBatisModel.getoOtherList().stream()
				.filter(other -> (0 <= other.getPaymentDate().compareTo(fromDate))
						&& (other.getPaymentDate().compareTo(toDate) < 0))
				.forEach(other -> {
					BigDecimal tempAmount = houseKeepModel.getoOther();
					tempAmount = tempAmount.add(other.getAmount());
					houseKeepModel.setoOther(tempAmount);
				});

		// 交際費
		myBatisModel.getoEntertainmentList().stream()
				.filter(entertainment -> (0 <= entertainment.getPaymentDate().compareTo(fromDate))
						&& (entertainment.getPaymentDate().compareTo(toDate) < 0))
				.forEach(entertainment -> {
					BigDecimal tempAmount = houseKeepModel.getoEntertainment();
					tempAmount = tempAmount.add(entertainment.getAmount());
					houseKeepModel.setoEntertainment(tempAmount);
				});

		// モノ費
		myBatisModel.getoGoodsList().stream()
				.filter(goods -> (0 <= goods.getPaymentDate().compareTo(fromDate))
						&& (goods.getPaymentDate().compareTo(toDate) < 0))
				.forEach(goods -> {
					BigDecimal tempAmount = houseKeepModel.getoGoods();
					tempAmount = tempAmount.add(goods.getAmount());
					houseKeepModel.setoGoods(tempAmount);
				});

		// 現金
		myBatisModel.getMoneyList().stream()
				.filter(money -> (0 <= money.getInputDate().compareTo(fromDate))
						&& (money.getInputDate().compareTo(toDate) < 0))
				.forEach(money -> {
					BigDecimal tempAmount = houseKeepModel.getMoney();
					tempAmount = tempAmount.add(money.getAmount());
					houseKeepModel.setMoney(tempAmount);
				});

		// 始金額：出金金額 + 前回繰越金
		BigDecimal allAmount = sumAmount.add(stayAmount);
		// その他費目
		BigDecimal otherAmount = houseKeepModel.getoOther();
		// モノ費
		BigDecimal goodsAmount = houseKeepModel.getoGoods();
		// 交際費
		BigDecimal entertainmentAmount = houseKeepModel.getoEntertainment();
		// 娯楽費
		BigDecimal wasteAmount = houseKeepModel.getoWaste();
		// 現金
		BigDecimal moneyAmount = houseKeepModel.getMoney();
		// 生活用品費
		BigDecimal liveAmount = houseKeepModel.getoLive();
		// 食費
		BigDecimal foodAmount = houseKeepModel.getoFood();

		// 用途不明金／繰越金
		BigDecimal unknownAmount = allAmount.subtract(
				(foodAmount.add(liveAmount).add(wasteAmount).add(entertainmentAmount).add(goodsAmount)
						.add(otherAmount)));

		if (moneyAmount.compareTo(BigDecimal.ZERO) > 0) {
			// 現金の入力あり：用途不明金として計上
			unknownAmount = unknownAmount.subtract(moneyAmount);
			houseKeepModel.setUnknownMoney(unknownAmount);
		} else {
			// 現金の入力なし：繰越金として計上
			houseKeepModel.setStayAmount(unknownAmount);
		}

		return houseKeepModel;
	}

	/**
	 * 月ごとの単票を作成する。
	 *
	 * @return HouseKeepModelリスト。
	 */
	public List<HouseKeepModel> makeHouseKeepModelList() {
		this.setRequestScoped();

		// 給与日を取得
		List<OAccount241> paydayList = myBatisModel.getoAccount241List().stream()
				.map(account241 -> (account241.getExpense().equals("MF（給与）")) ? account241 : null)
				.collect(Collectors.toList());
		paydayList.removeAll(Collections.singleton(null));

		List<HouseKeepModel> houseKeepModelList = new ArrayList<>();

		for (int i = 0; i < paydayList.size(); i++) {
			// 各月の計算期間を取得：[paydayAccount241] ~ [nextPaydayAccount241]
			OAccount241 paydayAccount241 = (OAccount241) paydayList.toArray()[i];
			OAccount241 nextPaydayAccount241 = null;
			if (i + 1 < paydayList.size()) {
				nextPaydayAccount241 = (OAccount241) paydayList.toArray()[i + 1];
			}
			final Date paymentDate = paydayAccount241.getPaymentDate();
			final Date nextPaymentDate = ((i + 1) < paydayList.size())
					? nextPaydayAccount241.getPaymentDate()
					: new GregorianCalendar(2099, 4, 1).getTime();

			// 画面に表示する月ごとの単票：HouseKeepModel
			HouseKeepModel houseKeepModel = new HouseKeepModel();
			houseKeepModel.setPaymentDate(paymentDate);

			// IN：給与
			houseKeepModel.setoAccount241(paydayAccount241.getInAmount());

			// OUT：クレジットカード
			myBatisModel.getoCreditList().stream()
					.filter(credit -> (0 <= credit.getPaymentDate().compareTo(paymentDate))
							&& (credit.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(credit -> {
						houseKeepModel.setoCredit(houseKeepModel.getoCredit().add(credit.getAmount()));
					});

			// OUT：食費
			myBatisModel.getoFoodList().stream()
					.filter(food -> (0 <= food.getPaymentDate().compareTo(paymentDate))
							&& (food.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(food -> {
						houseKeepModel.setoFood(houseKeepModel.getoFood().add(food.getAmount()));
					});

			// OUT：生活用品費
			myBatisModel.getoLiveList().stream()
					.filter(live -> (0 <= live.getPaymentDate().compareTo(paymentDate))
							&& (live.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(live -> {
						houseKeepModel.setoLive(houseKeepModel.getoLive().add(live.getAmount()));
					});

			// OUT：娯楽費
			myBatisModel.getoWasteList().stream()
					.filter(waste -> (0 <= waste.getPaymentDate().compareTo(paymentDate))
							&& (waste.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(waste -> {
						houseKeepModel.setoWaste(houseKeepModel.getoWaste().add(waste.getAmount()));
					});

			// OUT：その他費目
			myBatisModel.getoOtherList().stream()
					.filter(entertainment -> (0 <= entertainment.getPaymentDate().compareTo(paymentDate))
							&& (entertainment.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(entertainment -> {
						houseKeepModel
								.setoOther(houseKeepModel.getoOther().add(entertainment.getAmount()));
					});

			// OUT：交際費
			myBatisModel.getoEntertainmentList().stream()
					.filter(entertainment -> (0 <= entertainment.getPaymentDate().compareTo(paymentDate))
							&& (entertainment.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(entertainment -> {
						houseKeepModel
								.setoEntertainment(houseKeepModel.getoEntertainment().add(entertainment.getAmount()));
					});

			// OUT：常駐家庭用品費
			myBatisModel.getoGoodsList().stream()
					.filter(goods -> (0 <= goods.getPaymentDate().compareTo(paymentDate))
							&& (goods.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(goods -> {
						houseKeepModel.setoGoods(houseKeepModel.getoGoods().add(goods.getAmount()));
					});

			// OUT：出費合計
			// 口座241
			BigDecimal startSumAccount241 = new BigDecimal(0);
			BigDecimal inSumAccount241 = new BigDecimal(0);
			BigDecimal outSumAccount241 = new BigDecimal(0);
			houseKeepModel.setInAmount(new BigDecimal(0));
			houseKeepModel.setOutAmount(new BigDecimal(0));

			// Step1. 開始時点の残高を取得：開始時点残高
			Optional<OAccount241> oAccount241Opt = myBatisModel.getoAccount241List().stream()
					.filter(account241 -> (0 <= account241.getPaymentDate().compareTo(paymentDate))
							&& (account241.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.sorted((account241a, account241b) -> account241a.getPaymentDate()
							.compareTo(account241b.getPaymentDate()))
					.findFirst();
			startSumAccount241 = startSumAccount241.add(oAccount241Opt.get().getSum());

			// Step2.
			myBatisModel.getoAccount241List().stream()
					.filter(account241 -> (0 < account241.getPaymentDate().compareTo(paymentDate))
							&& (account241.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(account241 -> {
						houseKeepModel.setInAmount(houseKeepModel.getInAmount().add(account241.getInAmount()));
						houseKeepModel.setOutAmount(houseKeepModel.getOutAmount().add(account241.getOutAmount()));
					});
			inSumAccount241 = inSumAccount241.add(houseKeepModel.getInAmount());
			outSumAccount241 = outSumAccount241.add(houseKeepModel.getOutAmount());

			// 口座461
			BigDecimal startSumAccount461 = new BigDecimal(0);
			BigDecimal inSumAccount461 = new BigDecimal(0);
			BigDecimal outSumAccount461 = new BigDecimal(0);
			houseKeepModel.setInAmount(new BigDecimal(0));
			houseKeepModel.setOutAmount(new BigDecimal(0));

			// Step1. 開始時点の残高を取得：開始時点残高
			Optional<OAccount461> oAccount461Opt = myBatisModel.getoAccount461List().stream()
					.filter(account461 -> (0 <= account461.getPaymentDate().compareTo(paymentDate))
							&& (account461.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.sorted((account461a, account461b) -> account461a.getPaymentDate()
							.compareTo(account461b.getPaymentDate()))
					.findFirst();

			if (!oAccount461Opt.isPresent()) {
				oAccount461Opt = myBatisModel.getoAccount461List().stream()
						.filter(account461 -> (account461.getPaymentDate().compareTo(paymentDate) < 0))
						.sorted((account461a, account461b) -> (-1)
								* (account461a.getPaymentDate().compareTo(account461b.getPaymentDate())))
						.findFirst();
			}
			startSumAccount461 = startSumAccount461.add(oAccount461Opt.get().getSum());

			// Step2.
			myBatisModel.getoAccount461List().stream()
					.filter(account461 -> (0 < account461.getPaymentDate().compareTo(paymentDate))
							&& (account461.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(account461 -> {
						houseKeepModel.setInAmount(houseKeepModel.getInAmount().add(account461.getInAmount()));
						houseKeepModel.setOutAmount(houseKeepModel.getOutAmount().add(account461.getOutAmount()));
					});
			inSumAccount461 = inSumAccount461.add(houseKeepModel.getInAmount());
			outSumAccount461 = outSumAccount461.add(houseKeepModel.getOutAmount());

			// 口座定期
			BigDecimal startSumAccountConst = new BigDecimal(0);
			BigDecimal inSumAccountConst = new BigDecimal(0);
			BigDecimal outSumAccountConst = new BigDecimal(0);
			houseKeepModel.setInAmount(new BigDecimal(0));
			houseKeepModel.setOutAmount(new BigDecimal(0));

			// Step1. 開始時点の残高を取得：開始時点残高
			Optional<OAccountconst> oAccountConstOpt = myBatisModel.getoAccountConstList().stream()
					.filter(accountConst -> (0 <= accountConst.getPaymentDate().compareTo(paymentDate))
							&& (accountConst.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.sorted((accountConsta, accountConstb) -> accountConsta.getPaymentDate()
							.compareTo(accountConstb.getPaymentDate()))
					.findFirst();

			if (!oAccountConstOpt.isPresent()) {
				oAccountConstOpt = myBatisModel.getoAccountConstList().stream()
						.filter(accountConst -> (accountConst.getPaymentDate().compareTo(paymentDate) < 0))
						.sorted((accountConsta, accountConstb) -> (-1)
								* (accountConsta.getPaymentDate().compareTo(accountConstb.getPaymentDate())))
						.findFirst();
			}

			startSumAccountConst = startSumAccountConst.add(oAccountConstOpt.get().getSum());

			// Step2.
			myBatisModel.getoAccountConstList().stream()
					.filter(accountConst -> (0 < accountConst.getPaymentDate().compareTo(paymentDate))
							&& (accountConst.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(accountConst -> {
						houseKeepModel.setInAmount(houseKeepModel.getInAmount().add(accountConst.getInAmount()));
						houseKeepModel.setOutAmount(houseKeepModel.getOutAmount().add(accountConst.getOutAmount()));
					});
			inSumAccountConst = inSumAccountConst.add(houseKeepModel.getInAmount());
			outSumAccountConst = outSumAccountConst.add(houseKeepModel.getOutAmount());

			// 241
			BigDecimal startSumAll = new BigDecimal(0);
			BigDecimal inSumAll = new BigDecimal(0);
			BigDecimal outSumAll = new BigDecimal(0);
			BigDecimal usedSumAll = new BigDecimal(0);
			BigDecimal staySumAll = new BigDecimal(0);

			startSumAll = startSumAll.add(startSumAccount241);
			inSumAll = inSumAll.add(inSumAccount241);
			outSumAll = outSumAll.add(outSumAccount241);

			usedSumAll = outSumAll;
			staySumAll = startSumAll.add(inSumAll).subtract(usedSumAll);

			houseKeepModel.setOutSum(usedSumAll);
			houseKeepModel.setStayAmount(staySumAll);

			// OUT：用途不明金
			BigDecimal clearMoney = new BigDecimal(0);
			BigDecimal unKnownMoney = new BigDecimal(0);
			clearMoney = clearMoney.add(houseKeepModel.getoConstants());
			clearMoney = clearMoney.add(houseKeepModel.getoCredit());
			clearMoney = clearMoney.add(houseKeepModel.getoFood());
			clearMoney = clearMoney.add(houseKeepModel.getoLive());
			clearMoney = clearMoney.add(houseKeepModel.getoWaste());
			clearMoney = clearMoney.add(houseKeepModel.getoEntertainment());
			clearMoney = clearMoney.add(houseKeepModel.getoGoods());
			unKnownMoney = houseKeepModel.getOutSum().subtract(clearMoney);
			houseKeepModel.setUnknownMoney(unKnownMoney);

			// 461
			startSumAll = new BigDecimal(0);
			inSumAll = new BigDecimal(0);
			outSumAll = new BigDecimal(0);
			usedSumAll = new BigDecimal(0);
			staySumAll = new BigDecimal(0);

			startSumAll = startSumAll.add(startSumAccount461);
			inSumAll = inSumAll.add(inSumAccount461);
			outSumAll = outSumAll.add(outSumAccount461);

			usedSumAll = outSumAll;
			staySumAll = startSumAll.add(inSumAll).subtract(usedSumAll);

			houseKeepModel.setStartSum461(startSumAll);
			houseKeepModel.setInAmount461(inSumAll);
			houseKeepModel.setOutSum461(usedSumAll);
			houseKeepModel.setStayAmount461(staySumAll);

			// Const
			startSumAll = new BigDecimal(0);
			inSumAll = new BigDecimal(0);
			outSumAll = new BigDecimal(0);
			usedSumAll = new BigDecimal(0);
			staySumAll = new BigDecimal(0);

			startSumAll = startSumAll.add(startSumAccountConst);
			inSumAll = inSumAll.add(inSumAccountConst);
			outSumAll = outSumAll.add(outSumAccountConst);

			usedSumAll = outSumAll;
			staySumAll = startSumAll.add(inSumAll).subtract(usedSumAll);

			houseKeepModel.setStartSumConst(startSumAll);
			houseKeepModel.setInAmountConst(inSumAll);
			houseKeepModel.setOutSumConst(usedSumAll);
			houseKeepModel.setStayAmountConst(staySumAll);

			houseKeepModelList.add(houseKeepModel);
		}

		return houseKeepModelList;
	}

	/**
	 * 単月の明細を作成する。
	 *
	 * @return HouseKeepModelリスト。
	 */
	public MonthWeekModel makeMonthWeekModel() {
		this.setRequestScoped();

		MonthWeekModel monthWeekModel = new MonthWeekModel();

		List<Account241AsMonthModel> account241AsMonthModelList = getAccount241AsMonthModelList();
		int size = account241AsMonthModelList.size();
		Account241AsMonthModel account241AsMonthModel = account241AsMonthModelList.get(size - 1);

		// 給与
		monthWeekModel.setoAccount241(account241AsMonthModel.getPaymentAmount());
		monthWeekModel.setPaymentDate(account241AsMonthModel.getPaymentDate());

		// クレジットカード
		monthWeekModel.setoCredit(account241AsMonthModel.getCreditAmount());

		// 出金合計
		monthWeekModel.setOutSum(account241AsMonthModel.getOutSumAmount());

		// 残額
		BigDecimal lastAmount = new BigDecimal(0);
		lastAmount = monthWeekModel.getoAccount241().subtract(monthWeekModel.getOutSum());
		monthWeekModel.setLastAmount(lastAmount);

		// 出金明細リストを取得する。
		List<DateBigDecimalEntry> syukkinList = account241AsMonthModel.getWithdrawalEntryList();

		BigDecimal outAmount = new BigDecimal(0);
		Date outDate = null;
		for (int i = 0; i < syukkinList.size(); i++) {

			MonthWeekModelAsWeek monthWeekModelAsWeek = new MonthWeekModelAsWeek();
			//			if (!continueFlag) {
			//				outAmount = new BigDecimal(0);
			//				outDate = null;
			//			}

			// 「出金」ごとの計算期間を取得：[paydayAccount241] ~ [nextPaydayAccount241]
			DateBigDecimalEntry outPayment = (DateBigDecimalEntry) syukkinList.toArray()[i];
			DateBigDecimalEntry nextOutPayment = null;
			if ((i + 1) < syukkinList.size()) {
				nextOutPayment = (DateBigDecimalEntry) syukkinList.toArray()[i + 1];
			}
			final Date outPaymentDate = outPayment.getDate();
			final Date nextOutPaymentDate = (nextOutPayment != null)
					? nextOutPayment.getDate()
					: new GregorianCalendar(2099, 4, 1).getTime();

			outAmount = outAmount.add(outPayment.getAmount());
			outDate = outPayment.getDate();

			//			// 出金日が同じなら continue
			//			if (outPaymentDate.compareTo(nextOutPaymentDate) == 0) {
			//				continueFlag = true;
			//				continue;
			//			}
			//			continueFlag = false;

			// OUT：出金金額
			monthWeekModelAsWeek.setOut246(outAmount);
			monthWeekModelAsWeek.setPaymentDate(outDate);

			// OUT：食費
			myBatisModel.getoFoodList().stream()
					.filter(food -> (0 <= food.getPaymentDate().compareTo(outPaymentDate))
							&& (food.getPaymentDate().compareTo(nextOutPaymentDate) < 0))
					.forEach(food -> {
						monthWeekModelAsWeek.setoFood(monthWeekModelAsWeek.getoFood().add(food.getAmount()));
					});

			// OUT：生活用品費
			myBatisModel.getoLiveList().stream()
					.filter(live -> (0 <= live.getPaymentDate().compareTo(outPaymentDate))
							&& (live.getPaymentDate().compareTo(nextOutPaymentDate) < 0))
					.forEach(live -> {
						monthWeekModelAsWeek.setoLive(monthWeekModelAsWeek.getoLive().add(live.getAmount()));
					});

			// OUT：娯楽費
			myBatisModel.getoWasteList().stream()
					.filter(waste -> (0 <= waste.getPaymentDate().compareTo(outPaymentDate))
							&& (waste.getPaymentDate().compareTo(nextOutPaymentDate) < 0))
					.forEach(waste -> {
						monthWeekModelAsWeek.setoWaste(monthWeekModelAsWeek.getoWaste().add(waste.getAmount()));
					});

			// OUT：その他費目
			myBatisModel.getoOtherList().stream()
					.filter(entertainment -> (0 <= entertainment.getPaymentDate().compareTo(outPaymentDate))
							&& (entertainment.getPaymentDate().compareTo(nextOutPaymentDate) < 0))
					.forEach(entertainment -> {
						monthWeekModelAsWeek
								.setoOther(monthWeekModelAsWeek.getoOther().add(entertainment.getAmount()));
					});

			// OUT：交際費
			myBatisModel.getoEntertainmentList().stream()
					.filter(entertainment -> (0 <= entertainment.getPaymentDate().compareTo(outPaymentDate))
							&& (entertainment.getPaymentDate().compareTo(nextOutPaymentDate) < 0))
					.forEach(entertainment -> {
						monthWeekModelAsWeek
								.setoEntertainment(
										monthWeekModelAsWeek.getoEntertainment().add(entertainment.getAmount()));
					});

			// OUT：常駐家庭用品費
			myBatisModel.getoGoodsList().stream()
					.filter(goods -> (0 <= goods.getPaymentDate().compareTo(outPaymentDate))
							&& (goods.getPaymentDate().compareTo(nextOutPaymentDate) < 0))
					.forEach(goods -> {
						monthWeekModelAsWeek.setoGoods(monthWeekModelAsWeek.getoGoods().add(goods.getAmount()));
					});

			// OUT：用途不明金／現金
			BigDecimal clearMoney = new BigDecimal(0);
			BigDecimal unKnownMoney = new BigDecimal(0);
			clearMoney = clearMoney.add(monthWeekModelAsWeek.getoFood());
			clearMoney = clearMoney.add(monthWeekModelAsWeek.getoLive());
			clearMoney = clearMoney.add(monthWeekModelAsWeek.getoWaste());
			clearMoney = clearMoney.add(monthWeekModelAsWeek.getoEntertainment());
			clearMoney = clearMoney.add(monthWeekModelAsWeek.getoGoods());
			unKnownMoney = outPayment.getAmount().subtract(clearMoney);

			if ((i + 1) < syukkinList.size()) {
				// OUT：用途不明金
				monthWeekModelAsWeek.setUnknownMoney(unKnownMoney);
			} else {
				// OUT：現金
				monthWeekModelAsWeek.setCash(unKnownMoney);
			}

			monthWeekModel.addDetailModelList(monthWeekModelAsWeek);
		}

		return monthWeekModel;
	}

	/**
	 * 月ごとの単票を作成する。
	 *
	 * @return HouseKeepModelリスト。
	 */
	private List<Account241AsMonthModel> getAccount241AsMonthModelList() {
		this.setRequestScoped();

		List<Account241AsMonthModel> account241AsMonthModelList = new ArrayList<>();

		// 給与日リストを取得
		List<OAccount241> paydayList = myBatisModel.getoAccount241List().stream()
				.map(account241 -> (account241.getExpense().equals("MF（給与）")) ? account241 : null)
				.collect(Collectors.toList());
		paydayList.removeAll(Collections.singleton(null));

		for (int i = 0; i < paydayList.size(); i++) {

			Account241AsMonthModel account241AsMonthModel = new Account241AsMonthModel();

			// 各月の計算期間を取得：[paydayAccount241] ~ [nextPaydayAccount241]
			OAccount241 paydayAccount241 = (OAccount241) paydayList.toArray()[i];
			OAccount241 nextPaydayAccount241 = null;
			if ((i + 1) < paydayList.size()) {
				nextPaydayAccount241 = (OAccount241) paydayList.toArray()[i + 1];
			}
			final Date paymentDate = paydayAccount241.getPaymentDate();
			final Date nextPaymentDate = (nextPaydayAccount241 != null)
					? nextPaydayAccount241.getPaymentDate()
					: new GregorianCalendar(2099, 4, 1).getTime();

			// 設定する
			myBatisModel.getoAccount241List().stream()
					.filter(account241 -> (0 <= account241.getPaymentDate().compareTo(paymentDate))
							&& (account241.getPaymentDate().compareTo(nextPaymentDate) < 0))
					.forEach(account241 -> {
						switch (account241.getExpense()) {
						case ACCOUNT241_PAYMENT: // 給与
							account241AsMonthModel.setPaymentEntry(
									account241.getPaymentDate(),
									account241.getInAmount());
							break;
						case ACCOUNT241_BONUS: // 賞与
							account241AsMonthModel.setBonusEntry(
									account241.getPaymentDate(),
									account241.getInAmount());
							break;
						case ACCOUNT241_CREDIT:
							account241AsMonthModel.setCreditEntry(
									account241.getPaymentDate(),
									account241.getOutAmount());
							break;
						case ACCOUNT241_TENNIS:
							account241AsMonthModel.setTennisEntry(
									account241.getPaymentDate(),
									account241.getOutAmount());
							break;
						case ACCOUNT241_RENT:
							account241AsMonthModel.setRentEntry(
									account241.getPaymentDate(),
									account241.getOutAmount());
							break;
						case ACCOUNT241_WITHDRAWALLIST:
							account241AsMonthModel.addWithdrawalEntryList(
									account241.getPaymentDate(),
									account241.getOutAmount());
							break;
						default:
							//throw new RuntimeException("予期せぬ分岐");
							break;
						}
					});

			account241AsMonthModelList.add(account241AsMonthModel);
		}

		return account241AsMonthModelList.stream()
				.sorted((account241ModelOld, account241ModelNew) -> account241ModelOld
						.getPaymentDate().compareTo(account241ModelNew.getPaymentDate()))
				.collect(Collectors.toList());
	}
}