package com.taikang.health.iams.ldapauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Component
public class LdapAuthentication {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /***********************************************************************************
     * 建议： 将此部分连接LDAP的参数定义在配置文件中，便于后续发生变更时维护。                                     *
     ***********************************************************************************/

    @Autowired
    private ConstantProperties constantProperties;

    /***********************************************************************************
     * 附属：
     * @msgInfo 存储登录认证过程中的提示信息.
     * @userInfo 存储登录后获取的人员基本信息.
     */
    private String msgInfo;
    private Map<String, String> userInfo;
    public String getMsgInfo() {
        return msgInfo;
    }
    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
    public Map<String, String> getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
    }

    /***********************************************************************************
     * 获得一个LDAP连接句柄.
     * @param ldapUser
     * @param ldapPasswd
     * @return
     */
    private InitialLdapContext getDirContext(String ldapUser, String ldapPasswd) {
        InitialLdapContext ldapCtx = null;
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.ldap.version", "3");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, constantProperties.getLdapUrl());
            env.put(Context.SECURITY_PRINCIPAL, ldapUser);
            env.put(Context.SECURITY_CREDENTIALS, ldapPasswd);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PROTOCOL, "ssl");
            env.put(Context.REFERRAL, "follow");
            env.put("java.naming.ldap.factory.socket", this.getClass().getPackage().getName() + ".MySSLSocketFactory");
            //注意：上述参数值必须是“MySSLSocketFactory”类放置路径，要求将其与此类放置在同一个类包之下。
            ldapCtx = new InitialLdapContext(env, null);
        } catch (Exception e) {
            setMsgInfo("用户（" + ldapUser + "）认证失败，登录名或密码错误!");
            logger.info(e.getMessage(), e);
        }
//        catch (Exception e) {
//            setMsgInfo("系统错误，Ldap初始化连接时失败!");
//            logger.info(e.getMessage(), e);
//        }
        return ldapCtx;
    }


    /***********************************************************************************
     * 根据UID查询登录用户在Ldap上状态是否有效, 且是否在允许登录认证范围内。
     * @param ldapCtx
     * @param uid
     * @return
     */
    private boolean getUserDnByUid(InitialLdapContext ldapCtx, String uid) {
        // 定义变量标识是否允许用户完成登录.
        boolean allowLogin = false;
        try {
            // 查询控制.
            SearchControls ctrl = new SearchControls();
            ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
            // 此处控制在人员登录认证成功后，需要获取的人员属性值（有其它属性要求需与管理员确认）
            String[] lookupAttr = new String[]{
                    //登录名
                    "uid",
                    //姓名
                    "cn",
                    //OA组织机构代码
                    "tkoaorgcode",
                    //OA组织机构名称路径
                    "tkoaorgnamepath",
                    //状态（TRUE启用、FALSE停用）
                    "tkssovalid"
            };
            ctrl.setReturningAttributes(lookupAttr);

            // 执行查询.
            NamingEnumeration<SearchResult> en = ldapCtx.search(constantProperties.getLdapBaseDn(), constantProperties.getLdapFilter(), new String[]{uid}, ctrl);

            // 判断用户是否在允许登录认证范围内.
            if (en.hasMore()) {
                SearchResult result = en.next();
                Attributes attrs = result.getAttributes();

                // 判断人员状态信息. (tkssovalid	TRUE:启用、 FALSE:禁用)
                Attribute status = attrs.get("tkssovalid");
                String usageStatus = "TRUE";
                if (status != null && usageStatus.equalsIgnoreCase(status.get().toString())) {
                    allowLogin = true;
                    setMsgInfo("用户（" + uid + "）登录成功!");

                    /**
                     * 说明： 执行到此处，人员登录认证通过；（登录名和密码正确、人员状态有效、且在允许登录认证范围内）
                     *
                     * 需求： 应部分应用系统的要求，在登录认证完成后需要抓取用户部分信息，比如：中文名、手机号码等等，可以考虑在此处操作。
                     *
                     */
                    // 获取人员属性
                    NamingEnumeration<? extends Attribute> a = attrs.getAll();
                    this.userInfo = new HashMap<>(16);
                    while (a.hasMore()) {
                        Attribute n = a.next();
                        userInfo.put(n.getID(), (String) n.get());
                    }

                } else {
                    setMsgInfo("用户（" + uid + "）状态无效!");
                }
            } else {
                setMsgInfo("用户（" + uid + "）不在允许登录认证范围内!");
            }
        } catch (NamingException e) {
            setMsgInfo("系统错误，ldap查询用户信息时失败!");
            logger.info(e.getMessage(), e);
        }
        return allowLogin;
    }


    /***********************************************************************************
     * 主方法 : 登录验证用户名和口令.
     * @param uid
     * @param password
     * @return
     */
    public boolean login(String uid, String password) {
        boolean loginSuccess = false;
        InitialLdapContext ldapCtx = null;
        try {
            /** 连接Ldap验证登录名和密码是否正确。
             * 拼接登录用户dn.
             * */
            String ldapUserDn = "uid=" + uid + "," + constantProperties.getLdapBaseDn();

            ldapCtx = this.getDirContext(ldapUserDn, password);
            if (ldapCtx != null) {
                /** 执行到此步骤时，说明登录名和密码正确；**/
                /** 但仍需继续判断用户状态是否有效，以及用户是否在允许登录认证范围内；**/
                loginSuccess = this.getUserDnByUid(ldapCtx, uid);
            }
        } catch (Exception e) {
            logger.info(e.fillInStackTrace().toString(), e);
        } finally {
            // 注意：执行完成后一定要关闭LDAP连接
            if (ldapCtx != null) {
                try {
                    ldapCtx.close();
                } catch (Exception e) {
                    logger.info(e.fillInStackTrace().toString(), e);
                }
            }
        }
        return loginSuccess;
    }




}