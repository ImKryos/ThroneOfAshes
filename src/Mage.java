import java.util.Scanner;

public class Mage extends Hero {

    public Mage(String name) {
        super(name, 90, 10);
        setMaxSkillCooldown(4);
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
    public boolean useSkill(Hero[] heroes, int attackerIndex, Scanner scanner) {
        if (!isSkillReady()) {
            System.out.println(getName() + "'s Ice Blast is still on cooldown for " + skillCooldown + " turn(s)!");
            return false;
        }

        Hero target = selectTarget(heroes, attackerIndex, scanner);
        if (target == null) return false;
        String flavor = getName() + " invokes an Ice Blast, sending icicles flying into " + target.getName() + "!";
        System.out.println(Main.wrapFlavor(">>> " + flavor, Main.CYAN));
        int damage = getAttackPower();
        target.takeDamage(damage + getAttackPower());

        if (Math.random() < 0.5) {
            System.out.println(getName() + " triggers a double cast!");
            target.takeDamage(damage + getAttackPower());
        }

        startCooldown();
        return true;
    }

}
