package com.lcgalean.mqclient;

import com.lcgalean.mqclient.mq.MQConnectionConfig;
import com.lcgalean.mqclient.mq.MQService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQGetMessageOptions;

import java.io.EOFException;
import java.io.IOException;

@SpringBootApplication
public class MqclientApplication {

    private static final String QUEUE_MANAGER = "DCOLQMGRMIG01";
    private static final String QUEUE_NAME = "SFG_CONTROLM_SFG.QL.REQ";
    private static final String HOST = "10.8.74.110";
    private static final int PORT = 1418; //
    private static final String CHANNEL = "ADMIN.SVRCONN";
    private static final String USER_ID = "CNXQDSTR";
    //private static final String PASSWORD = "TU_CONTRASEÑA";


    public static void main(String[] args) {

        MQConnectionConfig config = new MQConnectionConfig(

                "NOMBRE_DE_TU_QUEUE_MANAGER",
                "NOMBRE_DE_TU_COLA",
                "TU_HOST",
                1414, // Puerto de conexión de MQ
                "NOMBRE_DEL_CANAL",
                "USUARIO", // Opcional
                "CONTRASEÑA" // Opcional
        );

        MQService mqService = null;
        try {
            mqService = new MQService(config);
            int queueDepth = mqService.getQueueDepth(config.getQueueName());
            System.out.println("Número de mensajes en la cola: " + queueDepth);
        } catch (MQException e) {
            System.err.println("Error al contar los mensajes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (mqService != null) {
                try {
                    mqService.disconnect();
                } catch (MQException e) {
                    System.err.println("Error al desconectar: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

}
