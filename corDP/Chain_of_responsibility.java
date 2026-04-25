import java.util.*;

abstract class MoneyHandler{
    protected MoneyHandler nextHandler;
    public MoneyHandler(){
        this.nextHandler = null;
    }
    public void setNextHandler(MoneyHandler next){
        this.nextHandler = next;
    }
    abstract public void dispense(int amount);
}

class ThousandHandler extends MoneyHandler{
    private int numNotes;
    public ThousandHandler(int notes){
        this.numNotes = notes;
    }

    @Override
    public void dispense(int amount) {
        int reqNotes = amount / 1000;
        if(reqNotes > numNotes){
            reqNotes = numNotes;
            numNotes = 0;
        }else{
            numNotes -= reqNotes;
        }

        if(reqNotes > 0) System.out.println("Dispensing amount "+reqNotes+ " x ₹1000 notes.");

        int remainingAmount = amount - reqNotes*1000;
        if(remainingAmount>0){
            if(nextHandler!=null) nextHandler.dispense(remainingAmount);
            else System.out.println("Remaning amount of "+ remainingAmount+" Cann't be fullfilled (Insufficient fund in ATM)");
        }
    }
}


public class Chain_of_responsibility{
    public static void main(String[] args) {
        MoneyHandler thousandHandler = new ThousandHandler(6);
        thousandHandler.dispense(4500);
    }
}