package com.taikang.test.scheduled.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PagerResult<T> implements Serializable {
    private static final long serialVersionUID = -6171751136953308027L;
    private Pager pager = new Pager();
    private List<T> data = new ArrayList();

    public PagerResult() {
        this.pager.noPaging();
        this.data = new ArrayList();
    }

    public static <T> PagerResult<T> build() {
        return new PagerResult();
    }

    public static <T> PagerResult<T> build(Pager pager, List<T> data) {
        return (new PagerResult()).pager(pager).data(data);
    }

    public PagerResult<T> pager(Pager pager) {
        if (pager != null && pager.isPageable()) {
            this.pager = pager;
        }

        return this;
    }

    public PagerResult<T> data(List<T> data) {
        if (data == null) {
            this.data = new ArrayList();
        } else {
            this.data = data;
        }

        return this;
    }

    public List<T> getData() {
        return this.data;
    }

    public Pager getPager() {
        return this.pager;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
