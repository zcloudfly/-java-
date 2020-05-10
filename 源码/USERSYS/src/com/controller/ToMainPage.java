package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.GetSqlSession;
import com.util.GetSqlSessionFactory;
/**
 * @name ToMainPage
 * @brief 跳转主页
 * @author Administrator
 *
 */
@WebServlet(name = "mainPage", value = "/mainPage")
public class ToMainPage extends HttpServlet{
	
		private static final long serialVersionUID = 1L;
		private static final Logger LOGGER = LoggerFactory.getLogger(GetSqlSessionFactory.class);

		    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        this.doGet(request, response);
		    }

		    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        try {
		                   
		        	request.setAttribute("span", 10);
		        	//response.sendRedirect("jsp/userHome.jsp?span=10");
		        	request.getRequestDispatcher("jsp/userHome.jsp").forward(request, response);
		        } catch (Exception e){
		            GetSqlSession.rollback();
		            LOGGER.error("insert error", e);
		        } finally {
		            GetSqlSession.commit();
		        }
		    }
	}
