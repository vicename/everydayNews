package com.guanyue.everydaynews.data;

import java.io.Serializable;

/**
 * Created by Li DaChang on 17/8/21.
 * ..-..---.-.--..---.-...-..-....-.
 */

public class MsgBean implements Serializable{
    public String msgContent;
    public long time;
    private static final long serialVersionUID = 23L;

    public MsgBean() {
    }

    public MsgBean(String msgContent, long time) {
        this.msgContent = msgContent;
        this.time = time;
    }
}
