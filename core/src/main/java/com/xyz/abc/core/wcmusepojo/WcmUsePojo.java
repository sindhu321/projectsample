package com.xyz.abc.core.wcmusepojo;

import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

public class WcmUsePojo extends WCMUse
{
	protected final org.slf4j.Logger log=LoggerFactory.getLogger(this.getClass());
			
	private String javaDesc;
	private String javaimg;
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		javaDesc = getProperties().get("/desc", "null");
		javaimg=getProperties().get("./img", "null");
		
		log.info("current componrnt::"+getComponent().getName());
		log.info("value of jaav imagre is::"+javaimg+" and java desc"+javaDesc);;
	}
	public String getJavaDesc() {
		return javaDesc;
	}
	public void setJavaDesc(String javaDesc) {
		this.javaDesc = javaDesc;
	}
	public String getJavaimg() {
		return javaimg;
	}
	public void setJavaimg(String javaimg) {
		this.javaimg = javaimg;
	}

}
