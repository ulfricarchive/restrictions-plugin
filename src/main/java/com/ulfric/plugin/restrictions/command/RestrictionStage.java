package com.ulfric.plugin.restrictions.command;

import org.bukkit.event.EventHandler;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.CommandPreRunEvent;
import com.ulfric.plugin.commands.stage.Stage;
import com.ulfric.plugin.restrictions.RestrictedActionService;
import com.ulfric.plugin.restrictions.RestrictedContext;

public class RestrictionStage extends Stage<String> {

	@Inject
	private RestrictedActionService service;

	@EventHandler
	public void on(CommandPreRunEvent event) {
		String restriction = get(event.getCommandType());

		if (restriction != null) {
			RestrictedContext context = new RestrictedContext();
			context.setAction(restriction);
			context.setSender(event.getContext().getSender());

			if (!service.takeRestriction(context)) {
				event.cancel(new RestrictionNotAllowedException(event.getContext(), restriction));
			}
		}
	}

	@Override
	protected String compute(Class<? extends Command> command) {
		Restricted restricted = Stereotypes.getFirst(command, Restricted.class);

		if (restricted == null) {
			return null;
		}

		return restricted.value().isEmpty() ? null : restricted.value();
	}

}
