import java.util.*;

/**
 * This class is part of the "World of Harry Potter and the Deathly Hallows" application. 
 * "World of Harry Potter and the Deathly Hallows" is a very simple, text based adventure game.  
 * 
 * A Wizard represents a wizard (in our game, namely Harry) that has to go to different locations and collect the right items, as well as cast
 * the right spell when it gets to the final destination, in order to win the game.
 *
 * @author  Aldo Bega
 * @version 31.03.2021
 */
public class Wizard
{
    private ArrayList<Item> ItemsToCarry = new ArrayList(); //stores the collected items

    /**
     * Constructor for objects of class Wizard
     */
    public Wizard()
    {
        //nothing to do
    }

    /** 
     * Setting an picked up item to the Wizard
     */
    public void setItemToWizard (Item item)
    {
        ItemsToCarry.add(item);
    }
    
    public ArrayList<Item> pickedUpItems()
    {
        return ItemsToCarry;
    }
    
    /** 
     * Printing the already carried Items
     */
    public void printItems()
    {
        if(ItemsToCarry.isEmpty())
        {
            System.out.println("There is no picked up item");
            return;
        }
        else
        {
            for(Item item: ItemsToCarry)
            {
                System.out.println(item.getDescription());
            }
        }
    }

    /** 
     * Creating an ArrayList of Strings, that will hold the description of each item
     */
    public ArrayList<String> listOfItems()
    {
        ArrayList<String> finalItems = new ArrayList(); 

        for(Item item: ItemsToCarry)
        {
            finalItems.add(item.getDescription());
        }

        return finalItems;
    }

    // public ArrayList<Item> getItems()
    // {
    // return ItemsToCarry;
    // }

    /** 
     * Creating an ArrayList of Items, that will copy the ItemsToCarry ArrayList, so that 
     * we can access it as a value and not as a reference.
     */
    public ArrayList<Item> getItemsForState()
    {
        ArrayList<Item> stateItems = new ArrayList(); 

        for(Item item: ItemsToCarry)
        {
            stateItems.add(item);
        }

        return stateItems;
    }

    /** 
     * Setting ItemsTCarry equal to previousItems, so that we can go back
     * to the previous state, in the begining of the previous room
     */
    public void previousItems(ArrayList<Item> previousItems)
    {
        ItemsToCarry = previousItems;
    }

}