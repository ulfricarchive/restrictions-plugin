package com.ulfric.plugin.restrictions;

import com.ulfric.plugin.Plugin;

public class RestrictionsPlugin extends Plugin {

	public RestrictionsPlugin() {
		install(AuditedRestrictedAccessService.class);
	}

}
