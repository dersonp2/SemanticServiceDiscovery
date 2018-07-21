import Model.Query;
import Observer.Listener;
import Observer.ObservableImpl;

public class Main {

    public static void main(String[] args) {
        consulta();
    }
    public static void consulta(){
        Query query = new Query();
        query.setPublisherID("Anderson@lsdi.ufma.br");
        query.setContinuos(true);
        query.setQuery("Consulta X");
        ObservableImpl consulta = new ObservableImpl();
        consulta.addListener(query, new Listener() {
            @Override
            public void update(String obj) {

            }
        });
    }
}
