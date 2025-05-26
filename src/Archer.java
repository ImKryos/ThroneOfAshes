import java.util.Scanner;

public class Archer extends Hero {

    public Archer(String name) {
        super(name, 110, 18);
    }

    public void attack(Hero target) {
        int damage = 15;
        target.takeDamage(damage);
    }

    @Override
    public String getAttackFlavor(Hero target) {
        String[] phrases = {
                this.getName() + " notches an arrow and fires a " + Main.GREEN + Main.BOLD + "💥perfect shot💥" + Main.RESET + " at " + target.getName() + "!",
                this.getName() + " looses a flurry of arrows toward " + target.getName() + " 🏹!",
                this.getName() + " vaults back and fires a " + Main.CYAN + Main.BOLD + "precision strike" + Main.RESET + " into " + target.getName() + "'s flank!"
        };
        return phrases[(int)(Math.random() * phrases.length)];
    }

    @Override
    public void useSkill(Hero[] heroes, int attackerIndex, Scanner scanner) {

    }

}
