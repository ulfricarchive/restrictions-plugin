package com.ulfric.plugin.restrictions;

import com.ulfric.plugin.services.Service;

public interface RestrictedActionService extends Service<RestrictedActionService> {

	public static RestrictedActionService get() {
		return Service.get(RestrictedActionService.class);
	}

	public static void doRestricted(Runnable runnable, RestrictedContext context) {
		RestrictedActionService service = get();

		if (service == null) {
			runnable.run();
			return;
		}

		service.tryRestricted(runnable, context);
	}

	void tryRestricted(Runnable runnable, RestrictedContext context);

}
