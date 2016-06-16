# Microblogging
A very small microblogging site using maven + mysql + hibernate + spring + spring mvc + jquery.

User can post, follow other people and watch their posts.

## Software stack
* Java 8
* Spring 4.2.5
* Hibernate 4.3
* Maven 3
* Tomcat 8
* MongoDB 3.2

## Development Environment
1. Install java 8, maven 3 and tomcat 8
2. Install mongodb 3.2 in ubuntu 16.04
   become root
   `su -`
   install mongodb binary and create user named mongodb
   `apt-get install mongodb`
   `mkdir -p /data/db/`
   `chown mongodb /data/db`
   start up mongodb
   `mongod`
   start up mongodb as a service
   `servcie mongodb start`
   connect to mongodb in localhost
   `mongo`
   within mongodb shell, show all databases
   `show databases`
3. Install mongodb 3.2 in mac os
   add /opt/local/bin to your path in order to use macports
   `port install mongodb`
   create db folder if not existing
   `sudo mkdir -p /data/db/`
   this command will allow mongod to run as current user
   sudo chown `id -u` /data/db
   mongod
   waiting for connections on port 27017

## Build
1. settings for mysql
   * create database demo 
   * create user 'demo' identified by 'demo'
   * assign all rights to the user 'demo'
     `grant all on demo.* to 'demo'@'%' identified by 'demo';`
     `flush privileges`
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
