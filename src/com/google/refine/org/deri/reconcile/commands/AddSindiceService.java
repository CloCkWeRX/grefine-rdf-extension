package com.google.refine.org.deri.reconcile.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.refine.org.deri.reconcile.model.ReconciliationService;
import com.google.refine.org.deri.reconcile.sindice.SindiceService;
import org.json.JSONException;

import com.google.refine.org.deri.reconcile.GRefineServiceManager;

public class AddSindiceService extends AbstractAddServiceCommand{

	@Override
	protected ReconciliationService getReconciliationService(HttpServletRequest request) throws JSONException, IOException {
		String domain = request.getParameter("domain");
		String name = getName(domain);
		String id = getIdForString(name);
		//make sure that id is unique
		if(GRefineServiceManager.singleton.hasService(id)){
			//id already exist
			throw new RuntimeException("Sindice service for domain '" + domain + "' is already defined!");
		}
		if(domain.trim().isEmpty()){
			throw new RuntimeException("doamin is required");
		}
		ReconciliationService service = new SindiceService(id, name, domain);
		GRefineServiceManager.singleton.addService(service);
		return service;
	}
	
	private String getName(String domain){
		return "Sindice (" + domain + ")";
	}

}
