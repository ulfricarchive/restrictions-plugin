package com.ulfric.plugin.restrictions.audit;

import java.util.UUID;
import java.util.logging.Logger;

import com.ulfric.commons.bukkit.command.CommandSenderHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.sinkro.Sinks;
import com.ulfric.plugin.restrictions.RestrictedActionService;
import com.ulfric.plugin.restrictions.RestrictedContext;

public class AuditedRestrictedAccessService implements RestrictedActionService {

	@Inject
	private Logger logger;

	@Inject
	private Sinks<Record> records;

	@Override
	public Class<RestrictedActionService> getService() {
		return RestrictedActionService.class;
	}

	@Override
	public void tryRestricted(Runnable runnable, RestrictedContext context) {
		logContext(context);
		recordContextInUserHistory(context);

		runnable.run();
	}

	private void logContext(RestrictedContext context) {
		logger.info("[audit] " + context.getAction() + " by " + context.getSender().getName());
	}

	private void recordContextInUserHistory(RestrictedContext context) {
		Record record = contextToRecord(context);
		if (record != null) {
			records.openSink(record.getUser().toString())
				.add(record);
		}
	}

	private Record contextToRecord(RestrictedContext context) {
		UUID user = CommandSenderHelper.getUniqueId(context.getSender());
		if (user == null) {
			return null;
		}

		Record record = new Record();
		record.setUser(user);
		record.setAction(context.getAction());
		return record;
	}

}
