import Inventory.Product;
import vendingMachine.VendingMachine;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to vending machine service!");

        VendingMachine vendingMachine = new VendingMachine();

        Product chips = new Product("lays",1,5.0);
        // inserting 3 lays chips
        for(int i=1;i<=3;i++){
            vendingMachine.addProduct(chips);
        }

        Product biskFarm = new Product("biskfarm",2,2.0);
        // inserting 3 biskFarms
        for(int i=1;i<=3;i++){
            vendingMachine.addProduct(biskFarm);
        }

        vendingMachine.insertCoin(5.0);
        vendingMachine.insertCoin(10.0);
        vendingMachine.pressButton(1);

        vendingMachine.insertCoin(2);
        vendingMachine.insertCoin(1);
        vendingMachine.pressButton(2);

    }
}