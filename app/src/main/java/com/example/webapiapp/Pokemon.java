package com.example.webapiapp;

public class Pokemon {
    private int id;
    private String name;
    private float weight;
    private float height;
    private int base_experience;
    private String moves;
    private String abilities;

    public Pokemon(int nat_num, String name, float weight, float height, int base_xp, String move, String ability) {
        this.id = nat_num;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.base_experience = base_xp;
        this.moves = move;
        this.abilities = ability;
    }

    public int getNat_num() {
        return id;
    }

    public void setNat_num(int nat_num) {
        this.id = nat_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
