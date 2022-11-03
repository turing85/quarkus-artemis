package io.quarkus.it.artemis.jms.withdefault.changeurl;

import java.util.Random;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.eclipse.microprofile.config.ConfigProvider;

import jakarta.jms.JMSContext;

public interface ArtemisHelper {

    default String createBody() {
        return Integer.toString(new Random().nextInt(Integer.MAX_VALUE), 16);
    }

    default JMSContext createDefaultContext() {
        String url = ConfigProvider.getConfig().getValue("quarkus.artemis.url", String.class);
        return new ActiveMQJMSConnectionFactory(url).createContext(JMSContext.AUTO_ACKNOWLEDGE);
    }

    default JMSContext createNamedOnContext() {
        String url = ConfigProvider.getConfig().getValue("quarkus.artemis.\"named-1\".url", String.class);
        return new ActiveMQJMSConnectionFactory(url).createContext(JMSContext.AUTO_ACKNOWLEDGE);
    }
}
