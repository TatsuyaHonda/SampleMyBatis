package house.keep.weekly.singlesheet;

import java.util.Date;
import java.util.List;

import house.keep.weekly.singlesheet.model.SingleSheetModel;

/**
 * 単票マネージャ
 *
 * @author Tatsuya
 *
 */
public interface SingleSheetManager {

	/**
	 * 対象月の単票を取得する。
	 *
	 * @param targetMonth 対象月
	 * @return 単票Model
	 */
	public SingleSheetModel getTargetSingleSheet(Date targetMonth);

	/**
	 * 単票リストを取得する。
	 *
	 * @return 単票Modelリスト
	 */
	public List<SingleSheetModel> getSingleSheetList();



}
