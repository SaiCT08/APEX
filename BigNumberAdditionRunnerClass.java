package bignumberaddition.project;
import java.util.*;
import java.io.*;

/**
 *
 * @author 235420
 */
public class BigNumberAdditionRunnerClass 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter test file");
        String fileName = console.nextLine();
        BigNumberAdditionProject bigNumAddProj = new BigNumberAdditionProject(fileName);
       
        System.out.println();
        System.out.println("Array:"); 
        System.out.println();
        bigNumAddProj.printArray(); // prints array
        
        System.out.println();
        System.out.println("Sum:");
        System.out.println();
        bigNumAddProj.addSum(); // calls method to add sum and commas
        System.out.println(bigNumAddProj); // Prints Sum
        System.out.println();
    }
}
