package de.rieckpil.learning.springboot2bookmessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.server.SMTPServer;

import static de.rieckpil.learning.springboot2bookmessaging.PrepareMockSmtpListener.LATCH;

public class PrepareMockSmtpListener implements SpringApplicationRunListener {

    public static final CountDownLatch LATCH = new CountDownLatch(1);

    private final SMTPServer smtpServer;

    public PrepareMockSmtpListener(final SpringApplication springApplication, String[] args) {
        final MessageHandlerFactory messageHandlerFactory = new MessageHandlerImpl();
        this.smtpServer = new SMTPServer(messageHandlerFactory);
        this.smtpServer.setPort(41209);
    }

    @Override
    public void starting() {
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        this.smtpServer.start();
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        context.getBeanFactory().registerSingleton("smtpServer", this.smtpServer);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
    }
}

class MessageHandlerImpl implements MessageHandlerFactory {

    static final Logger LOG = LoggerFactory
            .getLogger(SpringBoot2BookMessagingApplication.class);

    @Override
    public MessageHandler create(MessageContext ctx) {
        return new Handler(ctx);
    }

    static class Handler implements MessageHandler {
        MessageContext ctx;

        public Handler(MessageContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void from(String from) throws RejectException {
            LOG.info("FROM: {}", from);
        }

        @Override
        public void recipient(String recipient) throws RejectException {
            LOG.info("RECIPIENT: {}", recipient);
        }

        public void data(InputStream data) throws IOException {
            LOG.info("DATA:");
            LOG.info(convertStreamToString(data));
        }

        @Override
        public void done() {
            LATCH.countDown();
        }

        String convertStreamToString(InputStream is) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            final StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
            }
            return sb.toString();
        }
    }
}