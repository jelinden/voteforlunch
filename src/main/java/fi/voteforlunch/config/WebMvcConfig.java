package fi.voteforlunch.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import fi.voteforlunch.domain.User;

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
    registry.addResourceHandler("/resources/**").addResourceLocations(
        "/resources/");
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory cf = new JedisConnectionFactory();
    cf.setHostName("localhost");
    cf.setPort(6379);
    return cf;
  }

  @Bean
  public RedisTemplate<String, User> userTemplate() {
    RedisTemplate<String, User> redisTemplate = new RedisTemplate<String, User>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setValueSerializer(userValueSerializer());
    return redisTemplate;
  }

  @Bean
  public RedisSerializer<User> userValueSerializer() {
    return new JacksonJsonRedisSerializer<User>(User.class);
  }

  @Override
  public void configureMessageConverters(
      List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJacksonHttpMessageConverter());
  }

  @Bean
  public RedisAtomicLong redisAtomicLong() {
    return new RedisAtomicLong("user", redisConnectionFactory());
  }
}
