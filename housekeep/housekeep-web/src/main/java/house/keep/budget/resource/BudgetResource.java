package house.keep.budget.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

import house.keep.budget.BudgetCalculator;
import house.keep.budget.model.BudgetRowModel;

@Path("budget")
@RequestScoped
public class BudgetResource {

	@Context
	private HttpServletRequest httpServletRequest;

	@Inject
	private BudgetCalculator budgetCalculator;

	/**
	 * リソースメソッド
	 *
	 * @param targetMonthString 対象月（09）
	 * @return Viewable
	 */
	@GET
	@Path("/getBudget")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getBudget(@QueryParam("targetMonth") String targetMonthString) {
		Date targetMonthDate = convertStringToDate(targetMonthString);
		List<BudgetRowModel> budgetRowList = budgetCalculator.calculate(targetMonthDate);
		httpServletRequest.setAttribute("budgetRowList", budgetRowList);
		return new Viewable("/budgetIndex.jsp", budgetRowList);
	}

	/**
	 * String ⇒ Date
	 *
	 * @param targetMonthString
	 * @return
	 */
	private Date convertStringToDate(String targetMonthString) {
		Date targetMonthDate = null;

		if (targetMonthString == null || targetMonthString.isEmpty()) {
			targetMonthDate = new Date(System.currentTimeMillis());
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
			try {
				targetMonthDate = dateFormat.parse(targetMonthString);
			} catch (ParseException e) {
				throw new RuntimeException("QueryParamのパースに失敗しました。（QueryParam:" + targetMonthString + "）", e);
			}
		}
		return targetMonthDate;
	}

}
