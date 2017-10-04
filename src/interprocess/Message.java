/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Do
 */
public class Message extends Thread
{
    JsonParser parse = new JsonParser();

    public void produceOrder(PipedOutputStream output)
    {
        try 
        {
            String serialize;
            List<Order> order = new ArrayList<>();
            
            order.add(new Order("Vase", 2, "John", "john@gmail.com"));
            order.add(new Order("DVD", 20, "Peter", "peter@hotmail.com"));
            order.add(new Order("Car", 1, "Lily", "lily@yahoo.com"));
            order.add(new Order("Toy", 100, "Vivian", "viv@gmail.com"));
            order.add(new Order("Small sized clothes", 5, "Al", "al@gmail.com"));
            
            serialize = parse.serializeColours(order);
            byte[] response = serialize.getBytes("UTF8");
            output.write(response, 0, response.length);
            output.flush();
            System.out.println("From pipe output stream.");
            System.out.println("Serialize: " + serialize);
            System.out.println("\r");
            output.close();
        } 
        catch (UnsupportedEncodingException error) 
        {
            System.out.println("Error: " + error);
        }
        catch(IOException error)
        {
            System.out.println("Error: " + error);
        }
    }
    
    public void consumeOrder(PipedInputStream input)
    {
        try 
        {
            StringBuilder build = new StringBuilder();
            int number = -1;
            while((number = input.read()) != -1)
            {
                char character = (char)number;
                build.append(character);
            }
            String deserialize = build.toString();
            System.out.println("From pipe input stream.");
            List<Order> list = parse.deserializeColours(deserialize);
            int count = 1;
            if(list.size() > 0)
            {
                for(Order order : list)
                {
                    System.out.println("\r");
                    System.out.println("Order Details #" + count);
                    order.printDetails();
                    count++;
                }                
            }
            else
            {
                System.out.println("There are no orders.");
            }
            input.close();
        } 
        catch (IOException error) 
        {
            System.out.println("Error: " + error);
        }
    }
}
