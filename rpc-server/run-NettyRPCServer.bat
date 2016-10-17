cd ..
call mvn install
cd rpc-server
call mvn exec:java -Dexec.mainClass="com.cooper.rpc.NettyRPCServer"
pause