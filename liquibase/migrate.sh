#!/usr/bin/env bash
java -jar /liquibase-3.4.2-bin/liquibase.jar --driver=com.mysql.jdbc.Driver \
     --classpath=../target/magenta/WEB-INF/lib/mysql-connector-java-6.0.6.jar \
     --changeLogFile=changelog.xml \
     --url="jdbc:mysql://localhost/distance-calculator?autoReconnect=true&useSSL=false" \
     --username=root \
     --password=root \
     migrate
