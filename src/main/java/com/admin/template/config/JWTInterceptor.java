package com.admin.template.config;

import com.admin.template.utils.JWTUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * JWTInterceptor是一个拦截器，用于验证请求头中的JWT令牌是否有效。
 * 当有请求进入时，该拦截器会首先从请求头中获取令牌，并尝试验证其有效性。
 * 如果令牌验证成功，则放行请求；否则，拦截请求并返回相应的错误信息。
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        // 排除Swagger地址
        if (requestURI.startsWith("/doc.html") || requestURI.startsWith("/swagger")
                || requestURI.startsWith("/v2") || requestURI.startsWith("/webjars")) {
            return true;
        }

        // 创建一个Map对象，用于存储响应信息
        Map<String, Object> map = new HashMap<>();
        // 从请求头中获取令牌
        String token = request.getHeader("Authorization");
        try {
            DecodedJWT decode = JWT.decode(token);
            // 验证令牌的有效性
            JWT.require(Algorithm.HMAC256(JWTUtils.getSecret())).build().verify(token);
            // 放行请求
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效签名!");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期，请重新登陆!");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "token算法不一致!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效!");
        }
        // 设置状态为false
        map.put("status", false);
        // 将Map转化为JSON字符串（使用Jackson库）
        String json = new ObjectMapper().writeValueAsString(map);
        // 设置响应的Content-Type
        response.setContentType("application/json;charset=UTF-8");
        // 将JSON字符串写入响应中
        response.getWriter().println(json);
        // 不放行请求
        return false;
    }
}

