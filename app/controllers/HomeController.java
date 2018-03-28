package controllers;

import controllers.security.*;
import play.api.Environment;
import play.mvc.*;
import play.data.*;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

import models.users.*;
import models.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    
    private Environment env;

    // Declare a private FormFactory instance
    private FormFactory formFactory;

    //  Inject an instance of FormFactory it into the controller via its constructor
    @Inject
    public HomeController(Environment e, FormFactory f) {
        this.formFactory = f;
        this.env = e;
    }

    private User getUserFromSession(){
        return User.getUserById(session().get("email"));
    }

    public Result index() {
        return ok(index.render(getUserFromSession()));
    }


    /**
     *  Sign Up Methods
     *
     */
    public Result signUp() {
        Form<User> signUpForm = formFactory.form(User.class);

        return ok(signup.render(signUpForm, User.getUserById(session().get("email"))));
    }

    public Result signupSubmit() {

        Form<User> signUpForm = formFactory.form(User.class).bindFromRequest();

        // Check for errors
        if(signUpForm.hasErrors()) {
            // Display the form again
            return badRequest(signup.render(signUpForm, getUserFromSession()));
        }

        User u = signUpForm.get();

        if(u.getEmail().equals(User.find.ref(u.getEmail()))){
            flash("Email already exists");
            return redirect(routes.HomeController.signUp());

        }

        u.setRole("user");
        u.save();

        // Set a success message in temporary flash
        // for display in return view
        flash("success, Account Created");

        // Redirect to home
        return redirect(routes.HomeController.index());
    }

	public Result products(Long catId,String filter) {

    	    // Get list of all categories in ascending order
     	   List<Category> categoriesList = Category.findAll();
     	   List<Product> productsList = new ArrayList<Product>();
	
      		  if (catId == 0) {
     	   	    // Get list of all categories in ascending order
     	  	     productsList = Product.findAll(filter);
     		   }
     	 	  else {
     	 	      // Get products for selected category
     	 	      // Find category first then extract products for that cat.
      	  	    productsList = Product.findFilter(catId,filter);
     	  	 }

      	  return ok(products.render(catId,filter,productsList, categoriesList, getUserFromSession(), env));
   	 }

}
