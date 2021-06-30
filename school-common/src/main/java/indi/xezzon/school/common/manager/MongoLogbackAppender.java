package indi.xezzon.school.common.manager;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.mongodb.BasicDBObject;
import indi.xezzon.school.common.config.ApplicationContextProvider;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

/**
 * @author xezzon
 */
@Component
public class MongoLogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private static final String LOGGING_COLLECTION = "logging";

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        MongoTemplate mongoTemplate = ApplicationContextProvider.getBean(MongoTemplate.class);
        BasicDBObject logObject = new BasicDBObject();
        logObject.append("timestamp", LocalDateTimeUtil.of(iLoggingEvent.getTimeStamp(), ZoneId.systemDefault()));
        logObject.append("thread", iLoggingEvent.getThreadName());
        logObject.append("level", iLoggingEvent.getLevel().toString());
        logObject.append("logger", iLoggingEvent.getLoggerName());
        logObject.append("message", iLoggingEvent.getFormattedMessage());
        iLoggingEvent.getMDCPropertyMap().entrySet().parallelStream().forEach(entry -> {
            logObject.append(entry.getKey(), entry.getValue());
        });
        mongoTemplate.insert(logObject, LOGGING_COLLECTION);
    }
}
