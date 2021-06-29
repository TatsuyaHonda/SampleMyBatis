package house.keep.mybatis;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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

@Named
@RequestScoped
public class MyBatisControllerModel {
	// IN：口座241
	private List<OAccount241> oAccount241List;
	// IN：口座461
	private List<OAccount461> oAccount461List;
	// IN：口座定期
	private List<OAccountconst> oAccountConstList;
	// OUT：クレジットカード
	private List<OCredit> oCreditList;
	// OUT：食費
	private List<OFood> oFoodList;
	// OUT：生活用品費
	private List<OLive> oLiveList;
	// OUT：その他費目
	private List<OOther> oOtherList;
	// OUT：娯楽費
	private List<OWaste> oWasteList;
	// OUT：交際費
	private List<OEntertainment> oEntertainmentList;
	// OUT：常駐家庭用品費
	private List<OGoods> oGoodsList;
	// OUT：現金
	private List<Money> moneyList;

	public List<Money> getMoneyList() {
		return moneyList;
	}

	public void setMoneyList(List<Money> moneyList) {
		this.moneyList = moneyList;
	}

	public List<OAccount241> getoAccount241List() {
		return oAccount241List;
	}

	public void setoAccount241List(List<OAccount241> oAccount241List) {
		this.oAccount241List = oAccount241List;
	}

	public List<OAccount461> getoAccount461List() {
		return oAccount461List;
	}

	public void setoAccount461List(List<OAccount461> oAccount461List) {
		this.oAccount461List = oAccount461List;
	}

	public List<OAccountconst> getoAccountConstList() {
		return oAccountConstList;
	}

	public void setoAccountConstList(List<OAccountconst> oAccountConstList) {
		this.oAccountConstList = oAccountConstList;
	}

	public List<OCredit> getoCreditList() {
		return oCreditList;
	}

	public void setoCreditList(List<OCredit> oCreditList) {
		this.oCreditList = oCreditList;
	}

	public List<OFood> getoFoodList() {
		return oFoodList;
	}

	public void setoFoodList(List<OFood> oFoodList) {
		this.oFoodList = oFoodList;
	}

	public List<OLive> getoLiveList() {
		return oLiveList;
	}

	public void setoLiveList(List<OLive> oLiveList) {
		this.oLiveList = oLiveList;
	}

	public List<OWaste> getoWasteList() {
		return oWasteList;
	}

	public void setoWasteList(List<OWaste> oWasteList) {
		this.oWasteList = oWasteList;
	}

	public List<OEntertainment> getoEntertainmentList() {
		return oEntertainmentList;
	}

	public void setoEntertainmentList(List<OEntertainment> oEntertainmentList) {
		this.oEntertainmentList = oEntertainmentList;
	}

	public List<OGoods> getoGoodsList() {
		return oGoodsList;
	}

	public void setoGoodsList(List<OGoods> oGoodsList) {
		this.oGoodsList = oGoodsList;
	}

	public List<OOther> getoOtherList() {
		return oOtherList;
	}

	public void setoOtherList(List<OOther> oOtherList) {
		this.oOtherList = oOtherList;
	}

}