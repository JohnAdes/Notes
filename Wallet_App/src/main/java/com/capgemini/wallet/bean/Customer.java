package com.capgemini.wallet.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

public class Customer {

	private long Accountid;
	private String username,pass,fname,lname;
	private double balance;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate datecreated;
	
	@JsonFormat(pattern = "HH:mm:ss")
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private LocalTime timecreated;
	
	
//	public Customer(long accountid, String username, String pass, String fname, String lname, double balance,
//			LocalDate datecreated, LocalTime timecreated) {
//	
//		Accountid = accountid;
//		this.username = username;
//		this.pass = pass;
//		this.fname = fname;
//		this.lname = lname;
//		this.balance = balance;
//		this.datecreated = datecreated;
//		this.timecreated = timecreated;
//	}

	public long getAccountid() {
		return Accountid;
	}

	public void setAccountid(long accountid) {
		Accountid = accountid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDate getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(LocalDate datecreated) {
		this.datecreated = datecreated;
	}

	public LocalTime getTimecreated() {
		return timecreated;
	}

	public void setTimecreated(LocalTime timecreated) {
		this.timecreated = timecreated;
	}

	
	
	
	
	
	
	
}