package com.example.session.defaults;

import com.example.binding.MapperRegistry;
import com.example.session.SqlSession;

public class DefaultSession implements SqlSession {

    private final MapperRegistry mapperRegistry;
    public DefaultSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {

        return (T) ("你执行了selectOne，SQL："+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你执行了selectOne，SQL："+statement + "入参"+parameter.toString());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return this.mapperRegistry.getMapper(type, this);
    }

    @Override
    public <T> T selectAll() {
        return (T) ("你执行了selectAll");
    }
}
