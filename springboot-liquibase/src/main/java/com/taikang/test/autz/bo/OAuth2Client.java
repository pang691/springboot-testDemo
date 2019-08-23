package com.taikang.test.autz.bo;

import lombok.Data;

@Data
public class OAuth2Client  {

    private static final long serialVersionUID = -2185766021222571606L;
    //客户端名称
    private String name;
    //密钥
    private String secret;
    //备注
    private String remark;
    //状态
    private int status;
}
