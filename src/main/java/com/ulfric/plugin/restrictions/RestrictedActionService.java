package com.ulfric.plugin.restrictions;

import java.util.function.Supplier;

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

	public static <T> T callRestricted(Supplier<T> call, RestrictedContext context) {
		RestrictedActionService service = get();

		if (service == null) {
			return call.get();
		}

		return service.tryRestricted(call, context);
	}

	void tryRestricted(Runnable runnable, RestrictedContext context);

	<T> T tryRestricted(Supplier<T> call, RestrictedContext context);

}
