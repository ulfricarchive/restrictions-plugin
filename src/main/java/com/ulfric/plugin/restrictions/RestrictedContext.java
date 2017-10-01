package com.ulfric.plugin.restrictions;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.value.Bean;

public class RestrictedContext extends Bean {

	private String action;
	private CommandSender sender;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public CommandSender getSender() {
		return sender;
	}

	public void setSender(CommandSender sender) {
		this.sender = sender;
	}

}
