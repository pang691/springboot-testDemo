package com.my.test.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@Configuration
@Import(FdfsClientConfig.class)//解决FastFileStorageClient无法注入的问题
// 解决jmx重复注册bean的问题
//注意：@EnableMBeanExport解决问题JMX重复注册问题,
// 不要再配置 spring.jmx.enabled=false，以免影响SpringBoot默认的JMX监控
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
public class FdfsConfiguration {

}
