package com.sample.project.core.services;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.sample.project.core.interfaces.SearchQuery;
import com.sample.project.core.servlets.AddProperty;

@Component
@Service
public class AddPropertyWorkFlow implements WorkflowProcess{

	@Reference
SampleService ss;	
	
	@Override
	public void execute(WorkItem witem, WorkflowSession wsession, MetaDataMap map) throws WorkflowException {
		// TODO Auto-generated method stub
		WorkflowData wData = witem.getWorkflowData();
		String path = wData.getPayload().toString()+"/jcr:content";
		ss.adProperty(path);
	}

}
