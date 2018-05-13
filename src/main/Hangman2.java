import java.util.ArrayList;
import java.util.Scanner;

public class Hangman2 
{//Start of Main Class

    public static String hiddenWord;
    public static String currentMaskedWord;
    public static String hint;
    public static ArrayList  guessed;
    public static int numtries = 4;

    public static void getAWord()
    {
        System.out.println("Enter the hidden word for the other player to guess");

        try
        {
            Scanner hiddenWordScan =new Scanner(System.in);
            
            hiddenWord = hiddenWordScan.nextLine();
            currentMaskedWord = "";

            //computeMaskedWord();
        }
        catch(Exception e)
        {
            System.out.println("An error has occured");
            System.out.println(e.toString());
        }

    }

    public static void getHint()
    {
        System.out.println("Enter a hint about the word you entered:");

        try
        {
            Scanner HintScanner =new Scanner(System.in);

            hint = "";

           hint = HintScanner.nextLine();
        }
        catch(Exception e)
        {
            System.out.println("An error has occured");
            System.out.println(e.toString());
        }
        int i=1;
    }
    
    public static void computeMaskedWord()
    {
        currentMaskedWord = hiddenWord;

        for(int i=0; i<currentMaskedWord.length();i++)
        {
            Boolean notfound = true;
            for(int j = 0; j<guessed.size(); j++)
            {
                String currentArray = guessed.toArray()[j].toString();
                String currentGuess = "" + currentMaskedWord.charAt(i);

                currentArray = currentArray.toLowerCase();
                currentGuess = currentGuess.toLowerCase();

                if(currentGuess.charAt(0) == currentArray.charAt(0))
                {
                    notfound = false;
                }
            }

            if(notfound && 
                    (((((int)currentMaskedWord.charAt(i))>=65) &&
                    (((int)currentMaskedWord.charAt(i))<=90)) ||
                    ((((int)currentMaskedWord.charAt(i))>=97) &&
                    (((int)currentMaskedWord.charAt(i))<=122))))
            {
                 currentMaskedWord = currentMaskedWord.replace(currentMaskedWord.charAt(i), '*');
            }
        }
        
    }

    public static void checkAGuessedLetter(char guess)
    {
        if(hiddenWord.indexOf(guess) != -1)
        {
            guessed.add(guess);
        }
        else
        {
            numtries = numtries -1;
        }

        //computeMaskedWord();
    }
    
    public static char getAGuessedLetter()
    {
        System.out.println("Guess a letter that is in the hidden word");
        char guessChar = '\n';
        String guessString;
        
        try
        {
            Scanner guess = new Scanner(System.in);
            
            guessString = guess.next();
            
            guessChar = guessString.charAt(0);
        }
        catch(Exception e)
        {
            System.out.println("An error has occured");
            System.out.println(e.toString());
        }
        
        return guessChar;
    }

    public static void main(String[] args) 
    {//Start of Main
        
    //CONSTRUCTORS
        Scanner Scan=new Scanner(System.in);
        
    //Variables 
        String Name,yesno,host,guest;
        guessed = new ArrayList();
        int i=0;
        
    //Code
        System.out.println("WELCOME TO EDUCATIONAL HANGEMAN");
        
        System.out.println("Enter your Name:");
        Name=Scan.nextLine();
        
        System.out.println("Want to Play HANGMAN ?");
        System.out.println("Enter Y(yes) or N(no)");
        yesno=Scan.nextLine();
        if(yesno.equals("y"))
        {
            getAWord();
            getHint();

            //hide the users input
            for(int j=0; j<30; j++)
            {
                System.out.println("\n");
            }
            while(true)
            {
                System.out.println("\n\n");
                System.out.println("Your knowedge of the hidden word is:\n" + currentMaskedWord);
                System.out.println("The provided hint is:\n"+hint);
                if(numtries > 1)
                {
                    System.out.println("You currently have " + numtries + " tries remaining.");
                }
                else
                {
                    System.out.println("You currently have " + numtries + " try remaining.");
                }
                char userGuess = getAGuessedLetter();
                checkAGuessedLetter(userGuess);
                
                if(hiddenWord.equals(currentMaskedWord))
                {
                    System.out.println("congratulations you guessed the hidden word: " + hiddenWord);
                    break;
                }
                else if(numtries <= 0)
                {
                    System.out.println("You have failed to guess the hidden word: " + hiddenWord);
                    break;
                }

                System.out.println("Please guess again.");

            }

//            try
//            {//Start of TRY
//                FileInputStream fis = new FileInputStream("C:\\Users\\Vydehi\\Documents\\NetBeansProjects\\hangman\\src\\hangman\\test.txt");
//                BufferedInputStream bis = new BufferedInputStream(fis);
//                DataInputStream dis = new DataInputStream(bis);
//
//                while ((dis.available())!= 0 )
//                {//Start of while
//                    System.out.println("This is your word"+(i+1));
//                    String intext=dis.readLine();
//                    for( i =0;i<intext.length();i++)
//                     {
//                       System.out.print('-');
//                     }
//                    guest = Scan.nextLine();
//                     if(intext.equals(guest));
//                       System.out.println("CORRECT");
//
//
//
//                }//End of while
//                      }//End of TRY
//         catch(Exception e)
//         {//Start of CATCH
//                 System.out.println("error" + e);
//         }//End of CATCH
        }
                
    }//End of Main 

}//End of Main Class