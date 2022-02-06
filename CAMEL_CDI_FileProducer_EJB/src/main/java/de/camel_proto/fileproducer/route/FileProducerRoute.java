
package de.camel_proto.fileproducer.route;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.Uri;
import org.apache.logging.log4j.Logger;

@DoNotDiscover
public class FileProducerRoute extends RouteBuilder {
	public static String FILE_ROUTE = "fileRoute";

	@Inject
	@Uri("jms:queue:{{jms.camel.queue}}")
	private Endpoint inputQueue;

	@Inject
	@Uri("file:{{output.file.path}}")
	private Endpoint outputPath;

	@Inject
	private Logger logger;

	@Override
	public void configure() {
		logger.info("--- starting file producer route configure...");

		from(inputQueue).routeId(FILE_ROUTE)
				.log(LoggingLevel.INFO, "messages", "received message:  ${body}")
				.to(outputPath);
	}
}