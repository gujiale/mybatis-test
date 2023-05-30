package com.example;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class GuMapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;

    private Map<String, String> sqlSession;
    private final Class<T> mapperInterface;

    public GuMapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, objects);
        } else {
            return "你的被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
