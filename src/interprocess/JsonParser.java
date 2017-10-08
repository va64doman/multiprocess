/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprocess;

import java.util.*;
import org.json.*;

/**
 *
 * @author Van Do
 */
public class JsonParser 
{
    public String serializeColours(List<Order> list)
    {
        // Set JSON array for the list of orders
        JSONArray array = new JSONArray();
        // Go through each of the order in a list
        for(Order order : list)
        {
            // Set JSON object to store into JSON array
            JSONObject json = new JSONObject();
            // Put order's product into JSON object and set as productName as key
            json.put("productName", order.getProduct());
            // Put product's quantity into JSON object and set as productQuantity as key
            json.put("productQuantity", order.getQuantity());
            // Put customer's name into JSON object and set as customerName as key
            json.put("customerName", order.getName());
            // Put customer's address into JSON object and set as customerAddress as key
            json.put("customerAddress", order.getAddress());
            // Add JSON object to JSON array
            array.put(json);
        }
        // Serialize list into JSON string format and return this string
        String jsonString = array.toString();
        return jsonString;
    }

    public List<Order> deserializeColours(String inputJsonString)
    {
        // Set JSON array using JSON string
        JSONArray array = new JSONArray(inputJsonString);
        // Set list of order
        List<Order> list = new ArrayList<>();
        // Iterate through each object in JSON array
        for(int count = 0; count < array.length(); count++)
        {
            // Set JSON object by getting the object from JSON array
            JSONObject json = array.getJSONObject(count);
            // Initialise order object
            Order order = new Order();
            // Set order's product from JSON object's string using the key, productName
            order.setProduct(json.getString("productName"));
            // Set product's quantity from JSON object's integer using the key, productQuantity
            order.setQuantity(json.getInt("productQuantity"));
            // Set customer's name from JSON object's string using the key, customerName
            order.setName(json.getString("customerName"));
            // Set customer's address from JSON object's string using the key, customerAddress
            order.setAddress(json.getString("customerAddress"));
            // Add order to list
            list.add(order);
        }
        // Return list of object
        return list;
    }
}
