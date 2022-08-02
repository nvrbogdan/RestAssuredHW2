package com.endava.petclinic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Visit {
    private String date;
    private String description;
    private Integer id;
    private Pet pet;
    private PetType type;

    public Visit() {
    }

    public Visit(String date, String description, Pet pet, PetType type) {
        this.date = date;
        this.description = description;
        this.pet = pet;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(date, visit.date) && Objects.equals(description, visit.description) && Objects.equals(pet, visit.pet) && Objects.equals(type, visit.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, pet, type);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", pet=" + pet +
                ", type=" + type +
                '}';
    }
}
