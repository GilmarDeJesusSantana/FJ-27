package br.com.caelum.casadocodigo.conf;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

public class ServletDoSpring extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{ AppConfiguration.class, JpaConfig.class,SpringSecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
    @Override
public void onStartup(ServletContext servletContext)throws ServletException{
super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
        servletContext.setInitParameter("spring.profiles.active","dev");
    }

}
