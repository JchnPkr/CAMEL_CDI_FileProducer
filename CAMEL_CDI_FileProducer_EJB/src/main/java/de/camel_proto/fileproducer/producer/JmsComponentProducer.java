
package de.camel_proto.fileproducer.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.apache.camel.Component;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.jms.support.destination.JndiDestinationResolver;

public class JmsComponentProducer {
  @Produces
  @ApplicationScoped
  @Named("jms")
  Component getJmsComponent(ConnectionFactory connectionFactory) {
    JmsComponent jms = JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
    jms.setDestinationResolver(new JndiDestinationResolver());

    return jms;
  }
}
