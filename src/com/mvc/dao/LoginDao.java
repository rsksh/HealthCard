package com.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mvc.dbconnect.LoginDbConnect;
import com.mvc.dto.Logindto;

public class LoginDao {
private static final String CheckQuery="SELECT USERLOGIN, PASSWORDLOGIN FROM LOGIN_USER";
public String userauthenticate(Logindto loginbo1) throws SQLException{
String user=null;
String password=null;
Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;
String userNameDb=null;
String passwordNameDb=null;
user=loginbo1.getUser();
password=loginbo1.getPassword();
con=LoginDbConnect.getConnection();
ps=con.prepareStatement(CheckQuery);
rs=ps.executeQuery();
while(rs.next()){
userNameDb=rs.getString(1);
passwordNameDb=rs.getString(2);
if(user.equals(userNameDb) && password.equals(passwordNameDb))
{
   return "SUCCESS"; ////If the user entered values are already present in database, which means user has already registered so I will return SUCCESS message.
}//if close
}//while close
return "Not found record";
}
}