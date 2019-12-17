package com.taikang.health.iams.ldapauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantProperties {

    @Value("${ldap.ldapUrl}")
    private String ldapUrl;

    @Value("${ldap.ldapBaseDn}")
    private String ldapBaseDn;

    @Value("${ldap.ldapFilter}")
    private String ldapFilter;

    @Value("${ldap.ldapOrgBaseDn}")
    private String ldapOrgBaseDn;

    @Value("${ldap.ldapOrgFilter}")
    private String ldapOrgFilter;

    @Value("${ldap.ldapUserBaseDn}")
    private String ldapUserBaseDn;

    @Value("${ldap.bindUser}")
    private String bindUser;

    @Value("${ldap.bindPasswd}")
    private String bindPasswd;

    public String getLdapUrl() {
        return ldapUrl;
    }

    public String getLdapBaseDn() {
        return ldapBaseDn;
    }

    public String getLdapFilter() {
        return ldapFilter;
    }

    public String getLdapOrgBaseDn() {
        return ldapOrgBaseDn;
    }

    public String getLdapOrgFilter() {
        return ldapOrgFilter;
    }

    public String getLdapUserBaseDn() {
        return ldapUserBaseDn;
    }

    public String getBindUser() {
        return bindUser;
    }

    public String getBindPasswd() {
        return bindPasswd;
    }
}