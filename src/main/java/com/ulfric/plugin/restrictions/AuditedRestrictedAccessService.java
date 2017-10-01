package com.ulfric.plugin.restrictions;

import com.ulfric.dragoon.extension.inject.Inject;

import java.util.logging.Logger;

public class AuditedRestrictedAccessService implements RestrictedActionService {

	@Inject
	private Logger logger;

	@Override
	public Class<RestrictedActionService> getService() {
		return RestrictedActionService.class;
	}

	@Override
	public void doRestricted(Runnable runnable, RestrictedContext context) {
		logger.info("[audit] " + context.getAction() + " by " + context.getSender().getName());
		runnable.run();
	}

}
