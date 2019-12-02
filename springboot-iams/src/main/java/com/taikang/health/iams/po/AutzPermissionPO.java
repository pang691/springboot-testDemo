package com.taikang.health.iams.po;

import com.taikang.health.iams.bo.BasePO;

public class AutzPermissionPO extends BasePO {

    private static final long serialVersionUID = 8897722372023979540L;
    private String module;//模块
    private String uri;//URI
    private String operation;//操作
    private String remark;//备注
    private String priority;//优先权
    private String enable;//是否有效（1：有效，0：无效）



    public AutzPermissionPO() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        if (!super.equals(o)) {return false;}

        AutzPermissionPO that = (AutzPermissionPO) o;

        if (!module.equals(that.module)){ return false;}
        if (!uri.equals(that.uri)){ return false;}
        if (!operation.equals(that.operation)){ return false;}
        if (!remark.equals(that.remark)) {return false;}
        if (!priority.equals(that.priority)){ return false;}
        return enable.equals(that.enable);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + module.hashCode();
        result = 31 * result + uri.hashCode();
        result = 31 * result + operation.hashCode();
        result = 31 * result + remark.hashCode();
        result = 31 * result + priority.hashCode();
        result = 31 * result + enable.hashCode();
        return result;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "AutzPermissionPO{" +
                "permissionId='" + super.getId() + '\'' +
                ", module='" + module + '\'' +
                ", uri='" + uri + '\'' +
                ", operation='" + operation + '\'' +
                ", remark='" + remark + '\'' +
                ", priority='" + priority + '\'' +
                ", enable='" + enable + '\'' +
                '}';
    }
}