package mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.Person;
import util.SqlSessionFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PersonMapperTest {

    @Test
    public void test01() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        List<Person> personList = personMapper.readAll();

        for (Person person : personList) {
            System.out.println(person);
        }

    }

    @Test
    public void test02() throws IOException {
        //1. 加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("Mybatis-config.xml");

        // 2. 创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //3. 从工厂中获取连接
        SqlSession sqlSession = factory.openSession();

        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        List<Person> personList = personMapper.readAll();

        for (Person person : personList) {
            System.out.println(person);
        }

    }


    @Test
    public void testCreatePerson() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        PersonMapper pd = sqlSession.getMapper(PersonMapper.class);
        Person p = new Person();
        p.setName("三上悠亚");
        p.setSex("女");
        p.setAge(18);
        p.setAddress("日本");
        p.setTel("unknow");
        p.setEmail("ph@ph.com");
        pd.createPerson(p);
        sqlSession.commit(); //提交事务
        System.out.println("添加成功！");
    }

    @Test
    public void testDeletePerson() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        PersonMapper pd = sqlSession.getMapper(PersonMapper.class);
        pd.deletePerson(3);
        sqlSession.commit(); //提交事务
        System.out.println("删除成功！");

    }


    @Test
    public void testUpdatePerson() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        PersonMapper pd = sqlSession.getMapper(PersonMapper.class);
        Person p = new Person();
        p.setId(3);
        p.setName("三上悠亚");
        p.setSex("女");
        p.setAge(18);
        p.setAddress("日本");
        p.setTel("unknow");
        p.setEmail("ph@ph.com");
        pd.updatePerson(p);
        sqlSession.commit(); //提交事务
        System.out.println("修改成功！");

    }

    @Test
    public void testReadAll() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        List<Person> personList = personMapper.readAll();

        for (Person person : personList) {
            System.out.println(person);
        }
    }

}
