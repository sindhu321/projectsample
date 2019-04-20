package com.sample.project.core.services;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.project.core.interfaces.AddPropertyService;

@Component(immediate=true)
@Service(value=SampleService.class)

public class SampleService implements AddPropertyService
{
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	public void test() {}
	@Reference	
	ResourceResolverFactory factory;
	
	
	public void adProperty(String path) 
	{
//		factory.getAdministrativeResourceResolver(null);
	
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE,"getResolver");
		ResourceResolver resolver;
		try {
			resolver = factory.getServiceResourceResolver(map);
		
		Resource resource = resolver.getResource(path);
		
		log.info("Resource is::"+resource.getName().toString());
		Node node=resource.adaptTo(Node.class);
		log.info("Node is::"+node.getName());
		node.setProperty("service", "value");
		resolver.adaptTo(Session.class).save();
			
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
 