import java.util.ArrayList;

/**
 * A functional interface which is implemented by any type of instrument object
 * that can be modified. For the purpose of this Project, this interface is
 * implemented by two classes, Guitar and Drums class. If we wish to add any
 * other classes that inherit Instruments class and that are modifiable, then
 * those classes can also implement this interface.
 * 
 * @author Siddhant S. Karki
 *
 */
public interface Customizable {

    /**
     * A method that customizes any Customizable Instrument based on the
     * modifications supplied in the form of an ArrayList of Strings. These
     * modifications strings have to have a specific pattern to it, i.e
     * modification-value.
     * 
     * @param modifications an ArrayList<String> that contains modifications.
     */
    public void customize(ArrayList<String> modifications);
}
