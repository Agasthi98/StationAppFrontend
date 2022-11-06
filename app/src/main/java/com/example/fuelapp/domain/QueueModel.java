package com.example.fuelapp.domain;

/*
create queue model
 */
public class QueueModel {
    private String phone;
    private String queue;
    private String shed;

    public String getPhone() {
        return phone;
    }

    public String getQueue() {
        return queue;
    }

    public String getShed() {
        return shed;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public void setShed(String shed) {
        this.shed = shed;
    }
}
