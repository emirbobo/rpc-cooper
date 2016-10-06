call mvn clean package
cd target
java -cp rpc-server-1.0-SNAPSHOT.jar com.cooper.rpc.NettyRPCServer
pause