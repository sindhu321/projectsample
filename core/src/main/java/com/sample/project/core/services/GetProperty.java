package com.sample.project.core.services;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service(value=GetProperty.class)
 
public class GetProperty {
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Reference
	ResourceResolverFactory factory;
	
	
	public String getProperty()
	{
		log.info("entered value::");
		String title=null;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, "getResolver");
		
		ResourceResolver resolver;
		try {
			resolver = factory.getServiceResourceResolver(map);
		
		Resource resource = resolver.getResource("/content/exampleTemplate/jcr:content");
		log.info("Resource is::"+resource.getName());
		Node node=resource.adaptTo(Node.class);
		try {
			log.info("Node is::"+node.getName());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			title=node.getProperty("jcr:title").getString();
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		catch (LoginException e) {
			// TODO: handle exception
		}
		
		return title;

	}
	
}
