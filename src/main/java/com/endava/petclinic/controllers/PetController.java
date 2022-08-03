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

    public static Pet generateNewRandomPet() {
        Faker faker = new Faker();
        Pet pet = new Pet();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        pet.setName(faker.dog().name());
        pet.setOwner(OwnerController.generateNewRandomOwner());
        pet.setType(new PetType(faker.animal().name()));
        pet.setBirthDate(formatter.format(faker.date().birthday(1, 10)));

        return pet;
    }
}

