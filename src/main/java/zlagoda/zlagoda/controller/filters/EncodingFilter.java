package zlagoda.zlagoda.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebFilter(urlPatterns = { "/*" }, initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Parameter") })
public class EncodingFilter implements Filter {

	private static String ENCODING = "encoding";
	private String code;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter(ENCODING);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String codeRequest = request.getCharacterEncoding();

		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		code = null;
	}
}