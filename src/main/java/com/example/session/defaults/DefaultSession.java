package com.example.session.defaults;

import com.example.mapping.MappedStatement;
import com.example.session.Configuration;
import com.example.session.SqlSession;

public class DefaultSession implements SqlSession {

    private final Configuration config;
    public DefaultSession(Configuration config) {
        this.config = config;
    }

    @Override
    public <T> T selectOne(String statement) {

        return (T) ("你执行了selectOne，SQL："+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        System.out.println(statement);
        MappedStatement mappedStatement = config.getMappedStatement(statement);
        return (T) ("你的操作被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return this.config.getMapper(type, this);
    }

    @Override
    public <T> T selectAll() {
        return (T) ("你执行了selectAll");
    }

    @Override
    public Configuration getConfiguration() {
        return config;
    }
}