
package xyz.jtmiles.beastforgw2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Character implements Serializable {

    private String name;
    private String race;
    private String gender;
    private String profession;
    private Integer level;
    private String guild;
    private Integer age;
    private String created;
    private Integer deaths;
    private List<Crafting> crafting = new ArrayList<>();
    private Integer title;
    private Specializations specializations;
    private Skills skills;
    private List<Equipment> equipment = new ArrayList<>();
    private List<Integer> recipes = new ArrayList<>();
    private EquipmentPvp equipmentPvp;
    private List<Bag> bags = new ArrayList<>();
    private static final long serialVersionUID = 0L;

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
     *     The race
     */
    public String getRace() {
        return race;
    }

    /**
     * 
     * @param race
     *     The race
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * 
     * @return
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return
     *     The profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 
     * @param profession
     *     The profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
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
     *     The guild
     */
    public String getGuild() {
        return guild;
    }

    /**
     * 
     * @param guild
     *     The guild
     */
    public void setGuild(String guild) {
        this.guild = guild;
    }

    /**
     * 
     * @return
     *     The age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * @param age
     *     The age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 
     * @return
     *     The created
     */
    public String getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *     The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * 
     * @return
     *     The deaths
     */
    public Integer getDeaths() {
        return deaths;
    }

    /**
     * 
     * @param deaths
     *     The deaths
     */
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    /**
     * 
     * @return
     *     The crafting
     */
    public List<Crafting> getCrafting() {
        return crafting;
    }

    /**
     * 
     * @param crafting
     *     The crafting
     */
    public void setCrafting(List<Crafting> crafting) {
        this.crafting = crafting;
    }

    /**
     * 
     * @return
     *     The title
     */
    public Integer getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(Integer title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The specializations
     */
    public Specializations getSpecializations() {
        return specializations;
    }

    /**
     * 
     * @param specializations
     *     The specializations
     */
    public void setSpecializations(Specializations specializations) {
        this.specializations = specializations;
    }

    /**
     * 
     * @return
     *     The skills
     */
    public Skills getSkills() {
        return skills;
    }

    /**
     * 
     * @param skills
     *     The skills
     */
    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    /**
     * 
     * @return
     *     The equipment
     */
    public List<Equipment> getEquipment() {
        return equipment;
    }

    /**
     * 
     * @param equipment
     *     The equipment
     */
    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    /**
     * 
     * @return
     *     The recipes
     */
    public List<Integer> getRecipes() {
        return recipes;
    }

    /**
     * 
     * @param recipes
     *     The recipes
     */
    public void setRecipes(List<Integer> recipes) {
        this.recipes = recipes;
    }

    /**
     * 
     * @return
     *     The equipmentPvp
     */
    public EquipmentPvp getEquipmentPvp() {
        return equipmentPvp;
    }

    /**
     * 
     * @param equipmentPvp
     *     The equipment_pvp
     */
    public void setEquipmentPvp(EquipmentPvp equipmentPvp) {
        this.equipmentPvp = equipmentPvp;
    }

    /**
     * 
     * @return
     *     The bags
     */
    public List<Bag> getBags() {
        return bags;
    }

    /**
     * 
     * @param bags
     *     The bags
     */
    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }


}
