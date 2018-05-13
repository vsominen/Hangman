package edu.villanova.team;

import java.util.Scanner;
import edu.villanova.team.helpers.StringHelpers;


/*
 * This is the business logic for the Hangman game
 */
public class Hangman1 {

        // This controls the number of guesses allowed
        private static final int GUESSCOUNT = 10;
        
        private String guessMe; 
        private char guess;
        private String incorrectGuesses = "";
        private String hint;
        private char [] hidden;
        private int badGuesses = 0;
        private boolean win =  false;
        
        /* Prompt player 1 to enter a string */
        private void getSecretWord () {
                Scanner s = new Scanner (System.in);
                s.useDelimiter(System.getProperty("line.separator"));
                System.out.println ("Player 1 Enter a string: ");
                guessMe = s.next().toLowerCase();
        }
        
        /* Prompt player 1 to enter a hint */
        private void getHint () {
                Scanner s = new Scanner (System.in);
                System.out.println ("Player 1 Enter a hint: ");
                hint = s.next ();
        }
        
        /* Initialize hidden with _ for any letter or number */
        private void initHiddenString () {
                hidden = new char[guessMe.length()];
                StringHelpers.fillHiddenString(hidden, guessMe);
        }
        
        /* Player 2 guesses a letter */
        private void guessLetter () {           
                while (true)
                {
                        Scanner s = new Scanner (System.in);
                        System.out.print( "\nPlayer 2 Guess a letter: " );
                        String tempGuess = s.next();
                        if (tempGuess.length() > 1)
                        {
                                System.out.print( "\nPlease enter only 1 letter at a time. \n");
                        }
                        else
                        {
                                guess =  Character.toLowerCase(tempGuess.charAt(0));    
                                break;
                        }
                }
        }
                
        /* Handle bad guesses */
        
        private void handleMisMatch () {
                badGuesses++; // Increment badguess if a match was not found
                if (badGuesses == 1){   
                        incorrectGuesses += guess; 
                }
                else{   
                        incorrectGuesses += (", " + guess);
                }
        }
        
        /* Check if the player has won the game already */
        
        private void checkForWin () {
                String hiddenString = new String (hidden);
                 if (hiddenString.equals(guessMe)) {
                         win = true; // Player wins as all the letters in the word were guessed
                 }
        }
        
        /* Keep guessing a word as long as any result is not reached i.e. win or loss */
        
        private void guessWord () {
                do {     
                         HangManDrawer.showStatus (hidden, badGuesses, GUESSCOUNT, incorrectGuesses);
                         System.out.println ("Hint: " + hint);
                         guessLetter ();
                         boolean correctGuess = StringHelpers.searchAndReplace (hidden, guess, guessMe);
                         checkForWin ();
                         if (!correctGuess) {
                                 handleMisMatch ();
                         }
                 }while (badGuesses < GUESSCOUNT && !win);
        }
        
        /* The chief function */
        
         public void playGame () {
                 getSecretWord ();
                 getHint ();
                 initHiddenString ();
                 StringHelpers.clear_screen(); 
                 guessWord ();
                 HangManDrawer.displayResult (win, guessMe, badGuesses);
         }
}