import java.util.*;

interface VendingState{
    VendingState insertCoin(VendingMachine machine, int coin);
    VendingState selectItem(VendingMachine machine);
    VendingState dispense(VendingMachine machine);
    VendingState returnCoin(VendingMachine machine);
    VendingState refill(VendingMachine machine , int quantity);
}

class VendingMachine{
    private VendingState currentState;
    private int totalItem;
    private int itemPrice;
    private int insertedCoin;

    private VendingState noCoinState;
    private VendingState hasCoinState;
    private VendingState dispenseState;
    private VendingState soldOutState;

    public VendingMachine(int totalItem, int itemPrice){
        this.totalItem = totalItem;
        this.itemPrice = itemPrice;
        this.insertedCoin =0;

        noCoinState= new NoCoinState();
        hasCoinState = new HasCoinState();
        dispenseState = new DispenseState();
        // HasCoinState hasCoinState = new HasCoinState();

        if(totalItem > 0){
            currentState = noCoinState;
        }else currentState = soldOutState;
    }
    public VendingState getNoCoinState(){
        return noCoinState;
    }
    public VendingState getHasCoinState(){
        return hasCoinState;
    }
    public VendingState getDispenseState(){
        return dispenseState;
    }

    public void insert(int coin){
        currentState = currentState.insertCoin(this, coin);
    }

    public void setInsertedCoin(int coin){
        insertedCoin = coin;
    }

    public int getInsertedCoin(){
        return insertedCoin;
    }
    public void addCoin(int coin){
        insertedCoin += coin;
    }

    public void incrementItemCount(int quantity){
        totalItem += quantity;
    }

    public int getPrice(){
        return itemPrice;
    }

}
class NoCoinState implements VendingState{
    public VendingState insertCoin(VendingMachine machine, int coin){
        machine.setInsertedCoin(coin);
        System.out.println("Coin Inserted. Current balance: Rs" + coin);
        return machine.getHasCoinState(); // => transition from noCoinState to hasCoinState
    }
    public VendingState selectItem(VendingMachine machine){
        System.out.println("Please insert coin first");
        return machine.getNoCoinState();
    }
    public VendingState dispense(VendingMachine machine){
        System.out.println("Please insert coin then select Item first!");
        return machine.getNoCoinState();
    }
    public VendingState returnCoin(VendingMachine machine){
        System.out.println("No coin to return");
        return machine.getNoCoinState();
    }
    public VendingState refill(VendingMachine machine, int quantity){
        System.out.println("Items are refilling ...");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }
    public String getStateName(){
        return "NO_COIN";
    }
}

class HasCoinState implements VendingState{
    public VendingState insertCoin(VendingMachine machine, int coin){
        machine.addCoin(coin);
        System.out.println("Additional coin inserted. current balance : Rs"+ machine.getInsertedCoin());
        return machine.getHasCoinState();
    }
    public VendingState selectItem(VendingMachine machine){
        if(machine.getInsertedCoin() >= machine.getPrice()){
            System.out.println("Item selected. Dispensing...");
            int change = machine.getInsertedCoin() - machine.getPrice();
            if(change>0){
                System.out.println("Change returned: Rs "+change);
            }
            machine.setInsertedCoin(0);
            return machine.getDispenseState();
        }else{
            int needed = machine.getPrice()-machine.getInsertedCoin();
            System.out.println("Insufficient funds. Need Rs " + needed + " more.");
            return machine.getHasCoinState();
        }
    }
    public VendingState dispense(VendingMachine machine){
        System.out.println("Please select an Item first!");
        return machine.getHasCoinState();
    }
    public VendingState returnCoin(VendingMachine machine){
        System.out.println("Coin returned: Rs " + machine.getInsertedCoin());
        machine.setInsertedCoin(0);
        return machine.getNoCoinState();
    }

    public VendingState refill(VendingMachine machine, int quantity){
        System.out.println("Can't refil in this state");
        return machine.getHasCoinState(); // Stay in same state
    }
    
    public String getStateName() {
        return "HAS_COIN";
    }
}

class DispenseState implements VendingState{
    // pass
}

public class VendingMachineMain{
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(2, 20);
    }
}