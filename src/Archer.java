import java.util.Scanner;

public class Archer extends Hero {

    public Archer(String name) {
        super(name, 110, 18);
        setMaxSkillCooldown(2);
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
    public boolean useSkill(Hero[] heroes, int attackerIndex, Scanner scanner) {
        if (!isSkillReady()) {
            System.out.println(getName() + "'s Volley is still on cooldown for " + skillCooldown + " turn(s)!");
            return false;
        }

        String flavor = getName() + " unleashes a " + Main.BOLD + "volley of arrows" + Main.RESET + " at all enemies!";
        System.out.println(Main.wrapFlavor(">>> " + flavor, Main.CYAN));

        for (int i = 0; i < heroes.length; i++) {
            if (i != attackerIndex && heroes[i].isAlive()) {
                int damage = getAttackPower();
                System.out.println("→ " + heroes[i].getName() + " takes " + damage + " damage!");
                heroes[i].takeDamage(damage);
            }
        }
        startCooldown();
        return true;
    }


}
