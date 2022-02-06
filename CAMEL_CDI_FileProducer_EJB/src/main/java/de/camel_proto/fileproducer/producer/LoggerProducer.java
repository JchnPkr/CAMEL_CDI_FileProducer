/*
 * @(#)%filespec: CdiContext.java~1.3 %
 *
 * Copyright 2014 BZSt/ZIVIT. All rights reserved.
 * ZIVIT PROPRIETARY/CONFIDENTIAL.
 */

package de.camel_proto.fileproducer.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerProducer {
  @Produces
  public Logger produceLogger(InjectionPoint injectionPoint) {
    return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());
  }
}
