//package com.taikang.health.iams.supplier;
//
//import com.google.common.collect.Lists;
//import com.taikang.health.iams.bo.RoleModel;
//import com.taikang.health.iams.bo.UserModel;
//import com.taikang.health.iams.po.AutzRolePO;
//import com.taikang.health.iams.po.AutzUserPO;
//import com.taikang.health.iams.service.AuthResourceService;
//import com.taikang.health.iams.service.AuthRoleService;
//import com.taikang.health.iams.service.AuthUserService;
//import com.taikang.health.iams.service.AutzPermissionService;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class AuthSupplierAdmin implements AuthSupplier, AuthSystemSupplier, AuthMenuSupplier {
//
//    private static Logger logger = LoggerFactory.getLogger(AuthSupplierAdmin.class);
//    @Autowired
//    private AuthUserService userService;
//    @Autowired
//    private AuthResourceService resourceService;
//    @Autowired
//    private AuthRoleService roleService;
//    @Autowired
//    private AutzPermissionService permissionService;
//
//    @Override
//    public Authentication getByUserId(String userId) {
//        logger.info("根据用户ID获取用户认证");
//
//        Authentication authModel = new Authentication();
//        //用户信息
//        AutzUserPO userLocal = userService.selectByUserId(userId);
//        UserModel user = new UserModel();
//        if (userLocal != null) {
//            user.setId(userLocal.getId());
//            user.setUsername(userLocal.getUsername());
//            user.setPassword(userLocal.getPassword());
//            user.setName(userLocal.getUsername());
//            authModel.setUser(user);
//        }
//        //获取用户角色
//        List<AutzRolePO> roles = roleService.getRolesByUserId(userId);
//        Set<RoleModel> roleSet = new HashSet<>();
//        for (AutzRolePO roleLocal:roles) {
//            RoleModel role = new RoleModel();
//            role.setId(roleLocal.getId());
//            role.setName(roleLocal.getRoleCode());
//            roleSet.add(role);
//        }
//        authModel.setRoles(roleSet);
////
////        //获取用户权限信息 也就是具体的资源信息
////        List<AutzResourcePO> resourceLocals = resourceService.listResources(userId);
////        Set<PermissionModel> resourceSet = new HashSet<PermissionModel>();
////        for (AutzResourcePO resourceLocal : resourceLocals) {
////            PermissionModel permission = new PermissionModel();
////            permission.setId(resourceLocal.getId());
////            permission.setUri(resourceLocal.getUri());
////            permission.setModule(resourceLocal.getModule());
////
////            resourceSet.add(permission);
////        }
////        authModel.setPermissions(resourceSet);
////        //许可信息
////        List<AutzPermissionPO> permLocals = permissionService.listPermission(userId);
////        if (UtilCollection.isEmpty(permLocals)) {
////            return authModel;
////        }
////        Set<PermissionModel> permSet = new HashSet<PermissionModel>();
////        for(AutzPermissionPO permissionLocal : permLocals){
////            PermissionModel permission = new PermissionModel();
////            permission.setId(permissionLocal.getId());
////            permission.setModule(permissionLocal.getModule());
////            permission.addAction(permissionLocal.getOperation());
////            permSet.add(permission);
////        }
////        authModel.setPermissions(permSet);
//        return authModel;
//
//    }
//
//    @Override
//    public Authentication getByUsername(String userName) {
//        logger.info("根据用户名获取用户认证");
//        AutzUserPO userLocal = userService.selectByUsername(userName);
//        if (userLocal == null) {
//            return null;
//        }
//        return getByUserId(userLocal.getId());
//    }
//
//    @Override
//    public List<SystemModel> getUserSystem(String userId) {
//        logger.info("获取用户系统信息！");
//        if (null == userId) {
//            return Lists.newArrayList();
//        }
//        // 已注册系统
//        List<SystemModel> systems = Lists.newArrayList();
//        SystemModel system1 = new SystemModel().setId("1").setIcon("zmdi zmdi-shield-security")
//                .setBanner("/public/images/banner.png")
//                .setTheme("#16dac1")
//                .setName("hconnectadmin")
//                .setTitle("健康数据平台")
//                .setRemark("健康数据平台")
//                .setSortIndex(1)
//                .setStatus((byte) 1);
//        systems.add(system1);
//        return systems;
//    }
//
//    /**
//     * 用户的菜单信息  这里可以可以根据资源表type区分下  type=1 代表具体的资源菜单
//     */
//    @Override
//    public List<MenuModel> getUserMenuAsList(String userId) {
//
//        return Lists.newArrayList();
//    }
//
//}