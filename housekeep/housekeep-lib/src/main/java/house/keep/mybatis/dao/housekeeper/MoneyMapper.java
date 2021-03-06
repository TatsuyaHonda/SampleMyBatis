package house.keep.mybatis.dao.housekeeper;

import java.util.List;

import house.keep.mybatis.model.housekeeper.Money;

public interface MoneyMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table HOUSEKEEPER.MONEY
	 *
	 * @mbg.generated Sun Jul 07 18:21:16 JST 2019
	 */
	int insert(Money record);

	// 以下、オリジナル実装
	/**
	 * 月ごとレコード取得。
	 *
	 * @return List
	 */
	List<Money> selectAsMonth();
}