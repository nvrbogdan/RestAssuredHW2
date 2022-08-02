package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.endava.petclinic.models.Visit;
import com.github.javafaker.Faker;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class VisitController {
    public static Visit generateNewRandomVisit(Pet pet, PetType type){

        Faker faker = new Faker();
        Visit visit = new Visit();
        visit.setDescription(faker.howIMetYourMother().catchPhrase());
        visit.setDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        visit.setPet(pet);
        visit.setType(type);
        return visit;
    }
}
