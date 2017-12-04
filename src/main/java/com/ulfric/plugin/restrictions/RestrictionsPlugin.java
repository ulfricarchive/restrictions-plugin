package com.ulfric.plugin.restrictions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.restrictions.audit.AuditedRestrictedAccessService;
import com.ulfric.plugin.restrictions.command.RestrictedCommandContainer;

public class RestrictionsPlugin extends Plugin {

	public RestrictionsPlugin() {
		install(AuditedRestrictedAccessService.class);
		install(RestrictedCommandContainer.class);
	}

}
