package com.taikang.test.autz.bo;

public enum DataAccess {
    org_own_child("org_own_child"),
    org_own("org_own"),
    dept_own_child("dept_own_child"),
    dept_own("dept_own");

    private String scope = null;

    private DataAccess(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return this.scope;
    }

    public static DataAccess fromString(String str) {
        try {
            return valueOf(str.toLowerCase());
        } catch (Exception var2) {
            return dept_own;
        }
    }
}