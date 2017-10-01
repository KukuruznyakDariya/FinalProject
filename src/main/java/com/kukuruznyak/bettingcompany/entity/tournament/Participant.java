package com.kukuruznyak.bettingcompany.entity.tournament;

import com.kukuruznyak.bettingcompany.entity.Model;

public class Participant extends Model {
    /**
     * unique
     */
    private String name;
    private int age;
    private int weight;
    private String trainer;
    private String jockey;
    private Tournament tournament;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getJockey() {
        return jockey;
    }

    public void setJockey(String jockey) {
        this.jockey = jockey;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;

        Participant that = (Participant) o;

        if (getAge() != that.getAge()) return false;
        if (getWeight() != that.getWeight()) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getTrainer().equals(that.getTrainer())) return false;
        return getJockey().equals(that.getJockey());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + getWeight();
        result = 31 * result + getTrainer().hashCode();
        result = 31 * result + getJockey().hashCode();
        return result;
    }
}