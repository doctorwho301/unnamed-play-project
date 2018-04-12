package models.users;


import models.*;
import java.util.*;

import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.Model;

@Entity

@Table(name = "User")

@DiscriminatorValue("admin")

public class Admin extends User
{

	

	public Admin()
	{
	}

	public Admin(String role,String email,String name,String password,String address1, String address2, String city)
	{
		super(role,email,name,password,address1,address2,city);
	}
	//Methods
	

}
