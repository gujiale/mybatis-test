package com.example.session;

import com.example.builder.xml.XmlConfigBuilder;
import com.example.session.defaults.DefaultSessionFactory;

import java.io.Reader;

/**
 * @author 小傅哥，微信：fustack
 * @description 构建SqlSessionFactory的工厂
 * @github https://github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSessionFactory(config);
    }

}