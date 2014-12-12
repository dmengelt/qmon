package ch.filecloud.queuemonitor.web.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by domi on 8/6/14.
 */
@Component
public class CORSFilter implements Filter{

    private static Log LOGGER = LogFactory.getLog(CORSFilter.class);

    private static final String QMON_ALLOW_CROSS_ORIGIN = "qmon.allowCrossOrigin";

    @Value("#{ systemProperties['" + QMON_ALLOW_CROSS_ORIGIN + "']?:'false'}")
    private boolean allowCrossOrigin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(allowCrossOrigin) {
            LOGGER.warn("Setting 'Access-Control-Allow-Origin' => '*");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(allowCrossOrigin) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}
