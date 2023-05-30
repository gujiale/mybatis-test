package com.example.binding;

import com.example.session.SqlSession;

import java.lang.reflect.Proxy;

public class MapperProxyFactory<T> {
    Object object;

    private final Class<T> mapperInterface;


    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> guMapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, guMapperProxy);
    }
}
