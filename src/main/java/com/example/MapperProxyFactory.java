package com.example;

import java.lang.reflect.Proxy;
import java.util.Map;

public class MapperProxyFactory<T> {
    Object object;

    private final Class<T> mapperInterface;


    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        final GuMapperProxy<T> guMapperProxy = new GuMapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, guMapperProxy);
    }
}
