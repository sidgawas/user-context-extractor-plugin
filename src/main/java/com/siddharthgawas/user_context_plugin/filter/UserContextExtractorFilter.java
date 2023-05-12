package com.siddharthgawas.user_context_plugin.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;
import org.apache.apisix.plugin.runner.PostRequest;
import org.apache.apisix.plugin.runner.PostResponse;
import org.apache.apisix.plugin.runner.filter.PluginFilter;
import org.apache.apisix.plugin.runner.filter.PluginFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * The type User context extractor filter.
 */
@Component
@Slf4j
public class UserContextExtractorFilter implements PluginFilter {

    /**
     * The constant AUTHORIZATION.
     */
    public static final String AUTHORIZATION = "authorization";
    /**
     * The constant BEARER.
     */
    public static final String BEARER = "Bearer";
    /**
     * The constant X_USER_ID.
     */
    public static final String X_USER_ID = "X-User-ID";

    @Override
    public String name() {
        return "UserContextExtractorFilter";
    }

    @Override

    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        try {
            final var authorizationValue = request.getHeader(AUTHORIZATION);
            if (!StringUtils.hasLength(authorizationValue)) {
                response.setBody("JWT Token was not found");
                response.setStatusCode(401);
                return;
            }
            final var token = authorizationValue.substring(BEARER.length()).strip();
            final var decodedToken = JWT.decode(token);
            log.info("User ID: {}", decodedToken.getSubject());
            request.setHeader(X_USER_ID, decodedToken.getSubject());
        } catch (JWTDecodeException exception) {
            log.error("Error occurred while decoding jwt: {}", exception.getMessage(), exception);
            response.setBody("Invalid JWT");
            response.setStatusCode(401);
            return;
        }
        chain.filter(request, response);
    }

    @Override
    public void postFilter(PostRequest request, PostResponse response, PluginFilterChain chain) {
        chain.postFilter(request, response);
    }

    @Override
    public List<String> requiredVars() {
        return PluginFilter.super.requiredVars();
    }

    @Override
    public Boolean requiredBody() {
        return false;
    }

    @Override
    public Boolean requiredRespBody() {
        return false;
    }
}
