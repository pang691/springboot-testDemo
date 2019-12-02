package com.taikang.health.iams.enums;

public enum EnableStatus {

    /**
     * 启动 : 1
     */
    ENABLE(1),
    /**
     * 不启用 : 0
     */
    UN_ENABLE(0);

    private int value;

    EnableStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}