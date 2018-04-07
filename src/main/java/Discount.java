import java.util.ArrayList;

public class Discount {
    private ArrayList<Item> buyOneGetOneFree;
    private int moneyOffOverAmount;
    private int percentageOffWhenOverAmount;
    private int percentageOffForMembership;

    public Discount() {
        this.buyOneGetOneFree = new ArrayList<>();
        this.moneyOffOverAmount = 0;
        this.percentageOffWhenOverAmount = 0;
        this.percentageOffForMembership = 0;
    }

    public ArrayList<Item> getBOGOFItems() {
        return buyOneGetOneFree;
    }

    public void addItemToBOGOFList(Item item) {
        if (!buyOneGetOneFree.contains(item)) {
            buyOneGetOneFree.add(item);
        }
    }

    public boolean isItemBOGOF(Item item) {
        return (buyOneGetOneFree.contains(item));
    }

    public int getMoneyOffOverAmount() {
        return moneyOffOverAmount;
    }

    public void setMoneyOffOverAmount(int amount) {
        this.moneyOffOverAmount = amount;
    }

    public void setPercentageOffWhenOverAmount(int percentageOffWhenOverAmount) {
        if ((percentageOffWhenOverAmount <= 100) && (percentageOffWhenOverAmount >= 0)) {
            this.percentageOffWhenOverAmount = percentageOffWhenOverAmount;
        }
    }

    public void setPercentageOffForMembership(int percentageOffForMembership) {
        if ((percentageOffForMembership <= 100) && (percentageOffForMembership >=0)) {
            this.percentageOffForMembership = percentageOffForMembership;
        }
    }

    public Double getMoneyOffOverAmountDiscount() {
        return percentageOffWhenOverAmount/100.00;
    }

    public Double getMembershipDiscount() {
        return percentageOffForMembership/100.00;
    }
}
