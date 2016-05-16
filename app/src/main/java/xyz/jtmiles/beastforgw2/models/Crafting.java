
package xyz.jtmiles.beastforgw2.models;

import java.util.HashMap;
import java.util.Map;

public class Crafting {

    private String discipline;
    private Integer rating;
    private Boolean active;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The discipline
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * 
     * @param discipline
     *     The discipline
     */
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * 
     * @param active
     *     The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
