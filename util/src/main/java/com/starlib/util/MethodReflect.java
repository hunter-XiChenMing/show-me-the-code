package com.starlib.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodReflect<T> {
    private final Class<T> objClass;
    private Map<String, Method> setMethods;
    private Map<String, Method> getMethods;
    private final static Pattern SET_PATTERN = Pattern.compile("set(\\w+)");
    private final static Pattern GET_PATTERN = Pattern.compile("get(\\w+)");


    public MethodReflect(Class<T> objClass) {
        this.objClass = objClass;
        this.setMethods = new HashMap<String, Method>();
        this.getMethods = new HashMap<String, Method>();
        initMethods(setMethods, SET_PATTERN);
        initMethods(getMethods, GET_PATTERN);
    }

    public T copyBeanAttributes(T obj, Map<String, Object> param) {
        param.forEach((key, value) -> {
            setMethodValue(obj, key, value);
        });
        return obj;
    }

    private void setMethodValue(T obj, String key, Object value) {
        Method method = this.setMethods.get(key);
        try {
            method.invoke(obj, value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void initMethods(Map<String, Method> methodsMap, Pattern pattern) {
        Method[] methods = this.objClass.getMethods();
        for (Method method : methods) {
            Matcher matcher = pattern.matcher(method.getName());
            if (matcher.matches()) {
                String keyMethod = method.getName().toLowerCase(Locale.US);
                methodsMap.put(keyMethod, method);
            }
        }
    }
}