import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author  Aldo Bega
 * @version 31.03.2021
 */
public class GUI implements ActionListener
{
    // instance variables - replace the example below with your own
    private static JFrame frame;
    private static JPanel panel1;
    private static JPanel panel2;
    private static JPanel panel3;
    private static JPanel panel4;
    private static JPanel panel5;
    private static JButton showItems;
    private static JButton spell;
    private static JButton quit;
    private static JButton pickUp;
    private static JButton back;
    private static JButton north;
    private static JButton south;
    private static JButton west;
    private static JButton east;
    private static JLabel label;
    private static JTextField textField;
    private static JTextArea textArea;
    private static JScrollPane scroll;
    private static JList list;
    private Game game;
    
    

    /**
     * Constructor for objects of class GUI
     */
       public GUI()
       {
            panel1 = new JPanel();
            panel2 = new JPanel();
            panel3 = new JPanel();
            panel4 = new JPanel();
            panel5 = new JPanel();
            
            frame = new JFrame();
            frame.setSize(1000, 600);
            
            
            textArea = new JTextArea(30,60);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            textArea.setText("Welcome to the World of Harry Potter and The Deathly Hallows!");
            textArea.append("\nHelp Harry defeat Lord Voldemort and save the Wizarding World!.");
            textArea.append("\nFor Harry to defeat Lord Voldemort and his army, he has to first become the Master Of Death.");
            textArea.append("\nWhen you become the Master of Death, you are immortal.");
            textArea.append("\nBut what do you need to become the Master Of Death? ");
            textArea.append("\nYou need to possess the Deathly Hallows in order.\nElder Wand\nStone of Resurrection\nThe Invisibility Cloak");
            textArea.append("\nAfter possesing the Deathly Hallows in order,\n Harry needs to go to the Battle Ground and he has to fight Las ord Voldemort, \nbut he has to choose the right spell: Expeliarmus");
            textArea.append("\n\n The commands are as follows:");
            textArea.append("\n back: sends you back to the previous room");
            textArea.append("\n show items: shows the items you have picked up");
            textArea.append("\n Quit: Quit the game");
            textArea.append("\n North, South, West, East: the directions of the other rooms.");
            
        
        
            scroll = new JScrollPane(textArea);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setEnabled(true);
            
                scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
                        public void adjustmentValueChanged(AdjustmentEvent e) {  
                            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
                        }
                    });
            
            panel5.add(scroll);
            frame.add(panel1);
            
            showItems = new JButton("Show Items");
            showItems.addActionListener(this);
            
            quit = new JButton("Quit");
            quit.addActionListener(this);
            
            back = new JButton("Back");
            back.addActionListener(this);
            
            spell = new JButton("Spell");
            spell.addActionListener(this);
            
            pickUp = new JButton("Pick Up");
            pickUp.addActionListener(this);
            
            north = new JButton("North");
            north.addActionListener(this);
            
            south = new JButton("South");
            south.addActionListener(this);
            
            west = new JButton("West");
            west.addActionListener(this);
            
            east = new JButton("East");
            east.addActionListener(this);
            
            
            panel1.setLayout(new BorderLayout());
            panel3.setLayout(new GridLayout(4,1));
            panel4.setLayout(new GridLayout(1,4));
            
            panel4.add(showItems);
            panel4.add(back);
            panel4.add(quit);
            // panel4.add(spell);
           
            panel3.add(north);
            panel3.add(south);
            panel3.add(west);
            panel3.add(east);
            
            game = new Game();
       
            String currentRoomDesc = game.getCurrentRoomDescription(); 
            textArea.append("\n\n" + currentRoomDesc);
            
            panel1.add(panel5, BorderLayout.CENTER);
            panel1.add(panel2, BorderLayout.WEST);
            panel1.add(panel3, BorderLayout.EAST);
            panel1.add(panel4, BorderLayout.SOUTH);
            
            
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {   
        
    if (e.getSource() == pickUp)
    {
       Object selectedItem = this.list.getSelectedValue();
      // JOptionPane.showMessageDialog(null, selectedItem.toString());
       
        if(selectedItem != null)
        {
        game.pickUp(selectedItem.toString());
        textArea.append("\n\n Harry just picked up the ");
        textArea.append(selectedItem.toString());
       }
    }
        
    if (e.getSource() == quit)
    {
       disableButtons();
       textArea.append("\n\n Thank You for playing");
    }
        
    if (e.getSource() == showItems)
    {
     textArea.append("\n\n Picked up items are in this order:  ");
     
     ArrayList<String> pickedUpItems = game.pickedUpItems();
     
     for(String item: pickedUpItems)
     {
         textArea.append("\n" + item);
     }
    }
    
    else if (e.getSource() == back)
    {
      game.goBack();
      printDescription();
      addItems();
    }
    
    else if (e.getSource() == spell)
    {
      spells();
    }
    
    else if (e.getSource() == north)
    {
     if(game.goRoom("north") == false)
        textArea.append("\n\nNo door!");
        
     else{
         printDescription();
         panel2.removeAll();
         frame.revalidate();
         
         if(game.getRoomDescription().equals("in the Battle Ground and here he has to fight Lord Voldemort. He has to choose the right spell!"))
         {
           addSpells();
         }
        }
    }
    
    else if (e.getSource() == south)
    {
     
     if(game.goRoom("south") == false)
        textArea.append("\n\nNo door!");
     else
     {
         printDescription();
         addItems();
        }
    }
    
    else if (e.getSource() == west)
    {
     if(game.goRoom("west") == false)
        textArea.append("\n\nNo door!");
     else
     {
         printDescription();
         addItems();
        }
    }
    
    else if (e.getSource() == east)
    {
     if(game.goRoom("east") == false)
        textArea.append("\n\nNo door!");
     else
     {
         printDescription();
         addItems();
        }
    }
    
   
}

    private void printDescription(){
        String currentRoomDesc = game.getCurrentRoomDescription(); 
        textArea.append("\n\n" + currentRoomDesc);
         
        
    }
    
    private void  addItems()
    {
        ArrayList<String> currentRoomItems = game.getCurrentRoomItems();
        this.list = new JList(currentRoomItems.toArray());
        
        panel2.removeAll();
        
        if(!game.getRoomDescription().equals("standing at the Great Hall of Hogwarts School of Witchcraft and Wizardry"))
         {
           panel2.setLayout(new GridLayout(2,1));
           panel2.add(list);
           panel2.add(pickUp);
         }
        
        
     
        frame.revalidate();
    }
    
    private void addSpells()
    {
        String[] spells = {"expeliarmus", "Avada Kedavra"};
        this.list = new JList(spells);
        
        panel2.removeAll();
        panel2.setLayout(new GridLayout(2,1));
        panel2.add(list);
        panel2.add(spell);
        
        frame.revalidate();
    }
    
    private void spells()
    {
        Object selectedItem = this.list.getSelectedValue();
      
       if(selectedItem != null)
       {
              if(game.spell(selectedItem.toString()) == true)
              {
                textArea.append("\n\n Congratulations, You win ");
                disableButtons();
              }
              
              else 
            {
             textArea.append("\n\n Either you casted the wrong spell ");
             textArea.append(" Or you don't have the necessary items to win ");
             textArea.append("\n Game Over, You lose ");
             disableButtons();
            }
      }
    }
    
    private void disableButtons()
    {
        back.setEnabled(false);
        pickUp.setEnabled(false);
        quit.setEnabled(false);
        showItems.setEnabled(false);
        north.setEnabled(false);
        south.setEnabled(false);
        east.setEnabled(false);
        west.setEnabled(false);
        spell.setEnabled(false);
    }

}
