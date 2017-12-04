package com.ulfric.plugin.restrictions.command;

import com.ulfric.plugin.commands.CommandExtension;
import com.ulfric.plugin.restrictions.RestrictedActionService;
import com.ulfric.plugin.restrictions.RestrictedContext;

public interface RestrictionExtension extends CommandExtension {

	default void doRestricted(Runnable runnable, String code) {
		RestrictedContext action = new RestrictedContext();
		action.setSender(sender());
		action.setAction(code);
		RestrictedActionService.doRestricted(runnable, action);
	}

}
