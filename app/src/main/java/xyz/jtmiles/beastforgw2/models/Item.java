
package xyz.jtmiles.beastforgw2.models;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private String name;
    private String description;
    private String type;
    private Integer level;
    private String rarity;
    private Integer vendorValue;
    private Integer defaultSkin;
    private List<String> gameTypes = new ArrayList<String>();
    private List<String> flags = new ArrayList<String>();
    private List<Object> restrictions = new ArrayList<Object>();
    private Integer id;
    private String chatLink;
    private String icon;
    private Details details;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 
     * @param level
     *     The level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 
     * @return
     *     The rarity
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * 
     * @param rarity
     *     The rarity
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * 
     * @return
     *     The vendorValue
     */
    public Integer getVendorValue() {
        return vendorValue;
    }

    /**
     * 
     * @param vendorValue
     *     The vendor_value
     */
    public void setVendorValue(Integer vendorValue) {
        this.vendorValue = vendorValue;
    }

    /**
     * 
     * @return
     *     The defaultSkin
     */
    public Integer getDefaultSkin() {
        return defaultSkin;
    }

    /**
     * 
     * @param defaultSkin
     *     The default_skin
     */
    public void setDefaultSkin(Integer defaultSkin) {
        this.defaultSkin = defaultSkin;
    }

    /**
     * 
     * @return
     *     The gameTypes
     */
    public List<String> getGameTypes() {
        return gameTypes;
    }

    /**
     * 
     * @param gameTypes
     *     The game_types
     */
    public void setGameTypes(List<String> gameTypes) {
        this.gameTypes = gameTypes;
    }

    /**
     * 
     * @return
     *     The flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * 
     * @param flags
     *     The flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    /**
     * 
     * @return
     *     The restrictions
     */
    public List<Object> getRestrictions() {
        return restrictions;
    }

    /**
     * 
     * @param restrictions
     *     The restrictions
     */
    public void setRestrictions(List<Object> restrictions) {
        this.restrictions = restrictions;
    }

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
     *     The chatLink
     */
    public String getChatLink() {
        return chatLink;
    }

    /**
     * 
     * @param chatLink
     *     The chat_link
     */
    public void setChatLink(String chatLink) {
        this.chatLink = chatLink;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The details
     */
    public Details getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *     The details
     */
    public void setDetails(Details details) {
        this.details = details;
    }

}
