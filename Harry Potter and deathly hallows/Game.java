import java.util.*;

/**
 *  This class is the main class of the "World of Harry Potter and the Deathly Hallows"
 *  application. 
 *  "World of Harry Potter and the Deathly Hallows" is a very simple, text based
 *  adventure game.  Users can walk around some scenery. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *  
 *  This is the story of Harry Potter, the boy who lived. 
 *  He can become the saviour of the Wizarding World if he defeats
 *  (He Who Must Not Be Named) Lord Voldemort. 
 *  For Harry to defeat Lord Voldemort and his army, he has to first become the Master Of Death. 
 *  When you become the Master of Death, you are immortal. 
 *  But what do you need to become the Master Of Death? 
 *  You need to possess the Deathly Hallows in order.
 *  Elder Wand
 *  Stone of Resurrection
 *  The Invisibility Cloak
 *  
 *  Help Harry collect The Deathly Hallows and defeat Lord Voldemort!!
 * 
 * @author  Aldo Bega
 * @version 31.03.2021
 */

public class Game 
{
    // private Parser parser;
    private Room currentRoom;
    private Stack<State> states = new Stack();
    private Wizard Harry = new Wizard();
    // private String direction;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        // parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * Also create all the items inside the rooms
     */
    private void createRooms()
    {
        Room greatHall, olivanders, roomOfReq, chamberOfSecrets, finalDestination;
        Item ElderWand,DumbledoreWand,SnapeWand, MalfoyWand, HermioneWand, RonWand;
        Item InfinityStone, SpaceStone, PowerStone, MindStone, TimeStone, StoneOfResurrection ;
        Item SlytherinCloak, GryffindorCloak, RavenclawCloak, HufflepuffCloak, InvisibilityCloak;

        // create the wands
        ElderWand = new Item("ElderWand");
        DumbledoreWand = new Item("DumbledoreWand");
        SnapeWand = new Item("SnapeWand");
        MalfoyWand = new Item("MalfoyWand");
        RonWand = new Item("RonWand");
        HermioneWand = new Item("HermioneWand");

        // create the Stones
        InfinityStone =  new Item("InfinityStone");
        SpaceStone = new Item("SpaceStone");
        PowerStone = new Item("PowerStone");
        MindStone = new Item("MindStone");
        TimeStone = new Item("TimeStone"); 
        StoneOfResurrection = new Item("StoneOfResurrection");

        // create the Cloaks
        SlytherinCloak =  new Item("SlytherinCloak");
        GryffindorCloak = new Item("GryffindorCloak");
        RavenclawCloak = new Item("RavenclawCloak");
        HufflepuffCloak = new Item("HufflepuffCloak");
        InvisibilityCloak = new Item("InvisibilityCloak");

        // create the rooms
        greatHall = new Room("standing at the Great Hall of Hogwarts School of Witchcraft and Wizardry.");
        olivanders = new Room("at Olivanders");
        roomOfReq = new Room("in the Room of Requirement");
        chamberOfSecrets = new Room("in the Chamber of Secrets");
        finalDestination = new Room("in the Battle Ground and here he has to fight Lord Voldemort. He has to choose the right spell!");

        // Allow spells for some rooms
        finalDestination.allowSpells();

        // initialise Wands
        olivanders.setItems("ElderWand", ElderWand);
        olivanders.setItems("DumbledoreWand",DumbledoreWand);
        olivanders.setItems("SnapeWand",SnapeWand);
        olivanders.setItems("MalfoyWand", MalfoyWand);
        olivanders.setItems("RonWand", RonWand);
        olivanders.setItems("HermioneWand", HermioneWand);

        // initialise Stones
        chamberOfSecrets.setItems("InfinityStone", InfinityStone);
        chamberOfSecrets.setItems("SpaceStone", SpaceStone);
        chamberOfSecrets.setItems("PowerStone", PowerStone);
        chamberOfSecrets.setItems("MindStone", MindStone);
        chamberOfSecrets.setItems("TimeStone", TimeStone);
        chamberOfSecrets.setItems("StoneOfResurrection", StoneOfResurrection);

        // initialise Cloaks
        roomOfReq.setItems("SlytherinCloak",SlytherinCloak);
        roomOfReq.setItems("GryffindorCloak", GryffindorCloak);
        roomOfReq.setItems("RavenclawCloak", RavenclawCloak);
        roomOfReq.setItems("HufflepuffCloak", HufflepuffCloak);
        roomOfReq.setItems("InvisibilityCloak", InvisibilityCloak);

        // initialise room exits
        greatHall.setExit("east", olivanders);
        greatHall.setExit("south", chamberOfSecrets);
        greatHall.setExit("west", roomOfReq);
        greatHall.setExit("north", finalDestination);

        olivanders.setExit("west", greatHall);

        roomOfReq.setExit("east", greatHall);

        chamberOfSecrets.setExit("north", greatHall);

        finalDestination.setExit("south", greatHall);

        currentRoom = greatHall;// start game greatHall
        
        //set the first state, (in the great hall, itemsList is empty)
        State state = new State(currentRoom, Harry.getItemsForState());
        states.push(state);
    }
    
