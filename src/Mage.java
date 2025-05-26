import java.util.Scanner;

public class Mage extends Hero {

    public Mage(String name) {
        super(name, 90, 10);
    }

    public void attack(Hero target) {
        int damage = 25;
        target.takeDamage(damage);
    }

    @Override
    public String getAttackFlavor(Hero target) {
        String[] phrases = {
                this.getName() + " conjures a " + Main.RED + Main.BOLD + "🔥FIREBALL🔥" + Main.RESET + " and hurls it at " + target.getName() + "!",
                this.getName() + " raises their staff and calls down " + Main.BOLD + Main.YELLOW + "⚡ lightning ⚡" + Main.RESET + " on " + target.getName() + "!",
                this.getName() + " chants dark words as a " + Main.PURPLE + Main.BOLD + "shadow bolt" + Main.RESET + " streaks toward " + target.getName() + "!"
        };
        return phrases[(int)(Math.random() * phrases.length)];
    }

    @Override
    public void useSkill(Hero[] heroes, int attackerIndex, Scanner scanner) {

    }

}
