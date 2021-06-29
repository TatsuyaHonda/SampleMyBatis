package house.keep.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

import house.keep.model.account241.Account241AsMonthModel;
import house.keep.mybatis.HouseKeepModel;
import house.keep.mybatis.MyBatisController;
import house.keep.mybatis.model.housekeeper.OAccount241;
import house.keep.mybatis.model.housekeeper.OOther;

@Path("hello")
@RequestScoped
public class HelloResource {

	@Inject
	private MyBatisController myBatisController;

	@Context
	private HttpServletRequest httpServletRequest;

	/**
	 * リソースメソッド
	 *
	 * @return Viewable
	 */
	@GET
	@Path("/call")
	@Produces(MediaType.TEXT_HTML)
	public Viewable helloCall() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
		dateFormatter.format(new Date(System.currentTimeMillis()));

		List<HouseKeepModel> list = myBatisController.makeHouseKeepModelList();
		httpServletRequest.setAttribute("list", list);

		return new Viewable("/index3.jsp", list);
	}

	/**
	 * リソースメソッド
	 *
	 * @return Viewable
	 */
	@GET
	@Path("/detail")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getDetail() {
		Map<OAccount241, HouseKeepModel> detailMap = myBatisController.getDetailMap(1);
		httpServletRequest.setAttribute("detailMap", detailMap);

		return new Viewable("/WeekDetail.jsp", detailMap);
	}

	/**
	 * リソースメソッド
	 *
	 * @return Viewable
	 */
	@GET
	@Path("/monthly/{name}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getMonthly(@PathParam("name") final String name) {
		int paramInt = (name != null)
				? Integer.parseInt(name)
				: 1;
		Map<Account241AsMonthModel, HouseKeepModel> monthMap = myBatisController.getMonthMap(paramInt);

		Entry<Account241AsMonthModel, HouseKeepModel> entry = monthMap.entrySet().iterator().next();
		Account241AsMonthModel monthModel = entry.getKey();
		List<OOther> otherDetailList = myBatisController.getOtherList(monthModel.getPaymentDate());

		httpServletRequest.setAttribute("monthMap", monthMap);
		httpServletRequest.setAttribute("otherDetailList", otherDetailList);

		return new Viewable("/Monthly.jsp");
	}
}
