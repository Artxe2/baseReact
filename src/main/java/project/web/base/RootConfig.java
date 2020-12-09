package project.web.base;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = { "project.web.backend", "project.web.config", "project.web.socket" })
@EnableJpaRepositories(basePackages = { "org.spring.jpa" })
@EntityScan(basePackages = { "org.spring.jpa" })
public class RootConfig {

}
