package com.example.whatsapp_test2.Models;

public class SmsModel {

    String Id,sms;
    Long time;

    public SmsModel(String id, String sms) {
        Id = id;
        this.sms = sms;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public SmsModel() {
    }

    public SmsModel(String id, String sms, Long time) {
        Id = id;
        this.sms = sms;
        this.time = time;
    }
}
