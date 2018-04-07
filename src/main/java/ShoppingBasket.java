import java.util.HashMap;

public class ShoppingBasket {
    private HashMap<Item, Integer> items;
    int total;

    public ShoppingBasket() {
        this.items = new HashMap<>();
        this.total = 0;
    }

    public int getNumberOfUniqueItems() {
        return items.size();
    }

    public void addItem(Item item) {
        if (items.containsKey(item)) {
            int count = items.get(item);
            count ++;
            items.replace(item, count);
        } else {
            items.put(item, 1);
        }
    }

    public int getNumberOfCertainItem(Item item) {
        return items.get(item);
    }

    public void removeItem(Item item) {
        if (items.get(item) > 1) {
            int count = items.get(item);
            count --;
            items.replace(item, count);
        } else {
            items.remove(item);
        }
    }

    public void emptyBasket() {
        items.clear();
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }
}
