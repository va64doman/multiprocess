/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

import java.io.*;

/**
 *
 * @author Van Do
 */
public class Interprocess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        Message message = new Message();
        PipedOutputStream output = new PipedOutputStream();
        PipedInputStream  input  = new PipedInputStream();
        output.connect(input);
        
        Runnable producer = () -> message.produceOrder(output);
    }
    
}
