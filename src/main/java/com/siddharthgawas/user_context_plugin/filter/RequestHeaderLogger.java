package com.siddharthgawas.user_context_plugin.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;
import org.apache.apisix.plugin.runner.filter.PluginFilter;
import org.apache.apisix.plugin.runner.filter.PluginFilterChain;
import org.springframework.stereotype.Component;

/**
 * The type Request header logger.
 */
@Component
@Slf4j
public class RequestHeaderLogger implements PluginFilter {
    @Override
    public String name() {
        return "RequestHeaderLogger";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        reques.get
        final var headers = request.getHeaders();
        for (final var header: headers.entrySet()) {
            log.info("{} : {}", header.getKey(), header.getValue());
        }
        PluginFilter.super.filter(request, response, chain);
    }
}
