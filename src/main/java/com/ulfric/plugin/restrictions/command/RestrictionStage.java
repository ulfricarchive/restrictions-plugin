package com.ulfric.plugin.restrictions.command;

import org.bukkit.event.EventHandler;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.commands.CommandPreRunEvent;
import com.ulfric.plugin.commands.Stage;
import com.ulfric.plugin.restrictions.RestrictedActionService;
import com.ulfric.plugin.restrictions.RestrictedContext;

public class RestrictionStage extends Stage<String> {

	@Inject
	private RestrictedActionService service;

	@EventHandler
	public void on(CommandPreRunEvent event) {
		String restriction = commandToContext.computeIfAbsent(event.getContext().getCommandType(), command -> {
			Restricted restricted = Stereotypes.getFirst(command, Restricted.class);

			if (restricted == null) {
				return null;
			}

			return restricted.value().isEmpty() ? null : restricted.value();
		});

		if (restriction != null) {
			RestrictedContext context = new RestrictedContext();
			context.setAction(restriction);
			context.setSender(event.getContext().getSender());

			if (!service.takeRestriction(context)) {
				event.cancel(new RestrictionNotAllowedException(event.getContext(), restriction));
			}
		}
	}

}
