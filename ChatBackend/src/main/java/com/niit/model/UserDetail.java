package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserDetail {

	@Id
	String loginname;
	
	String password;
	String userName;
	String emailId;
	int mobileNo;
	String address;
	
	
}
