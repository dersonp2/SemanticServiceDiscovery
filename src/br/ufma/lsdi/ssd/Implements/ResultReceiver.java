package br.ufma.lsdi.ssd.Implements;

import br.ufma.lsdi.ssd.Communication.Communication;
import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Interfaces.Observable;
import br.ufma.lsdi.ssd.Model.Query;
import eu.larkc.csparql.common.RDFTuple;
import org.slf4j.Logger;

import java.util.ArrayList;


/**
 * Created by Anderson on 07/06/2018.
 *
 * @author Anderson
 * @version 0.1
 */

public class ResultReceiver implements Observable {
    private Communication communication = new Communication();
    private Listener listener;
    private Logger logger;


    @Override
    public void addListener(Query query, Listener listener) {
        logger = new ConfigLog().log(ResultReceiver.class);
        //logger.info("Recebeu a consulta");
        this.listener = listener;
        communication.query(query, this);

    }

    @Override
    public void removeListener(Listener listener) {
        this.listener = null;
        //logger.info("Recebeu o Listener");
    }

    @Override
    public void notifyListener(java.util.Observable o, ArrayList<RDFTuple> rdfTuples) {
        if (listener != null)
            listener.update(o,rdfTuples);
    }

    public void disconnect(Listener listener) {
        logger.info("Desconectar");
        Communication communication = new Communication();
        communication.disconnect();
        removeListener(listener);
    }
}
