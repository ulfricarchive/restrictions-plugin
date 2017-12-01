package com.ulfric.plugin.restrictions.audit;

import java.util.UUID;

import com.ulfric.commons.value.Bean;

public class Record extends Bean {

	private String action;
	private UUID user;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public UUID getUser() {
		return user;
	}

	public void setUser(UUID user) {
		this.user = user;
	}

}
