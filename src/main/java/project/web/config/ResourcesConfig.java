package project.web.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	Logger logger = LoggerFactory.getLogger(ResourcesConfig.class);
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/static/"
			,"classpath:/static/uploaded_files/"
	};
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/**/**")) {
			registry.addResourceHandler("/**/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
	}
}