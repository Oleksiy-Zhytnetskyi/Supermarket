package zlagoda.zlagoda.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebFilter;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.controller.utils.SessionManager;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.locale.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/controller/manager/*", "/controller/cashier/*" })
public class UrlUnauthorizedAccessFilter implements Filter {

	private final static Logger LOGGER = Logger.getLogger(UrlUnauthorizedAccessFilter.class);
	private static final String UNAUTHORIZED_ACCESS = "Unauthorized access to the resource: ";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

		UserEntity user = SessionManager.getInstance().getUserFromSession(httpRequest.getSession());

		if (!isUserRegistered(user) || !isUserAuthorizedForResource(httpRequest.getRequestURI(), user)) {
			logInfoAboutUnauthorizedAccess(httpRequest.getRequestURI());
			HttpWrapper httpWrapper = new HttpWrapper(httpRequest, httpResponse);
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put(Attribute.ERROR, Message.UNAUTHORIZED_ACCESS_ERROR);
			RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.HOME, urlParams);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}

	private boolean isUserRegistered(UserEntity user) {
		return user != null;

	}

	private boolean isUserAuthorizedForResource(String servletPath, UserEntity user) {
		return (isManagerPage(servletPath) && user.getRole().equals(UserRole.MANAGER))
				|| (isWaiterPage(servletPath) && user.getRole().equals(UserRole.CASHIER));
	}

	private boolean isManagerPage(String requestURI) {
		return requestURI.contains(UserRole.MANAGER.name());
	}

	private boolean isWaiterPage(String requestURI) {
		return requestURI.contains(UserRole.CASHIER.name());
	}

	private void logInfoAboutUnauthorizedAccess(String uri) {
		LOGGER.info(UNAUTHORIZED_ACCESS + uri);
	}
}