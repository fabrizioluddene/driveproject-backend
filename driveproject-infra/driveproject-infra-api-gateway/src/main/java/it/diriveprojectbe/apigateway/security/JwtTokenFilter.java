package it.diriveprojectbe.apigateway.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
import it.diriveprojectbe.project.api.excpetion.NoUserFoundException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        JSONObject jsonObjectResponse = new JSONObject();
        String messageError = null;
        String codeError = null;
        boolean jwtTokenHasError = false;

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null) {
            try {
                jwtTokenProvider.validateToken(token);
            } catch (IllegalArgumentException e) {
                messageError = ApplicationCodeEnum.JWTISNOTVALID.getMessage();
                codeError = ApplicationCodeEnum.JWTISNOTVALID.getCode();
                jwtTokenHasError =true;
            } catch (ExpiredJwtException e) {
                messageError = ApplicationCodeEnum.JWTISEXPIRED.getMessage();
                codeError = ApplicationCodeEnum.JWTISEXPIRED.getCode();
                jwtTokenHasError =true;
            } catch (JwtException e) {
                messageError = ApplicationCodeEnum.JWTISNOTVALID.getMessage();
                codeError = ApplicationCodeEnum.JWTISNOTVALID.getCode();
                jwtTokenHasError =true;
            } catch (NoUserFoundException e) {
                messageError = ApplicationCodeEnum.NOTFOUND.getMessage();
                codeError = ApplicationCodeEnum.NOTFOUND.getCode();
                jwtTokenHasError =true;
            }catch (JWTAuthenticationException e){
                messageError = ApplicationCodeEnum.USERNOTFOUND.getMessage();
                codeError = ApplicationCodeEnum.USERNOTFOUND.getCode();
                jwtTokenHasError =true;
            }

        } else {

            messageError = ApplicationCodeEnum.JWTNOTPRESENTHEADER.getMessage();
            codeError = ApplicationCodeEnum.JWTNOTPRESENTHEADER.getCode();
            jwtTokenHasError =true;
        }
        if (jwtTokenHasError) {
            try {
                response.getWriter().write(new JSONObject()
                        .put("code", codeError)
                        .put("message", messageError)
                        .toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Authentication auth = jwtTokenProvider.getAuthentication(token, request);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        filterChain.doFilter(req, res);

    }
}