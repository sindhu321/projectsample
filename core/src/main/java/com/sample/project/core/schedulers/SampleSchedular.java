package com.sample.project.core.schedulers;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.project.core.services.GetProperty;
import com.sample.project.core.services.SampleService;

@Component(immediate=true, description="This component triggers for every minute", metatype=true)
@Service(value=Runnable.class)
@Property(name="schedular.expression",value="10 0 0 ? * * *")

public class SampleSchedular implements Runnable{
	@Reference
//	public SampleService service;
	public GetProperty prop;
	protected final Logger log=LoggerFactory.getLogger(this.getClass());	
	
	@Override
	public void run() {
		log.info("From Scheduler ::");
		log.info("ebnter");
//		service.adProperty("/content/exampleTemplate/jcr:content");
		prop.getProperty();
	}

}
