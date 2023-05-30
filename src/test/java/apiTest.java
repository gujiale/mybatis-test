import com.example.io.Resources;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;
import com.example.session.SqlSessionFactoryBuilder;
import dao.IUserDao;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class apiTest {
    @Test
    public void test_MapperProxyFactory() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
            String res = iUserDao.queryUserInfoById("1001");
            System.out.println("测试结果："+res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
