
package xyz.jtmiles.beastforgw2.models;

import java.util.HashMap;
import java.util.Map;

public class Skills {

    private Pve_ pve;
    private Pvp_ pvp;
    private Wvw_ wvw;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The pve
     */
    public Pve_ getPve() {
        return pve;
    }

    /**
     * 
     * @param pve
     *     The pve
     */
    public void setPve(Pve_ pve) {
        this.pve = pve;
    }

    /**
     * 
     * @return
     *     The pvp
     */
    public Pvp_ getPvp() {
        return pvp;
    }

    /**
     * 
     * @param pvp
     *     The pvp
     */
    public void setPvp(Pvp_ pvp) {
        this.pvp = pvp;
    }

    /**
     * 
     * @return
     *     The wvw
     */
    public Wvw_ getWvw() {
        return wvw;
    }

    /**
     * 
     * @param wvw
     *     The wvw
     */
    public void setWvw(Wvw_ wvw) {
        this.wvw = wvw;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
