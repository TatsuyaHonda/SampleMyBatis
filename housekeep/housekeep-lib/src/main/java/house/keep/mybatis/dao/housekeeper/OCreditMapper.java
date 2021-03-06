package house.keep.mybatis.dao.housekeeper;

import java.util.List;

import house.keep.mybatis.model.housekeeper.OCredit;

/**
 * クレジットカード。
 */
public interface OCreditMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HOUSEKEEPER.O_CREDIT
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    int insert(OCredit record);

	// 以下、オリジナル実装
	/**
	 * 月ごとレコード取得。
	 *
	 * @return List
	 */
	List<OCredit> selectAsMonth();
}