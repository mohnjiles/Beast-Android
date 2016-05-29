package xyz.jtmiles.beastforgw2.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JT on 5/21/2016.
 */
public class Account {
    private String id;
    private String name;
    private int world;
    @SerializedName("commander")
    private boolean hasCommander;
    @SerializedName("guilds")
    private List<String> guildIds;
    @SerializedName("created")
    private String createdDate;
    private String access;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorld() {
        return world;
    }

    public void setWorld(int world) {
        this.world = world;
    }

    public boolean isHasCommander() {
        return hasCommander;
    }

    public void setHasCommander(boolean hasCommander) {
        this.hasCommander = hasCommander;
    }

    public List<String> getGuildIds() {
        return guildIds;
    }

    public void setGuildIds(List<String> guildIds) {
        this.guildIds = guildIds;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
