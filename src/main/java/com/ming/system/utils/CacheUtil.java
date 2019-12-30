package com.ming.system.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存机制
 */
public class CacheUtil {
    private static CacheUtil cacheUtil;
    private static Map<String,Object> cacheMap;

    private CacheUtil(){
        cacheMap = new HashMap<String, Object>();
    }

    public static CacheUtil getInstance(){
        if (cacheUtil == null){
            cacheUtil = new CacheUtil();
        }
        return cacheUtil;
    }

    /**
     * 添加缓存
     * @param key
     * @param obj
     */
    public void addCacheData(String key,Object obj){
        cacheMap.put(key,obj);
    }

    /**
     * 取出缓存
     * @param key
     * @return
     */
    public Object getCacheData(String key){
        return cacheMap.get(key);
    }

    /**
     * 清楚缓存
     * @param key
     */
    public void removeCacheData(String key){
        cacheMap.remove(key);
    }
}