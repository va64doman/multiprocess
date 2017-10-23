/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing.Packages;

// Access project
import interprocess.*;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Van Do
 */
public class OrderTest 
{
    // Initialise piped input and output stream
    PipedInputStream input  = new PipedInputStream();
    PipedOutputStream output = new PipedOutputStream();
    // Access Message class methods
    Message message = new Message();
    // Access JsonParser class methods
    JsonParser parse = new JsonParser();
    // Initialise list
    List<Order> order = new ArrayList<>();
    
    @Before
    public void setUp() throws IOException
    {
        // Before testing, connect piped output stream to piped input stream
        output.connect(input);
        // Add order to list
        order.add(new Order("Toy", 12, "John", "john@gmail.com"));
        order.add(new Order("T-shirt", 10, "Megan", "meg@hotmail.com"));
    }
    
    @Test
    public void checkIfEnableToRunThreadForOutputStream()
    {
        // Test if it can run on output stream in pipes by adding list to output stream
        // After adding list, serialize list into JSON string and send to piped input stream
        Runnable outputStream = () -> message.produceOrder(output, order);
        new Thread(outputStream).start();
    }
    
    @Test
    public void serializeListToJsonString()
    {
        // Check if list can convert to JSON string
        String serialize = parse.serializeColours(order);
        System.out.println("Testing list to JSON string: " + serialize);
        assertFalse("JSON String is [] because [] means empty list.", serialize.equals("[]"));
    }
    
    @Test
    public void deserializeJsonStringToList()
    {
        // Check if JSON string can convert to list
        String serialize = parse.serializeColours(order);
        List<Order> deserialize = parse.deserializeColours(serialize);
        System.out.println("Deserialize between JSON string and list");
        for(int count = 0; count < deserialize.size(); count++)
        {
            System.out.print(deserialize.get(count).getProduct() + " ");
            System.out.print(deserialize.get(count).getQuantity() + " ");
            System.out.print(deserialize.get(count).getName() + " ");
            System.out.print(deserialize.get(count).getAddress() + " ");
            System.out.println();
        }
        assertFalse("JSON string was unable to convert into list.", deserialize.isEmpty());
    }
    
    @After
    public void checkIfEnableToRunThreadForInputStream()
    {
        // After testing, check the results by converting into message from piped output stream for input stream
        Runnable inputStream = () -> message.consumeOrder(input);
        new Thread(inputStream).start();
    }
}
