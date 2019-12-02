package com.taikang.test.scheduled.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.StringHelper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface DTO extends Map {
    Integer getAsInteger(String var1, Integer var2);

    Integer getAsInteger(String var1);

    Long getAsLong(String var1, Long var2);

    Long getAsLong(String var1);

    String getAsString(String var1, String var2);

    String getAsString(String var1);

    BigDecimal getAsBigDecimal(String var1, BigDecimal var2);

    BigDecimal getAsBigDecimal(String var1);

    Double getAsDouble(String var1, Double var2);

    Double getAsDouble(String var1);

    Float getAsFloat(String var1, Float var2);

    Float getAsFloat(String var1);

    Date getAsDate(String var1, Date var2);

    Date getAsDate(String var1);

    Timestamp getAsTimestamp(String var1, Timestamp var2);

    Timestamp getAsTimestamp(String var1);

    Boolean getAsBoolean(String var1, Boolean var2);

    Boolean getAsBoolean(String var1);

    List getAsList(String var1);

    static String routeString(DTO paramDto, String path) {
        return (String)route(paramDto, path, "");
    }

    static <T> T route(DTO paramDto, String path, T defaultValue) {
        if (StringUtils.isBlank(path)) {
            return defaultValue;
        } else if (paramDto == null) {
            return defaultValue;
        } else {
            //int i = false;
            Object obj = null;
            DTO bakDto = paramDto;
            String[] pathpart = path.split(".");

            int i;
            for(i = 0; i < pathpart.length; ++i) {
                String part = pathpart[i];
                obj = bakDto.get(part);
                if (obj == null || !(obj instanceof DTO) || i >= pathpart.length - 1) {
                    break;
                }

                bakDto = (DTO)obj;
            }

            if (i == pathpart.length - 1) {
                return obj == null ? defaultValue : (T)obj;
            } else {
                return null;
            }
        }
    }
}

