package com.zjc.keepwork.pojo;

public class OrderInfo {
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "body='" + body + '\'' +
                '}';
    }
}
