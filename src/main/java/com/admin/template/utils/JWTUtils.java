package com.admin.template.utils;

import cn.hutool.core.util.IdUtil;
import com.admin.template.domain.SystemUserDo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @className: JWTUtils
 * @description: JWTUtils工具类
 * @author: YangQian
 * @date: 2023/10/12 10:56
 */
public class JWTUtils {

    //JWT签名密钥
    private static final String secret = "!Q@W3e4r%T^Y";

    //JWT发行者标识
    private static final String issuer = "AdminTemplateWebGaoYu";

    //JWT接收者标识
    private static final String audience = "AdminTemplateJavaYQ";

    /**
     * 获取token
     *
     * @param systemUserDo
     * @return
     */
    public static String generateToken(SystemUserDo systemUserDo) {
        return JWT.create()
                .withJWTId(IdUtil.fastSimpleUUID())
                .withKeyId(systemUserDo.getId().toString())
                .withSubject(systemUserDo.getUsername())
                .withClaim("password", systemUserDo.getPassword())
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withAudience(audience)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10000))
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * token解析
     *
     * @param token
     * @return
     */
    public static DecodedJWT validateToken(String token) {
        return JWT.decode(token);
    }

    /**
     * 获取JWT签名密钥
     *
     * @return
     */
    public static String getSecret() {
        return secret;
    }
}
