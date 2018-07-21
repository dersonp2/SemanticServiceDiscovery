package Communication;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class Connect {
    private static String brokerUrl = "tcp://lsdi.ufma.br:1883";
    private static MqttClient client = null;
    private String tmpDir = System.getProperty("java.io.tmpdir");
    private MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

    public static  Connect instance = null;

    //Método Singleton
    public static Connect getInstance (){
        if (instance == null){
                instance = new Connect();
        }
        return instance;
    }
    public MqttClient Connection(){
        if(client == null){
            try {
                client = new MqttClient(brokerUrl, "SubscribreCliente", dataStore);
                client.connect();
                System.out.println("*-*-*-*-*-*- Connect - Conect()");
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return client;
    }
}
