import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.util.Scanner;

public class FrontEnd extends Thread {

    private static Scanner in = new Scanner(System.in);
    private static Scanner fileReader = null;
    private static String inputFileName = "";
    private static LinkedList<Instrument> orderData = new LinkedList<>();
    private static boolean isFileLoaded = false;

    public static void main(String[] args) {
        System.out.println("WELCOME TO INSTRUMENT BILLING SERVICE!!");
        makeChoice();
        System.out.print(orderData.get(2));
    }

    /**
     * 
     */
    public static void uiComp() {
        System.out.println(
                "------------------------------------------------------");
        System.out.println("Choose any of the Options below: ");
        System.out.print("1)Load File from a text file\n"
                + "2)Export Instrument Bills\n3)Sort the Instrument orders\n"
                + "4)Exit\n");
        System.out.println(
                "------------------------------------------------------");
    }

    /**
     * 
     */
    private static void uiComp3() {
        System.out.println(
                "-----------------------------------------------------");
        System.out.println(
                "<--------------------Thank you----------------------->");
        System.out.println(
                "-----------------------------------------------------");
    }

    /**
     * 
     * @param choice
     */
    public static void makeChoice() {
        boolean isRunning = true;
        do {
            uiComp();
            System.out.print("Pick a number (1-4): ");
            int choice = in.nextInt();
            switch (choice) {
            case 1:
                loadFile();
                isFileLoaded = true;
                break;
            case 2:
                task2();
                break;
            case 3:
                task3();
                break;
            case 4:
                uiComp3();
                isRunning = false;
                break;
            default:
                System.out.println("Invalid Input Try Again :(");
                break;
            }
        } while (isRunning);
    }
    
    private static void task2() {
        if (isFileLoaded) {
            exportFile();
        } else {            
            System.out.println("File Not Loaded, Enter 1 to load file");
        }
    }
    private static void task3() {
        if (isFileLoaded) {
            Collections.sort(orderData);
            System.out.print("Items Sorted!");
        } else {
            System.out.println("File Not Loaded, Enter 1 to load file");
        }
    }

    /**
     * 
     */
    public static void loadFile() {
        System.out.print("Enter the file name you want to load(with .txt) : ");
        inputFileName = in.next();
        try {
            readFile(inputFileName);
            System.out.println("Input File loaded Successfully Loaded! ");
            System.out.println("Note: Ready to Print The Bill");
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Entered File Doesn't Exist");
        }
    }

    /**
     * 
     * @param fileName
     * @throws FileNotFoundException
     */
    private static void readFile(String fileName) throws FileNotFoundException {
        in = new Scanner(System.in);
        Scanner strLink = null;
        fileReader = new Scanner(new File(fileName));
        while (fileReader.hasNextLine()) {
            ArrayList<String> strData = new ArrayList<>();
            ArrayList<String> modData = new ArrayList<>();
            strLink = new Scanner(fileReader.nextLine());
            while (strLink.hasNext()) {
                strData.add(strLink.next());
                readInstruments(strLink, strData);
                double cost = strLink.nextDouble();
                int quantity = strLink.nextInt();
                readMods(strLink, modData);
                loadInstruments(strData, cost, quantity, modData);
            }
        }
    }

    /**
     * 
     * @param strLink
     * @param mods
     */
    private static void readMods(final Scanner strLink,
            ArrayList<String> mods) {
        while (strLink.hasNextLine()) {
            mods.add(strLink.next());
        }
    }

    /*
     * 
     */
    private static void readInstruments(final Scanner str,
            ArrayList<String> data) {
        if (data.get(0).equals("Guitar")) {
            data.add(str.next());
            data.add(str.next());
        } else {
            data.add(str.next());
        }
    }

    /**
     * 
     * @param a
     * @param cost
     * @param q
     * @param mod
     */
    private static void loadInstruments(final ArrayList<String> a, double cost,
            int q, ArrayList<String> mod) {
        switch (a.get(0)) {
        case "Guitar":
            Guitar g = new Guitar(a.get(2), a.get(1), cost, q, mod);
            g.customize(mod);
            orderData.add(g);
            break;
        case "Drums":
            orderData.add(new Drums(a.get(1), cost, q, mod));
            break;
        default:
            orderData.add(new Instrument("", 0.0, 0));
            break;
        }
    }

    /**
     * 
     */
    public void run() {
        run(this.getName());
    }

    /**
     * 
     * @param className
     */
    private void run(final String className) {
        try {
            double total = 0.0;
            PrintWriter writer = new PrintWriter(
                    new File(className + "_order.txt"));
            ArrayList<Instrument> temp = new ArrayList<>(orderData);
            for (Instrument i : temp) {
                if (i.getClass().getName().equals(className)) {
                    writer.write(i.toString());
                    writer.println();
                    total += i.cost;
                }
            }
            billingPart(writer, total);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void billingPart(final PrintWriter writer,
            final double total) {
        double tax = 0.13 * total;
        writer.write("\nTotal Cost: " + total);
        writer.write("\nTax: " + tax);
        writer.write("\nAmount Due: " + (total + tax));
    }

    /**
     * 
     */
    private static void exportFile() {
        System.out.println("Enter the 1st Class Name: ");
        String g1 = in.next();
        System.out.println("Enter the 2nd Class Name : ");
        String g2 = in.next();
        Thread t1 = new FrontEnd();
        t1.setName(g1);
        Thread t2 = new FrontEnd();
        t2.setName(g2);
        t1.start();
        t2.start();
        t1.interrupt();
        t2.interrupt();
    }

}
