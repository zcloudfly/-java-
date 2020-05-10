package com.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户实体类
 * @author ZYF 20190807
 *
 */
public class Users implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int sex;
	private int bornYear;//出生年
	private int tripDistance;//旅行路程
	private int tripTime;//旅行时间 天
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getBornYear() {
		return bornYear;
	}
	public void setBornYear(int bornYear) {
		this.bornYear = bornYear;
	}
	public int getTripDistance() {
		return tripDistance;
	}
	public void setTripDistance(int tripDistance) {
		this.tripDistance = tripDistance;
	}
	public int getTripTime() {
		return tripTime;
	}
	public void setTripTime(int tripTime) {
		this.tripTime = tripTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", sex=" + sex + ", bornYear=" + bornYear + ", tripDistance=" + tripDistance
				+ ", tripTime=" + tripTime + "]";
	}
	//折线数据
	public int[] lineData(String stype){
		int[] line=new int[2];
		if(stype.equals("BORNYEAR")){
		    line[0]=this.bornYear;
		}else if(stype.equals("TRIPDISTANCE")){
			line[0]=this.tripDistance;
		}else if(stype.equals("TRIPTIME")){
			line[0]=this.tripTime;
		}
		line[1]=this.count;
		return line;
	}
	//pie数据/ column
	public Map pieData(String stype,String spans){
		    int span=Integer.valueOf(spans);
			Map map=new HashMap();
			if(stype.equals("BORNYEAR")){
				map.put("name", String.valueOf(this.bornYear)+'-'+(this.bornYear+span));
		    }else if(stype.equals("TRIPDISTANCE")){
				map.put("name", String.valueOf(this.tripDistance)+'-'+(this.tripDistance+span));
			}else if(stype.equals("TRIPTIME")){
				map.put("name", String.valueOf(this.tripTime)+'-'+(this.tripTime+span));
			}
			map.put("y",this.count);
			return map;
		}
	

}
