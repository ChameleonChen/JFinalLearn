package org.myjfinal.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {

	protected Handler nextHandler;
	//TODO 了解Handler链的机制
	public abstract void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled);
}
