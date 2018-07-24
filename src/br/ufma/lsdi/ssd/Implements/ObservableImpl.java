package br.ufma.lsdi.ssd.Implements;

import br.ufma.lsdi.ssd.Communication.Communication;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Interfaces.Observable;
import br.ufma.lsdi.ssd.Model.Query;

/**
 * Created by Anderson on 07/06/2018.
 * @author Anderson
 * @version 0.1
 */

public class ObservableImpl implements Observable {
    private Communication communication = new Communication();
    private Listener listener;

    @Override
    public void addListener(Query query, Listener listener) {
        communication.query(query, this);
        System.out.println("*-*-*-*-*-*- ObservableImpl - Mandou a consulta para o br.ufma.lsdi.ssd.Communication");
        this.listener = listener;
    }

    @Override
    public void removeListener(Listener listener) {
    this.listener=null;
    }

    @Override
    public void notifyListener(String obj) {
    listener.update(obj);
    }
    public void disconnect(Listener listener){
        Communication communication = new Communication();
        communication.disconnect();
        removeListener(listener);
    }
}
