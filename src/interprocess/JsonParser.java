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
        JSONArray array = new JSONArray();
        for(Order order : list)
        {
            JSONObject json = new JSONObject();
            json.put("productName", order.getProduct());
            json.put("productQuantity", order.getQuantity());
            json.put("customerName", order.getName());
            json.put("customerAddress", order.getAddress());
            array.put(json);
        }
        // Serialize list into JSON string format
        String jsonString = array.toString();
        return jsonString;
    }

    public List<Order> deserializeColours(String inputJsonString)
    {
        JSONArray array = new JSONArray(inputJsonString);
        List<Order> list = new ArrayList<>();
        for(int count = 0; count < array.length(); count++){
            JSONObject json = array.getJSONObject(count);
            Order order = new Order();
            order.setProduct(json.getString("productName"));
            order.setQuantity(json.getInt("productQuantity"));
            order.setName(json.getString("customerName"));
            order.setAddress(json.getString("customerAddress"));
            list.add(order);
        }
        
        return list;
    }
}
