
package de.camel_proto.fileproducer.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.camel.PropertyInject;

public class ServerConnectionFactoryProducer implements ConnectionFactoryProducer {
	@PropertyInject("{{jndi.connectionFactory}}")
	private String cfJndi;

	@Named
	@Produces
	@ApplicationScoped
	public ConnectionFactory getConnectionFactory() {
		ConnectionFactory cf = null;

		try {
			cf = (ConnectionFactory) InitialContext.doLookup(cfJndi);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return cf;
	}
}