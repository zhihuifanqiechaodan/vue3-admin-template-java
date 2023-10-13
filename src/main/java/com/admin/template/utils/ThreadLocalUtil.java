package com.admin.template.utils;

import java.util.Map;

/**
 * @className: ThreadLocalUtil
 * @description:
 * @author: YangQian
 * @date: 2023/10/13 15:11
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<Map<String, String>> CONTEXT = new ThreadLocal<>();

    public static void setContext(Map<String, String> contextMap) {
        CONTEXT.set(contextMap);
    }

    public static String getContext(String key) {
        Map<String, String> contextMap = CONTEXT.get();
        if (contextMap != null) {
            return contextMap.get(key);
        }
        return null;
    }

    public static Integer getUserId(String key) {
        Map<String, String> contextMap = CONTEXT.get();
        if (contextMap != null) {
            return contextMap.get(key) == null ? null : Integer.parseInt(contextMap.get(key));
        }
        return null;
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
