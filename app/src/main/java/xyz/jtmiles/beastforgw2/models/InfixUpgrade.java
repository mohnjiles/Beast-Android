
package xyz.jtmiles.beastforgw2.models;

import java.util.ArrayList;
import java.util.List;

public class InfixUpgrade {

    private Integer id;
    private List<Attribute> attributes = new ArrayList<Attribute>();

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
     *     The attributes
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

}
