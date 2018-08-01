package br.ufma.lsdi.ssd.Exception;

import org.eclipse.paho.client.mqttv3.MqttException;

public class BrokerConnectException extends MqttException {
    public BrokerConnectException(int reasonCode) {
        super(reasonCode);
    }
}
