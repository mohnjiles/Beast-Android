
package xyz.jtmiles.beastforgw2.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equipment {

    private Integer id;
    private String slot;
    private List<Integer> infusions = new ArrayList<Integer>();
    private List<Integer> upgrades = new ArrayList<Integer>();
    private Integer skin;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The slot
     */
    public String getSlot() {
        return slot;
    }

    /**
     * 
     * @param slot
     *     The slot
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }

    /**
     * 
     * @return
     *     The infusions
     */
    public List<Integer> getInfusions() {
        return infusions;
    }

    /**
     * 
     * @param infusions
     *     The infusions
     */
    public void setInfusions(List<Integer> infusions) {
        this.infusions = infusions;
    }

    /**
     * 
     * @return
     *     The upgrades
     */
    public List<Integer> getUpgrades() {
        return upgrades;
    }

    /**
     * 
     * @param upgrades
     *     The upgrades
     */
    public void setUpgrades(List<Integer> upgrades) {
        this.upgrades = upgrades;
    }

    /**
     * 
     * @return
     *     The skin
     */
    public Integer getSkin() {
        return skin;
    }

    /**
     * 
     * @param skin
     *     The skin
     */
    public void setSkin(Integer skin) {
        this.skin = skin;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
