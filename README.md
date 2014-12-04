# qMon - [![Build Status](https://travis-ci.org/domi55/qmon.svg?branch=master)](https://travis-ci.org/domi55/qmon)
Manage your [HornetQ](http://hornetq.jboss.org/) enabled Java backend.
qMon is a simple [Spring Boot](http://projects.spring.io/spring-boot/) app to control your HornetQ queues and topics through JMX.

![qMon index view](/static/sc_index.png)
## Installation & Usage
1) Install [node.js](http://nodejs.org/download/), [Maven](http://maven.apache.org/download.cgi) and [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

2) Install [bower](http://bower.io/) and the used bower packages:
```bash
$ npm install -g bower
$ cd <qmon-dir>
$ bower install
```

3) Build and Run
```bash
$ mvn clean package
$ java -Dqmon.config=/absolute/path/to/config.json -Dserver.port=<server-port> -jar target/ch.filecloud.queue-monitor-<version>.jar
```
The application will be available on ```http://localhost:<server-port>```

Make sure your HornetQ enabled server was started with the following properites:

```sh
-Dcom.sun.management.jmxremote.ssl=false              // for now
-Dcom.sun.management.jmxremote.authenticate=false     // for now
-Dcom.sun.management.jmxremote.local.only=false
-Dcom.sun.management.jmxremote.port=<your-jmx-port>
```
## Sample config JSON
```json
{
    "environments":
     [
            {
                "key": "localhost2",
                "hostname":"localhost",
                "jmxPort":"9006",
                "order": 2,
                "label":"local2",
                "stage": "DEV"
            },
            {
                "key": "localhost",
                "hostname":"localhost",
                "jmxPort":"9005",
                "order": 1,
                "label":"local",
                "stage": "DEV"
            }
     ]
}
```
## Artifacts
The latest snapshot build (executable jar) can be found here:
[http://artifacts.filecloud.ch/content/repositories/snapshots/ch/filecloud/ch.filecloud.queue-monitor/](http://artifacts.filecloud.ch/content/repositories/snapshots/ch/filecloud/ch.filecloud.queue-monitor/)



