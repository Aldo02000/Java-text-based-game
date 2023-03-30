
/**
 * This class is part of the "World of Harry Potter and the Deathly Hallows" application. 
 * "World of Harry Potter and the Deathly Hallows" is a very simple, text based adventure game.  
 * 
 * An Item represents an item that has to be picked up by the wizard. Each room has its own items and the wizard can pick up
 * as many items as he wishes
 *
 * @author  Aldo Bega
 * @version 31.03.2021
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;

    /**
     * Constructor for objects of class Item
     * Each Item has a description
     */
    public Item(String description)
    {
        // initialise instance variables
        this.description = description;
        
    }
    
    
    /**
     * Get the description of the item 
     */
    public String getDescription()
    {
        return description;
    }
}
    
