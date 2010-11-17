package com.mattinsler.cement.example.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.mattinsler.cement.CementRequestLogWriter;
import com.mattinsler.cement.logging.CementLogger;
import com.mattinsler.cement.mongo.MongoCementRequestLogWriter;
import com.mattinsler.cement.mongo.logging.MongoCementLogger;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 6, 2010
 * Time: 1:30:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoggerModule implements Module {
    public void configure(Binder binder) {
        // logger
        binder.bind(CementLogger.class).to(MongoCementLogger.class);

        // request log writer
        binder.bind(CementRequestLogWriter.class).to(MongoCementRequestLogWriter.class);
    }
}
