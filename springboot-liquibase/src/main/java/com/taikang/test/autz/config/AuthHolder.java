package com.taikang.test.autz.config;

import com.taikang.test.annotation.UtilTools.UtilWeb;
import com.taikang.test.autz.CacheManagerHolder;
import com.taikang.test.autz.bo.UserModel;
import com.taikang.test.autz.util.Caches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.Objects;

@Component
public final class AuthHolder {
    private static final Logger logger = LoggerFactory.getLogger(AuthHolder.class);
    private static Authenticator authenticator = null;
    private static AuthSupplier authSupplier = null;
//    private static AuthSystemSupplier authSystemSupplier = null;
//    private static AuthMenuSupplier authMenuSupplier = null;
//    @Autowired
//    private Authenticator thisAuthenticator;
//    @Autowired
//    private AuthSupplier thisAuthSupplier;
//    @Autowired
//    private AuthSystemSupplier thisAuthSystemSupplier;
//    @Autowired
//    private AuthMenuSupplier thisAuthMenuSupplier;

    public AuthHolder() {
    }

//    @PostConstruct
//    public void init() {
//        if (authenticator == null && this.thisAuthenticator != null) {
//            setAuthenticator(this.thisAuthenticator);
//            logger.info("Select Authenticator:" + this.thisAuthenticator.getClass());
//        }
////
//        if (authSupplier == null && this.thisAuthSupplier != null) {
//            setAuthSupplier(this.thisAuthSupplier);
//            logger.info("Select AuthSupplier :" + this.thisAuthSupplier.getClass());
//        }
//
//        if (authSystemSupplier == null && this.thisAuthSystemSupplier != null) {
//            setAuthSystemSupplier(this.thisAuthSystemSupplier);
//            logger.info("Select AuthSystemSupplier :" + this.thisAuthSystemSupplier.getClass());
//        }
//
//        if (authMenuSupplier == null && this.thisAuthMenuSupplier != null) {
//            setAuthMenuSupplier(this.thisAuthMenuSupplier);
//            logger.info("Select AuthMenuSupplier :" + this.thisAuthMenuSupplier.getClass());
//        }
//
//    }

    public static Authentication getByUsername(String username) {
        if (authSupplier == null) {
            throw new ValidationException("please check your spring context contains AuthSupplier or not?");
        } else {
            Objects.requireNonNull(username);
            Authentication auth = null;
            boolean inCache = false;
            Cache cache = CacheManagerHolder.getManager().getCache(Caches.auth.getCacheName());
            if (cache != null) {
                Cache.ValueWrapper wrapper = cache.get(username);
                if (wrapper != null) {
                    auth = (Authentication)wrapper.get();
                    inCache = true;
                    logger.debug("已从缓存中获取权限信息-" + username);
                }

                if (auth == null) {
                    auth = authSupplier.getByUsername(username);
                }

                if (!inCache && auth != null) {
                    cache.put(username, auth);
                }

                return auth;
            } else {
                throw new ValidationException("please check your application.yml contains spring.cache or not?");
            }
        }
    }

    public static Authentication getByUserId(String userId) {
        if (authSupplier == null) {
            throw new ValidationException("please check your spring context contains AuthSupplier or not?");
        } else {
            Objects.requireNonNull(userId);
            Authentication auth = null;
            boolean inCache = false;
            Cache cache = CacheManagerHolder.getManager().getCache(Caches.auth.getCacheName());
            if (cache != null) {
                Cache.ValueWrapper wrapper = cache.get(userId);
                if (wrapper != null) {
                    auth = (Authentication)wrapper.get();
                    inCache = true;
                    logger.debug("已从缓存中获取权限信息-" + userId);
                }

                if (auth == null) {
                    auth = authSupplier.getByUserId(userId);
                }

                if (!inCache && auth != null) {
                    cache.put(userId, auth);
                }

                return auth;
            } else {
                throw new ValidationException("please check your application.yml contains spring.cache or not?");
            }
        }
    }

    public static Authentication currentAuthentication() {
        HttpServletRequest request = UtilWeb.currentRequestInSpring();
        if (authenticator == null) {
            return null;
        } else {
//            UserModel userModel = authenticator.bindingUser(request);
            UserModel userModel = null;
            if (userModel == null) {
                return null;
            } else {
                String username = userModel.getUsername();
                return getByUsername(username);
            }
        }
    }

    public static UserModel currentUser() {
        Authentication auth = currentAuthentication();
        return auth == null ? null : auth.getUser();
    }

    public static String currentUserID() {
        UserModel user = currentUser();
        return user != null ? user.getId() : null;
    }

    public static UserModel currentUserAnyCase() {
        try {
            Authentication auth = currentAuthentication();
            return (UserModel)(auth == null ? new AnonymousUserModel() : auth.getUser());
        } catch (Exception var1) {
            return new AnonymousUserModel();
        }
    }

    public static void setAuthenticator(Authenticator authc) {
        authenticator = authc;
    }
//
    public static void setAuthSupplier(AuthSupplier authSupplier) {
        authSupplier = authSupplier;
    }
//
//    public static void setAuthSystemSupplier(AuthSystemSupplier authSystemSupplier) {
//        authSystemSupplier = authSystemSupplier;
//    }
//
//    public static void setAuthMenuSupplier(AuthMenuSupplier authMenuSupplier) {
//        authMenuSupplier = authMenuSupplier;
//    }
//
//    public static Authenticator getAuthenticator() {
//        if (authenticator == null) {
//            throw new ConfigException("check existed Authenticator bean in spring context?");
//        } else {
//            return authenticator;
//        }
//    }
//
//    public static AuthSupplier getAuthSupplier() {
//        if (authSupplier == null) {
//            throw new ConfigException("check existed AuthSupplier bean in spring context?");
//        } else {
//            return authSupplier;
//        }
//    }
//
//    public static AuthSystemSupplier getAuthSystemSupplier() {
//        if (authSystemSupplier == null) {
//            throw new ConfigException("check existed AuthSystemSupplier bean in spring context?");
//        } else {
//            return authSystemSupplier;
//        }
//    }
//
//    public static AuthMenuSupplier getAuthMenuSupplier() {
//        if (authMenuSupplier == null) {
//            throw new ConfigException("check existed AuthMenuSupplier bean in spring context?");
//        } else {
//            return authMenuSupplier;
//        }
//    }
}