package com.lcgalean.mqclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;

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
		MQQueueManager qMgr = null;
		try {
			MQEnvironment.hostname = HOST;
			MQEnvironment.port = PORT;
			MQEnvironment.channel = CHANNEL;
			MQEnvironment.properties.put(CMQC.USER_ID_PROPERTY, USER_ID);
			qMgr = new MQQueueManager(QUEUE_MANAGER);
			int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
			MQQueue queue = qMgr.accessQueue(QUEUE_NAME, openOptions);

			MQMessage msg = new MQMessage();
			msg.writeString("Tu mensaje aquí");

			MQPutMessageOptions pmo = new MQPutMessageOptions();
			queue.put(msg, pmo);

			System.out.println("Mensaje enviado con éxito.");


			System.out.println(msg.correlationId.toString());
			System.out.println(msg.messageId.toString());
		} catch (MQException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
