package org.myjfinal.config;

import java.util.ArrayList;
import java.util.List;

import org.myjfinal.plugin.IPlugin;;

final public class Plugins {

	private final List<IPlugin> pluginList = new ArrayList<IPlugin>();
	
	public void add(IPlugin plugin) {
		if (plugin == null) {
			throw new NullPointerException("Can not add a null plugin");
		}
		
		pluginList.add(plugin);
	}
	
	public List<IPlugin> getPluginList() {
		if (pluginList == null) {
			throw new NullPointerException("pluginList can not be null");
		}
		
		return pluginList;
	}
}
