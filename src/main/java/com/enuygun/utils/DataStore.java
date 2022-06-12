package com.enuygun.utils;

import java.util.concurrent.ConcurrentHashMap;

public class DataStore {

    private static final ThreadLocal<ConcurrentHashMap<Object, Object>> map = ThreadLocal.withInitial(ConcurrentHashMap::new);

    public static synchronized void put(Object key, Object value) {
        if (key != null && value != null)  {
            map.get().put(key, value);
        }
    }

    public static synchronized Object remove(Object key) {
        if (key != null) {
            return map.get().remove(key);
        }
        return null;
    }

    public static synchronized Object get(Object key) {
        if (key != null) {
            return map.get().get(key);
        }
        return null;
    }
}
