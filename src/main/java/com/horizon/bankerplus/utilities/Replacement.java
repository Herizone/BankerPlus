package com.horizon.bankerplus.utilities;

public class Replacement {

    private String then, now;

    public Replacement(String then, String now) {
        this.then = then;
        this.now = now;
    }

    public String getNow() {
        return now;
    }

    public String getThen() {
        return then;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setThen(String then) {
        this.then = then;
    }
}
