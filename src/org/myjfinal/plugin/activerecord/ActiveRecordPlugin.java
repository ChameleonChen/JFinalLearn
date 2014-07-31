package org.myjfinal.plugin.activerecord;

import org.myjfinal.plugin.IPlugin;

public class ActiveRecordPlugin implements IPlugin {

	private Boolean devMode = null;
	
	public ActiveRecordPlugin setDevMode(boolean devMode) {
		this.devMode = devMode;
		return this;
	}
	
	public Boolean getDevMode() {
		return devMode;
	}
	
	public boolean start() {
		return false;
	}

	public boolean stop() {
		return false;
	}

}
