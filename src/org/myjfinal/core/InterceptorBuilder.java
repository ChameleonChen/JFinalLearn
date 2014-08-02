package org.myjfinal.core;

import java.util.HashMap;
import java.util.Map;

import org.myjfinal.aop.Before;
import org.myjfinal.aop.Interceptor;

public class InterceptorBuilder {
	
	private Map<Class<Interceptor>, Interceptor> interMap = new HashMap<Class<Interceptor>, Interceptor>();

	private Interceptor[] createInterceptors(Before beforeAnnotation) {
		if (beforeAnnotation == null) 
			return null;
		
		Interceptor[] result = null;
		
		@SuppressWarnings("unchecked")
		Class<Interceptor>[] interceptorClasses = (Class<Interceptor>[]) beforeAnnotation.value();
		if (interceptorClasses != null && interceptorClasses.length > 0) {
			result = new Interceptor[interceptorClasses.length];
			
			for (int i=0; i<interceptorClasses.length; i++) {
				result[i] = interMap.get(interceptorClasses[i]);
				if (result[i] != null) {
					continue;
				}
				
				try {
					result[i] = interceptorClasses[i].newInstance();
					interMap.put(interceptorClasses[i], result[i]);
				} catch (Exception e) {
					new RuntimeException();
				}
			}
		}
		
		return result;
	}
}
