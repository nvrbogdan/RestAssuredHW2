package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class PetController {

    public PetController() {
    }

    public static Pet generateNewRandomPet(Owner owner, PetType type) {
        Faker faker = new Faker();
        Pet pet = new Pet();
        pet.setName(faker.funnyName().name());
        pet.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        pet.setOwner(owner);
        pet.setType(type);
        return pet;
    }
}

