import java.util.*;
import java.io.*;
/**
 * This class contains the driver for the Hang Man program.  This program
 * asks the user to choose from a list of topics, then chooses a phrase
 * randomly from the tipic file and uses that in a game of hang man.
 * 
 * @author Matthew O'Neill 
 * @version 2/20/19
 */
public class GamePlay
{
   /**
    * This is the main method for the hang man program's driver class.
    * In this method, a user is asked to choose from a list of categories,
    * then a phrase is randomly generated from a list. Then, the user
    * plays a game of hang-man to guess the phrase.
    */
   public static void main(String[ ] args)throws IOException{
        Scanner in = new Scanner(System.in);
        char guess; //letter guess of user
        //create object so you can use in while loop later
        GameWord myPhrase = new GameWord("one day at a time" );
       
        //generate random line in file
        Random rand = new Random();
        int randomLine = rand.nextInt(20);
        
        Scanner line = new Scanner(System.in);
        
        //display categories
        System.out.println("Choose from the categories:");
        System.out.println("Enter '1' for sayings: ");
        System.out.println("Enter '2' for Premier League Teams: ");
        System.out.println("Enter '3' for Liverpool Players");
        System.out.println("Enter '4' for famous current NBA players: ");
        System.out.println("Enter '5' for famous UNC players: ");
        
        //get choice from user
        Scanner otherScanner = new Scanner(System.in);
        int category = otherScanner.nextInt();
        
        //validate user input
        while(category < 1 || category > 5){
            System.out.println("Your input was incorrect. Enter 1-5:");
            category = otherScanner.nextInt();
        }
        
        //use user choice to read from specific file
        switch(category){
            case 1:
                line = new Scanner(new File("sayings.txt"));
            break;
            case 2:
             line = new Scanner(new File("premiere_league_teams.txt"));
            break;
            case 3:
                line = new Scanner(new File("liverpool_players.txt"));
            break;
            case 4:
                line = new Scanner(new File("nba_players.txt"));
            break;
            case 5:
            line = new Scanner(new File("unc_players.txt"));
            break;
        }
        //count used to keep track of line number
        int count = 0;
        String tempString;
        while(line.hasNext()){
            //if random line is same as count, call setPhrase method
            if(count == randomLine){
                tempString = line.nextLine();
                tempString = tempString.toLowerCase();
                myPhrase.setPhrase(tempString);
                break;
            }
            //else just go to next line
            else{
                line.nextLine();
            }
            //increment count
            count++;
        }
       
       boolean [ ] used = new boolean[255];
       //diplay string
       System.out.println("\n" + myPhrase.toString( ));
       //check for win, then get letter from user
       while(!myPhrase.checkWin( ) && ! myPhrase.getGameOver( ))
       {
         do{
           System.out.print("\nEnter your character: ");
           guess = in.nextLine( ).charAt(0);
           if(used[guess]) System.out.println("That letter already guessed.");
        }while (used[guess]);
         used[guess] = true; 
         myPhrase.find(guess);
         System.out.println("\n" + myPhrase.toString( ));
       }
       //display win message
       if(myPhrase.checkWin( ))
       {
         System.out.println("You got it!");
       }
        
       System.out.println("Game Over");  
       System.out.println("Game Over"); 
       System.out.println("Game Over"); 
       System.out.println("Game Over"); 
    }
}
