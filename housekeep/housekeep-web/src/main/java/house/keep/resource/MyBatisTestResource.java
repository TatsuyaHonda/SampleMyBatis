//package house.keep.resource;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//import javax.enterprise.context.RequestScoped;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import house.keep.mybatis.dao.housekeeper.OFoodMapper;
//import house.keep.mybatis.model.housekeeper.OFood;
//
//@Path("mybatis")
//@RequestScoped
//public class MyBatisTestResource {
//
//	//	@Inject
//	//	private OFoodMapper oFoodMapper;
//
//	public static void main(String[] args) {
//		try {
//			String resource = "mybatis-config.xml";
//			InputStream inputStream = Resources.getResourceAsStream(resource);
//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//			SqlSession session = sqlSessionFactory.openSession();
//
//			OFoodMapper oFoodMapper = session.getMapper(OFoodMapper.class);
//
//			List<OFood> oFoodList = oFoodMapper.selectAsMonth();
//
//			StringBuilder sb = new StringBuilder();
//			sb.append("<li>PAYMENT_MONTH, TRUNC_DATE, SUM_O_FOOD</li>");
//			oFoodList.stream()
//					.forEach(ofood -> {
//						sb.append("<li>");
//						sb.append(ofood.getPaymentMonth().toString() + "	" + ofood.getPaymentDate().toString()
//								+ "	" + ofood.getAmount());
//						sb.append("</li>");
//					});
//			System.out.println(sb.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * リソースメソッド
//	 *
//	 * @return
//	 * @throws IOException
//	 */
//	@GET
//	@Path("/call")
//	@Produces(MediaType.TEXT_HTML)
//	public String hello() {
//		StringBuilder sb = new StringBuilder();
//		try {
//			String resource = "mybatis-config.xml";
//			InputStream inputStream = Resources.getResourceAsStream(resource);
//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//			SqlSession session = sqlSessionFactory.openSession();
//
//			OFoodMapper oFoodMapper = session.getMapper(OFoodMapper.class);
//
//			List<OFood> oFoodList = oFoodMapper.selectAsMonth();
//
//			sb.append("<tr><td>PAYMENT_MONTH</td><td>PAYMENT_DATE</td><td>SUM_O_FOOD</td></tr>");
//			oFoodList.stream()
//					.forEach(ofood -> {
//						sb.append("<tr><td>" + ofood.getPaymentMonth().toString() + "</td><td>"
//								+ ofood.getPaymentDate().toString() + "</td><td>" + ofood.getAmount() + "</td></tr>");
//					});
//			System.out.println(sb.toString());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		String html = "<html><body><h2>Hello World!</h2><br/><table border=1>" + sb.toString() + "</table></body></html>";
//		return html;
//	}
//}
