package fi.voteforlunch.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "fi.voteforlunch")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public BeanNameViewResolver configureBeanNameViewResolver() {
    return new BeanNameViewResolver();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
  
  @Bean
  public static PropertySourcesPlaceholderConfigurer properties() {
    PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
    Resource[] resources = new ClassPathResource[] { new ClassPathResource("common.properties") };
    propertySources.setLocations(resources);
    propertySources.setIgnoreUnresolvablePlaceholders(true);
    return propertySources;
  }

  @Override
  public void configureMessageConverters(
      List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJacksonHttpMessageConverter());
  }
}
