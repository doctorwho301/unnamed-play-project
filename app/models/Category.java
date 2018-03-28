package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

// Product Entity managed by the ORM
@Entity
public class Category extends Model {

    // Properties
    // Annotate id as the primary key
    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products;




    // Default constructor
    public Category() {

    }

    public Category(Long id, String name, List<Product> products) {
        this.setId(id);
        this.setName(name);
        this.setProducts(products);
    }

    //Generic query helper for entity Computer with id Long
    public static Finder<Long,Category> find = new Finder<Long,Category>(Category.class);

    //Methods

    //Find all Products in the database in ascending order by name
    public static List<Category> findAll() {
        return Category.find.where().orderBy("name asc").findList();
    }


    // Generate options for an HTML select control
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();

        // Get all categories from the DB and add to the options Hash map
        for(Category c: Category.findAll()) {
            options.put(c.getId().toString(), c.getName());
        }
        return options;
    }
   

    public static Boolean inCategory(Long cat,Long prod)
    {
	return find.where().eq("products.id", prod).eq("id",cat).findRowCount()>0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    //Misc
    //This Gets the Categorys to which are stored in catSelect in Products And Converts them to Category Objects And Stores them in a list.
    public static List getCatConv(List<Long> catSelect) 
    {

	List<Category> categories = new ArrayList<Category>();

    	for(int i=0;i<catSelect.size();i++)
	{
	categories.add(find.byId(catSelect.get(i)));
	}
	return categories;
    }

}

