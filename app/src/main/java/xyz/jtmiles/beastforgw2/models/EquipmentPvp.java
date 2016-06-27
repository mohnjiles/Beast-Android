
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EquipmentPvp implements Serializable {

    private Integer amulet;
    private Integer rune;
    private List<Integer> sigils = new ArrayList<>();
    private static final long serialVersionUID = 0L;

    /**
     * 
     * @return
     *     The amulet
     */
    public Integer getAmulet() {
        return amulet;
    }

    /**
     * 
     * @param amulet
     *     The amulet
     */
    public void setAmulet(Integer amulet) {
        this.amulet = amulet;
    }

    /**
     * 
     * @return
     *     The rune
     */
    public Integer getRune() {
        return rune;
    }

    /**
     * 
     * @param rune
     *     The rune
     */
    public void setRune(Integer rune) {
        this.rune = rune;
    }

    /**
     * 
     * @return
     *     The sigils
     */
    public List<Integer> getSigils() {
        return sigils;
    }

    /**
     * 
     * @param sigils
     *     The sigils
     */
    public void setSigils(List<Integer> sigils) {
        this.sigils = sigils;
    }

}
