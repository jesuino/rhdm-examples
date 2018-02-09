# Red Hat Decision Manager Hello World example

This is the simples hello world example for Red Hat Decision Manager.

What you will find here is:

* [RHDM Simplest Project](./hello-rhdm-project): The simples project containing a single rule
* [RHDM Clients](./hello-rhdm-client): JMS and REST client for RHDM execution server


### Running

Considering you are running RHDM locally on your machine you can follow the steps below:

* cd into `hello-rhdm-project` and run `mvn clean install kieserver:deploy`
* Build the client by going to `hello-rhdm-client` and run `mvn clean package`, then chose the client you want to run:
  * To run the REST client: `mvn exec:java -Dexec.mainClass="org.example.RESTClient"`
  * To run the JMS client: `mvn exec:java -Dexec.mainClass="org.example.JMSClient"`
* Whatever client you run you you should see the message `Hello John!` in logs.
