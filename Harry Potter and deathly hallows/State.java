import java.util.*;
/**
 * This class is part of the "World of Harry Potter and the Deathly Hallows" application. 
 * "World of Harry Potter and the Deathly Hallows" is a very simple, text based adventure game.
 *
 * A state represent a state in a current moment
 * This class is used to go back to the previous state everytime the input is the command back
 * This calss saves the state in the beginning of each room
 *
 * @author  Aldo Bega
 * @version 31.03.2021
 */
public class State
{
    // A state has a room and and arraylist of items
    private Room room;
    private ArrayList<Item> itemsList;

    /**
     * Constructor for objects of class State
     */
    public State(Room room, ArrayList<Item> itemsList)
    {
        // initialise instance variabrles
        this.room = room;
        this.itemsList = itemsList;
       
    }
    
    /**
     * Get the room of the State
     */
    public Room getRoom()
    {
        return room;
    }
    
    /**
     * Get the ArrayList of items of the State
     */
    public ArrayList<Item> getItemsList()
    {
        return itemsList;
    }

}
