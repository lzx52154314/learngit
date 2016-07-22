package com.fotile.xz.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {
	public RestApplication() {
		// 服务类所在的包路径
		packages("com.fotile.xz.rest.resources");
	}
}
