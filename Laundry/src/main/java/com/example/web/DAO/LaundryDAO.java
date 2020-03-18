package com.example.web.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.web.model.LaundryDTO;
import com.example.web.util.CommonUtil;


public class LaundryDAO {
	private CommonUtil dbconn;
	public static LaundryDAO instance=null;
	public static LaundryDAO getInstance() {
		if (instance==null) {
			instance=new LaundryDAO();
		}
		return instance;
	}
	public LaundryDAO() {
		// TODO Auto-generated constructor stub
		try {
			if(dbconn==null) {
				dbconn=CommonUtil.getInstance();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal=Calendar.getInstance();
	public void CreateLaundry(String table) {
	Connection conn = null;
	ResultSet rset = null;
	PreparedStatement pstmt = null;
	StringBuilder sql = new StringBuilder();
	try {
		conn=dbconn.openmysql();
		sql = new StringBuilder();
		sql.append("create database if not exists Laundry;");
		pstmt=conn.prepareStatement(sql.toString());
		pstmt.execute();
		sql = new StringBuilder();
		sql.append("create table if not exists Laundry."+table+"(id int(255) not null AUTO_INCREMENT,dong varchar(10),hosu int(10) not null default 0,name varchar(20),phone varchar(15),work varchar(100),amount varchar(10),pay enum('선불','후불'),output enum('입고','출고'),date date default curdate(),primary key(id));");
		pstmt=conn.prepareStatement(sql.toString());
		pstmt.execute();
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		CommonUtil.sqlclose(conn, pstmt, rset);
	}
}
public ArrayList<LaundryDTO> list(String table,String keyField,String search){
	Connection conn = null;
	ResultSet rset = null;
	PreparedStatement pstmt = null;
	StringBuilder sql = new StringBuilder();
	java.util.Date currdate=new java.util.Date();
	ArrayList<LaundryDTO> item = new ArrayList<LaundryDTO>();
	try {
		conn = dbconn.openmysql();
		sql = new StringBuilder();
		/*
		if(keyWord=="") {
		*/
		sql.append("select * from Laundry."+table+" order by date desc");
		pstmt = conn.prepareStatement(sql.toString());
		/*
		}
		else {
	    sql.append("select * from Laundry."+table+" where dong like ? order by date desc");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, "%" + keyWord.replaceAll(" ", "%") + "%");
		}
		*/
		rset = pstmt.executeQuery();
		if(rset.next()) {
			do {
				/*
				ldto.setId(rset.getInt("id"));
				ldto.setDong(rset.getString("dong"));
				ldto.setHosu(rset.getString("hosu"));
				ldto.setName(rset.getString("name"));
				ldto.setPhone(rset.getString("phone"));
				ldto.setWork(rset.getString("work"));
				ldto.setAmount(rset.getString("amount"));
				ldto.setPay(rset.getString("pay"));
				ldto.setOutput(rset.getString("output"));
				ldto.setDate(rset.getDate("date"));
				*/
				
				int id=rset.getInt("id");
				String dong=rset.getString("dong");
				int hosu=rset.getInt("hosu");
				String name=rset.getString("name");
				String phone=rset.getString("phone");
				String work=rset.getString("work");
				String pay=rset.getString("pay");
			    String output=rset.getString("output");
			    Date date=rset.getDate("date");
			    String amount=rset.getString("amount");
			    String msg="";
			    cal.setTime(date);
			    cal.add(cal.DATE, +7);
			    
		
			    		if(rset.getString(keyField).contains(search)) {
			    	    	if(dbconn.check(currdate, cal.getTime())==1) {
			    			//if(dong.contains(search)||hosu.contains(search)||name.contains(search)||phone.contains(search)){
						   LaundryDTO ldto=new LaundryDTO(id, dong, hosu, name, phone, work, pay, output, date,msg, amount);
						   item.add(ldto);
						    }
			    	    	else {
				    			//if(dong.contains(search)||hosu.contains(search)||name.contains(search)||phone.contains(search)){
							   LaundryDTO ldto=new LaundryDTO(id, dong, hosu, name, phone, work, pay, output, date,msg, amount);
							   item.add(ldto);
							    }
			    	    	
			    	}
			   
			    
			}while(rset.next());
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		CommonUtil.sqlclose(conn, pstmt, rset);
	}
	return item;
	
}
public ArrayList<LaundryDTO> read(String table,int idx){
	Connection conn = null;
	ResultSet rset = null;
	PreparedStatement pstmt = null;
	StringBuilder sql = new StringBuilder();
	java.util.Date currdate=new java.util.Date();
	ArrayList<LaundryDTO> item = new ArrayList<LaundryDTO>();
	try {
		conn = dbconn.openmysql();
		sql = new StringBuilder();
		/*
		if(keyWord=="") {
		*/
		sql.append("select * from Laundry."+table+" order by date desc");
		pstmt = conn.prepareStatement(sql.toString());
		/*
		}
		else {
	    sql.append("select * from Laundry."+table+" where dong like ? order by date desc");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, "%" + keyWord.replaceAll(" ", "%") + "%");
		}
		*/
		rset = pstmt.executeQuery();
		if(rset.next()) {
			do {
				/*
				ldto.setId(rset.getInt("id"));
				ldto.setDong(rset.getString("dong"));
				ldto.setHosu(rset.getString("hosu"));
				ldto.setName(rset.getString("name"));
				ldto.setPhone(rset.getString("phone"));
				ldto.setWork(rset.getString("work"));
				ldto.setAmount(rset.getString("amount"));
				ldto.setPay(rset.getString("pay"));
				ldto.setOutput(rset.getString("output"));
				ldto.setDate(rset.getDate("date"));
				*/
				int id=rset.getInt("id");
				String dong=rset.getString("dong");
				int hosu=rset.getInt("hosu");
				String name=rset.getString("name");
				String phone=rset.getString("phone");
				String work=rset.getString("work");
				String pay=rset.getString("pay");
			    String output=rset.getString("output");
			    Date date=rset.getDate("date");
			    String amount=rset.getString("amount");
			   if(id==idx){	
			    LaundryDTO ldto=new LaundryDTO(id, dong, hosu, name, phone, work, pay, output, date,"", amount);
				item.add(ldto);
				}
			}while(rset.next());
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		CommonUtil.sqlclose(conn, pstmt, rset);
	}
	return item;
	
}

public int insert(String table,LaundryDTO ldto) {
	Connection conn = null;
	ResultSet rset = null;
	PreparedStatement pstmt = null;
	StringBuilder sql = new StringBuilder();
	int flag=0;
	try {
		conn = dbconn.openmysql();
		conn.setAutoCommit(false);
		sql = new StringBuilder();
		sql.append("insert into Laundry."+table+"(id,dong,hosu,name,phone,work,pay,output,amount) values (?,?,?,?,?, ?,?,?,?);");
		pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setInt(1, ldto.getId());
		pstmt.setString(2, dbconn.setUTFchar(ldto.getDong()));
		pstmt.setInt(3, ldto.getHosu());
		pstmt.setString(4, dbconn.setUTFchar(ldto.getName()));
		pstmt.setString(5, dbconn.setUTFchar(ldto.getPhone()));
		pstmt.setString(6, dbconn.setUTFchar(ldto.getWork()));
		pstmt.setString(7, dbconn.setUTFchar(ldto.getPay()));
		pstmt.setString(8, dbconn.setUTFchar(ldto.getOutput()));
		pstmt.setString(9, dbconn.setUTFchar(ldto.getAmount()));
		if (pstmt.executeUpdate() == 1) {
			conn.commit();
		flag=1;
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		CommonUtil.sqlclose(conn, pstmt, rset);
	}
	return flag;
}
public int update(String table,LaundryDTO ldto) {
	Connection conn = null;
	ResultSet rset = null;
	PreparedStatement pstmt = null;
	StringBuilder sql = new StringBuilder();
	int flag=0;
	try{
		conn = dbconn.openmysql();
		conn.setAutoCommit(false);
		sql = new StringBuilder();
		sql.append("update Laundry."+table+" set dong=?,hosu=?,name=?,phone=?,work=?,pay=?,output=?,amount=? where id=?;");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, dbconn.setUTFchar(ldto.getDong()));
		pstmt.setInt(2, ldto.getHosu());
		pstmt.setString(3, dbconn.setUTFchar(ldto.getName()));
		pstmt.setString(4, dbconn.setUTFchar(ldto.getPhone()));
		pstmt.setString(5, dbconn.setUTFchar(ldto.getWork()));
		pstmt.setString(6, dbconn.setUTFchar(ldto.getPay()));
		pstmt.setString(7, dbconn.setUTFchar(ldto.getOutput()));
		pstmt.setString(8, dbconn.setUTFchar(ldto.getAmount()));
		pstmt.setInt(9, ldto.getId());
		if (pstmt.executeUpdate() == 1) {
			conn.commit();
			flag=1;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		CommonUtil.sqlclose(conn, pstmt, rset);
	}
	return flag;
}
public void delete(String table,LaundryDTO ldto) {
	Connection conn = null;
	ResultSet rset = null;
	PreparedStatement pstmt = null;
	StringBuilder sql = new StringBuilder();
	try{
		conn = dbconn.openmysql();
		conn.setAutoCommit(false);
		sql = new StringBuilder();
		sql.append("delete from Laundry."+table+" where id=?;");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setInt(1, ldto.getId());
		if (pstmt.executeUpdate() == 1) {
			conn.commit();
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		CommonUtil.sqlclose(conn, pstmt, rset);
	}
}
}
