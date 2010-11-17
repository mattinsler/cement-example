package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.mattinsler.cement.example.service.LogService;
import com.mattinsler.cement.mongo.model.ExceptionEntity;
import com.mattinsler.cement.mongo.model.LogEntity;
import com.mattinsler.cement.util.Function;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 27, 2010
 * Time: 12:44:28 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractLogHandler {
    public static class ExceptionItem {
        public static class ThrowableItem {
            String message;
            String class_name;
            List<StackItem> stack;
        }
        public static class StackItem {
            String filename;
            String method;
            int line;
            String class_name;
        }
        public List<ThrowableItem> throwables;
        ExceptionItem(ExceptionEntity entity) {
            this.throwables = new ArrayList<ThrowableItem>();
            for (ExceptionEntity.Throwable t : entity.getThrowableList()) {
                ThrowableItem item = new ThrowableItem();
                item.message = t.getMessage();
                item.class_name = t.getFullClassName();
                item.stack = new ArrayList<StackItem>();
                for (ExceptionEntity.StackElement s : t.getStackElementList()) {
                    StackItem stackItem = new StackItem();
                    stackItem.filename = s.getFilename();
                    stackItem.method = s.getMethod();
                    stackItem.line = s.getLine();
                    stackItem.class_name = s.getFullClassName();
                    item.stack.add(stackItem);
                }
                this.throwables.add(item);
            }
        }
    }
    public static class LogItem {
        String request_id;
        String server_hostname;
        Date timestamp;

        LogEntity.Level level;
	    String message;
	    ExceptionItem exception;
    }

    protected static final Function<LogItem, LogEntity> LogItemTransform = new Function<LogItem, LogEntity>() {
        @Override
        public LogItem execute(LogEntity argument) {
            LogItem item = new LogItem();
            item.request_id = argument.getRequestId();
            item.server_hostname = argument.getServerHostname();
            item.timestamp = argument.getTimestamp();
            item.level = argument.getLevel();
            item.message = argument.getMessage();
            if (argument.hasException()) {
                item.exception = new ExceptionItem(argument.getException());
            }
            return item;
        }
    };

    protected LogService logService;

    @Inject
    void setDependencies(LogService logService) {
        this.logService = logService;
    }
}
