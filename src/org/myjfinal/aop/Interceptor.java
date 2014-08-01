package org.myjfinal.aop;

import org.myjfinal.core.ActionInvocation;

public interface Interceptor {
	void intercept(ActionInvocation ai);
}
