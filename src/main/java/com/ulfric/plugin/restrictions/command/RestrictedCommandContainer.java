package com.ulfric.plugin.restrictions.command;

import com.ulfric.broken.ErrorHandler;
import com.ulfric.broken.StandardCriteria;
import com.ulfric.dragoon.application.Container;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.extension.postconstruct.PostConstruct;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.exception.CommandChannel;
import com.ulfric.plugin.locale.TellService;

public class RestrictedCommandContainer extends Container {

	@Inject
	@CommandChannel
	private ErrorHandler errorHandler;

	public RestrictedCommandContainer() {
		install(RestrictionStage.class);
	}

	@PostConstruct
	private void setupErrorHandler() {
		errorHandler.withHandler(RestrictionNotAllowedException.class)
			.setCriteria(StandardCriteria.EXACT_TYPE_MATCH)
			.setAction(restriction ->
				TellService.sendMessage(restriction.getContext().getSender(), "command-restricted",
					Details.of("restriction", restriction.getRestriction())))
			.add();
	}

}
