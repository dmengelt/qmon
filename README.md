# qMon - [![Build Status](https://travis-ci.org/domi55/qmon.svg?branch=master)](https://travis-ci.org/domi55/qmon)
Manage your [HornetQ](http://hornetq.jboss.org/) enabled Java backend.
qMon is a simple [Spring Boot](http://projects.spring.io/spring-boot/) app to control your HornetQ queues and topics through JMX.
## Installation & Usage
1) Install [node.js](http://nodejs.org/download/), [Maven](http://maven.apache.org/download.cgi) and [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

2) Install [bower](http://bower.io/) and the used bower packages:
```sh
$ npm install -g bower
$ cd <qmon-dir>
$ bower install
```

3) Build and Run
```sh
$ mvn clean package
$ java -Dqmon.remote.jmx.url=<your-jmx-host>:<your-jmx-port> -Dserver.port=<server-port> -jar target/ch.filecloud.queue-monitor-0.0.1-SNAPSHOT.jar
```
The application will be available on http://localhost:\<server-port\>

Make sure your HornetQ enabled server was started with the following properites:

```sh
-Dcom.sun.management.jmxremote.ssl=false              // for now
-Dcom.sun.management.jmxremote.authenticate=false     // for now
-Dcom.sun.management.jmxremote.local.only=false
-Dcom.sun.management.jmxremote.port=<your-jmx-port>
```



