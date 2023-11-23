package com.example.webapiapp;

public class Pokemon {
    private String id;
    private String name;
    private float weight;
    private float height;
    private int base_experience;
    private String moves;
    private String abilities;
    private String image_url;
    private String sprite_url;

    public Pokemon(String nat_num, String name, String image_url) {
        this.id = nat_num;
        this.name = name;
        this.image_url = image_url;
    }

    public String getNat_num() {
        return id;
    }

    public void setNat_num(String nat_num) {
        this.id = nat_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    /*
    public String getSprite_url() {
        return sprite_url;
    }

    public void setSprite_url(String sprite_url) {
        this.sprite_url = sprite_url;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public String getMove() {
        return moves;
    }

    public void setMove(String move) {
        this.moves = move;
    }

    public String getAbility() {
        return abilities;
    }

    public void setAbility(String ability) {
        this.abilities = ability;
    }
     */
}
