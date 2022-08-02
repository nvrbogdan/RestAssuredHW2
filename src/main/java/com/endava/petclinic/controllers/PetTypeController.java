package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.PetType;
import com.github.javafaker.Faker;

public class PetTypeController {
    public static PetType generateNewRandomPetType(){
        Faker faker = new Faker();
        PetType type = new PetType();
        type.setName(faker.animal().name());
        return type;
    }
}

