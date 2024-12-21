import java.util.Scanner;
import java.util.Random;

class Bagels {
    private static final int ARRAY_SIZE = 3;
    private static final int RANDOM_MAX = 10;
    static boolean letsPlayAGame = true;
    static Scanner input = new Scanner(System.in);
    static int[] number;

    public static void main(String[] args) {
        System.out.println("Welcome to Bagels, a deductive logic game!");
        System.out.println("Rules:");
        System.out.println("\tI will generate a random 3 digit number (between 000 and 999, if that helps).");
        System.out.println("\tEnter numbers to guess! I will provide the following hints:");
        System.out.println("\t\tPico = One digit is correct, but in the wrong position.");
        System.out.println("\t\tFermi = One digit is correct, and in the right position.");
        System.out.println("\t\tBagels = There are no correct digits.");
        System.out.println("\t\t\tI may say Pico or Fermi multiple times. For example: Fermi Fermi, or Pico Pico Pico.");

        number = generateNumbers();

        do{
            System.out.println("Enter a whole integer to play or -1 to quit:");
            String choice = input.next();
            if(Integer.parseInt(choice) < 0){
                letsPlayAGame = false;
            } else {
                int[] guess = parseGuess(choice);
                
                if(guess[0] == -1) {
                    System.out.println("You must guess 3 numbers at a time.");
                    continue;
                }

                String result = result(number, guess);
                if(result == "Correct") {
                    System.out.println("You've got it!\n\n\t\tCongratulations!\n\nUntil next time :)");
                    break;
                }
                
                System.out.println(result);
            }
        }while(letsPlayAGame);
    }

    private static int[] generateNumbers() {
        Random src = new Random();
        int[] arr = {src.nextInt(RANDOM_MAX), src.nextInt(RANDOM_MAX),src.nextInt(RANDOM_MAX)};
        return arr;
    }
    private static int[] parseGuess(String answer) {
        if(answer.length() != ARRAY_SIZE){
            int[] fail = {-1};
            return fail;
        }
        int[] result = new int[ARRAY_SIZE];
        for(int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) != '0') result[i] = Character.getNumericValue(answer.charAt(i));
        }
        return result;
    }
    private static String result(int[] number, int[] guess) {
        String outcome = "";
        for(int y = 0; y < number.length; y++) {
            for(int x = 0; x < guess.length; x++) {
                if(guess[x] == number[y] && guess[y] != number[y] ){
                    outcome += "Pico";
                    break;
                }
                else if(guess[x] == number[y] && guess[y] == number[y] ) {
                    outcome += "Fermi";
                    break;
                }
            }
        }
        if(outcome.contains("FermiFermiFermi")) outcome = "Correct";
        if(outcome.length() == 0) outcome = "Bagel";
        return outcome;
    }
}