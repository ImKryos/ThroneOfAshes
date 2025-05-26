import java.util.Scanner;

public class GameLauncher {

    private Scanner scanner = new Scanner(System.in);

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    // ─── Title Banner ─────────────────────────────
    public void showTitle() {
        clearScreen();
        System.out.println(Main.PURPLE + "▄▄▄█████▓ ██░ ██  ██▀███   ▒█████   ███▄    █ ▓█████     ▒█████    █████▒       ▄▄▄        ██████  ██░ ██ ▓█████   ██████ ");
        System.out.println("▓  ██▒ ▓▒▓██░ ██▒▓██ ▒ ██▒▒██▒  ██▒ ██ ▀█   █ ▓█   ▀    ▒██▒  ██▒▓██   ▒       ▒████▄    ▒██    ▒ ▓██░ ██▒▓█   ▀ ▒██    ▒ ");
        System.out.println("▒ ▓██░ ▒░▒██▀▀██░▓██ ░▄█ ▒▒██░  ██▒▓██  ▀█ ██▒▒███      ▒██░  ██▒▒████ ░       ▒██  ▀█▄  ░ ▓██▄   ▒██▀▀██░▒███   ░ ▓██▄   ");
        System.out.println("░ ▓██▓ ░ ░▓█ ░██ ▒██▀▀█▄  ▒██   ██░▓██▒  ▐▌██▒▒▓█  ▄    ▒██   ██░░▓█▒  ░       ░██▄▄▄▄██   ▒   ██▒░▓█ ░██ ▒▓█  ▄   ▒   ██▒");
        System.out.println("  ▒██▒ ░ ░▓█▒░██▓░██▓ ▒██▒░ ████▓▒░▒██░   ▓██░░▒████▒   ░ ████▓▒░░▒█░           ▓█   ▓██▒▒██████▒▒░▓█▒░██▓░▒████▒▒██████▒▒");
        System.out.println("  ▒ ░░    ▒ ░░▒░▒░ ▒▓ ░▒▓░░ ▒░▒░▒░ ░ ▒░   ▒ ▒ ░░ ▒░ ░   ░ ▒░▒░▒░  ▒ ░           ▒▒   ▓▒█░▒ ▒▓▒ ▒ ░ ▒ ░░▒░▒░░ ▒░ ░▒ ▒▓▒ ▒ ░");
        System.out.println("    ░     ▒ ░▒░ ░  ░▒ ░ ▒░  ░ ▒ ▒░ ░ ░░   ░ ▒░ ░ ░  ░     ░ ▒ ▒░  ░              ▒   ▒▒ ░░ ░▒  ░ ░ ▒ ░▒░ ░ ░ ░  ░░ ░▒  ░ ░");
        System.out.println("  ░       ░  ░░ ░  ░░   ░ ░ ░ ░ ▒     ░   ░ ░    ░      ░ ░ ░ ▒   ░ ░            ░   ▒   ░  ░  ░   ░  ░░ ░   ░   ░  ░  ░  ");
        System.out.println("          ░  ░  ░   ░         ░ ░           ░    ░  ░       ░ ░                      ░  ░      ░   ░  ░  ░   ░  ░      ░  ");
        System.out.println();
        System.out.println();
        Main.pause(1000);
        showTagLine();
        System.out.println();
        Main.pause(5000);
    }

    // ─── Tagline Delivery ─────────────────────────
    public void showTagLine() {
        System.out.print(Main.RESET + "                                       The throne is lost.");
        Main.pause(1000);
        System.out.println("   The empire is cinder.");
        Main.pause(1500);
        System.out.print(Main.RED + "                                                  The ");
        Main.pause(250);
        System.out.print("ash ");
        Main.pause(250);
        System.out.println("remembers." + Main.RESET);
    }

    public void showMainMenu() {
        while (true) {
            clearScreen();
            System.out.println(Main.RED + "╔════════════════════════════════╗");
            System.out.println("             " + Main.PURPLE + "MAIN MENU" + Main.RED);
            System.out.println("╚════════════════════════════════╝" + Main.RESET);
            System.out.println("1. Start Game");
            System.out.println("2. How to Play");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Enter choice: ");
            String input = scanner.next();

            switch (input) {
                case "1":
                    startGame();

                    break;
                case "2":
                    // showHelp();
                    System.out.println("Show Help is a work in progress.");
                    break;
                case "3":
                    quitGame();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ash cannot shape what never was.");
                    Main.pause(1000);
            }
        }
    }

    public void quitGame() {
        System.out.println("Ash falls... and your tale remains unfinished.");
        Main.pause(1500);
        System.exit(0);
    }

    public void startGame() {
        clearScreen();
        System.out.println("The ash begins to stir...");
        Main.pause(1500);
        Main.launchGame();
    }

}
