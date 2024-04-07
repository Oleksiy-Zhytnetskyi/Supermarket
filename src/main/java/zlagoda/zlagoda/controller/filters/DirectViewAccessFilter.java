package zlagoda.zlagoda.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.locale.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/views/*" })
public class DirectViewAccessFilter implements Filter {

	private final static Logger LOGGER = Logger.getLogger(DirectViewAccessFilter.class);
	private static String UNAUTHORIZED_ACCESS = "Unauthorized access to the resource: ";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		logInfoAboutUnauthorizedAccess(httpRequest.getRequestURI());
		httpResponse.sendRedirect(toHomePageWithErrorMessage(httpRequest.getContextPath()));
	}

	@Override
	public void destroy() {
	}

	private String toHomePageWithErrorMessage(String contextPath) throws UnsupportedEncodingException {
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(Attribute.ERROR, Message.DIRECT_VIEW_ACCESS_ERROR);
		return new StringBuffer(contextPath).append(ServletPath.HOME)
				.append(RedirectionManager.getInstance().generateUrlParams(urlParams)).toString();
	}

	private void logInfoAboutUnauthorizedAccess(String uri) {
		LOGGER.info(UNAUTHORIZED_ACCESS + uri);
	}
}