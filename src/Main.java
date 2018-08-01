import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Implements.ResultReceiver;
import org.slf4j.Logger;

public class Main {

    private static Logger logger = null;

    public static void main(String[] args) {
        logger = new ConfigLog().log(Main.class);
        consulta();
    }

    public static void consulta(){
        String query = "REGISTER QUERY UpStreamQuery AS "
                + "SELECT ?s "
                + "FROM STREAM <stream1> [RANGE 12s STEP 5s] "
                + "WHERE { ?s ?p ?o}";

        Query q = new Query.Builder().query(query)
                .continuos(true)
                .publisherID("Anderson@lsdi.ufma.br")
                .build();

        ResultReceiver consulta = new ResultReceiver();
        consulta.addListener(q, new Listener() {
            @Override
            public void update(String obj) {
            logger.info(obj);
            }
        });
    }
}
