package com.example.session.defaults;

import com.example.session.Configuration;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;

public class DefaultSessionFactory implements SqlSessionFactory {

    private final Configuration config;

    public DefaultSessionFactory(Configuration config) {
        this.config = config;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSession(this.config);
    }
}
