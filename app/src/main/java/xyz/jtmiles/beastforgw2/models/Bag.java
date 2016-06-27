
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bag implements Serializable {

    private Integer id;
    private Integer size;
    private List<Inventory> inventory = new ArrayList<>();
    private static final long serialVersionUID = 0L;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 
     * @return
     *     The inventory
     */
    public List<Inventory> getInventory() {
        return inventory;
    }

    /**
     * 
     * @param inventory
     *     The inventory
     */
    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

}
