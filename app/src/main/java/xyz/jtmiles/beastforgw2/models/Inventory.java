
package xyz.jtmiles.beastforgw2.models;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Integer id;
    private Integer count;
    private String binding;
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
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The binding
     */
    public String getBinding() {
        return binding;
    }

    /**
     * 
     * @param binding
     *     The binding
     */
    public void setBinding(String binding) {
        this.binding = binding;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
