/*
    10-Jun-19
    @AtteJantunen
*/

public class Car {

    private int Wheel = 0;
    private String Body;
    private String Chassis;
    private String Engine;

    public Car() {
        Body body = new  Body();
        Chassis chassis = new Chassis();
        Engine engine = new Engine();
        Wheel wheel1 = new Wheel();
        Wheel wheel2 = new Wheel();
        Wheel wheel3 = new Wheel();
        Wheel wheel4 = new Wheel();


    }

    public void printcar() {
        System.out.println("Autoon kuuluu:");
        System.out.println("\t"+Body);
        System.out.println("\t"+Chassis);
        System.out.println("\t"+Engine);
        System.out.println("\t"+Wheel + " Wheel");
    }

    class Wheel {

        public  Wheel() {
            System.out.println("Valmistetaan: Wheel");
            Wheel += 1;
        }
    }

    class Chassis {
        public Chassis() {
            Chassis = "Chassis";
            System.out.println("Valmistetaan: Chassis");
        }
    }

    class Engine{
        public Engine() {
            Engine = "Engine";
            System.out.println("Valmistetaan: Engine");
        }
    }

    class Body{
        public Body() {
            Body = "Body";
            System.out.println("Valmistetaan: Body");
        }
    }

}
