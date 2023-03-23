package com.app.sociallogin.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ContainerConfig {

    @Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createAjpConnector());
		return tomcat;
	}

	private Connector createAjpConnector() {
		Connector ajpConnector = new Connector("AJP/1.3");
		ajpConnector.setPort(17109);
		ajpConnector.setSecure(false);
		ajpConnector.setAllowTrace(false);
		ajpConnector.setScheme("http");

		AjpNioProtocol protocol = (AjpNioProtocol) ajpConnector.getProtocolHandler();

		try {
			protocol.setSecretRequired(false);
			protocol.setAddress(InetAddress.getByName("0.0.0.0"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return ajpConnector;
	}
}
