package Good_Code;

public class CharacterFactory {
    private Character prototypeCharacter;

    public CharacterFactory() {
        prototypeCharacter = new Character("DefaultName", 100, 50, 1);
    }

    public Character createCharacterWithNewName(String name) throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        return new Character(name, clonedCharacter.getHealth(),
                clonedCharacter.getAttackPower(), clonedCharacter.getLevel());
    }

    public Character createCharacterWithNewLevel(int level) throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        return new Character(clonedCharacter.getName(),
                clonedCharacter.getHealth(), clonedCharacter.getAttackPower(), level);
    }

    public Character createCharacterWithNewAttackPower(int attackPower) throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        return new Character(clonedCharacter.getName(),
                clonedCharacter.getHealth(), attackPower, clonedCharacter.getLevel());
    }
}