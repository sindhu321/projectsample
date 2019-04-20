package com.sample.project.core.servlets;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.project.core.services.SampleService;




@SuppressWarnings("serial")
@SlingServlet(
		
		methods="GET",
		paths="/bin/firstServlet"
		)
public class FirstServlet  extends SlingSafeMethodsServlet
{
	@Reference
	private SampleService service;
	
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws javax.servlet.ServletException,java.io.IOException
	{
	     ResourceResolver resolver = req.getResourceResolver() ;
		 Resource resource = resolver.getResource("/content/exampleTemplate/jcr:content");
		 log.info("Resource Path ::"+resource.getPath());
		 service.test();
		 Node node = resource.adaptTo(Node.class);
		 Session session = resolver.adaptTo(Session.class);
		 try {
			log.info("Node ::"+node.getName());
			
//			node.addNode("add Node").setPrimaryType("cq:PageContent");
			node.setProperty("backend", "added");
			session.save();
			log.info("value is ::"+node.getProperty("backend"));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
