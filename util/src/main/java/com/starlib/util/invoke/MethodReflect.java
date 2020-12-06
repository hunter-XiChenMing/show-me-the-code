package com.starlib.util.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用反射 操作对象属性
 * @param <T>
 */
public class MethodReflect<T> {
    private final Class<T> objClass;
    private final Map<String, Method> setMethods;
    private final Map<String, Method> getMethods;
    private final static Pattern SET_PATTERN = Pattern.compile("set(\\w+)");
    private final static Pattern GET_PATTERN = Pattern.compile("get(\\w+)");


    public MethodReflect(Class<T> objClass) {
        this.objClass = objClass;
        this.setMethods = new HashMap<>();
        this.getMethods = new HashMap<>();
        initMethods(setMethods, SET_PATTERN);
        initMethods(getMethods, GET_PATTERN);
    }

    public void copyBeanAttributes(T obj, Map<String, Object> param) {
        param.forEach((key, value) -> setMethodValue(obj, key, value));
    }

    private void setMethodValue(T obj, String key, Object value) {
        String methodName = ("set".concat(key)).toLowerCase(Locale.US);
        if (this.setMethods.containsKey(methodName)) {
            Method method = this.setMethods.get(methodName);
            try {
                method.invoke(obj, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
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