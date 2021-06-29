//package house.keep.resource;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//@Path("hello")
//public class HelloResourceOld {
//
//	/** Oracle 接続ユーザ */
//	private static final String ORACLE_USER = "housekeeper";
//	/** Oracle 接続パスワード */
//	private static final String ORACLE_PASSWORD = "oracle";
//	/** Oracle 接続サーバ */
//	private static final String ORACLE_HOST = "153.126.202.190";
//	/** Oracle SID */
//	private static final String ORACLE_SID = "XE";
//
//	/**
//	 * リソースメソッド
//	 *
//	 * @return
//	 */
//	@GET
//	@Path("/call")
//	@Produces(MediaType.TEXT_HTML)
//	public String hello() {
//
//		StringBuilder sb = this.connectDatabase();
//		String html = "<html><body><h2>Hello World!</h2><ul>" + sb.toString() + "</ul></body></html>";
//		System.out.println(html);
//		return html;
//	}
//
//	/**
//	 * Oracle に接続する。
//	 */
//	private StringBuilder connectDatabase() {
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		StringBuilder sb = new StringBuilder();
//
//		try {
//			// JDBCドライバクラスのロード
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			// Connectionの作成
//			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + ORACLE_HOST + ":1521:" + ORACLE_SID,
//					ORACLE_USER,
//					ORACLE_PASSWORD);
//
//			// Statementの作成
//			statement = connection.createStatement();
//
//			// Resultsetの作成
//			StringBuilder sbSql = new StringBuilder();
//			sbSql.append("SELECT");
//			sbSql.append("        a.TRUNC_DATE AS TRUNC_DATE");
//			sbSql.append("        ,SUM(a.AMOUNT) AS SUM_O_FOOD");
//			sbSql.append("    FROM");
//			sbSql.append("        (");
//			sbSql.append("            SELECT");
//			sbSql.append("                    TRUNC(x.PAYMENT_DATE, 'MONTH') AS TRUNC_DATE");
//			sbSql.append("                    ,x.AMOUNT");
//			sbSql.append("                    ,x.PAYMENT_DATE");
//			sbSql.append("                FROM");
//			sbSql.append("                    o_FOOD x");
//			sbSql.append("        ) a");
//			sbSql.append("    GROUP BY");
//			sbSql.append("        a.TRUNC_DATE");
//			sbSql.append("    ORDER BY");
//			sbSql.append("        a.TRUNC_DATE DESC");
//			System.out.println(sbSql.toString());
//			resultSet = statement.executeQuery(
//					sbSql.toString());
//
//			// 取得したデータを出力する
//			sb.append("<li>TRUNC_DATE, SUM_O_FOOD</li>");
//			//			sb.append(System.getProperty("line.separator"));
//			while (resultSet.next()) {
//				sb.append("<li>");
//				sb.append(resultSet.getString("TRUNC_DATE") + ", " + resultSet.getString("SUM_O_FOOD"));
//				sb.append("</li>");
//				//				sb.append(System.getProperty("line.separator"));
//			}
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			//						throw e;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			//						throw e;
//		} catch (Throwable e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				/* クローズ処理 */
//				if (resultSet != null) {
//					resultSet.close();
//					resultSet = null;
//				}
//
//				if (statement != null) {
//					statement.close();
//					statement = null;
//				}
//
//				if (connection != null) {
//					connection.close();
//					connection = null;
//				}
//			} catch (Throwable e) {
//				e.printStackTrace();
//				// nop
//			}
//		}
//		return sb;
//	}
//}
