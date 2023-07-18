import java.util.ArrayList;

/**
 * This is a Drums class which is a child class for Instrument class. This class
 * represents all the Drums instruments/ modifications in the Instrument
 * Modification System. This class contains all the information about a Drums,
 * for example: Number of Toms, Color of the drum set, Number of Crash, and
 * Whether or it has a double bass.
 * 
 * @author Siddhant S. Karki
 *
 */
public class Drums extends Instrument implements Customizable {

    /**
     * Number of Toms is by default 2
     */
    protected int numTom = 2;

    /**
     * Number of crash by default 2
     */
    protected int numCrash = 2;

    /**
     * Default color for a Drums is grey
     */
    protected String color = "grey";

    /**
     * Initially drums don't come with double bass.
     */
    protected boolean hasDoubleBass = false;

    /**
     * A default constructor that initializes all numerical variables to 0 and
     * Strings to an empty string.
     */
    public Drums() {
        this("", 0.0, 0, new ArrayList<String>() {
        });
    }

    /**
     * A Constructor that Initializes the brand, cost, quantity, and the
     * modifications with the supplied values for brand,cost,quantity, and
     * modifications.
     * 
     * @param brand    a String that you want to set the brand instance variable
     *                 to.
     * @param cost     a double which is the cost of the Drums.
     * @param quantity an int which is the quantity of the drums.
     * @param mods     an ArrayList<String> which contains modifications in form
     *                 of strings.
     */
    public Drums(String brand, double cost, int quantity,
            ArrayList<String> mods) {
        super(brand, cost, quantity);
        this.mods = new ArrayList<String>(mods);
        this.customize(mods);
    }

    /**
     * A copy constructor that copies a Drums object to this Drum object.
     * 
     * @param g A Drums object that you want to copy.
     */
    public Drums(Drums g) {
        this(g.brand, g.cost, g.quantity, g.mods);
    }

    /**
     * A clone method that clones a Copy of the supplied Drums object.
     * 
     * @param g The Drums object the is to cloned.
     * @return a Drums object which is a clone.
     */
    public Drums Clone(Drums g) {
        return new Drums(g);
    }

    /**
     * This method customizes a Drums object based on the supplied Arraylist of
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
            case "crash":
                addComponent(m);
                break;
            case "tom":
                addComponent(m);
                break;
            case "color":
                modColor(mod);
                break;
            case "double-bass":
                addDoubleBass();
                break;
            default:
                break;
            }
        }
    }

    /**
     * A Method that adds the supplied component to the Drums Object subject to
     * changes in other properties of the Drums object.
     * 
     * @param component A String which contains the value of the component. In
     *                  this case this is either crash or Tom
     */
    private void addComponent(String component) {
        String compVal = "";
        if (component.contains("crash")) {
            compVal = component.substring(6);
            this.numCrash += Integer.parseInt(compVal);
            cost += 250 * numCrash;
        } else if (component.contains("tom")) {
            compVal = component.substring(4);
            this.numTom += Integer.parseInt(compVal);
            cost += 375 * numTom;
        }
    }

    /**
     * A mutator method that sets the color of the Drums object to the applied
     * color.
     * 
     * @param color a String which is the value of the color.
     */
    private void setColor(String color) {
        this.color = color;
    }

    /**
     * A Helper method for the customize method which modifies the color of the
     * Drums Object along with necessary changes in the properties like cost and
     * quality index.
     * 
     * @param newColor A String which is the value of color to be applied.
     */
    private void modColor(String newColor) {
        this.setColor(newColor);
        int rate = 0;
        switch (newColor) {
        case "Blue":
            rate = 50;
            break;
        case "Golden":
            rate = 100;
            break;
        case "Shiny-Green":
            rate = 150;
            break;
        case "Deep-Purple":
            rate = 250;
            break;
        default:
            rate = 10;
            break;
        }
        cost += rate * this.quantity;
    }

    /**
     * A helper method for customize method which adds double bass to the Drums
     * object and applied necessary changes to it instance variables.
     */
    private void addDoubleBass() {
        if (!hasDoubleBass) {
            cost += (0.05 * cost);
            hasDoubleBass = true;
        }
        cost += 15;
    }

    /**
     * The string format of a Drums is: Brand: || Price: || Quantity: Guitar
     * Type: Modifications: Num Toms: || Num Crash: || Color: || Double bass
     * added: || Quality Index: .
     */
    public String toString() {
        String doubleBass = hasDoubleBass ? "Yes" : "No";
        String gFormat = super.toString() + String.format(
                "%n Modifications: Num Toms: %d || Num Crash: %d"
                        + "|| Color: %s || Double Bass Added: %s || Quality Index: %f",
                this.numTom, this.numCrash, this.color, doubleBass,
                this.calcQI());
        return gFormat;
    }

}
