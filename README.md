# spring-mvc-jdbc-tutorial

This tutorial code used to explain Spring JDBC concepts:

* add **spring-context** and **spring-jdbc** and **mysql-connector-java** plus **spring-webmvc** and dependencies
* add **javax.servlet.jsp-api**, **javax.servlet-api** and **jstl** dependencies for implementing Java server-side UI.
* add DAO model, repository interface and repo implementations
* add datasource bean definition with spring-resource loading configurations
* add bean for Spring wrappers over Java JDBC drivers same as **JdbcTemplate**, **NamedParameterJdbcTemplate**, **SimpleJdbcInsert**.
* add service controller(biz-layer) for getting UI request from Spring Dispatcher Servlet and wire with DAO layer to process web requests.
* support using GET/POST HTTP requests to access CRUD services.
* using **web.xml** to config Spring web application.
* using tag libs in JSP view pages to iterate/post and formatting Java objects to HTML contents and pass HTML input forms to Java Object.
* using @InitBinder to convert String date input to java.util.Date format.
* add CustomSqlErrorCode translator with Spring **SQLErrorCodeSQLExceptionTranslator** parent class.