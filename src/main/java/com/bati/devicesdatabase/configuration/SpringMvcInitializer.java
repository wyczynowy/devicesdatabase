package com.bati.devicesdatabase.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletRegistration;
//import java.io.File;

import com.bati.devicesdatabase.configuration.SpringMvcConfiguration;
import com.bati.devicesdatabase.configuration.WebSecurityConfig;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfiguration.class, WebSecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringMvcConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}