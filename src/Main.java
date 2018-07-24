import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Implements.ObservableImpl;

public class Main {

    public static void main(String[] args) {
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
                System.out.println("CHEGOOOOOOOOOOOOOUUUUU NO MAIIIINNNNNNNNNNNNN porrrrrrrrrrrraaaaaaaaaaaaaaaaaaa");
            }
        });
    }
}
