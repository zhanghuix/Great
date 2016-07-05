package com.file.manager.model;

/**
 * 接收接口返回值
 * Created by ronnie on 2015/10/27.
 */
public class Message {
    private int code;
    private String msg;
    private String data;

    public Message(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Message() {
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
