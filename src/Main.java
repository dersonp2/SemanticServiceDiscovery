import Model.Query;
import Interfaces.Listener;
import Implements.ObservableImpl;

public class Main {

    public static void main(String[] args) {
        consulta();
    }
    public static void consulta(){
        Query q = new Query.Builder().query("Select ta")
                .continuos(true)
                .publisherID("Anderson tal")
                .build();

        ObservableImpl consulta = new ObservableImpl();
        consulta.addListener(q, new Listener() {
            @Override
            public void update(String obj) {

            }
        });
    }
}
