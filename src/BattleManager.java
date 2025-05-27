import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BattleManager {

    private Scanner scanner;
    private Hero[] heroes;
    private int playerIndex;

    public BattleManager(Scanner scanner, Hero[] heroes, int playerIndex) {
        this.scanner = scanner;
        this.heroes = heroes;
        this.playerIndex = playerIndex;
    }

    public void startCombat() {
        while (Main.countAlive(heroes) > 1) {
            for (int i = 0; i < heroes.length; i++) {
                Hero attacker = heroes[i];
                if (!attacker.isAlive()) continue;

                if (i == playerIndex) playerTurn(i, attacker);
                else aiTurn(i, attacker);
            }
            printEndOfRound(heroes);
        }
        for (Hero hero : heroes) {
            if (hero.isAlive()) {

                // If player is last hero, display victory message
                if (hero == heroes[playerIndex]) {
                    System.out.println("\n" + Main.GREEN + Main.BOLD + "Victory! " + hero.getName() + " is the last hero standing!" + Main.RESET);
                }
                // Otherwise, display defeat message
                else {
                    System.out.println(Main.RED + Main.BOLD + "Defeat! " + hero.getName() + " is the last hero standing!" + Main.RESET);
                    System.out.println(Main.RED + "The ashes whisper your name, one last time..." + Main.RESET);
                }
            }
        }
        Main.pause(3000);
    }

    /**
     * --- COMBAT: PLAYER TURN --- Handles player's turn during combat
     *
     * <p>Prompts the user to select a valid target, executes an attack, and displays flavor text
     *
     * @param playerIndex the index of the player in the heroes array
     * @param attacker the Hero object representing the attacking player
     */
    private void playerTurn(int playerIndex, Hero attacker) {
        attacker.reduceCooldown();
        Main.pause(1000);
        System.out.println("Your turn! Choose an action: ");

        int actionChoice = 0;

        while (true) {
            System.out.println("1. Attack");
            System.out.println("2. Skill");

            System.out.print("Enter your choice: ");
            try {
                actionChoice = scanner.nextInt();

                if (actionChoice != 1 && actionChoice != 2) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        if (actionChoice == 1) {
            System.out.println("Choose a target to attack: ");
            int option = 1;
            int[] validTargets = new int[heroes.length];

            for (int index = 0; index < heroes.length; index++) {
                if (index != playerIndex && heroes[index].isAlive()) {
                    System.out.println(option + ". " + heroes[index].getName());
                    validTargets[option - 1] = index; // Map display option back to actual hero index
                    option++;
                }
            }

            int targetIndex;
            while (true) {
                System.out.print("Enter your target: ");
                try {
                    int chosen = scanner.nextInt();
                    if (chosen < 1 || chosen >= option) {
                        System.out.println("Invalid choice. Please choose a number between 1 and " + (option - 1));
                        continue;
                    }
                    targetIndex = validTargets[chosen - 1];
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            Hero target = heroes[targetIndex];
            executeAttack(attacker, target);

        } else {
            boolean skillUsedSuccessfully = false;
            while (!skillUsedSuccessfully) {
                skillUsedSuccessfully = attacker.useSkill(heroes, playerIndex, scanner);

                if (!skillUsedSuccessfully) {
                    System.out.println("Skill could not be used. Please choose a different action:");
                    System.out.println("1. Try skill again");
                    System.out.println("2. Basic attack");

                    int retryChoice = 0;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            retryChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (retryChoice == 1 || retryChoice == 2) break;
                            else System.out.println("Please enter 1 or 2.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine();
                        }
                    }

                    if (retryChoice == 2) {
                        System.out.println("Choose a target to attack: ");
                        int option = 1;
                        int[] validTargets = new int[heroes.length];

                        for (int index = 0; index < heroes.length; index++) {
                            if (index != playerIndex && heroes[index].isAlive()) {
                                System.out.println(option + ". " + heroes[index].getName());
                                validTargets[option - 1] = index;
                                option++;
                            }
                        }

                        int targetIndex;
                        while (true) {
                            System.out.print("Enter your tarter: ");
                            try {
                                int chosen = scanner.nextInt();
                                if (chosen < 1 || chosen >= option) {
                                    System.out.println("Invalid choice. Please choose a number between 1 and " + (option - 1));
                                    continue;
                                }
                                targetIndex = validTargets[chosen - 1];
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.nextLine();
                            }
                        }

                        Hero target = heroes[targetIndex];
                        executeAttack(attacker, target);
                        skillUsedSuccessfully = true;
                    }
                }
            }
        }
    }


    /**
     * --- COMBAT: AI TURN --- Handles the AI turns during combat
     *
     * <p>Selects a valid target, executes an attack, and displays flavor text
     *
     * @param aiIndex the index of the current AI in the heroes array
     * @param attacker the Hero object representing the attacking AI
     */
    private void aiTurn(int aiIndex, Hero attacker) {
        attacker.reduceCooldown();

        if (attacker.isStunned()) {
            System.out.println(attacker.getName() + " is stunned and skips their turn!");
            attacker.setStunned(false); // Clear the stun after skipping
            Main.pause(1000);
            return;
        }

        List<Integer> validTargets = new ArrayList<>();
        for (int j = 0; j < heroes.length; j++) {
            if (j != aiIndex && heroes[j].isAlive()) {
                validTargets.add(j);
            }
        }

        int targetIndex = validTargets.get((int) (Math.random() * validTargets.size()));

        Hero target = heroes[targetIndex];
        // Execute the attack
        executeAttack(attacker, target);
    }

    // --- End of Round Method ---
    private void printEndOfRound(Hero[] heroes) {
        Main.pause(1500);
        // Print Hero status at the end of each round
        System.out.println("\n" + Main.BOLD + "----- End of Round -----" + Main.RESET + "\n");

        // Print status for each hero
        for (Hero hero : heroes) {
            hero.status();
            System.out.println();
        }
    }

    // Execute attack and wrap flavor
    private void executeAttack(Hero attacker, Hero target) {
        String flavor = attacker.getAttackFlavor(target);
        String colorOuter = (attacker == heroes[playerIndex]) ? Main.CYAN : Main.YELLOW;
        System.out.println(Main.wrapFlavor(">>> " + flavor, colorOuter));
        attacker.attack(target);
        Main.pause(1500);
    }

}
