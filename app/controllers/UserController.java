package controllers;

//import controllers.security.AuthUser;
import controllers.security.Secured;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.*;
import javax.inject.Inject;
import java.util.*;


import java.util.ArrayList;
import java.util.List;
import play.api.Environment;

import views.html.*;
import models.*;
import models.users.User;

import views.html.*;


@Security.Authenticated(Secured.class)
// Authorise user (check if logged in)
//*@With(AuthUser.class)*@
public class UserController extends Controller {
// Declare a private FormFactory instance
    private FormFactory formFactory;

    /** http://stackoverflow.com/a/37024198 **/
    private Environment env;

    //  Inject an instance of FormFactory it into the controller via its constructor
    @Inject
    public UserController(Environment e, FormFactory f) {
        this.formFactory = f;
        this.env = e;
    }

    // Method returns the logged in user (or null)
    private User getUserFromSession() {
        return User.getUserById(session().get("email"));
    }

    public Result profile(){
        return ok(profile.render(getUserFromSession()));
    }

    public Result listProduct(){
	Form<Product> listProductForm = formFactory.form(Product.class);
        // Render the Add Product View, passing the form object
        return ok(listproduct.render(listProductForm, User.getUserById(session().get("email")), env));
    }

    @Transactional
    public Result productSubmit() {
    //    String saveImageMsg;
        // Bind form instance to the values submitted from the form
        Form<Product> listProductForm = formFactory.form(Product.class).bindFromRequest();
        // Check for errors
        // Uses the validate method defined in the Login class
        if (listProductForm.hasErrors()) {
            // If errors, show the form again
            return badRequest(listproduct.render(listProductForm, getUserFromSession(), env));
        }
	Product p = listProductForm.get();

         if (p.getId() == null) {
            // Save to the database via Ebean (remember Product extends Model)
            p.save();
        }
        // Product already exists so update
        else if (p.getId() != null) {
            p.update();
        }

        p.setSeller(getUserFromSession().getUsername());

       // MultipartFormData data = request().body().asMultipartFormData();
        //FilePart image = data.getFile("upload");

        // Save the image file
       // saveImageMsg = saveFile(p.getId(), image);

        // Set a success message in temporary flash
        // for display in return view

	flash("success" + p.getName() + " has been submitted");
            return redirect(controllers.routes.UserController.profile());  
    }
}
