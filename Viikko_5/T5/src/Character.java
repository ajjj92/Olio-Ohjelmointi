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
        System.out.println(String.format("%s taistelee aseella %s", charactername, weaponname));
    }

    class Knight extends Character {
        public Knight() {
            charactername = "Ritari";
        }


    }

    class King extends Character {
        public King() {
            charactername = "Kuningas";
        }

    }

    class Queen extends Character {
        public Queen() {
            charactername = "Kuningatar";
        }

    }

    class Troll extends Character {
        public Troll() {
            charactername = "Peikko";
        }
    }

    public class Weapon{


        public Weapon() {
            //construct
        }
    }


    class Knife extends Weapon {
        public Knife() {
            weaponname = "Veitsi";
        }
    }

    class Axe extends Weapon {
        public Axe() {
            weaponname = "Kirves";
        }
    }
    class Sword extends Weapon {
        public Sword() {
            weaponname = "Miekka";
        }
    }
    class Club extends Weapon {
        public Club() {
            weaponname = "Nuija";
        }
    }

}
