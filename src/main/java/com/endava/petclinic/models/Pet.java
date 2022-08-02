package com.endava.petclinic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private Integer id;
    private String name;
    private String birthDate;
    private Owner owner;
    private PetType type;

    public Pet() {
    }

    public Pet(String name, String birthDate, Owner owner, PetType type) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
        Pet pet = (Pet) o;
        return Objects.equals(name, pet.name) && Objects.equals(birthDate, pet.birthDate) && Objects.equals(owner, pet.owner) && Objects.equals(type, pet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, owner, type);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", owner=" + owner +
                ", type=" + type +
                '}';
    }
}
