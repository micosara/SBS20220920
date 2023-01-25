package com.jsp.context;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

	private static Map<String, Object> container = new HashMap<String, Object>();
	
	private ApplicationContext() {}
	
	public static Map<String, Object> getApplicationContext() {
		return container;
	}
}
