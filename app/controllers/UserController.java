package controllers;

//import controllers.security.AuthUser;
import controllers.security.Secured;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.*;
import javax.inject.Inject;
import java.util.*;

import play.api.Environment;

import views.html.*;
import models.*;
import models.users.User;

import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;

// File upload and image editing dependencies
//import org.im4java.core.ConvertCmd;
//import org.im4java.core.IMOperation;
//.
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
        return ok(listproduct.render(listProductForm, User.getUserById(session().get("email"))));
    }

    @Transactional
    public Result productSubmit() {
        // Bind form instance to the values submitted from the form
        Form<Product> listProductForm = formFactory.form(Product.class).bindFromRequest();
        // Check for errors
        // Uses the validate method defined in the Login class
        if (listProductForm.hasErrors()) {
            // If errors, show the form again
            return badRequest(listproduct.render(listProductForm, getUserFromSession()));
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

	flash("success", "Product " + p.getName() + " has been created");
            return redirect(controllers.routes.UserController.profile());  
    }
}