    public String getCurrentRoomDescription ()
    {
        return currentRoom.getLongDescription();
    }
    
    public String getRoomDescription ()
    {
        return currentRoom.getShortDescription();
    }
    
    public ArrayList<String> getCurrentRoomItems()
    {
        
        ArrayList<String> currentRoomItems = new ArrayList<>();
        ArrayList<Item> roomItems = currentRoom.getRoomItems();
        
        for(Item item: roomItems)
        {
            currentRoomItems.add(item.getDescription());
        }
        
        return currentRoomItems;
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    // public void play() 
    // {            
        // // printWelcome();

        // // Enter the main command loop.  Here we repeatedly read commands and
        // // execute them until the game is over.

        // boolean finished = false;
        // while (! finished) {
            // Command command = parser.getCommand();
            // finished = processCommand(command);
        // }
        // System.out.println("Thank you for playing.  Good bye.");
    // }

    /**
     * Print out the opening message for the player.
     */
    // private void printWelcome()
    // {
        // System.out.println();
        // System.out.println("Welcome to the World of Harry Potter and The Deathly Hallows!");
        // System.out.println("Help Harry defeat Lord Voldemort and save the Wizarding World!.");
        // System.out.println("Type 'help' if you need help.");
        // System.out.println();
        // System.out.println(currentRoom.getLongDescription());

    // }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    // private boolean processCommand(Command command) 
    // {
        // boolean wantToQuit = false;

        // if(command.isUnknown()) {
            // System.out.println("I don't know what you mean...");
            // return false;
        // }

        // String commandWord = command.getCommandWord();
        // // if (commandWord.equals("help")) {
            // // printHelp();
        // // }
        // // else if (commandWord.equals("go")) {
            // // goRoom(direction);
        // // }
        // if (commandWord.equals("quit")) {
            // wantToQuit = quit();
        // }
        // // else if (commandWord.equals("pickUp")) {
            // // pickUp(command);
        // // }
        // // else if (commandWord.equals("showItems")) {
            // // showPickedUpItems();
        // // }
        // // else if (commandWord.equals("spell")) {
            // // wantToQuit = spell(command);
        // // }
        // else if (commandWord.equals("back")) {
            // goBack();
        // }
        // // else command not recognised.
        // return wantToQuit;
    // }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    // private void printHelp() 
    // {
        // System.out.println("Harry is lost. Harry is alone. Harry wanders");
        // System.out.println("around at Hogwarts School of Witchcraft and Wizardry.");
        // System.out.println();
        // System.out.println("The command words are:");
        // parser.showCommands();
        // System.out.println();
        // System.out.println("The spells are: expeliarmus and avadaKedavra");
    // }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    public boolean goRoom(String direction) 
    {
        // if(!command.hasSecondWord()) {
            // // if there is no second word, we don't know where to go...
            // System.out.println("Go where?");
            // return;
        // }

        // String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        }
        else {
            currentRoom = nextRoom;
            
            State state = new State(currentRoom, Harry.getItemsForState());
            states.push(state);

            return true;
        }
    }
    
    
    
    /** 
     * Try to go back (in the state) in the begining of the previous room. If there are items collected in this room or
     * in the previous room, these items are lost.
     */
    public void goBack ()
    {
                        
        // if(command.hasSecondWord()) {
            // System.out.println("Back what?");
            // return;
        // }
        
        if(!(states.isEmpty()) && !(states.size() == 1))
        {
            State previousState = states.pop();
            if (previousState.getRoom() == currentRoom){ //If we are still in the same room, we pop again
                previousState = states.pop();
            }
            
            // Seting the actual state equal to the previous state
            currentRoom = previousState.getRoom();      
            Harry.previousItems(previousState.getItemsList());
            
            // putting the previous state (actual state after we pop)
            // again in the stack, so that it doesn't get lost
            states.push(previousState); 
            
        }
        // else
        // {
            // System.out.println("\n nowhere to go back \n");
        // }
        
        // System.out.println(currentRoom.getLongDescription());
    }
    
    
    /** 
     * Try to pick up an item. If there is an item,pick it up,
     * otherwise print an error message.
     */
    public void pickUp(String item) 
    {
        // if(!command.hasSecondWord()) {
            // // if there is no second word, we don't know where to go...
            // System.out.println("Pick up what?");
            // return;
        // }

        // String itemkey = command.getSecondWord();

        // if (itemToHold == null) {
            // System.out.println("There is no such item! Type item name as shown!");
            // return;
        // }
        
        Item itemToHold = currentRoom.getItem(item);
        Harry.setItemToWizard(itemToHold);

    }

    /** 
     * Try to cast a spell. If the spells are not allowed in the current room, return error.
     * Lose the game if Harry doesn't possess the right items or if he doesn't cast the right spell.
     * Else win the game.
     */
    public boolean spell(String spell) 
    {
        // if (!currentRoom.areSpellsAllowed()){
            // System.out.println("Sorry, there are no spells allowed in this room!");
            
            // return false;
        // }

        // if(!command.hasSecondWord()) {
            // // if there is no second word, we don't know where to go...
            // System.out.println("which spell?");
            // return false;
        // }

        // String nameOfSpell = command.getSecondWord();

        // if (!nameOfSpell.equals("expeliarmus") &&  !nameOfSpell.equals("avadaKedavra")) {
            // System.out.println("There is no such Spell!");
            
            // return false;
        // }

        if (!wizardHasNeededItems()){
            // System.out.println("You lose!");
            return false;    
        }

        if(spell.equals("expeliarmus"))
        {
            // System.out.println("You win!");
            return true;
        }

        // System.out.println("You lose!");
        return false;        
    }
    
    
    /** 
     * Listing the picked up items
     */
    private void showPickedUpItems ()
    {
        System.out.println("Picked up items are in this order: ");
        Harry.printItems();

    }

    public ArrayList<String> pickedUpItems()
    {
        ArrayList<String> returnPickUpItems = new ArrayList<>();
        ArrayList<Item> pickedUpItems = Harry.pickedUpItems();
        
        for(Item item: pickedUpItems)
        {
            returnPickUpItems.add(item.getDescription());
        }
        
        return returnPickUpItems;
    }
    
    /** 
     * Check if Harry possesses the right items in the right order
     */
    private boolean wizardHasNeededItems()
    {
        ArrayList<String> itemsOrder;
        itemsOrder = Harry.listOfItems();

        if (itemsOrder.size() < 3){
            
            return false;
        }

        String firstItem = itemsOrder.get(0);
        String secondItem = itemsOrder.get(1);
        String thirdItem = itemsOrder.get(2);

        if(firstItem.equals("ElderWand") && secondItem.equals("StoneOfResurrection") && thirdItem.equals("InvisibilityCloak"))
        {
            return true;
        }

        return false;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    // public boolean quit() 
    // {
        // // if(command.hasSecondWord()) {
            // // System.out.println("Quit what?");
            // // return false;
        // // }
            // return true;  // signal that we want to quit
        
    // }
}
