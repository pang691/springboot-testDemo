package com.taikang.test.annotation.out;

import java.io.Serializable;

public class RestMessageBean implements Model {
    @Dict2NameLabel(target = "itemCode",classified = "itemName")
    private String itemName;
    private String itemCode;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "RestMessageBean{" +
                "itemName='" + itemName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                '}';
    }
}
