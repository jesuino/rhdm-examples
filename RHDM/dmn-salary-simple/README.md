# DMN using Red Hat Decision Manager

This is an example of DMN using RHDM local API.

The model was created with [Trisotech](http://www.trisotech.com) and this is what it looks like:

![Salary model](https://github.com/jesuino/rhdm-examples/blob/master/RHDM/dmn-salary-simple/dmn_model.png)


What you will find on this repository is:

* [The Project](./rhdm-dmn-project): A project containing the model above
* [Embedded App](./rhdm-dmn-embedded): A simple embedded example (run the DMN example in your Java application)
* [Remote App](./rhdm-dmn-remote): A simple remote application to run DMN on Decision Server

### Requirements

The requirements to run this project is:

* Maven 3.2.3+
* Java JDK 8

### Common Step: Install the project

The first step is to install the project in your local maven repository. To do this change directory to [rhdm-dm-project](./rhdm-dmn-project) and run `mvn clean install`. That's all.

### Running Locally

To run the embedded example cd into [rhdm-dmn-embedded](./rhdm-dmn-project) and then run:

~~~
mvn clean package exec:java -Dexec.mainClass="org.example.HelloDMNEmbedded"
~~~

You should see the following message in maven output: *Number of vacation is: 24`

### Running on Kie Server

To run the example on a running kie server you must install the project and create a container for it. To do this first make sure to correctly modify `pom.xml` If the server is not running in localhost or use a different credential then `kieserver:kieserver1!`  and then change directory to [rhdm-dm-project](./rhdm-dmn-project) and run: `clean install kieserver:deploy`. If the container was correctly installed you should something like the following in server logs:
~~~
18:00:26,692 INFO  [org.drools.compiler.kie.builder.impl.KieRepositoryImpl] (default task-26) KieModule was added: ZipKieModule[releaseId=org.example:rhdm-dmn-project:1.0,file=/home/wsiqueir/.m2/repository/org/example/rhdm-dmn-project/1.0/rhdm-dmn-project-1.0.jar]
18:00:26,955 INFO  [org.kie.server.services.impl.KieServerImpl] (default task-26) Container org.example:rhdm-dmn-project:1.0 (for release id org.example:rhdm-dmn-project:1.0) successfully started
~~~

Then simply run the client. Change directory to [rhdm-dmn-remote](./rhdm-dmn-remote) and then run:
~~~
mvn clean package exec:java -Dexec.mainClass="org.example.HelloDMNRemote"
~~~
You should see the following message in maven output: *Number of vacation is: 24

