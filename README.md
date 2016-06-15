# Microblogging
A very small microblogging site using maven + mysql + hibernate + spring + spring mvc + jquery.

User can post, follow other people and watch their posts.

## Software stack
* Java 8
* Spring 4.2
* Hibernate 4
* Maven 3
* MySQL 5.5
* Tomcat 8
* MongoDB 3.2

## Initialization
1. install java 8, maven 3, mysql 5.5 and tomcat 8

## Build
1. settings for mysql
   * create database demo 
   * create user 'demo' with password 'demo'
   * assign all rights to the user 'demo'
     `grant all on demo.* to 'demo'@'%' identified by 'demo';`
     `FLUSH PRIVILEGES`
   * restart mysql at localhost:3306
2. settings for tomcat
   * start tomcat in 8080
   * change installation address of tomcat folder in pom.xml, e.g.:
     for linux `<tomcat.path>/home/doudou/java/apache-tomcat-6.0.35</tomcat.path>`
     for windows `<tomcat.path>D:\\java\\tomcat6\\</tomcat.path>`
3. run maven build command 
   build without running test cases `mvn -Dmaven.test.skip=true install`
   `mvn install cargo:redeploy`
4. open browser and go to http://hostname:8080/blogging/