import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;

public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("Testing the class");
        Scanner myObj = new Scanner(System.in);
        System.out.println("enter a word");
        String name = myObj.nextLine();
        System.out.println("the word was " + name);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Create Guest");
        options.add("Create Sell Ticket");
        System.out.println(options);
        System.out.println("Lets make the originial price: " + 120 + " and we can discount it by 23 percent");
        System.out.println("the new discounted price would be: " + handleDiscount(0,23));
        System.out.println("System is continuing");

    }

    public static double handleDiscount(double original, double discountPercent) throws Exception{
        if (original == 0) {
            throw new Exception("Original price to discount cannot be 0");
        }
        double multiplier =  1 - (discountPercent / 100);
        double discountPrice = original * multiplier;
        BigDecimal rounded = new BigDecimal(discountPrice).setScale(2,RoundingMode.HALF_UP);
        double newPrice = rounded.doubleValue();

        return newPrice;
    }


}
