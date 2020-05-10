package com.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zyf on 2019/08/3.
 *
 * ʹ�õ���ģʽ��ȡSqlSessionFactory
 */
public class GetSqlSessionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetSqlSessionFactory.class);

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * ˽�й��췽����ʹ���಻�ɴ����¶���
     */
    private GetSqlSessionFactory(){

    }

    /**
     * ʹ��ͬ����
     * @return sql session ����
     */
    synchronized public static SqlSessionFactory getSqlSessionFactory(){
        if (sqlSessionFactory == null){
            //��ȡ��Դ�ļ���
            String resorce = "mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resorce);
            } catch (IOException e) {
                LOGGER.error("Get Resource Error:", e);
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;
    }

}
