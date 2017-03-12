package com.alex.controller;

import com.alex.common.AppProp;
import com.alex.common.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caozhennan on 2017/3/11.
 */
@Controller
@RequestMapping("pri")
public class Primary {
    private static Logger log = LoggerFactory.getLogger(Primary.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AppProp appProp;

    @Autowired
    private CacheService cacheService;

    @RequestMapping("time")
    @ResponseBody
    public String time() {
        log.debug(request.getRequestURI());

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        cacheService.valueSet("lastAccess", time);

        return time;
    }

    @RequestMapping("lastAccess")
    @ResponseBody
    public String lastAccess() {
        log.debug(request.getRequestURI());

        String last = cacheService.valueGet("lastAccess");
        if (last == null) {
            last = "no data";
        }
        return last;
    }
}
