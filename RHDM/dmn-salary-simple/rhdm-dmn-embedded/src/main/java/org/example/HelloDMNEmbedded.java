package org.example;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;

public class HelloDMNEmbedded {

	private static final String MODEL_ID = "Vacation Day Count - Solution 1";
	private static final String NAMESPACE = "http://www.trisotech.com/definitions/_b2ef74f8-3453-44f7-887a-86ea1dd43d80";

	public static void main(String[] args) {
		// KIE API 
		ReleaseId releaseId = new ReleaseIdImpl("org.example", "rhdm-dmn-project", "1.0");
		KieContainer kc = KieServices.Factory.get().newKieContainer(releaseId);
		KieBase kb = kc.getKieBase();
		KieSession ks = kb.newKieSession();
		
		// Load the runtime, the model and create a new context
		DMNRuntime dmnRuntime = ks.getKieRuntime(DMNRuntime.class);
		DMNModel dmnModel = dmnRuntime.getModel(NAMESPACE, MODEL_ID);
		DMNContext dmnContext = dmnRuntime.newContext();
		
		// Set input values on a context
		dmnContext.set("Years of Service", 20);
		dmnContext.set("Age", 32);
		
		// Evaluate the context on a model
		DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);
		
		// get a specific result and display it
		DMNDecisionResult result = dmnResult.getDecisionResultByName("Number of vacation");
		System.out.println("Number of vacation is: " + result.getResult());
	}

}