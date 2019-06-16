import java.util.ArrayList;
import java.util.Locale;

/**
 * 6/16/19
 * AtteJantunen
 */

public class BottleDispenser {


    private int bottles;
    private float money;
    private ArrayList<Bottle> pullot;
    private Integer i;
    private static BottleDispenser bottleDispenser = null;

    public BottleDispenser() {
        bottles = 6;
        money = 0;

        pullot = new ArrayList<>();

        pullot.add(new Bottle("Pepsi Max", 0.5f, 1.80f));
        pullot.add(new Bottle("Pepsi Max", 1.5f, 2.20f));
        pullot.add(new Bottle("Coca-Cola Zero", 0.5f, 2.00f));
        pullot.add(new Bottle("Coca-Cola Zero", 1.5f, 2.50f));
        pullot.add(new Bottle("Fanta Zero", 0.5f, 1.95f));
        pullot.add(new Bottle("Fanta Zero", 0.5f, 1.95f));





    }
    public static BottleDispenser getInstance() {
        if (bottleDispenser == null){
            bottleDispenser = new BottleDispenser();
        }
        return bottleDispenser;

    }
    public void deleteBottle(Integer pullonumero) {
        pullot.remove(pullonumero-1);
    }

    public void addMoney() {
        money += 1;
        System.out.println("Klink! Lisää rahaa laitteeseen!");
    }

    public void buyBottle(Integer pullonumero) {
        pullonumero -=1;
        if(money - pullot.get(pullonumero).getPrice() >= 0){
            money -= pullot.get(pullonumero).getPrice();
            System.out.println(String.format("KACHUNK! %s tipahti masiinasta!", pullot.get(pullonumero).getName()));
        }else{
            System.out.println("Syötä rahaa ensin!");
        }

    }
    public void returnMoney() {


        System.out.println(String.format("Klink klink. Sinne menivät rahat! Rahaa tuli ulos %.2f€", money));
        money = 0;
    }
    public void printBottles() {
        i = 0;
        pullot.forEach(pullo -> {
            System.out.println(String.format("%d. Nimi: %s", i+1, pullo.getName()));
            System.out.println(String.format(Locale.US, "	Koko: %s	Hinta: %s", Float.toString(pullo.getSize()), Float.toString(pullo.getPrice())));
            i+=1;
        });


    }
}
