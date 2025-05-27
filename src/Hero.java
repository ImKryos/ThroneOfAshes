import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Hero {

    // Declare variables to be used for each hero
    private final String name;
    private int health;
    private int maxHp;
    private int attackPower;

    // Constructor for new heroes
    public Hero(String name, int maxHp, int attackPower) {
        this.name = name;
        this.health = maxHp;
        this.maxHp = maxHp;
        this.attackPower = attackPower;
    }

    // Gets the name of the referenced hero
    public String getName() {
        return this.name;
    }

    // Gets the health of the reference hero
    public int getHealth() {
        return this.health;
    }

    // Causes referenced hero to take damage
    public void takeDamage(int damage) {
        int oldHealth = this.health;
        this.health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println(this.name + " has been defeated!");
            }
        else {
            System.out.println(this.name + " took " + damage + " damage!");
        }
        // Show updated HP bar
        animateHpBarChange(oldHealth, health);
    }

    // Display name and health of referenced hero
    public void status() {
        System.out.println("Name: " + this.name);
        System.out.println(getHpBar());
    }

    // Abstract attack method that will be overridden for each hero type
    public abstract void attack(Hero target);

    // Determines if the referenced hero is alive
    public boolean isAlive() {
        return health > 0;
    }

    // Default getAttackFlavor - overridden by each hero class
    public String getAttackFlavor(Hero target) {
        return this.name + " attacks " + target.getName() + "!";
    }


    /**
     * Generates a visual representation of the hero's current HP as a bar.
     *
     * <p>Example : [██████      ] 30/100 (if HP is at ~30%)
     *
     * @return A string representing the HP bar, scaled to 20 segments.
     */
    public String getHpBar(int health) {
        int barLength = 20; // Total number of segments in the HP bar
        int filledLength = (int) (((double) health / maxHp * barLength)); // Calculate how many should be filled
        double healthRatio = (double) health / maxHp;

        // Set the colors for each health ratio
        String color;
        if (healthRatio > 0.6) {
            color = Main.GREEN;
        } else if (healthRatio > 0.3) {
            color = Main.YELLOW;
        } else {
            color = Main.RED;
        }

        // Build a string to store the constructed HP bar
        StringBuilder bar = new StringBuilder();
        bar.append("[");
        bar.append(color);
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█"); // Filled portion of bar
            } else {
                bar.append("-"); // Empty portion of bar
            }
        }

        bar.append(Main.RESET); // Reset color back to default
        bar.append("] ");
        bar.append(health).append("/").append(maxHp); // Add HP numbers after the bar
        return bar.toString();
    }

    public String getHpBar() {
        return getHpBar(this.health);
    }

    public void animateHpBarChange(int oldHealth, int newHealth) {
        int steps = 10; // number of animation steps
        int delay = 50; // milliseconds between frames

        for (int i = 1; i <= steps; i++) {
            int interpolatedHealth = oldHealth - (int) (((double) (oldHealth - newHealth) / steps) * i);
            if (interpolatedHealth < 0) {
                interpolatedHealth = 0;
            }

            String bar = getHpBar(interpolatedHealth);
            String paddedBar = String.format("\r%-40s", bar);
            System.out.print(paddedBar);
            Main.pause(delay);
        }
        System.out.println();
    }

    public abstract boolean useSkill(Hero[] heroes, int attackerIndex, Scanner scanner);

    public int getAttackPower() {
        return this.attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    private boolean stunned;
    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public boolean isStunned() {
        return stunned;
    }


    /**
     * Helper method to select a target from heroes other than attacker.
     * Needs a scanner to read user input.
     *
     * @param heroes Array of all heroes
     * @param attackerIndex Index of the current hero using the skill (to exclude self)
     * @param scanner Scanner to get user input
     * @return the selected Hero target
     */
    protected Hero selectTarget(Hero[] heroes, int attackerIndex, Scanner scanner) {
        System.out.println("Choose a target for your skill:");

        int option = 1;
        int[] validTargets = new int[heroes.length];

        for (int i = 0; i < heroes.length; i++) {
            if (i != attackerIndex && heroes[i].isAlive()) {
                System.out.println(option + ". " + heroes[i].getName());
                validTargets[option - 1] = i;
                option++;
            }
        }

        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice < option) {
                    return heroes[validTargets[choice - 1]];
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine(); // clear bad input
            }
        }
    }

    protected int skillCooldown = 0;
    protected int maxSkillCooldown;

    public void setMaxSkillCooldown(int turns) {
        this.maxSkillCooldown = turns;
    }

    public boolean isSkillReady() {
        return skillCooldown == 0;
    }

    public void reduceCooldown() {
        if (skillCooldown > 0) {
            skillCooldown--;
        }
    }

    public void startCooldown() {
        skillCooldown = maxSkillCooldown;
    }

}
