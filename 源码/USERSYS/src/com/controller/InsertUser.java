package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.entity.Users;
import com.util.GetSqlSession;
import com.util.GetSqlSessionFactory;
/**
 * @name InsertUser
 * @brief 插入用户信息
 * @author Administrator
 * 
 */
@WebServlet(name = "InsertUsersServlet", value = "/insertUsers")
public class InsertUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(GetSqlSessionFactory.class);

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        this.doGet(request, response);
	    }
        
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        File file = new File("d://user.txt");///user_show_sys/config/user.txt
	        PrintWriter out = response.getWriter();
	        try {
	        	   FileReader reader = new FileReader(file);
	               BufferedReader br = new BufferedReader(reader); 
	               String line=null;
	               int count=0;
	               while ((line = br.readLine()) != null) {
	            	   count++;
	                   // 分割数据
	            	   String[] split = line.split(";");
	            		Users users = new Users();
	        	        users.setId(Integer.parseInt(split[0]));
	        	        users.setSex(Integer.parseInt(split[1]));
	        	        users.setBornYear(Integer.parseInt(split[2]));
	        	        users.setTripDistance(Integer.parseInt(split[3]));
	        	        users.setTripTime(Integer.parseInt(split[4]));
	        	        SqlSession sqlSession = GetSqlSession.getSqlSession();
	        	        sqlSession.insert("users.sql.insertUsers", users);
	        	        LOGGER.info(  users.getId()+",count:"+count);
	                   
	               }
	              
	               out.print("yes");
	        } catch (Exception e){
	            GetSqlSession.rollback();
	            LOGGER.error("insert error", e);
	            out.print("no");
	        } finally {
	            GetSqlSession.commit();
	        }
	    }
}
