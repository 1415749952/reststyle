title reststyle
set JAVA_HOME=C:\Program Files\Java\jdk-11.0.11

java -Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Dfile.encoding=UTF-8 -jar ../reststyle-web-1.0.0.jar --console.encode=UTF-8
pause