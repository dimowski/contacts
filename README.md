# Contacts
:busts_in_silhouette: Simple web application to organize contacts (using Java Servlets and JSP technologies).

# Installation
All you need to do is follow the next steps:
  1. You need to take the compiled code and package it with command `mvn package`.
  2. Create database schema using `01-create-user-and-schema.sql` script.
  3. Deploy the `WAR` to Apache Tomcat web server.
  4. (optional) You can also add some data to database using `02-data.sql` script.

# Application settings

### Storing files
You can specify the path for storing files in `\src\main\resources\contactapp.properties`. By default it is:

```properties
users.photo = D:/uploadedFiles/users_photo
users.attachments = D:/uploadedFiles/attachments
```

### Email settings
Email setting is specified in `\src\main\resources\mailServer.properties`. By default it is:

```properties
mail.send.from = kach.itechart@gmail.com
mail.send.password = itechart
mail.admin.address = kach.itechart@gmail.com
mail.smtp.host = smtp.gmail.com

mail.smtp.port = 587
mail.smtp.auth = true
mail.smtp.starttls.enable = true
```

### Logging configiguration
Logging configuration is specified in file `\src\main\resources\log4j2.xml`. By default it writes to the File named
`dk-contact-logs/contactapp.log`, which will be located in root folder of Apache Tomcat web server.

```xml
<Configuration status="warn" name="ContactApp" packages="">
    <Appenders>
        <File name="FileAppender" fileName="dk-contact-logs/contactapp.log" append="false">
            <PatternLayout>
                <Pattern>%d [%p] %c{1}.%M %m%n</Pattern>
            </PatternLayout>
        </File>
    </Appenders>
    <Loggers>
        <Root level="debug">
            <AppenderRef ref="FileAppender"/>
        </Root>
    </Loggers>
</Configuration>
```