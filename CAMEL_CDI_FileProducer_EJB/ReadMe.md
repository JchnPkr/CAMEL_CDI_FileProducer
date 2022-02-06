#Camel CDI WAS Beispiel

Einfaches Beispiel, welches Dateien über eine Queue empfängt und in einem Ordner ablegt.

##Konfiguration

Zur Nutzung dieses Beispiels müssen folgende Resourcen konfiguriert sein

###Auf dem Server
- Connection Factory unter JNDI jms/connection/connectionFactory (konfigurierbar in application.properties)
- Queue unter JNDI jms/camel/queue (konfigurierbar in application.properties)
- URL zu dem Pfad der log4j Datei (in den bindings vorbelegt mit url/camel/fileproducer/logger)
- URL zu dem Pfad der properties Datei (in den bindings vorbelegt mit url/camel/fileproducer/properties)

###Auf dem lokalen System
- Pfad unter dem die empfangenen Nachrichten als Datei abgelegt werden (Beispiel ist in den Properties mit logs/incomingData konfiguriert)
