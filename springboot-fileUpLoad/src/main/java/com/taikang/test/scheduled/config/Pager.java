package com.taikang.test.scheduled.config;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class Pager implements Serializable {
    private static final long serialVersionUID = -6170751136953308027L;
    private static final String PARAM_PAGER = "pager";
    private static final String PARAM_PAGEABLE = "pageable";
    private static final String PARAM_PAGE_NO = "pageNo";
    private static final String PARAM_PAGE_SIZE = "pageSize";
    private static final String PARAM_TOTAL = "total";
    private static final String PARAM_TOTAL_PAGE = "totalPage";
    public static String[] PAGER_PARAMS = new String[]{"pager", "pageable", "pageNo", "pageSize", "total", "totalPage"};
    private Boolean pageable = false;
    private Integer pageNo = null;
    private Integer pageSize = null;
    private Integer total = null;
    private Integer totalPage = null;

    public Pager() {
    }

    public static Pager build(DTO dto) {
        if (dto != null && !dto.isEmpty()) {
            Pager pager = new Pager();
            if (dto.containsKey("pageable")) {
                pager.setPageable(dto.getAsBoolean("pageable"));
            }

            if (dto.containsKey("pageNo")) {
                pager.setPageNo(dto.getAsInteger("pageNo"));
            }

            if (dto.containsKey("pageSize")) {
                pager.setPageSize(dto.getAsInteger("pageSize"));
            }

            if (dto.containsKey("total")) {
                pager.setTotal(dto.getAsInteger("total"));
            }

            return pager;
        } else {
            return null;
        }
    }

    public void doPaging() {
        this.doPaging(0, 15);
    }

    public void doPaging(Integer pageIndex, Integer pageSize, Integer total) {
        this.doPaging(pageIndex, pageSize);
        this.setTotal(total);
    }

    public void doPaging(Integer pageIndex, Integer pageSize) {
        this.setPageNo(pageIndex);
        this.setPageSize(pageSize);
    }

    public void noPaging() {
        this.setPageable(false);
    }

    public boolean isPageable() {
        return this.pageable;
    }

    public Pager setPageable(Boolean pageable) {
        this.pageable = pageable;
        if (this.total == null || this.total < 0) {
            this.total = 0;
        }

        if (this.totalPage == null || this.totalPage < 0) {
            this.totalPage = 0;
        }

        if (this.pageNo == null || this.pageNo < 0) {
            this.pageNo = 1;
        }

        if (this.pageSize == null || this.pageSize < 0) {
            this.pageSize = 15;
        }

        return this;
    }

    public Pager setPageNo(Integer pageNo) {
        if (pageNo != null) {
            this.setPageable(true);
        }

        this.pageNo = pageNo;
        return this;
    }

    public Pager setPageSize(Integer pageSize) {
        if (pageSize != null) {
            this.setPageable(true);
        }

        this.pageSize = pageSize;
        return this;
    }

    public Pager setTotal(Integer total) {
        if (total != null) {
            this.setPageable(true);
        }

        this.total = total;
        if (total != null) {
            this.totalPage = total % this.pageSize == 0 ? total / this.pageSize : total / this.pageSize + 1;
        }

        return this;
    }

    public Boolean getPageable() {
        return this.pageable;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotal() {
        return this.total;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
