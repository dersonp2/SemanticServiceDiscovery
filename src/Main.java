import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Implements.ObservableImpl;
import org.slf4j.Logger;

public class Main {

    private static Logger logger = null;

    public static void main(String[] args) {
        logger = new ConfigLog().log(Main.class);
        consulta();
    }

    public static void consulta(){

        Query q = new Query.Builder().query("Select X where.......")
                .continuos(true)
                .publisherID("Anderson@lsdi.ufma.br")
                .build();

        ObservableImpl consulta = new ObservableImpl();
        consulta.addListener(q, new Listener() {
            @Override
            public void update(String obj) {
            logger.info("Update");
            }
        });
    }
}
