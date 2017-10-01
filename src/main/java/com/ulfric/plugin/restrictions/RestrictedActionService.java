package com.ulfric.plugin.restrictions;

import com.ulfric.plugin.services.Service;

public interface RestrictedActionService extends Service<RestrictedActionService> {

	public static RestrictedActionService get() {
		return Service.get(RestrictedActionService.class);
	}

	void doRestricted(Runnable runnable, RestrictedContext context);

}
