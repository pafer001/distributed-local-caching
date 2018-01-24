package com.pafer.distributed.local.cache.mq;

/**
 * @author wangzhenya
 */
public class LocalCacheClientConfiguration {

    private String user;
    private String pwd;
    private String vHost;
    private String queueName;
    private String address;
    //consumer 需要
    private int qosCount = 5;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getvHost() {
        return vHost;
    }

    public void setvHost(String vHost) {
        this.vHost = vHost;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQosCount() {
        return qosCount;
    }

    public void setQosCount(int qosCount) {
        this.qosCount = qosCount;
    }
}
