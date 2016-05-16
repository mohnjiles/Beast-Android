
package xyz.jtmiles.beastforgw2.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wvw {

    private Integer id;
    private List<Integer> traits = new ArrayList<Integer>();
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
