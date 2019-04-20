package com.sample.project.core;

import com.adobe.cq.sightly.WCMUsePojo;

public class Slightly2Java extends WCMUsePojo 
{
	private String var;

	public String getVar() {
		return var;
	}

	
	@Override
	public void activate() throws Exception {
		String value1,value2;
		
		value1=get("value1",String.class);
		value2=get("value2",String.class);
		var=value1+" "+value2;
		
	}
	
}
