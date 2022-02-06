
package testConfig;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

import de.camel_proto.fileproducer.producer.ConnectionFactoryProducer;

@Alternative
public class TestConnectionFactoryProducer implements ConnectionFactoryProducer {
  @Produces
  public ConnectionFactory getConnectionFactory() {
    return new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
  }
}
