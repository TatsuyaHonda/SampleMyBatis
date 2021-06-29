package house.keep.mybatis.dao.housekeeper;

import java.util.List;

import house.keep.mybatis.model.housekeeper.OAccountconst;

public interface OAccountconstMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HOUSEKEEPER.O_ACCOUNTCONST
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    int insert(OAccountconst record);

	// 以下、オリジナル実装
	/**
	 * 月ごとレコード取得。
	 *
	 * @return List
	 */
	List<OAccountconst> selectAsMonth();
}