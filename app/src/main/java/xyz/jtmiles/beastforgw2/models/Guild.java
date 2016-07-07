
package xyz.jtmiles.beastforgw2.models;


import com.google.gson.annotations.SerializedName;

public class Guild {

    @SerializedName("guild_id")
    private String guildId;
    @SerializedName("guild_name")
    private String guildName;
    private String tag;
    private Emblem emblem;

    /**
     * 
     * @return
     *     The guildId
     */
    public String getGuildId() {
        return guildId;
    }

    /**
     * 
     * @param guildId
     *     The guild_id
     */
    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    /**
     * 
     * @return
     *     The guildName
     */
    public String getGuildName() {
        return guildName;
    }

    /**
     * 
     * @param guildName
     *     The guild_name
     */
    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    /**
     * 
     * @return
     *     The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * 
     * @param tag
     *     The tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 
     * @return
     *     The emblem
     */
    public Emblem getEmblem() {
        return emblem;
    }

    /**
     * 
     * @param emblem
     *     The emblem
     */
    public void setEmblem(Emblem emblem) {
        this.emblem = emblem;
    }


}
