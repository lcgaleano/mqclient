package com.lcgalean.mqclient.mq;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;

public class MQService {

    private MQQueueManager queueManager;

    public MQService(MQConnectionConfig config) throws MQException {
        // Configuración de MQEnvironment
        MQEnvironment.hostname = config.getHost();
        MQEnvironment.port = config.getPort();
        MQEnvironment.channel = config.getChannel();
        MQEnvironment.userID = config.getUser();
        MQEnvironment.password = config.getPassword();

        // Conectar al Queue Manager
        this.queueManager = new MQQueueManager(config.getQueueManagerName());
    }

    public int getQueueDepth(String queueName) throws MQException {
        int depth = 0;
        MQQueue queue = null;
        try {
            // Abrir la cola
            int openOptions = CMQC.MQOO_INQUIRE;
            queue = queueManager.accessQueue(queueName, openOptions);

            // Obtener la profundidad de la cola
            depth = queue.getCurrentDepth();
        } finally {
            if (queue != null) {
                queue.close();
            }
        }
        return depth;
    }

    public void disconnect() throws MQException {
        if (queueManager != null) {
            queueManager.disconnect();
        }
    }


}
