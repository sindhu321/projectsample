package com.sample.project.core.servlets;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.project.core.services.SampleService;

@SlingServlet(
		
		methods="GET",
		paths="/bin/addProperty"
		)

public class AddProperty extends SlingSafeMethodsServlet
{
	@Reference
	private SampleService service;
	
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws javax.servlet.ServletException,java.io.IOException
	{
		log.info("entered into servlet:");
		service.adProperty("/content/exampleTemplate/jcr:content");
	}
}
