package models.users;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import play.Logger;

// Product Entity managed by the ORM
@Entity

public class User extends Model {


    @Id
    private String email;

    @Constraints.Required
    private String name;

    private String username;


    @Constraints.Required
    private String password;

    @Constraints.Required
    private String role;

   
    @Constraints.Required
    private String address1;

    @Constraints.Required
    private String address2;

    private String city;

    public User()
    {

    }
    //Overloaded Constructors

    public User(String email,String name,String password,String role, String address1, String address2, String city)

    {

        this.role = role;
        this.email = email;
        this.name = name;
        this.username = name;
        this.password = password;
        this.address1 = address1;
        this.address2 = address2;
		this.city = city;
        this.password = password;
    }

    //Generic query helper for entity User with unique id String
    public static Finder<String,User> find = new Finder<String,User>(User.class);

    // Find all Users in the database
    public static List<User> findAll() {
        return User.find.all();
    }

    // Static method to authenticate based on username and password
    // Returns user object if found, otherwise NULL
    public	static User authenticate(String email, String password) {
        // If found return the user object with matching username and password
        return find.where().eq("email", email).eq("password", password).findUnique();
    }

    // Check if a user is logged in (by id - email address)
    public static User getUserById(String id) {
        if (id == null)
            return null;
        else
            // Find user by id and return object
            return find.byId(id);
    }

    public String getEmail()
    {
        return email;
    }
    public String getRole()
    {
        return role;
    }
    public String getName()
    {
        return name;
    }
    public String getUsername()
    {
	return username;
    }
    public String getPassword()
    {
        return password;
    }
    public String getAddress1()
    {
	return address1;
    }
    public String getAddress2()
    {
	return address2;
    }
    public String getCity()
    {
	return city;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setUsername(String username)
    {
	this.username = username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setAddress1(String address1)
    {
	this.address1 = address1;
    }
    public void setAddress2(String address2)
    {
	this.address2 = address2;
    }
    public void setCity(String city)
    {
	this.city = city;
    }
}
