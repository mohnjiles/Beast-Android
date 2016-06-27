
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wvw_ implements Serializable {

    private Integer heal;
    private List<Integer> utilities = new ArrayList<>();
    private Integer elite;
    private List<String> legends = new ArrayList<>();
    private static final long serialVersionUID = 0L;

    /**
     * 
     * @return
     *     The heal
     */
    public Integer getHeal() {
        return heal;
    }

    /**
     * 
     * @param heal
     *     The heal
     */
    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    /**
     * 
     * @return
     *     The utilities
     */
    public List<Integer> getUtilities() {
        return utilities;
    }

    /**
     * 
     * @param utilities
     *     The utilities
     */
    public void setUtilities(List<Integer> utilities) {
        this.utilities = utilities;
    }

    /**
     * 
     * @return
     *     The elite
     */
    public Integer getElite() {
        return elite;
    }

    /**
     * 
     * @param elite
     *     The elite
     */
    public void setElite(Integer elite) {
        this.elite = elite;
    }

    /**
     * 
     * @return
     *     The legends
     */
    public List<String> getLegends() {
        return legends;
    }

    /**
     * 
     * @param legends
     *     The legends
     */
    public void setLegends(List<String> legends) {
        this.legends = legends;
    }

}
