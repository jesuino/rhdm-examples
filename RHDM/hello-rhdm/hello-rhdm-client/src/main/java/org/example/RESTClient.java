package org.example;

import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.KieCommands;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

public class RESTClient {

	/*
	 * The Kie Server (Decision Manager Server) information
	 * */
	private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
	private static final String USER = "kieserver";
	private static final String PASSWORD = "kieserver1!";
	private static final String CONTAINER_NAME = "hello-rhdm";

	private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

	public static void main(String[] args) {
		// Configuration for the Server
		KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
		conf.setMarshallingFormat(FORMAT);
		KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
		// To execute rules we must use the Rules client
		RuleServicesClient rulesClients = kieServicesClient.getServicesClient(RuleServicesClient.class);

		// We create the commands 
		KieCommands kieCommands = KieServices.Factory.get().getCommands();
		BatchExecutionCommand batchCommand = kieCommands.newBatchExecution(Arrays.asList(
			kieCommands.newInsert("John"), 
			kieCommands.newFireAllRules()
		));
		// We now execute the commands, in this case we have no results, the result will be something printed on server console
		
		rulesClients.executeCommandsWithResults(CONTAINER_NAME, batchCommand);
	}

}