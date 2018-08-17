package br.ufma.lsdi.ssd.ConfigLog;

import org.apache.log4j.*;

public class Configurator {
    protected Configurator() {
    }

    public static void configure() {
        Logger root = Logger.getRootLogger();
        root.addAppender(new ConsoleAppender(new PatternLayout("%-5p %r [%d] [%t] %c %x - %m%n")));
    }

    public static void configure(Appender appender) {
        Logger root = Logger.getRootLogger();
        root.addAppender(appender);
    }

    public static void resetConfiguration() {
        LogManager.resetConfiguration();
    }
}
