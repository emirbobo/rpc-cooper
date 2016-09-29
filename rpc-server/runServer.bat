call mvn clean dependency:copy-dependencies -DoutputDirectory=lib package
cd target
java -cp rpc-server-1.0-SNAPSHOT.jar com.fwtest.server.Server
pause