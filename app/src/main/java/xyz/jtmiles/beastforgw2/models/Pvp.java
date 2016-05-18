
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pvp implements Serializable {

    private Integer id;
    private List<Integer> traits = new ArrayList<Integer>();
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
     *     The traits
     */
    public List<Integer> getTraits() {
        return traits;
    }

    /**
     * 
     * @param traits
     *     The traits
     */
    public void setTraits(List<Integer> traits) {
        this.traits = traits;
    }

}
