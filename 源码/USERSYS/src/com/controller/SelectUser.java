package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.entity.Users;
import com.entity.UtilEntity;
import com.sun.xml.internal.ws.util.StringUtils;
import com.util.GetSqlSession;
import com.util.GetSqlSessionFactory;
/**
 * @name SelectUser
 * @brief 查询
 * @author Administrator
 * 
 */
@WebServlet(name = "SelectUsers", value = "/SelectUsers")
public class SelectUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(GetSqlSessionFactory.class);

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        this.doGet(request, response);
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        PrintWriter out = response.getWriter();
	       // HttpSession session = request.getSession();
	        //session.removeAttribute("span");
	        try {
	        	Map inputMap=new HashMap();
	        	String begin = request.getParameter("begin");
	        	String end = request.getParameter("end");
	        	String selectType = request.getParameter("selectType");
	        	String span=request.getParameter("span");
	        	inputMap.put("end", end);
		        inputMap.put("begin", begin);
		        inputMap.put("sType", selectType);
	        	
	        	SqlSession sqlSession = GetSqlSession.getSqlSession();
	        	
	        	UtilEntity util= (UtilEntity) sqlSession.selectList("users.sql.getColMaxAndMin",inputMap).get(0);
		        if(null==span||"".equals(span)){
		        	span=(util.getMax()-util.getMin())/10+"";
		        	if(begin!=null&&!begin.equals("")&&end!=null&&!end.equals("")){
		        		span=(Integer.parseInt(end)-Integer.parseInt(begin))/10+"";
		        	}
		        	if(begin!=null&&!begin.equals("")&&(end==null||end.equals(""))){
		        		span=(util.getMax()-Integer.parseInt(begin))/10+"";
		        	}
		        	if((begin==null||begin.equals(""))&&end!=null&&!end.equals("")){
		        		span=(Integer.parseInt(end)-util.getMin())/10+"";
		        	}
		        	
		        }
		       // session.setAttribute("span", span);
	        	String sql = sql(selectType,span,begin,end,util.getMax(),util.getMin());
		        inputMap.put("sql", sql);
		        List<Users> selectList = sqlSession.selectList("users.sql.getUsersDataByWhere",inputMap);	            
		        List data=new ArrayList<>();
	            String type = request.getParameter("type");
	            //折线图
	            if("line".equals(type)){
		            for (Users o : selectList) {
		            	data.add(o.lineData(selectType));
					}
		        //饼图
	            }else if("pie".equals(type)){
	            	for (Users o : selectList) {
	            		data.add(o.pieData(selectType,span));
					}
	            //柱状图
	            }else if("column".equals(type)){
	            	for (Users o : selectList) {
	            		data.add(o.pieData(selectType,span));
					}
	            }else{
	            	//表格
	            	Map resultMap=new HashMap();
	            	int draw = Integer.parseInt(request.getParameter("draw"));
	            	int start = Integer.parseInt(request.getParameter("start"));
	            	int length = Integer.parseInt(request.getParameter("length"));
		            inputMap.put("start", start);
		            inputMap.put("length", length);
		            int totalCount = sqlSession.selectOne("getUsersCountByWhere", inputMap);
		            data = sqlSession.selectList("users.sql.getUsersByWhere", inputMap);
		            resultMap.put("recordsFiltered", totalCount);
	            	resultMap.put("recordsTotal", totalCount);
	            	resultMap.put("draw", draw);
	            	resultMap.put("data", data);
	            	Object json =JSON.toJSON(resultMap);
	            	out.print(json);
	            	return ;
	            }
	            Object json = JSON.toJSON(data);
	            out.print(json);
	        } catch (Exception e){
	            GetSqlSession.rollback();
	            LOGGER.error("select error", e);
	            out.print("error");
	        } finally {
	        	out.close();
	        }
	    }
  /**
   * 拼接sql
   * @param selectType
   * @param span
   * @param begin
   * @param end
   * @param max
   * @param min
   * @return
   */
  private String sql(String selectType,String span,String begin,String end,int max,int min) {
	  StringBuilder sql = new StringBuilder();
	  if(null==begin||"".endsWith(begin)){
		  begin=String.valueOf(min);
	  }
	  if(null==end||"".endsWith(end)){
		  end=String.valueOf(max);
	  }
	  int ibegin=Integer.parseInt(begin);
	  int iend=Integer.parseInt(end);
	  int ispan=Integer.parseInt(span);
	  int i=(Integer.parseInt(end)-Integer.parseInt(begin))/Integer.parseInt(span);
	  String format="when %s>=%s and %s>=%s then %s ";
	  for(int j=0;j<=i;j++){
		  sql.append(String.format(format, selectType,ibegin,ibegin+ispan,selectType,ibegin));
		  ibegin=ibegin+ispan;
	  }
	  return sql.toString();
  }

}
