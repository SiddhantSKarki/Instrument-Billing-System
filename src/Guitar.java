import java.util.ArrayList;

/**
 * This is a Guitar class which is a child class for Instrument class. This
 * class represents all the Guitar/Guitar modifications in the Instrument
 * Modification System. This class contains all the information about a guitar,
 * for example: Number of Strings, Type of wood, Type of Guitar, and Whether or
 * it is re wired or not.
 * 
 * @author Siddhant S. Karki
 *
 */
public class Guitar extends Instrument implements Customizable {

    // Defining Instance variables
    protected int numStrings = 6;
    protected String type = "Electric";
    protected String wood = "Standard-Maple";
    protected boolean isRewired = false;

    /**
     * A default constructor that initialized the number of strings to 0, type
     * and wood type to an empty string.
     */
    public Guitar() {
        this("", "", 0.0, 0, new ArrayList<>() {
        });
    }

    /**
     * A Constructor that Initializes the brand, cost, quantity, and the
     * modifications with the supplied values for brand,cost,quantity, and
     * modifications.
     * 
     * @param brand    a String that you want to set the brand instance variable
     *                 to.
     * @param cost     a double which is the cost of the Guitar.
     * @param quantity an int which is the quantity of the Guitar.
     * @param mods     an ArrayList<String> which contains modifications in form
     *                 of strings.
     */
    public Guitar(String brand, String type, double cost, int quantity,
            ArrayList<String> mods) {
        super(brand, cost, quantity);
        this.mods = new ArrayList<>(mods);
        this.type = type;
    }

    /**
     * A copy constructor that copies a Guitar object to this Guitar object.
     * 
     * @param g A Guitar object that you want to copy.
     */
    public Guitar(Guitar g) {
        this(g.brand, g.type, g.cost, g.quantity, g.mods);
    }

    /**
     * The string format of a Guitar is: Brand: || Price: || Quantity: Guitar
     * Type: Modifications: NumStrings: || Wood: || Re-wired Electronics: ||
     * Quality Index: .
     */
    public String toString() {
        String reWiring = isRewired ? "Yes" : "No";
        String gFormat = super.toString() + String.format(
                "%n Guitar Type: %s%n Modifications: NumStrings:%d || Wood: %s || Re-Wiried Electronics: %s || Quality Index: %f",
                this.type, this.numStrings, this.wood, reWiring, this.calcQI());
        return gFormat;
    }

    /**
     * This method customizes a Guitar object based on the supplied Arraylist of
     * modifications. Each element/modification in the list is indicated in a
     * special string pattern, i.e modification-value.
     */
    public void customize(ArrayList<String> mods) {
        String cat = "";
        String mod = "";
        if (mods.size() < 1) {
            return;
        }
        for (String m : mods) {
            int sep = m.indexOf("-");
            if (sep > 0) {
                cat = m.substring(0, sep);
                mod = m.substring(sep + 1);
            } else {
                cat = m;
            }
            switch (cat) {
            case "string":
                this.reString(Integer.parseInt(mod));
                break;
            case "wood":
                this.upgradeWood(mod);
                break;
            case "electronics":
                this.upgradeElec();
                break;
            default:
                break;
            }
        }
    }

    /**
     * This is a helper method for the customize method, which takes a string
     * and applied modifications to the guitar object.
     * 
     * @param newType a String that contains the modification value for wood.
     */
    public void upgradeWood(String newType) {
        this.setWood(newType);
        int rate = 0;
        switch (newType) {
        case "Mahogony":
            rate = 500;
            break;
        case "Maple":
            rate = 400;
            break;
        case "Roasted-Maple":
            rate = 550;
            break;
        case "Alder":
            rate = 250;
            break;
        default:
            rate = 0;
            break;
        }
        cost += rate * this.quantity;
    }

    /**
     * This is a helper method for the customize method, which applied the
     * rewiring of electronics in the Guitar object. and applied modifications
     * to the guitar object.
     */
    public void upgradeElec() {
        if (!isRewired) {
            this.isRewired = true;
            if (this.type.equalsIgnoreCase("Acoustic")) {
                cost += 45 * this.quantity;
            } else {
                cost += 150 * this.quantity;
            }
        }
    }

    /**
     * A helper method that applies the modifications of a parameter into the
     * guitar object. This also takes in account of type of guitar whether or
     * not it has minimum of 6 strings.
     * 
     * @param numStr the modification value for strings.
     */
    private void reString(int numStr) {
        if (this.type.equalsIgnoreCase("Electric")) {
            if (numStr > 6) {
                this.numStrings = numStr;
                this.cost += 400 * this.quantity;
            } else if (numStr == 4) {
                this.cost -= 50 * this.quantity;
                this.numStrings = 4;
                this.setType("Bass");
            }
        } else {
            cost += 30 * this.quantity;
        }
    }

    /**
     * A clone method that clones a Copy of the supplied Guitar object.
     * 
     * @param g The Guitar object the is to cloned.
     * @return a Guitar object which is a clone.
     */
    public Guitar Clone(Guitar g) {
        return new Guitar(g);
    }

    /**
     * A method that sets the wood type for a Guitar Object.
     * 
     * @param wood A String that you want to set the type to.
     */
    public void setWood(String wood) {
        this.wood = wood;
    }

    /**
     * A method that sets the instrument type for a Guitar Object.
     * 
     * @param type A String that you want to change the type to.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * A getter method that gets the current number of guitar strings.
     * 
     * @return an int which is the number of Guitar String in the Guitar Object.
     */
    public int getNumStrings() {
        return this.numStrings;
    }

    /**
     * A Getter method that gets the type of a Guitar Object.
     * 
     * @return a String which is the type of the Guitar object.
     */
    public String getType() {
        return this.type;
    }

    /**
     * A Getter method that gets the type of wood of the Guitar object.
     * 
     * @return a String which is the type of wood of the Guitar object.
     */
    public String getWood() {
        return this.wood;
    }

}
