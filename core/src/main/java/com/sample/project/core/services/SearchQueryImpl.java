package com.sample.project.core.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.sample.project.core.interfaces.SearchBean;
import com.sample.project.core.interfaces.SearchQuery;

@Component(immediate=true)
@Service(value=SearchQuery.class)

public class SearchQueryImpl implements SearchQuery{

	

	@Reference	
	ResourceResolverFactory factory;
	
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<SearchBean> getQueryResult(String queryTearm) {
		log.info("Query Tearm is"+queryTearm);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE,"getResolver");
		ResourceResolver resolver;
		List<SearchBean> searchlist=new ArrayList<SearchBean>();
		try {
			resolver = factory.getServiceResourceResolver(map);
		
			QueryBuilder builder = resolver.adaptTo(QueryBuilder.class);
			
			Session session = resolver.adaptTo(Session.class);
			
			Map<String,String> pmap=new HashMap();
			
			pmap.put("fulltext",queryTearm);
			pmap.put("path", "/content/exampleTemplate");
			pmap.put("p.limit", "-1");
			Query query = builder.createQuery(PredicateGroup.create(pmap), session);
			SearchResult result = query.getResult();
			Iterator<Node> itrNodes = result.getNodes();
			
			while(itrNodes.hasNext())
			{
				SearchBean searchbean=new SearchBean();
				Node node = itrNodes.next();
				log.info("patrh is:"+node.getPath()); 
				log.info("Nmae is:"+node.getName());
				searchbean.setQueryName(node.getName());
				
				searchbean.setQueryPath(node.getPath());
				
				searchlist.add(searchbean);
			}
		}
		
		catch(Exception e)
		{
			
		}
		return searchlist;
	}

}
