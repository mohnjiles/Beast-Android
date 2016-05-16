
package xyz.jtmiles.beastforgw2.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specializations {

    private List<Pve> pve = new ArrayList<Pve>();
    private List<Pvp> pvp = new ArrayList<Pvp>();
    private List<Wvw> wvw = new ArrayList<Wvw>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The pve
     */
    public List<Pve> getPve() {
        return pve;
    }

    /**
     * 
     * @param pve
     *     The pve
     */
    public void setPve(List<Pve> pve) {
        this.pve = pve;
    }

    /**
     * 
     * @return
     *     The pvp
     */
    public List<Pvp> getPvp() {
        return pvp;
    }

    /**
     * 
     * @param pvp
     *     The pvp
     */
    public void setPvp(List<Pvp> pvp) {
        this.pvp = pvp;
    }

    /**
     * 
     * @return
     *     The wvw
     */
    public List<Wvw> getWvw() {
        return wvw;
    }

    /**
     * 
     * @param wvw
     *     The wvw
     */
    public void setWvw(List<Wvw> wvw) {
        this.wvw = wvw;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
