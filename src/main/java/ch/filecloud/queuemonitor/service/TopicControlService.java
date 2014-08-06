package ch.filecloud.queuemonitor.service;

import ch.filecloud.queuemonitor.domain.TopicInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.JMSServerControl;
import org.hornetq.api.jms.management.SubscriptionInfo;
import org.hornetq.api.jms.management.TopicControl;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import java.util.*;

/**
 * Created by domi on 8/3/14.
 */
@Component
public class TopicControlService extends ControlService {

    private static Log LOGGER = LogFactory.getLog(TopicControlService.class);

    public List<TopicInfo> getTopics() {
        List<TopicInfo> topicInfos = new ArrayList<TopicInfo>();

        JMSServerControl jmsServerControl = getJMSServerControl();

        for (String topic : getJMSServerControl().getTopicNames()) {
            topicInfos.add(createTopicInfo(topic));
        }

        return topicInfos;
    }

    public TopicInfo getTopic(String topicName) {
        return createTopicInfo(topicName);
    }

    public List<SubscriptionInfo> getSubscribtions(String topicName) {
        return createSubscriptionInfo(topicName);
    }

    private TopicInfo createTopicInfo(String topicName) {
        try {
            TopicControl topicControl = getTopicControl(topicName);
            return new TopicInfo(topicName, topicControl.getMessageCount(), topicControl.getMessagesAdded(), topicControl.getSubscriptionCount());
        } catch (Exception e) {
            LOGGER.error("Error occurred while trying to create the topic information for topic " + topicName, e);
            // TODO - throw custom exception and add global exception handler (spring aop or @ExceptionHandler)
            return null;
        }
    }

    private List<SubscriptionInfo> createSubscriptionInfo(String topicName) {
        try {
            TopicControl topicControl = getTopicControl(topicName);
            return Arrays.asList(SubscriptionInfo.from(topicControl.listAllSubscriptionsAsJSON()));
        } catch (Exception e) {
            LOGGER.error("Error occurred while trying to get the subscriptions for topic " + topicName, e);
            // TODO - throw custom exception and add global exception handler (spring aop or @ExceptionHandler)
            return null;
        }
    }

    private TopicControl getTopicControl(String topicName) {
        try {
            ObjectName objectName = ObjectNameBuilder.DEFAULT.getJMSTopicObjectName(topicName);
            return MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnectionFactoryBean.getObject(), objectName, TopicControl.class, false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object name not found!", e);
        }
    }
}
