package com.util;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zyf on 2019/08/3.
 */
public class GetSqlSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetSqlSession.class);

    private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    /**
     * ��ȡSqlSession
     * @return sqlSession
     */
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = tl.get();
        if (sqlSession == null){
            sqlSession = GetSqlSessionFactory.getSqlSessionFactory().openSession();
            tl.set(sqlSession);
        }
        LOGGER.info("Get SqlSession hashCode : {}." , sqlSession.hashCode());
        return sqlSession;
    }

    /**
     *  �ύ����
     */
    public static void commit(){
        if (tl.get() != null){
            tl.get().commit();
            tl.get().close();
            tl.set(null);
            LOGGER.info("SqlSession commit.");
        }
    }

    /**
     *  �ع�����
     */
    public static void rollback(){
        if (tl.get() != null){
            tl.get().rollback();
            tl.get().close();
            tl.set(null);
            LOGGER.info("SqlSession rollback.");
        }
    }

}