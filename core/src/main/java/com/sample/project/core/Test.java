package com.sample.project.core;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.cq.sightly.WCMUsePojo;

public class Test extends WCMUsePojo {
	
	private String dialogv;
	private String compo;
	public String getDialogv() {
		return dialogv;
	}
	public void setDialogv(String dialogv) {
		this.dialogv = dialogv;
	}
	public String getCompo() {
		return compo;
	}
	public void setCompo(String compo) {
		this.compo = compo;
	}
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		
		dialogv=getProperties().get("text","null");
		compo=getComponent().getName();
		
		
		
	}

}
