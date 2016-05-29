# techforum-2016-from-fuse-transform-to-jboss


## install book search fuse on Apache Servicemix

### Download Apache Servicemix
Download the binary from https://servicemix.apache.org/downloads/servicemix-5.0.6.html

### Install Apache Servicemix
Unzip/Untar the archive to a folder of your choice. We will refer to this folder as ${SMX_HOME}.

### Configure Apache Servicemix
copy 'activemq-connection-factory.xml' to ${SMX_HOME}/etc
copy 'bookSearchFuse.cfg' to ${SMX_HOME}/etc
copy 'CREATE_BOOK_DETAILS_DATABASE_SCHEMA.sql' to ${SMX_HOME}/etc
copy 'book-details-datasource.xml' to ${SMX_HOME}/etc
copy 'org.apache.cxf.osgi.cfg' to ${SMX_HOME}/etc

### Install book-search-fuse
 Make sure you are running Java 7!
 Goto ${SMX_HOME}/bin and execute 'servicemix'
 
In the Servicemix shell, execute the following commands:
 karaf@root> features:addurl mvn:com.github.muellerc.techforum-2016-from-fuse-transform-to-jboss.book-search-fuse/build/1.0.0-SNAPSHOT/xml/features
 karaf@root> features:install -v book-search-fuse-all
 
### Test with curl
getting the wadl document:
 curl -i -X GET -H "Accept: application/xml" http://localhost:8181/book-search-fuse/v1?_wadl

Getting the book details for the book with isbn 3000481109:
 curl -i -X GET -H "Accept: application/json" http://localhost:8181/book-search-fuse/v1/books/3000481109



## install book search jboss on JBoss
Download the binary from https://www.jboss.org/download-manager/file/jboss-eap-7.0.0-installer.jar
Execute the installer and choose the defaults.

### Configure JBoss EAP 7.0.0
 add the following line into ${JBOSS_HOME}/standalone/configuration/standalone-full.xml
 <jms-queue name="IMAGE" entries="java:/jms/queue/IMAGE"/>

### Install book-search-jboss
 Make sure you are running Java 8!
 Goto ${JBOSS_HOME}/bin and execute 'standalone.sh -c standalone-full.xml' (Linux) or 'standalone.bat -c standalone-full.xml' (Windows)
 


curl -i -X GET -H "Accept: application/json" http://localhost:8080/book-search-jboss/v1/books/3000481109