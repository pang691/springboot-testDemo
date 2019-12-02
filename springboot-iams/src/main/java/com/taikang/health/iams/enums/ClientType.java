package com.taikang.health.iams.enums;

public enum ClientType {

    /**
     * USER TOKEN : 1
     */
    USER_TOKEN(1),
    /**
     * API TOKEN : 2
     */
    API_TOKEN(2);

    private int value;

    ClientType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}