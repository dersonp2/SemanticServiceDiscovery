package br.ufma.lsdi.ssd.ConfigLog;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLog {
    public ConfigLog() {
    }

    public Logger log (Class c){
        Logger logger = LoggerFactory.getLogger(c);
        //PropertyConfigurator.configure("log4j.properties");
        //BasicConfigurator.configure();
       /* String caminho = "src/ConfigLog/csparql_log4j.properties";
        try {
            PropertyConfigurator.configure(caminho);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }*/
        return logger;
    }
}
