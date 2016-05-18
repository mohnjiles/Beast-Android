
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;

public class Inventory implements Serializable {

    private Integer id;
    private Integer count;
    private String binding;
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


}
