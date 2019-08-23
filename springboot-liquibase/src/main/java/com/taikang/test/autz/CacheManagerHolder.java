package com.taikang.test.autz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class CacheManagerHolder {
    private static final Logger logger = LoggerFactory.getLogger(CacheManagerHolder.class);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new NamedThreadFactory("PRINT_CACHE", true));
    private ScheduledFuture<?> sendFuture = null;
    private int monitorInterval = 3600;
    private static CacheManagerHolder instance = null;
    @Autowired(
        required = false
    )
    private CacheManager cacheManager;
    private static CacheManager target;

    private CacheManagerHolder() {
    }

    public static synchronized CacheManagerHolder getInstance() {
        if (null == instance) {
            instance = new CacheManagerHolder();
        }

        return instance;
    }

    public static final CacheManager getManager() {
        return target;
    }

    @PostConstruct
    public void init() {
        if (this.cacheManager == null) {
            this.cacheManager = new ConcurrentMapCacheManager();
        }

        if (target == null && this.cacheManager != null) {
            target = this.cacheManager;
            logger.info("系统选择了缓存-" + this.cacheManager.getClass());
        }

        this.sendFuture = this.scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    CacheManagerHolder.this.send();
                } catch (Throwable var2) {
                    CacheManagerHolder.logger.error("Unexpected error occur at send statistic, cause: " + var2.getMessage(), var2);
                }

            }
        }, (long)this.monitorInterval, (long)this.monitorInterval, TimeUnit.MINUTES);
    }

    private void send() {
        logger.info("");
        this.cacheManager.getCacheNames().forEach((cacheName) -> {
            logger.info("[" + cacheName + "]-------->" + this.cacheManager.getCache(cacheName).getClass());
        });
        logger.info("");
    }

    public void destroy() {
        if (this.sendFuture != null) {
            logger.info("关闭定时打印缓存池功能");
            this.sendFuture.cancel(true);
        }

    }
}