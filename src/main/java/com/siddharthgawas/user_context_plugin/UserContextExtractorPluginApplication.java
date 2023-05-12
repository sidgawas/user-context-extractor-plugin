package com.siddharthgawas.user_context_plugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.siddharthgawas.user_context_plugin","org.apache.apisix.plugin.runner"})
public class UserContextExtractorPluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserContextExtractorPluginApplication.class, args);
	}

}
