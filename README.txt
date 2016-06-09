A very small microblogging site using maven + mysql + hibernate + spring +
spring mvc + jquery.
User can post, follow other people and watch their posts.

How to build
1. install java 8, maven 3, mysql 5.5 and tomcat 8
2. settings for mysql
  2.1 create database demo
create database demo
  2.2 create user 'demo' with password 'demo'
  2.3 assign all rights to the user 'demo'
grant all on demo.* to 'demo'@'%' identified by 'demo';
FLUSH PRIVILEGES
 2.4 restart mysql at localhost:3306
3. settings for tomcat
  3.1 start tomcat in 8080
  3.2 change installation address of tomcat folder in pom.xml, e.g.:
    for linux
      <tomcat.path>/home/jhan/java/apache-tomcat-6.0.35</tomcat.path>
    for windows
      <tomcat.path>D:\\java\\tomcat6\\</tomcat.path>
4. run maven build command 
   >mvn install cargo:redeploy
5. open browser and go to http://hostname:8080/blogging/
