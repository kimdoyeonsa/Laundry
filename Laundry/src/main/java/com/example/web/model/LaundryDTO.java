package com.example.web.model;


import java.sql.Timestamp;

public class LaundryDTO{
private int id;
private String dong;
private int hosu;
private String name;
private String phone;
private String work;
private String pay;
private String msg;
private String output;
private Timestamp date;
private String amount;
public LaundryDTO() {
	// TODO Auto-generated constructor stub
}

public LaundryDTO(int id, String dong, int hosu, String name, String phone, String work, String pay, String output, Timestamp date,String msg, String amount) {
    this.id = id;
    this.dong = dong;
    this.hosu = hosu;
    this.name = name;
    this.phone = phone;
    this.work = work;
    this.pay = pay;
    this.output = output;
    this.msg=msg;
    this.date = date;
    this.amount = amount;
}


public LaundryDTO(int id, String dong, int hosu, String name, String phone, String work, String pay, String output, String amount) {
    this.id = id;
    this.dong = dong;
    this.hosu = hosu;
    this.name = name;
    this.phone = phone;
    this.work = work;
    this.pay = pay;
    this.output = output;
    this.amount = amount;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public String getDong() {
	return dong;
}
public void setDong(String dong) {
	this.dong = dong;
}
public int getHosu() {
	return hosu;
}
public void setHosu(int hosu) {
	this.hosu = hosu;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getWork() {
	return work;
}
public void setWork(String work) {
	this.work = work;
}
public String getPay() {
	return pay;
}
public void setPay(String pay) {
	this.pay = pay;
}
public String getOutput() {
	return output;
}
public void setOutput(String output) {
	this.output = output;
}
public Timestamp getDate() {
	return date;
}
public void setDate(Timestamp date) {
	this.date = date;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}







}
