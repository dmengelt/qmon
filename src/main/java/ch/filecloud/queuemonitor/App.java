package ch.filecloud.queuemonitor;

import ch.filecloud.queuemonitor.common.QmonEnvironmentConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by domi on 7/21/14.
 */
@SpringBootApplication
public class App {

    private static final String QMON_CONFIG = "qmon.config";

    @Value("#{ systemProperties['" + QMON_CONFIG + "'] }")
    private String qmonConfig;

    @Bean
    protected QmonEnvironmentConfiguration getQmonEnvironmentConfiguration() {
        return QmonEnvironmentConfiguration.create(qmonConfig);
    }

    @Bean
    protected ObjectMapper getObjectMapper() {
        ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return jacksonMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(getObjectMapper());
        return mappingJackson2HttpMessageConverter;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
