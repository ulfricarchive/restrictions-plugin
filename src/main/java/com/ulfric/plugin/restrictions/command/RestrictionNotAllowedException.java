package com.ulfric.plugin.restrictions.command;

import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.exception.CommandException;

public class RestrictionNotAllowedException extends CommandException {

	private String restriction;

	public RestrictionNotAllowedException(Context context, String restriction) {
		super(context);

		this.restriction = restriction;
	}

	public String getRestriction() {
		return restriction;
	}

}
