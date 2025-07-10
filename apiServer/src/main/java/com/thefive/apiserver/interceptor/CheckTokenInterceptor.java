package com.thefive.apiserver.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

    private static final String JWT_SECRET = "mdis123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行预检请求
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        // 放行静态资源请求
        if (request.getRequestURL().toString().contains("/images") ||
                request.getRequestURL().toString().contains("/static")) {
            return true;
        }

        // 获取Token
        String token = request.getHeader("token");
        if (token == null || token.trim().isEmpty()) {
            throw new Exception("Token为空，请先登录！");
        }

        // 校验Token
        try {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey(JWT_SECRET);
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);

            // 可以在这里获取用户信息并存储到request中
            Claims claims = claimsJws.getBody();
            request.setAttribute("userId", claims.get("userId"));
            request.setAttribute("username", claims.get("username"));
            request.setAttribute("roleId", claims.get("roleId"));

        } catch (Exception e) {
            throw new Exception("Token验证失败，请重新登录！");
        }

        return true;
    }
}