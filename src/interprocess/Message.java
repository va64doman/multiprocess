/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

import java.io.*;
import java.util.*;

/**
 *
 * @author Van Do
 */
public class Message extends Thread
{
    public void produceOrder(PipedOutputStream output)
    {
        string serialize;
        List<Order> order = new ArrayList<>();
        
        order.add(new Order("Vase", 2, "John", "john@gmail.com"));
        order.add(new Order("DVD", 20, "Peter", "peter@hotmail.com"));
        order.add(new Order("Car", 1, "Lily", "lily@yahoo.com"));
        order.add(new Order("Toy", 100, "Vivian", "viv@gmail.com"));
        order.add(new Order("Small sized clothes", 5, "Al", "al@gmail.com"));
        
        
    }
}
