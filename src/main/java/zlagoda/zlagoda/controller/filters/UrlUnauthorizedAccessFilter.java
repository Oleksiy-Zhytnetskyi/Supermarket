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
import java.util.*;

@WebFilter(urlPatterns = { "/controller/*" } )
public class UrlUnauthorizedAccessFilter implements Filter {

	private static final String ALLOWED_PATH = "/controller/login";

	private static final ArrayList<String> MANAGER_BANNED_PATHS = new ArrayList<>(Arrays.asList(
			"/createReceipt",
			"/createSale"
	));

	private static final ArrayList<String> CASHIER_BANNED_PATHS = new ArrayList<>(Arrays.asList(
			"/allUsers",
			"/createUser",
			"/createProduct",
			"/createStoreProduct",
			"/createCategory",
			"/createCustomerCard",
			"/updateUser",
			"/updateProduct",
			"/updateStoreProduct",
			"/updateCategory",
			"/updateReceipt",
			"/updateSale",
			"/delete",
			"/statistic"
	));

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

		String path = ((HttpServletRequest) servletRequest).getRequestURI();

		if (!path.contains(ALLOWED_PATH) && (!isUserRegistered(user) || !isUserAuthorizedForResource(httpRequest.getRequestURI(), user))) {
			logInfoAboutUnauthorizedAccess(httpRequest.getRequestURI());
			HttpWrapper httpWrapper = new HttpWrapper(httpRequest, httpResponse);
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put(Attribute.ERROR, Message.UNAUTHORIZED_ACCESS_ERROR);
			RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.BASE_PAGE, urlParams);
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
		return (isManagerAllowedPage(servletPath) && user.getRole().equals(UserRole.MANAGER))
				|| (isCashierAllowedPage(servletPath) && user.getRole().equals(UserRole.CASHIER));
	}

	private boolean isCashierAllowedPage(String servletPath) {
		for (final String path : CASHIER_BANNED_PATHS) {
			if (servletPath.contains(path)) {
				return false;
			}
		}
		return true;
	}

	private boolean isManagerAllowedPage(String servletPath) {
		for (final String path : MANAGER_BANNED_PATHS) {
			if (servletPath.contains(path)) {
				return false;
			}
		}
		return true;
	}

	private void logInfoAboutUnauthorizedAccess(String uri) {
		LOGGER.info(UNAUTHORIZED_ACCESS + uri);
	}
}