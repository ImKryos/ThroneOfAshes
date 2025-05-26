import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    // ANSI escape codes for text color
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    // Pause (sleep) method
    public static void pause (int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Flavor wrapper to properly color attack messages, both before and after other colors used in the flavor
    public static String wrapFlavor(String flavor, String outerColor) {
        return outerColor + flavor.replace(Main.RESET, Main.RESET + outerColor) + Main.RESET;
    }


    // Method that will be used to determine how many heroes are still alive
    public static int countAlive(Hero[] heroes) {
        int aliveCount = 0;

        for (Hero hero : heroes) {
            if (hero.isAlive()) aliveCount++;
        }
        return aliveCount;

    }


    // ─── LAUNCH GAME METHOD  ─────────────────────────
    public static void launchGame() {

        // Setup scanner for player input
        Scanner scanner = new Scanner(System.in);

        // Create available heroes array
        Hero[] heroes = {
                new Knight("Aragorn"),
                new Mage("Gandalf"),
                new Archer("Legolas")

        };

        // Print list of selectable heroes
        for (int i = 0; i < heroes.length; i++) {
            System.out.println(i + 1 + ". " + heroes[i].getName());
        }

        // Ask player for input to select hero. Their input -1 is stored in playerIndex
        System.out.print("Select your hero: ");
        int input = scanner.nextInt();
        int playerIndex = input - 1;

        // ─── Start of battle  ─────────────────────────
        BattleManager battle = new BattleManager(scanner, heroes, playerIndex);
        battle.startCombat();
    }


    // ─── Main Method ─────────────────────────
    public static void main(String[] args) {
        GameLauncher title = new GameLauncher();
        title.showTitle();
        title.showMainMenu();

    }
}