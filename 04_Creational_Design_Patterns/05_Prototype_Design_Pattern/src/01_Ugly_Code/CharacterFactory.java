package Ugly_Code;

public class CharacterFactory {
    // Too many methods for every small change
    public Character createCharacterWithNewName(String name) {
        return new Character(name, 100, 50, 1);
    }

    public Character createCharacterWithNewLevel(int level) {
        return new Character("DefaultName", 100, 50, level);
    }

    public Character createCharacterWithNewAttackPower(int attackPower) {
        return new Character("DefaultName", 100, attackPower, 1);
    }

    public Character createCharacterWithNewHealth(int health) {
        return new Character("DefaultName", health, 50, 1);
    }
    // More and more methods for every possible variation...
}