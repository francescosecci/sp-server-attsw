package com.pufose.server;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.core.env.AbstractEnvironment;


@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer{

	

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "mongo");
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev"); 
        super.onStartup(servletContext);
    }
	
	
}
