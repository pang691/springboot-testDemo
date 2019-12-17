//package com.taikang.health.iams.supplier;
//
//import com.taikang.health.iams.service.OAuth2Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OAuth2SupplierOnline implements OAuth2Supplier {
//
//    @Autowired
//    private OAuth2Service oauth2Service ;
//
//
//    @Override
//    public AccessToken getAccessTokenModel(String accessToken) {
//        return oauth2Service.getAccessToken(accessToken);
//    }
//
//}