
package xyz.jtmiles.beastforgw2.models;

import java.util.ArrayList;
import java.util.List;

public class Details {

    private String type;
    private String damageType;
    private Integer minPower;
    private Integer maxPower;
    private Integer defense;
    private List<Object> infusionSlots = new ArrayList<Object>();
    private InfixUpgrade infixUpgrade;
    private Integer suffixItemId;
    private String secondarySuffixItemId;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The damageType
     */
    public String getDamageType() {
        return damageType;
    }

    /**
     * 
     * @param damageType
     *     The damage_type
     */
    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    /**
     * 
     * @return
     *     The minPower
     */
    public Integer getMinPower() {
        return minPower;
    }

    /**
     * 
     * @param minPower
     *     The min_power
     */
    public void setMinPower(Integer minPower) {
        this.minPower = minPower;
    }

    /**
     * 
     * @return
     *     The maxPower
     */
    public Integer getMaxPower() {
        return maxPower;
    }

    /**
     * 
     * @param maxPower
     *     The max_power
     */
    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    /**
     * 
     * @return
     *     The defense
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     * 
     * @param defense
     *     The defense
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     * 
     * @return
     *     The infusionSlots
     */
    public List<Object> getInfusionSlots() {
        return infusionSlots;
    }

    /**
     * 
     * @param infusionSlots
     *     The infusion_slots
     */
    public void setInfusionSlots(List<Object> infusionSlots) {
        this.infusionSlots = infusionSlots;
    }

    /**
     * 
     * @return
     *     The infixUpgrade
     */
    public InfixUpgrade getInfixUpgrade() {
        return infixUpgrade;
    }

    /**
     * 
     * @param infixUpgrade
     *     The infix_upgrade
     */
    public void setInfixUpgrade(InfixUpgrade infixUpgrade) {
        this.infixUpgrade = infixUpgrade;
    }

    /**
     * 
     * @return
     *     The suffixItemId
     */
    public Integer getSuffixItemId() {
        return suffixItemId;
    }

    /**
     * 
     * @param suffixItemId
     *     The suffix_item_id
     */
    public void setSuffixItemId(Integer suffixItemId) {
        this.suffixItemId = suffixItemId;
    }

    /**
     * 
     * @return
     *     The secondarySuffixItemId
     */
    public String getSecondarySuffixItemId() {
        return secondarySuffixItemId;
    }

    /**
     * 
     * @param secondarySuffixItemId
     *     The secondary_suffix_item_id
     */
    public void setSecondarySuffixItemId(String secondarySuffixItemId) {
        this.secondarySuffixItemId = secondarySuffixItemId;
    }

}
