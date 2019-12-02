package com.taikang.test.basebo;

import com.alibaba.fastjson.annotation.JSONField;
import com.taikang.test.scheduled.config.Pager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseBO implements BO {
    private static final long serialVersionUID = 8117157871004374622L;
    @JSONField(
        serialize = false
    )
    @ApiModelProperty(
        hidden = true
    )
    private Pager pager = new Pager();

    public BaseBO() {
    }

    public void noPaging() {
        this.pager.noPaging();
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public void setPageable(boolean pageable) {
        this.pager.setPageable(pageable);
    }

    public void setPageNo(Integer pageNo) {
        this.pager.setPageNo(pageNo);
    }

    public void setPageSize(Integer pageSize) {
        this.pager.setPageSize(pageSize);
    }

    public void setTotal(Integer total) {
        this.pager.setTotal(total);
    }

    @JSONField(
        serialize = false
    )
    public Pager getPager() {
        return this.pager;
    }

    @JSONField(
        serialize = false
    )
    @ApiModelProperty(
        hidden = true
    )
    public Boolean getPageable() {
        return this.pager.isPageable();
    }

    @JSONField(
        serialize = false
    )
    @ApiModelProperty(
        hidden = true
    )
    public Integer getPageNo() {
        return this.pager.getPageNo();
    }

    @JSONField(
        serialize = false
    )
    @ApiModelProperty(
        hidden = true
    )
    public Integer getPageSize() {
        return this.pager.getPageSize();
    }

    @JSONField(
        serialize = false
    )
    @ApiModelProperty(
        hidden = true
    )
    public Integer getTotal() {
        return this.pager.getTotal();
    }
}
