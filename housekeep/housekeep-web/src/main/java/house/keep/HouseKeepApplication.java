package house.keep;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

@ApplicationPath("/housekeep")
// jersey-MVC用設定：extends ResourceConfig
public class HouseKeepApplication extends ResourceConfig {

	public HouseKeepApplication() {
		// Jersey MVCの登録、ビューとしてJSPを使う
		register(JspMvcFeature.class);
		// JSPファイルを保存するフォルダを指定する
		property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/views/");
		// com.example以下の全パッケージを登録対象にする
		packages(true, this.getClass().getPackage().getName());
	}

}
