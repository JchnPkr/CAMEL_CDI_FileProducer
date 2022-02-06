
package de.camel_proto.fileproducer.service;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import de.camel_proto.fileproducer.route.DoNotDiscover;

/**
 * StartUp-Bean, der die Ressourcen der Anwendung initialisiert.
 */
@Singleton
@Startup
@LocalBean
public class StartUpService {
	@Resource(name = "url/camel/loggerRef", type = URL.class)
	URL loggingUrl;

	@Resource(name = "url/camel/propertiesRef", type = URL.class)
	URL propertiesUrl;

	@Inject
	Logger logger;

	@Inject
	@DoNotDiscover
	Instance<RouteBuilder> routes;

	@Inject
	CamelContext camelContext;

	/**
	 * PostConstruct-Methode des Services, um Anwendungsstatus zu setzen und die
	 * Anwendung zu initialisieren.
	 */
	@PostConstruct
	void postConstruct() {
		configLogger();
		configCamelContext();
	}

	/**
	 * Initialisiert den Logger.
	 *
	 */
	private void configLogger() {
		this.logger.traceEntry();
		try {
			logger.info("configuring logger from file: " + loggingUrl.getPath());

			LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
			loggerContext.setConfigLocation(this.loggingUrl.toURI());
			loggerContext.updateLoggers();
		} catch (URISyntaxException e) {
			logger.error("Logger config URL could not be set.");
		}
		this.logger.traceExit();
	}

	private void configCamelContext() {
		logger.info("configuring properties from file: " + propertiesUrl.getPath());

		Properties properties = new Properties();
		PropertiesComponent component = new PropertiesComponent();
		component.setInitialProperties(properties);
		component.setLocation("file:" + propertiesUrl.getPath());

		camelContext.setPropertiesComponent(component);

		for (RouteBuilder builder : routes) {
			logger.info("adding routes: " +
					builder.getRouteCollection().getRoutes().stream().map(r -> r.getId()).collect(Collectors.joining(", ")));

			try {
				camelContext.addRoutes(builder);
			} catch (Exception e) {
				logger.error("failed to add routes.", e);
			}
		}
	}
}
