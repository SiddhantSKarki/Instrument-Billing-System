import java.util.ArrayList;

/**
 * This is an Instrument class which is a parent class for all the instruments
 * in the Instrument Modification center. This class is general representation
 * of all the instruments so it contains methods and instance variables that
 * are/might be common/alike to all the instrument. For the purpose of this
 * project we are only considering two instruments, i.e. Guitar and Drums.
 * 
 * @author Siddhant S. Karki (Unique ID: karkiss)
 *
 */
public class Instrument implements Comparable<Instrument>, Cloneable {
    // Defining instance variables
    /**
     * The total cost of an instrument.
     */
    protected double cost;

    /**
     * The Name of the Instrument.
     */
    protected String brand;

    /**
     * The List of all the modifications in the Instrument
     */
    protected ArrayList<String> mods;

    /**
     * The quantity of an Instrument
     */
    protected int quantity;

    /**
     * The quality index of an instrument calculated based on number of
     * modifications
     */
    protected double qti;

    /**
     * A default constructor that initializes the price to 0.0, brand as an
     * empty string, and Quantity as 0.
     */
    public Instrument() {
        this("", 0.0, 0);
    }

    /**
     * A parameterized constructor that initializes an Instrument object with
     * the brand name as the supplied brand, total cost as the supplied value of
     * cost, and quantity as the give number of quantity q.
     * 
     * @param brand A String which is the brand name.
     * @param cost  a double which is the total cost of the instrument.
     * @param q     an int which is the quantity of instrument in the order.
     */
    public Instrument(String brand, double cost, int q) {
        this.brand = brand;
        this.cost = cost;
        this.quantity = q;
    }

    /**
     * A copy constructor that copies the properties of the supplied Instrument
     * object to this instrument.
     * 
     * @param i An Instrument object that is to be copied
     */
    public Instrument(Instrument i) {
        this(i.brand, i.cost, i.quantity);
    }

    // Getter Methods from here

    /**
     * A method that gets the total cost of this Instrument.
     * 
     * @return a double value which is the cost of the instrument.
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * A method that returns the quantity of this Instrument.
     * 
     * @return an int which is the quantity of instrument order.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * A getter method that returns the brand name of this instrument.
     * 
     * @return a String which is the brand name of the Instrument.
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * The string format of an Instrument is: Brand: || Price: || Quantity:.
     */
    public String toString() {
        String format = String.format("Brand: %s| Price: %.2f| Quantity: %d",
                this.brand, this.cost, this.quantity);
        return format;
    }

    /**
     * A Clone method for Instrument that clones the Instrument passed as a
     * parameter;
     * 
     * @param i an Instrument object that needs to be cloned.
     * @return An instrument object which is the cloned.
     */
    public Instrument Clone(Instrument i) {
        return new Instrument(i);
    }

    /**
     * A method that compares two instrument objects. Two instruments are
     * compared base on their cost and the quality index.
     */
    public int compareTo(Instrument i) {
        if (i.cost == this.cost) {
            return (int) (Math.signum(i.qti - this.qti));
        } else {
            return (int) (Math.signum(i.cost - this.cost));
        }
    }

    /**
     * This method calculated the Quality index based on the number of
     * modifications on an instrument. The index is calculated based on their
     * cost number of modifications.
     * 
     * @return a double value which is the quality index of the instrument.
     */
    public double calcQI() {
        double index = (this.cost % 4) / this.quantity;
        if (mods.size() > 1) {
            return (index + mods.size()) % 4;
        } else {
            return 1;
        }
    }

    /**
     * An equals method that checks whether two instrument objects are equals or
     * not. Two instruments are equals if and only if their cost and quality
     * indices are equals.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Instrument)) {
            return false;
        }
        Instrument i = (Instrument) obj;
        return this.compareTo(i) == 0 ? true : false;

    }

}
