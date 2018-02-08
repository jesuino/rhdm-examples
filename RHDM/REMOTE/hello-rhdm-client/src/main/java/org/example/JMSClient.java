package org.example;

import java.util.Arrays;

import javax.naming.InitialContext;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.KieCommands;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

public class JMSClient {

	/*
	 * The Kie Server (Decision Manager Server) information
	 * */
	private static final String USER = "kieserver";
	private static final String PASSWORD = "kieserver1!";
	private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
	private static final String CONTAINER_NAME = "hello-rhdm";

	public static void main(String[] args) throws Exception{
		KieServicesClient kieServicesClient = buildClient();
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

	private static KieServicesClient buildClient() throws Exception {
		java.util.Properties env = new java.util.Properties();
		env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
		                "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(javax.naming.Context.PROVIDER_URL, PROVIDER_URL);
		env.put(javax.naming.Context.SECURITY_PRINCIPAL, USER);
		env.put(javax.naming.Context.SECURITY_CREDENTIALS, PASSWORD);
		InitialContext context  = new InitialContext(env);
		return KieServicesFactory.newKieServicesJMSClient(context, USER, PASSWORD);
	}

}