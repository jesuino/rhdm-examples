package org.example;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNResult;
import org.kie.server.client.DMNServicesClient;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;

public class HelloDMNRemote {

	private static final String MODEL_ID = "Vacation Day Count - Solution 1";
	private static final String NAMESPACE = "http://www.trisotech.com/definitions/_b2ef74f8-3453-44f7-887a-86ea1dd43d80";

	private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
	private static final String USER = "kieserver";
	private static final String PASSWORD = "kieserver1!";
	private static final String CONTAINER = "org.example:rhdm-dmn-project:1.0";

	public static void main(String[] args) {
		// Configuration for the Server
		KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
		KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
		DMNServicesClient dmnClient = kieServicesClient.getServicesClient(DMNServicesClient.class);

		// Load the runtime, the model and create a new context
		DMNContext dmnContext = dmnClient.newContext();

		// Set input values on a context
		dmnContext.set("Years of Service", 20);
		dmnContext.set("Age", 32);

		// Evaluate the context on a model
		DMNResult dmnResult = dmnClient.evaluateAll(CONTAINER, NAMESPACE, MODEL_ID, dmnContext).getResult();
		// get a specific result and display it
		DMNDecisionResult result = dmnResult.getDecisionResultByName("Number of vacation");
		System.out.println("Number of vacation is: " + result.getResult());
	}

}