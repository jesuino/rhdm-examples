# DMN using Red Hat Decision Manager

This is an example of DMN using RHDM local API.

The model was created with [Trisotech](http://www.trisotech.com) and this is what it looks like:

![Salary model](https://github.com/jesuino/rhdm-examples/blob/master/RHDM/dmn-salary-simple/dmn_model.png)


What you will find on this repository is:

* [The Project](./rhdm-dmn-project): A project containing the model above
* [Embedded App](./rhdm-dmn-embedded): A simple embedded example (run the DMN example in your Java application)
* [Remote App](./rhdm-dmn-remote): A simple remote application to run DMN on Decision Server

### Running Locally

To run the embedded example cd into [rhdm-dmn-embedded](./rhdm-dmn-project) and then run:

~~~
mvn clean package exec:java -Dexec.mainClass="org.example.HelloDMNEmbedded"
~~~

You should see the following message in maven output: *Number of vacation is: 24*

### Running on Kie Server

TODO
