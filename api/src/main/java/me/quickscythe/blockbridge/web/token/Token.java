package me.quickscythe.blockbridge.web.token;

import me.quickscythe.blockbridge.BlockBridgeApi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Token {
    private String token;
    private String ip;
    private long created;
    private final BlockBridgeApi bba;

    public Token(String token, String ip, BlockBridgeApi bba) {
        this.token = token;
        this.ip = ip;
        this.created = new Date().getTime();
        this.bba = bba;
    }

    public String getToken() {
        return token;
    }

    public String getIp() {
        return ip;
    }

    public long getCreated(){
        return created;
    }

    public boolean isExpired() {
        return new Date().getTime() - created >= TimeUnit.MILLISECONDS.convert(bba.TOKEN_VALID_TIME(), TimeUnit.HOURS);
    }
}
