package ua.nure.lisyak.SummaryTask4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * Sets request charset encoding. 
 */
@WebFilter("/*")
public class EncodingFilter implements Filter{
	private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);
	
	private static final String DEFAULT_ENCODING = "UTF-8";
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.trace("Filter initialization starts");
		encoding = fConfig.getInitParameter("encoding");
		if (encoding == null){
			LOGGER.trace("Encoding from Filter Config --> " + encoding);
			encoding = DEFAULT_ENCODING;
		}
		
		LOGGER.trace("Filter initialization finished");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOGGER.debug("Filter starts");
		String requestEncoding = request.getCharacterEncoding();
		
		if (requestEncoding == null) {
			LOGGER.trace("Request encoding = null, set encoding --> " + encoding);
			request.setCharacterEncoding(encoding);
		}
		
		response.setCharacterEncoding(encoding);
		
		LOGGER.debug("Filter finished");		
		chain.doFilter(request, response);
	}

	
	public void destroy() {
		LOGGER.debug("Filter destruction starts");
		// no op
		LOGGER.debug("Filter destruction finished");
	}
}
