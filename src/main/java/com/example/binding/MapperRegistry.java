package com.example.binding;

import cn.hutool.core.lang.ClassScanner;
import com.example.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*映射器注册机，负责管理所有的映射器，映射器管理所有的映射关系*/
public class MapperRegistry {
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();


    //只有请求addMapper的时候才会去注册，实际上是动态加载的
    public<T> void addMapper(Class<T> type) {
        //只添加接口类
        if(type.isInterface()) {
            //如果已加载，直接抛异常
            if(hasMapper(type)) {
                throw new RuntimeException("Type:"+type+" id already knowned to the MapperRegistry!!");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("error");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        }catch (Exception e) {
            throw new RuntimeException("can't create instance", e);
        }
    }


    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for(Class<?> mapperClass:mapperSet) {
            addMapper(mapperClass);
        }
    }

    public<T> boolean hasMapper(Class<T> key) { return knownMappers.containsKey(key);}


}
