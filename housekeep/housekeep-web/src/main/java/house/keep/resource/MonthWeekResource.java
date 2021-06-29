package house.keep.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

import house.keep.model.MonthWeekModel;
import house.keep.mybatis.MyBatisController;

@Path("hellohello")
@RequestScoped
public class MonthWeekResource {

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
	public Viewable getMonthWeek() {

		MonthWeekModel monthWeekModel = myBatisController.makeMonthWeekModel();
		httpServletRequest.setAttribute("monthWeekModel", monthWeekModel);

		return new Viewable("/index2.jsp", monthWeekModel);
	}
}
