package com.guanyue.everydaynews.data;

/**
 * Created by Li DaChang on 17/8/21.
 * ..-..---.-.--..---.-...-..-....-.
 */

public class MsgBean {
    public String msgContent;
    public long time;

    public MsgBean() {
    }

    public MsgBean(String msgContent, long time) {
        this.msgContent = msgContent;
        this.time = time;
    }
}
