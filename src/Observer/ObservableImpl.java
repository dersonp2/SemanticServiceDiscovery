package Observer;

import Communication.Communication;
import Model.Query;

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
        System.out.println("*-*-*-*-*-*- ObservableImpl - Mandou a consulta para o Communication");
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
}
