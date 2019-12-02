package com.taikang.health.iams.bo;

import lombok.Data;

@Data
public class BasePO {
   public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
