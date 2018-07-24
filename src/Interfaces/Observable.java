package Interfaces;

import Model.Query;

/**
 * Created by Anderson on 07/06/2018.
 */

public interface Observable {
    public void addListener(Query query, Listener listener);
    public void removeListener(Listener listener);
    public void notifyListener(String obj);
}
