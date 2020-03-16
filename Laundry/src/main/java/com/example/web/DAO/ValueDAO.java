package com.example.web.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.web.util.CommonUtil;

public class ValueDAO {
	private CommonUtil dbconn;
	public static ValueDAO instance=null;
	public static ValueDAO getInstance() {
		if (instance==null) {
			instance=new ValueDAO();
		}
		return instance;
	}
	public ValueDAO() {
		// TODO Auto-generated constructor stub
		try {
			if(dbconn==null) {
				dbconn=CommonUtil.getInstance();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int select_int_nextval(String database, String table, String no) {

		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		int tno = 1;
		try {
			conn = dbconn.openmysql();
			// sql.append("use "+database);
			// pstmt = conn.prepareStatement(sql.toString());
			// rset = pstmt.executeQuery();
			sql = new StringBuilder();
			sql.append("select ifnull(max(" + no + ")+1, 1) from " + database + "." + table + " for update");
			pstmt = conn.prepareStatement(sql.toString());

			rset = pstmt.executeQuery();
			if (rset.next()) {
				tno = rset.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.sqlclose(conn, pstmt, rset);

		}
		return tno;
	}

}
