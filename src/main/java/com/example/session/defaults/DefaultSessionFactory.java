package com.example.session.defaults;

import com.example.binding.MapperRegistry;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;

public class DefaultSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSession(this.mapperRegistry);
    }
}
