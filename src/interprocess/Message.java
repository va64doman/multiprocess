/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

import java.io.*;
import java.util.*;

/**
 * Message --- This class allows to read or write message stream from one end of the pipe to another end.
 * @author Van Do
 */
public class Message extends Thread
{
    // Inherit Message with Thread class because the message is a thread
    // Get JsonParser class to access methods
    JsonParser parse = new JsonParser();
    // Add scanner to input data for the list of order
    // Use delimiter to get string from line
    Scanner scan = new Scanner(System.in).useDelimiter("[\r\n]");
    /**
     * Convert the list of Order objects into JSON string.
     * Write the JSON string into bytes and send to PipedInputStream. See {@link #consumeOrder}.
     * @param output connected to piped input stream where this pipe writes JSON string in bytes to receiver.
     * @param order list of Order objects by user.
     * @exception UnsupportedEncodingException - catch errors when the message encoding into bytes have failed.
     * @exception IOException - catch errors if write stream of bytes to pipe output stream has failed.
     */
    public void produceOrder(PipedOutputStream output, List<Order> order)
    {
        try 
        {
            // Encode list of order into JSON string
            String serialize = parse.serializeColours(order);
            // Set an arrays of bytes by encoding JSON string into sequence of bytes using 8-bit blocks
            byte[] response = serialize.getBytes("UTF8");
            // Wrtie those bytes to inter-process communication using pipes
            output.write(response, 0, response.length);
            // Clear buffer for current stream
            output.flush();
            // Display the serialize strings to piped output steam
            System.out.println("From pipe output stream. Send to server.");
            System.out.println("\r");
            System.out.println("Serialize: " + serialize);
            System.out.println("\r");
            // Close output stream
            output.close();
        } 
        catch (UnsupportedEncodingException error) 
        {
            // Catch when encoding is not supporting
            System.out.println("Error: " + error);
        }
        catch(IOException error)
        {
            // Catch when input/output errors occurred
            System.out.println("Error: " + error);
        }
    }
    /**
     * Read JSON string from piped output stream and convert into list of Order objects
     * to make it readable for the receiver.
     * @param input convert byte of data from piped output stream and convert into array of characters.
     * @exception IOException - catch errors when stream of bytes was unable to be read.
     */
    public void consumeOrder(PipedInputStream input)
    {
        try 
        {
            // Initialise string builder to build string from piped input stream
            // PipedInputStream reads the sequence of bytes from PipedOutputStream
            StringBuilder build = new StringBuilder();
            int number;
            // Iterate while bytes of data from piped input stream is not -1
            while((number = input.read()) != -1)
            {
                // Set character by converting integer into letter, number or symbols
                char character = (char)number;
                // Append character to string builder to build a full text for string
                build.append(character);
            }
            // Set deserialize from string builder
            String deserialize = build.toString();
            System.out.println("From pipe input stream. Receive from client.");
            // Deserialize JSON string into list of orders
            List<Order> list = parse.deserializeColours(deserialize);
            // Set count to 1 to initialise the starting order
            int count = 1;
            // Check if list is not empty
            if(list.size() > 0)
            {
                // Iterate through each order from the list
                for(Order order : list)
                {
                    // Display this order's details
                    System.out.println("\r");
                    System.out.println("Order Details #" + count);
                    order.printDetails();
                    // Increment count by 1
                    count++;
                }                
            }
            else
            {
                // If list is empty, display this message
                System.out.println("There are no orders.");
            }
            input.close();
        } 
        catch (IOException error) 
        {
            // Catch if input/output errors occured
            System.out.println("Error: " + error);
        }
    }
    /**
     * Create a list of orders by user interface.
     * @param output using output piped stream for produce order method to enable to connect pipes.
     */
    public void addOrder(PipedOutputStream output)
    {
        // Initialise list of order as array list
        List<Order> order = new ArrayList<>();
        String choice;
        // Set hasSelected to false
        Boolean hasSelected = false;
        // Continue while input is not yes or no
        do
        {
            // Input yes or no to set hasSelected to true
            System.out.print("Will you order any products? (Type Yes or No fully) ");
            choice = scan.next();
            if("no".equalsIgnoreCase(choice) || "yes".equalsIgnoreCase(choice))
            {
                hasSelected = true;
            }
        }
        while(!hasSelected);
        // Iterate loop while choice is yes when keep ordering or first order
        while(!choice.equalsIgnoreCase("no"))
        {
            // Order's name, product, product's quantity and address
            String name, product, address;
            int quantity;
            // Continue while the first character of name that is not empty
            do
            {
                // Input customer's name
                System.out.print("Enter your name (This should not be blank): ");
                name = scan.next();
            }
            while(isStringNullOrWhiteSpace(name));
            // Continue while the first character of product that is not empty
            do
            {
                // Input product
                System.out.print("Enter the product you want to buy (This should not be blank): ");
                product = scan.next();
            }
            while(isStringNullOrWhiteSpace(product));
            // Enter product's quantity
            System.out.print("Enter the number of quantity for this product: ");
            quantity = handleInt();
            // Enter customer's address
            System.out.print("Enter your contact or email address (optional): ");
            address = scan.next();
            // Add new order to list
            order.add(new Order(product, quantity, name, address));
            // Continue while input is not yes or no
            do
            {
                System.out.print("Do you want to keep adding order? (Type Yes or No fully) ");
                choice = scan.next();
                if("no".equalsIgnoreCase(choice) || "yes".equalsIgnoreCase(choice))
                {
                    hasSelected = true;
                }
                else
                {
                    // Set hasSelected to false to avoid keeping hasSelected to true to cause end loop
                    hasSelected = false;
                }
            }
            while(!hasSelected);
        }
        
        System.out.println();
        // Write bytes of data using piped output stream and list of order
        produceOrder(output, order);
    }
    // Handle integer input
    /**
     * Handling integer input.
     * @exception InputMismatchException - catch if input does not match integer requirement.
     * @return integer value
     */
    private int handleInt()
    {
        int input = 0;
        // Assuming this continue in a loop until the user has entered the integer
        boolean loop = true;
        // Continue this loop until the user has entered the input correctly
        while(loop)
        {
            // Try and catch error if the user has not entered the integer
            try
            {
                input = scan.nextInt();
                if(input > 0)
                {
                    // If quantity is greater than 0, end loop
                    loop = false;
                }
                else
                {
                    // Continue looping when quantity is less than 1 or incorrect input
                    System.out.print("Quantity must be greater than 0. Enter quantity: ");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.print("Try again. Wrong input. Enter quantity: ");
                scan.nextLine();
            }
        }
        // Return integer
        return input;
    }
    // Handle string input for emptiness.
    /**
     * Handling string input for emptiness.
     * @param value the string input by the user.
     * @return the statement of the string is not empty.
     */
    private boolean isStringNullOrWhiteSpace(String value)
    {
        // If value is empty, then return true
        if(value == null)
        {
            return true;
        }
        // Go through all characters in string and check if the any character is not white space
        for(int count = 0; count < value.length(); count++)
        {
            // If this character is not white space, then return false
            if(!Character.isWhitespace(value.charAt(count)))
            {
                return false;
            }
        }
        // Return true if all characters are white space
        return true;
    }
}
