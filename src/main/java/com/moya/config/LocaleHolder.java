package com.moya.config;

import org.springframework.stereotype.Component;

@Component
public class LocaleHolder {

    private static final ThreadLocal<String> currentLocale = new ThreadLocal<>();

    public static void setLocale(String locale) {
        currentLocale.set(locale);
    }

    public static String getLocale() {
        String locale = currentLocale.get();
        return locale != null ? locale : "ko";
    }

    public static void clear() {
        currentLocale.remove();
    }
}