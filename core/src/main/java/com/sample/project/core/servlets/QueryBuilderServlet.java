package com.sample.project.core.servlets;


import java.util.Iterator;
import java.util.List;

import javax.xml.ws.RespectBinding;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.project.core.interfaces.SearchBean;
import com.sample.project.core.interfaces.SearchQuery;



@SuppressWarnings("serial")
@SlingServlet(
		methods="GET",
		paths="/bin/search")
public class QueryBuilderServlet extends SlingSafeMethodsServlet
{	
	@Reference
	SearchQuery sq;
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws javax.servlet.ServletException,java.io.IOException
	{
		String queryTearm = req.getParameter("queryTearm");
		log.info("Query Tearm is:"+queryTearm);
		List<SearchBean> finalList = sq.getQueryResult(queryTearm);
		Iterator<SearchBean> itrfinllist = finalList.iterator();
		res.setContentType("text/html");
		while(itrfinllist.hasNext())
		{
			SearchBean bean = itrfinllist.next();
			log.info("name is::"+bean.getQueryName());
			log.info("path is::"+bean.getQueryPath());
			res.getOutputStream().print("path is --- "+bean.getQueryPath()+"<br>Name is ----->"+bean.getQueryPath());
		}
	}
}
