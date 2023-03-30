import java.util.*;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Harry Potter and the Deathly Hallows" application. 
 * "World of Harry Potter and the Deathly Hallows" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Aldo Bega
 * @version 31.03.2021
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room
    private HashMap<String, Item> items;
    private boolean roomAllowsSpells = false; 

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        items = new HashMap();
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public ArrayList<Item> getRoomItems()
    {   
        ArrayList<Item> roomItems = new ArrayList<>();
        
        for(String item: items.keySet())
        {
            Item currentItem;
            
            if (item != null)
            { 
                currentItem = items.get(item);
                roomItems.add(currentItem);
            }
        }
        
        return roomItems;
    }
    
    /**
     * Setting items to the room
     * @param itemKey The key (String) of the Item
     * @param item  The actual item to put in
     */
    public void setItems (String itemKey, Item item)
    {
        items.put(itemKey,item);
    
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        String message = "Harry is " + description + ".\n" + "Here you can find: ";
        
        if (items.isEmpty())
            message += "Nothing";
      
        for(String item: items.keySet())
        {
            Item currentItem;
            
            if (item != null)
            { 
                currentItem = items.get(item);
                message += currentItem.getDescription() + " ";
            }
        }
        
        if (!items.isEmpty())
            message += ".\n" + "Which Item should Harry pick up?";
        
        message += ".\n" + getExitString();
        return message;
    }
    

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Get the Item, which has itemKey as key
     * @param itemKey The key of the Item
     * @return Item  The item found with the corresponding key
     */
    public Item getItem(String itemKey) 
    {
        return items.get(itemKey);
    }
    
    /**
     * Get exists, since getExitString has private access in Room
     */
    public String getExits()
    {
        return getExitString();
    }
    
    /**
     * Allow spells in a specific room
     */
    public void allowSpells(){
        roomAllowsSpells = true;
    }
    
    /**
     * return true if spells are allowed in the current Room
     */
    public boolean areSpellsAllowed(){
     return roomAllowsSpells;
    }
    
    
}

