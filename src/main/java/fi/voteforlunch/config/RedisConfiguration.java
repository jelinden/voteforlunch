package fi.voteforlunch.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import fi.voteforlunch.domain.User;

@Configuration
@PropertySource("classpath:/common.properties")
public class RedisConfiguration {
  @Value("${profile}")
  private String profile;
  
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory cf = new JedisConnectionFactory();
    if (profile.equals("dev")) {
      cf.setHostName("localhost");
      cf.setPort(6379);
    } else {
      try {
        URI redisUri = new URI(System.getenv("REDISCLOUD_URL"));
        cf.setPoolConfig(new JedisPoolConfig());
        cf.setHostName(redisUri.getHost());
        cf.setPort(redisUri.getPort());
        cf.setUsePool(true);
        cf.setTimeout(Protocol.DEFAULT_TIMEOUT);
        cf.setPassword(redisUri.getUserInfo().split(":", 2)[1]);
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }
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
  
  @Bean
  public RedisAtomicLong redisAtomicLong() {
    return new RedisAtomicLong("user", redisConnectionFactory());
  }
}
