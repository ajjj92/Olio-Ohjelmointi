/*
    11-Jun-19
    @AtteJantunen
*/

public class Character {

    protected String charactername;
    protected  String weaponname;



    public Character() {
        // construct
        charactername = "default nimi";
        weaponname = "default ase";
    }

    public void printFight() {
        System.out.println(String.format("%s tappelee aseella %s", charactername, weaponname));
    }

    class Knight extends Character {
        public Knight() {
            charactername = "Knight";
        }


    }

    class King extends Character {
        public King() {
            charactername = "King";
        }

    }

    class Queen extends Character {
        public Queen() {
            charactername = "Queen";
        }

    }

    class Troll extends Character {
        public Troll() {
            charactername = "Troll";
        }
    }

    public class Weapon{


        public Weapon() {
            //construct
        }
    }


    class Knife extends Weapon {
        public Knife() {
            weaponname = "Knife";
        }
    }

    class Axe extends Weapon {
        public Axe() {
            weaponname = "Axe";
        }
    }
    class Sword extends Weapon {
        public Sword() {
            weaponname = "Sword";
        }
    }
    class Club extends Weapon {
        public Club() {
            weaponname = "Club";
        }
    }

}
