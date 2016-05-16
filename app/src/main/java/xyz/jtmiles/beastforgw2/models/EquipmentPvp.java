
package xyz.jtmiles.beastforgw2.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentPvp {

    private Integer amulet;
    private Integer rune;
    private List<Integer> sigils = new ArrayList<Integer>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
