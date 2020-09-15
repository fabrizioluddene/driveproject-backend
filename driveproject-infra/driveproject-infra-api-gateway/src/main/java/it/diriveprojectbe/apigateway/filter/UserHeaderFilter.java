package it.diriveprojectbe.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import it.diriveprojectbe.apigateway.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

public class UserHeaderFilter extends ZuulFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest =  context.getRequest();
        context.addZuulRequestHeader("x-auth-user", jwtTokenProvider.getUsername(httpServletRequest.getHeader(AUTHORIZATION).substring(7)) );
        return null;
    }
}
