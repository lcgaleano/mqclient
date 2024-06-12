package com.lcgalean.mqclient.mq;

public class MQConnectionConfig {

    private String queueManagerName;
    private String queueName;
    private String host;
    private int port;
    private String channel;
    private String user;
    private String password;

    public MQConnectionConfig(String queueManagerName, String queueName, String host, int port, String channel, String user, String password) {
        this.queueManagerName = queueManagerName;
        this.queueName = queueName;
        this.host = host;
        this.port = port;
        this.channel = channel;
        this.user = user;
        this.password = password;
    }

    public String getQueueManagerName() {
        return queueManagerName;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getChannel() {
        return channel;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
