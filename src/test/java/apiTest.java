import com.example.binding.MapperRegistry;
import com.example.session.SqlSession;
import com.example.session.defaults.DefaultSessionFactory;
import dao.IUserDao;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class apiTest {
    @Test
    public void test_MapperProxyFactory() {
        //创建注册机
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("dao");
        //创建session
        DefaultSessionFactory defaultSessionFactory = new DefaultSessionFactory(mapperRegistry);
        SqlSession defaultSession = defaultSessionFactory.openSession();

        //建立mapper，创建相应的代理类
        IUserDao iUserDao = defaultSession.getMapper(IUserDao.class);
        System.out.println(iUserDao.queryUserName("1001"));
    }

    @Test
    public void test_proxy_class() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class}, (proxy, method, args) -> "你的操作被代理了！");
        String result = userDao.queryUserName("10001");
        System.out.println("测试结果：" + result);
    }
}
