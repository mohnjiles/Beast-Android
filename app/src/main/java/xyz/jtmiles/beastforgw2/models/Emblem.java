
package xyz.jtmiles.beastforgw2.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Emblem {

    @SerializedName("background_id")
    private Integer backgroundId;
    @SerializedName("foreground_id")
    private Integer foregroundId;
    private List<Object> flags = new ArrayList<Object>();
    @SerializedName("background_color_id")
    private Integer backgroundColorId;
    @SerializedName("foreground_primary_color_id")
    private Integer foregroundPrimaryColorId;
    @SerializedName("foreground_secondary_color_id")
    private Integer foregroundSecondaryColorId;

    /**
     * 
     * @return
     *     The backgroundId
     */
    public Integer getBackgroundId() {
        return backgroundId;
    }

    /**
     * 
     * @param backgroundId
     *     The background_id
     */
    public void setBackgroundId(Integer backgroundId) {
        this.backgroundId = backgroundId;
    }

    /**
     * 
     * @return
     *     The foregroundId
     */
    public Integer getForegroundId() {
        return foregroundId;
    }

    /**
     * 
     * @param foregroundId
     *     The foreground_id
     */
    public void setForegroundId(Integer foregroundId) {
        this.foregroundId = foregroundId;
    }

    /**
     * 
     * @return
     *     The flags
     */
    public List<Object> getFlags() {
        return flags;
    }

    /**
     * 
     * @param flags
     *     The flags
     */
    public void setFlags(List<Object> flags) {
        this.flags = flags;
    }

    /**
     * 
     * @return
     *     The backgroundColorId
     */
    public Integer getBackgroundColorId() {
        return backgroundColorId;
    }

    /**
     * 
     * @param backgroundColorId
     *     The background_color_id
     */
    public void setBackgroundColorId(Integer backgroundColorId) {
        this.backgroundColorId = backgroundColorId;
    }

    /**
     * 
     * @return
     *     The foregroundPrimaryColorId
     */
    public Integer getForegroundPrimaryColorId() {
        return foregroundPrimaryColorId;
    }

    /**
     * 
     * @param foregroundPrimaryColorId
     *     The foreground_primary_color_id
     */
    public void setForegroundPrimaryColorId(Integer foregroundPrimaryColorId) {
        this.foregroundPrimaryColorId = foregroundPrimaryColorId;
    }

    /**
     * 
     * @return
     *     The foregroundSecondaryColorId
     */
    public Integer getForegroundSecondaryColorId() {
        return foregroundSecondaryColorId;
    }

    /**
     * 
     * @param foregroundSecondaryColorId
     *     The foreground_secondary_color_id
     */
    public void setForegroundSecondaryColorId(Integer foregroundSecondaryColorId) {
        this.foregroundSecondaryColorId = foregroundSecondaryColorId;
    }


}
