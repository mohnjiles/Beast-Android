
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Specializations implements Serializable {

    private List<Pve> pve = new ArrayList<Pve>();
    private List<Pvp> pvp = new ArrayList<Pvp>();
    private List<Wvw> wvw = new ArrayList<Wvw>();
    private static final long serialVersionUID = 0L;

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


}
