package server;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ItemDAOImpl implements ItemDAO {

    private Set<ItemDTO> itemlist;

    public ItemDAOImpl() {
        this.itemlist = new HashSet<>();
        ItemDTO guitar = new ItemDTO(1, "guitar", "Gibson LesPaul", 1000000, 60);
        ItemDTO notebook = new ItemDTO(2, "notebook", "Dell 3542", 300000, 120);
        ItemDTO car = new ItemDTO(3, "car", "Nissan Tiida", 5000000, 30);
        ItemDTO piano = new ItemDTO(4, "piano", "Yamaha", 2500000, 240);
        ItemDTO bag = new ItemDTO(5, "bag", "Swiss Gear", 23000, 74);
        ItemDTO phone = new ItemDTO(6, "phone", "LG G5", 3000000, 99);
        itemlist.add(guitar);
        itemlist.add(notebook);
        itemlist.add(car);
        itemlist.add(piano);
        itemlist.add(bag);
        itemlist.add(phone);
    }

    @Override
    public Set<ItemDTO> getItems() {
        return Collections.unmodifiableSet(itemlist);
    }
}
