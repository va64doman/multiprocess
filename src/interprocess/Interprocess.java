/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

import java.io.*;

/**
 * Interprocess --- This main class contains thread to run the pipes method
 * @author Van Do
 * @version 1.0
 */
public class Interprocess 
{
    /**
     * This static void contains the runnable program for connect an inter-process communication using pipes.
     * @param args - the command line arguments.
     * @throws IOException - if input is does not meet with the variables.
     * @throws InterruptedException - if pipe is interrupted, it cannot complete the action of sending and receiving.
     */
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        // Get Message class to access methods
        Message message = new Message();
        // Initialise piped output stream
        PipedOutputStream output = new PipedOutputStream();
        // Initialise piped input stream
        PipedInputStream  input  = new PipedInputStream();
        // Connect to the receiver to send message
        output.connect(input);
        // Run a thread for the producer to create a list of order
        Runnable producer = () -> message.addOrder(output);
        // Run a thread for the consumer to display the list of order
        Runnable consumer = () -> message.consumeOrder(input);
        // Execute this thread for producer and consumer
        new Thread(producer).start();
        new Thread(consumer).start();
    }
    
}
