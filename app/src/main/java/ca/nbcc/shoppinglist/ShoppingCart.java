package ca.nbcc.shoppinglist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

    //Map to store the shopping list
    private Map shoppingList = new HashMap();

    //Method to add an item to the shopping list
    public void addItem(String item){
        if(this.shoppingList.containsKey(item)){
            this.shoppingList.replace(item, (int)this.shoppingList.get(item)+1);
        }else{
            this.shoppingList.put(item, 1);
        }
    }

    //Get the Shopping list
    public Map<String, Integer> getShoppingList(){
        return shoppingList;
    }

    //Method to clear the shopping list
    public void clearList(){
        this.shoppingList.clear();
    }
}
