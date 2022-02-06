
package de.camel_proto.fileproducer.producer;

import javax.jms.ConnectionFactory;

public interface ConnectionFactoryProducer {
  public ConnectionFactory getConnectionFactory();
}