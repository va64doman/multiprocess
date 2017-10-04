/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

/**
 *
 * @author Van Do
 */
public class Order {
    // Get and set product's name
    private String productName;
    // Get and set quantity
    private int productQuantity;
    // Get and set customer's name
    private String customerName;
    // Get and set customer's address
    private String customerAddress;
    
    public Order()
    {
        
    }
    
    public Order(String product, int quantity, String customer, String address)
    {
        productName = product;
        productQuantity = quantity;
        customerName = customer;
        customerAddress = address;
    }
    
    public String getProduct()
    {
        return productName;
    }
    public int getQuantity()
    {
        return productQuantity;
    }
    public String getName()
    {
        return customerName;
    }
    public String getAddress()
    {
        return customerAddress;
    }
    
    public void setProduct(String name)
    {
        productName = name;
    }
    public void setQuantity(int quantity)
    {
        productQuantity = quantity;
    }
    public void setName(String name)
    {
        customerName = name;
    }
    public void setAddress(String address)
    {
        customerAddress = address;
    }
    
    public void printDetails()
    {
        System.out.println("Customer name: " + customerName);
        System.out.println("Customer address: " + customerAddress);
        System.out.println("Product name: " + productName);
        System.out.println("Quantity: " + productQuantity);
    }
}
