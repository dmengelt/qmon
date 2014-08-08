#### Installation & Usage

```sh
$ npm install -g yo
$ cd <checkout-dir>
$ bower update
```

```sh
$ mvn clean package
$ java -Dqmon.remote.jmx.url=<your-jmx-host>:<your-jmx-port> -Dserver.port=<server-port> -jar target/ch.filecloud.queue-monitor-0.0.1-SNAPSHOT.jar
```
The application will be available on http://localhost:<server-port>

Make sure your HornetQ enabled server was started with the following properites:

```sh
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.local.only=false
-Dcom.sun.management.jmxremote.port=<your-port>
```



