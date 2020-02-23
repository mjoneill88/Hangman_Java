import java.io.*;
import java.util.Random;
import java.util.Scanner;
/**
 * This class contains the GameWorld class. This class contains the fiels
 * and methods necessary to play the hang man program.
 * 
 * @author Matthew O'Neill 
 * @version 2/20/19
 */
public class GameWord
{
   
   private String phrase; //phrase to guess
   private StringBuilder inProgress; //to modify string
   private int numberWrongGuesses; //keeps track of wrong guesses
   //holds the current state of the partially guessed string
   private StringBuilder state;
   private boolean gameOver; //determines if game is over or not
   
   /**
    * This no-args constructor sets the default values of the fields to 0,
    * null, or false.
    */
   public GameWord( )
   {
       inProgress = new StringBuilder( );
       phrase = new String( );
       numberWrongGuesses = 0;
       state = new StringBuilder( );
       gameOver = false;
    }
    
    /**
     * This constructor takes in a phrase and uses it to set the fields
     * necessary to play the hang man game.
     * @param String phraseToGuess - phrase that is to be guesses in hang man game
     */
    public GameWord(String phraseToGuess)
    {
        phrase = new String(phraseToGuess);
        state = new StringBuilder( );
        //use phraseToGuess to set other fields
        inProgress = new StringBuilder( );
        for(int k = 0; k < phrase.length( ); k++)
        {
            inProgress.append( "_ ");
        }
        numberWrongGuesses = 0;
        gameOver = false;
    }
        
    /**
     * This set method sets the phrase field and then sets the rest of the
     * fields using the phraseToGuess parameter
     * @param String phraseToGuess - phrase go be guessed in hang man  game
     */
    public void setPhrase(String phraseToGuess)
    {
        phrase = new String(phraseToGuess);
        state = new StringBuilder( );
        //use phraseToGuess to set other fields
        inProgress = new StringBuilder( );
        for(int k = 0; k < phrase.length( ); k++)
        {
            inProgress.append( "_ ");
        }
        numberWrongGuesses = 0;
        state = new StringBuilder( );
        gameOver = false;
    }
    
    /**
     * This get method returns the boolean gameOver
     * @return boolean getGameOver - boolean determining if game is over
     */
    public boolean getGameOver( )
    {
        return gameOver;
    }
    
    /**
     * This method searches the phrase string for the character entered
     * by the user.
     */
    public void find(char symbol)
    {
        int count = 0;
        int pos = 0;
        //find position of user's guess if it exists in the string
        pos = phrase.indexOf(symbol);
        while(pos != -1)
        { 
          count++;
          inProgress.setCharAt(pos*2,symbol);
          pos = phrase.indexOf(symbol, pos + 1);;
        }
        if(count == 0)
        {   
            //increment number of wrong guesses
            numberWrongGuesses++;
            updateState( );
        }
    }
    
  /**
   * This method checks to determine if the guess of the user is correct.
   * @return boolean match - boolean representing if character matches
   * letter in the phrase.
   */
  public boolean checkWin( )
   {
      boolean match = true;
      //check if user has won
       for(int k = 0, m = 0; k < phrase.length( ) && match; k++, m += 2)
      {
          if(phrase.charAt(k) != inProgress.charAt(m))
          {
              match = false;
         
          }
      }
      return match;
    }
   
   /**
    * This method updates the string that is used for the hang man and
    * displays the hang-man-like depiction.
    */
   private void updateState( )
   { 
       switch(numberWrongGuesses)
       {
           //build hang man depiction
           case 1: state.append("\n\t\t\t\t\t 0");
                   break;
           case 2: state.append( "\n\t\t\t\t       /");
                   break;
           case 3: state.append(" |");
                   break;
           case 4: state.append( " \\");
                   break;
           case 5: state.append("\n\t\t\t\t         |");
                   break;
           case 6: state.append("\n\t\t\t\t        /");
                   break;
           case 7: state.append(" \\\n\n\t\t\t\t      OH NO!!\n");
                   gameOver = true;
      }
  }
  
  /**
   * This method returns a string representing the progress of the game.
   * @return String -string representing the state of the game.
   */
  public String toString(  )
  {
      //display string
      return new String("\n***************************************\n" 
            + inProgress + "\n" + state.toString( ));
  }
}