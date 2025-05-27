import java.util.Scanner;

public class Knight extends Hero {

    public Knight(String name) {
        super(name, 120, 25);
        setMaxSkillCooldown(3);
    }

    @Override
    public void attack(Hero target) {
        int damage = 20;
        target.takeDamage(damage);
    }

    @Override
    public String getAttackFlavor(Hero target) {
        String[] phrases = {
                this.getName() + " 🗡️ " + "swings a broadsword at " + target.getName() + "!",
                this.getName() + " 🛡️" + "charges into " + target.getName() + " with a " + Main.BOLD + Main.BLUE + "shield " + "bash" + Main.RESET + "!",
                this.getName() + " delivers a " + Main.BOLD + "crushing" + Main.RESET + " blow to " + target.getName() + "!"
        };
        return phrases[(int)(Math.random() * phrases.length)];
    }

    @Override
    public boolean useSkill(Hero[] heroes, int attackerIndex, Scanner scanner) {
        if (!isSkillReady()) {
            System.out.println(getName() + "'s Shield Bash is still on cooldown for " + skillCooldown + " turn(s)!");
            return false;
        }

        Hero target = selectTarget(heroes, attackerIndex, scanner);
        if (target == null) return false;
        String flavor = getName() + " slams their shield into " + target.getName() + "!";
        System.out.println(Main.wrapFlavor(">>> " + flavor, Main.CYAN));
        int damage = getAttackPower();
        target.takeDamage(damage);

        if (Math.random() < 0.5) {
            target.setStunned(true);
            System.out.println(target.getName() + " is stunned and will miss their next turn!");
        }

        startCooldown();
        return true;
    }

}
