/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

/**
 * Order --- This class contains data of order.
 * @author Van Do
 */
public class Order 
{
    // Get and set product's name
    /**
     * The name of the product user wants to sell.
     */
    private String productName;
    // Get and set quantity
    /**
     * The number of products to sell.
     */
    private int productQuantity;
    // Get and set customer's name
    /**
     * The name of the customer.
     */
    private String customerName;
    // Get and set customer's address
    /**
     * The email address or home address of the customer.
     */
    private String customerAddress;
    // Null constructor
    /**
     * Null class constructor to access methods.
     */
    public Order()
    {
        
    }
    // Parameterised constructor
    /**
     * Parameterized constructor to store object's order data.
     * @param product - the name of the product. See {@link #productName}.
     * @param quantity - the number of products. See {@link #productQuantity}.
     * @param customer - the customer's name. See {@link #customerName}.
     * @param address - the customer's email or home address. See {@link #customerAddress}.
     */
    public Order(String product, int quantity, String customer, String address)
    {
        productName = product;
        productQuantity = quantity;
        customerName = customer;
        customerAddress = address;
    }
    // Getters
    /**
     * Get the name of the product. See {@link #productName}.
     * @return name of the product.
     */
    public String getProduct()
    {
        // Return product's name
        return productName;
    }
    /**
     * Get the number of products. See {@link #productQuantity}.
     * @return number of products.
     */
    public int getQuantity()
    {
        // Return product's quantity
        return productQuantity;
    }
    /**
     * Get the customer's name. See {@link #customerName}.
     * @return customer's name.
     */
    public String getName()
    {
        // Return customer's name
        return customerName;
    }
    /**
     * Get the customer's address. See {@link #customerAddress}.
     * @return customer's address.
     */
    public String getAddress()
    {
        // Return customer's address
        return customerAddress;
    }
    // Setters
    /**
     * Set product's name. See {@link #productName}.
     * @param name - the name of the product.
     */
    public void setProduct(String name)
    {
        // Set product's name
        productName = name;
    }
    /**
     * Set product's quantity. See {@link #productQuantity}.
     * @param quantity - the number of products.
     */
    public void setQuantity(int quantity)
    {
        // Set product's quantity
        productQuantity = quantity;
    }
    /**
     * Set customer's name. See {@link #customerName}.
     * @param name - the name of the customer or buyer.
     */
    public void setName(String name)
    {
        // Set customer's name
        customerName = name;
    }
    /**
     * Set customer's address. See {@link #customerAddress}.
     * @param address - the customer's email or home address.
     */
    public void setAddress(String address)
    {
        // Set customer's address
        customerAddress = address;
    }
    // Print order's details
    /**
     * Print this order details to the piped output stream.
     */
    public void printDetails()
    {
        System.out.println("Customer name: " + customerName);
        System.out.println("Customer address: " + customerAddress);
        System.out.println("Product name: " + productName);
        System.out.println("Quantity: " + productQuantity);
    }
}
