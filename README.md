# techforum-2016-from-fuse-transform-to-jboss


## install book search fuse on Apache Servicemix

### Download Apache Servicemix
Download the binary from https://servicemix.apache.org/downloads/servicemix-4.5.3.html

### Install Apache Servicemix
Unzip/Untar the archive to a folder of your choice. We will refer to this folder as ${SMX_HOME}.

### Configure Apache Servicemix
copy 'CREATE_BOOK_DETAILS_DATABASE_SCHEMA.sql' to ${SMX_HOME}/etc
copy 'mock-datasource-book-details.xml' to ${SMX_HOME}/etc

add 'org.w3c.dom.xpath, \' to line 295 in ${SMX_HOME}/etc/jre.properties

### Install book-search-fuse
 goto ${SMX_HOME}/bin and execute 'servicemix'
 
In the Servicemix shell, execute the following commands:
 karaf@root> osgi:install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.derby/10.9.1.0_1
 karaf@root> feature:install spring-jdbc
 
 karaf@root> osgi:install -s blueprint:file:etc/mock-datasource-book-details.xml
 karaf@root> osgi:install -s mvn:com.github.muellerc.techforum-2016-from-fuse-transform-to-jboss/book-search-fuse/1.0.0-SNAPSHOT

### Test with curl
getting the wadl document:
 curl -i -X GET -H "Accept: application/xml" http://localhost:8181/book-search-fuse/v1?_wadl

Getting the book details for the book with isbn 3000481109:
 curl -i -X GET -H "Accept: application/json" http://localhost:8181/book-search-fuse/v1/books/3000481109



The JBoss example is tested with:
- Wildfly 10.0.0.Final
- Wildfly 9.0.2.Final

curl -i -X GET -H "Accept: application/json" http://localhost:8080/book-search-jboss/v1/books/3000481109