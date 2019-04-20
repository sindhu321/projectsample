package com.sample.project.core.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.Workspace;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
//import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate=true)
@Service(value=EventListener.class) 
public class SimpleEventListener implements EventListener
{
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Reference
	ResourceResolverFactory factory;
	
	
	@Activate
	public void Activate(final ComponentContext componentContext)
	{
		log.info("entered acrtive ::");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE,"getResolver");
		ResourceResolver resolver;
		try {
			resolver = factory.getServiceResourceResolver(map);
			Session session = resolver.adaptTo(Session.class);
			try {
			Workspace work = session.getWorkspace();
			ObservationManager obs = work.getObservationManager();
			log.info("Entered Session::");
			
			String[] nodeType= {"cq:Page","cq:PageContent"};

				obs.addEventListener(this,
						Event.PROPERTY_ADDED | Event.PROPERTY_CHANGED | Event.NODE_ADDED | Event.NODE_REMOVED, "/content", true, null, nodeType, false);
				log.info("comppleted session");
			} catch (UnsupportedRepositoryOperationException e) {
				// TODO Auto-generated catch block
				log.info("ji");
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				log.info("ji");
				e.printStackTrace();
				
			}
		}
		catch(LoginException e)
		{
			log.info("ji");
			e.printStackTrace();
		}
	}

	@Override
	public void onEvent(EventIterator events) {
		
		log.info("Event TRiggered:");
		while(events.hasNext())
		{
			log.info("etered event");
			try {
				log.info("Evenet at:"+events.nextEvent().getPath());
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
