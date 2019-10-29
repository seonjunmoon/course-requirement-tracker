package mun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is Item class that provides information of the Item object.
 *
 * @author Seonjun Mun
 * @version 6/14/2018
 */
public class Item {

    private String name;
    private int num;
    private List<Item> parents = new ArrayList<>();

    /**
     * Constructor of the Item object.
     *
     */
    public Item(String name, int num, List<Item> parents) {
        this.name = name;
        this.num = num;
        this.parents = parents;
    }

    /**
     * Gets the name from the object.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number from the object.
     *
     * @return number
     */
    public int getNum() {
        return num;
    }

    /**
     * Gets the list from the object.
     *
     * @return parents list
     */
    public List<Item> getParents() {
        return Collections.unmodifiableList(parents);
    }
}
