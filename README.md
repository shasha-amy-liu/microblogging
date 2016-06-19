# Microblogging
A microblogging site uses Java, maven, mongodb, spring, jquery and tomcat.

User can post, follow other people and watch their posts.

## Software stack
* Java 8
* Spring 4.2.5
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
1. install without testing
   `mvn clean install -Dmaven.test.skip=true`
2. settings for mysql
   * create database demo 
   * create user 'demo' identified by 'demo'
   * assign all rights to the user 'demo'
     `grant all on demo.* to 'demo'@'%' identified by 'demo';`
     `flush privileges`
   * restart mysql at localhost:3306
3. settings for tomcat
   * start tomcat in 8080
4. TODO: fix maven tomcat plugin

## Spring MVC
Repository, Service and Controller are the special Components:
1. Component – Indicates a auto scan component.
2. Repository – Indicates DAO component in the persistence layer.
3 Service – Indicates a Service component in the business layer.
4. Controller – Indicates a controller component in the presentation layer.

Refer to http://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in for their difference.