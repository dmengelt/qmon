package ch.filecloud.queuemonitor;

import ch.filecloud.queuemonitor.common.QmonEnvironmentConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import static ch.filecloud.queuemonitor.common.Consts.*;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by domi on 7/21/14.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {

    private static Log LOGGER = LogFactory.getLog(App.class);

    private final String REMOTE_JMX_URL_PREFIX = "service:jmx:rmi://localhost/jndi/rmi://";
    private final String REMOTE_JMX_URL_SUFFIX = "/jmxrmi";
    private final String REMOTE_JMX_URL_DEFAULT = "localhost:9005";

    @Value("#{ systemProperties['" + QMON_REMOTE_JMX_URL_PROPERTY +"'] }")
    private String remoteJmxUrl;

    @Value("#{ systemProperties['" + QMON_CONFIG +"'] }")
    private String qmonConfig;

    @Bean
    protected MBeanServerConnectionFactoryBean getMBeanServerConnectionFactoryBean() throws MalformedURLException {
        MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();

        if(remoteJmxUrl == null) {
            remoteJmxUrl = REMOTE_JMX_URL_DEFAULT;
        }

        LOGGER.info("Using JMX URL " + remoteJmxUrl);

        //QmonEnvironmentConfiguration qmonEnvironmentConfiguration = getQmonEnvironmentConfiguration();

        mBeanServerConnectionFactoryBean.setServiceUrl(REMOTE_JMX_URL_PREFIX + remoteJmxUrl + REMOTE_JMX_URL_SUFFIX);
        return mBeanServerConnectionFactoryBean;
    }

    @Bean
    protected QmonEnvironmentConfiguration getQmonEnvironmentConfiguration() {
        try {
            return QmonEnvironmentConfiguration.create(qmonConfig);
        } catch (IOException e) {
           throw new IllegalArgumentException("Unable to parse config JSON", e);
        }
    }

    @Bean
    protected ObjectMapper getObjectMapper() {
        ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return  jacksonMapper;
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
