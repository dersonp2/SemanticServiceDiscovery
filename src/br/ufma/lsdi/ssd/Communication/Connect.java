package br.ufma.lsdi.ssd.Communication;

import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Exception.BrokerConnectException;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;

public class Connect {
    private static String brokerUrl = "tcp://lsdi.ufma.br:1883";
    private static MqttClient client = null;
    private String tmpDir = System.getProperty("java.io.tmpdir");
    private MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
    private static Logger logger;
    public static Connect instance = null;

    //Método Singleton
    public static Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
            logger = new ConfigLog().log(Connect.class);
        }
        return instance;
    }

    public MqttClient Connection(String clientID) {
        if (client == null) {
            try {
                client = new MqttClient(brokerUrl, clientID, dataStore);

                client.connect();
            } catch (MqttException e) {
                e.printStackTrace();
                client = null;
                System.out.println("Erro ao iniciar o Broker");

            }
        }
        return client;
    }
}
