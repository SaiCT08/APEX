package bignumberaddition.project;
import java.util.*;
import java.io.*;
/**
 *
 * @author 235420
 */
public class BigNumberAdditionProject 
{

    private String sum;
    private final byte[][]arrValues;
    private final int numOfRows;
    
    public BigNumberAdditionProject(String fileName) throws FileNotFoundException // constructor that puts values from file into jagged rray
    {
        File data = new File(fileName); // reading from a file
        Scanner read = new Scanner(data);
        numOfRows = read.nextInt();
        this.arrValues = new byte[numOfRows][]; // initializing array
        
        
       for(int r = 0; r < arrValues.length; r++) //loops through arrValues rows in array
       {   
           String stringValue = read.next(); // looks through whole string on line
           arrValues[r] = new byte[stringValue.length()]; //create column length based on length of string in line above.
           for(int c = 0; c < stringValue.length(); c++) // loops through arrValues columns in array
           {
                   String individualVal = stringValue.substring(c, c+1); // gets each individual number by it self
                   byte byteValue = Byte.parseByte(individualVal);  // converts string to byte
                   arrValues[r][c] = byteValue; // puts byte value into array
               
           }
       }
    }
    
    public void addSum() // method that adds up all the numbers 
    {
        int maxLength = 0; // find largest number
        for (byte[] arrValue : arrValues) 
        {
            if (arrValue.length > maxLength) 
            {
                maxLength = arrValue.length;
            }
        }
        
        int[] result = new int[maxLength + 1]; // array to store result bc of carrying
        int carry = 0; // carry variable
        for(int c = 0; c < maxLength; c++) // column addition
        {
            int columnSum = carry; // initialize column sum with carry
            
            for (byte[] arrValue : arrValues) 
            {
                int index = arrValue.length - 1 - c; // tells index from the ride side of row
                if (index >= 0) 
                {
                    columnSum += arrValue[index]; // adds number to sum of column
                }
            }
            
            result[maxLength - c] = columnSum % 10; // place each digit at right index
            carry = columnSum / 10; // carry over the remainder of sum
        }
        result[0] = carry; // if theres any extra carry left, put it into the front
        
        sum = " ";
        boolean zeroAtStart = true; // skips any zeros at start
        for (int i = 0; i < result.length; i++) // goes through till a number that is not zero is found
        {
            if (zeroAtStart && result[i] == 0) // checks if number is zero
            {
                continue; // i learned this online. it iterates till the condition isn't true. 
            }
            zeroAtStart = false;
            sum += result[i];
        }
        
        if (sum.equals("")) // if its empty, print 0
        {
            sum = "0";
        }
        
        addCommas(); // call helper method to add commas
        
    }
    
    private void addCommas() // helper method that adds commas to the sum
    {
        String sumWithCommas = "";
        int count = 0;
        for (int i = sum.length() - 1; i > 0; i--) // goes through sum
        {
            sumWithCommas = sum.charAt(i) + sumWithCommas; // adds each individual number to sumWithCommas
            count++; // count + 1
            if (count % 3 == 0 && i > 0) // check if count is divisible by 3, and if it is, adds commas
            {
                sumWithCommas = "," + sumWithCommas;
            }
        
        }
        if (sumWithCommas.substring(0,1).equals(",")) // if theres a comma at beginning, it removes it
        {
            sumWithCommas = sumWithCommas.substring(1);
        }
        
        sum = sumWithCommas;
        
        
    }
    
    @Override
    public String toString() // method prints out sum
    {
       return sum;
    }
   
    public void printArray() // method prints out array with each row as an individual number with commas
    {
        for (int r = 0; r < arrValues.length; r++)
        {
            int length = arrValues[r].length; // to check number of digits in row
            int count = 0; // var to check if place is divisible by 3
            System.out.print(""); 
            for (int c = 0; c < arrValues[r].length; c++)
            {
                System.out.print(arrValues[r][c]);
                count++;
                
                if ((length - count) % 3 == 0 && c != length - 1) // adds comma if not last number, and if divisible by 3
                {
                    System.out.print(",");
                }
            }
            System.out.println("");
        }
    }
}
