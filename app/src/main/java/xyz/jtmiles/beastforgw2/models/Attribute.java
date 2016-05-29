
package xyz.jtmiles.beastforgw2.models;

public class Attribute {

    private String attribute;
    private Integer modifier;

    /**
     * 
     * @return
     *     The attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 
     * @param attribute
     *     The attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * 
     * @return
     *     The modifier
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * 
     * @param modifier
     *     The modifier
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

}
