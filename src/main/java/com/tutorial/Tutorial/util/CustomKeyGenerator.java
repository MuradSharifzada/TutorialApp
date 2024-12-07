package com.tutorial.Tutorial.util;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object...params) {
        return target
                .getClass()
                .getSimpleName()
                + "_" + method.getName() + "_" + Arrays.toString(params);
    }
}
