
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;

public class Crafting implements Serializable {

    private String discipline;
    private Integer rating;
    private Boolean active;
    private static final long serialVersionUID = 0L;

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

}
