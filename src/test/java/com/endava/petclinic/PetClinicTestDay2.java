package com.endava.petclinic;

import com.endava.petclinic.controllers.OwnerController;
import com.endava.petclinic.controllers.PetController;
import com.endava.petclinic.controllers.PetTypeController;
import com.endava.petclinic.controllers.VisitController;
import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.endava.petclinic.models.Visit;
import com.endava.petclinic.utils.EnvReader;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PetClinicTestDay2 {

    @Test
    public void postOwnerTest() {
        HashMap<String, String> owner = new HashMap<>();
        owner.put("id", null);
        owner.put("firstName", "George");
        owner.put("lastName", "Popescu");
        owner.put("address", "Tineretului 7");
        owner.put("city", "Bucharest");
        owner.put("telephone", "0760055782");

        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org/")
                .basePath("/petclinic")
                .port(80)
                .contentType(ContentType.JSON)
                .body(owner).log().all()
                .when()
                .post("/api/owners").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer ownerId = response.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org/")
                .basePath("/petclinic")
                .port(80)
                .pathParam("ownerId", ownerId)
                .when()
                .get("/api/owners/{ownerId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(ownerId));

    }

    @Test
    public void postOwnerTestWithObject() {
        Faker faker = new Faker();
        Owner owner = OwnerController.generateNewRandomOwner();

        ValidatableResponse response = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .body(owner).log().all()
                .when()
                .post("/api/owners").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        owner.setId(response.extract().jsonPath().getInt("id"));
//        Integer ownerId = response.extract().jsonPath().getInt("id");
//
        ValidatableResponse getResponse = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("ownerId", owner.getId())
                .when()
                .get("/api/owners/{ownerId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
        //.body("id", is(owner.getId()));

        Owner ownerFromGetResponse = getResponse.extract().as(Owner.class);
        assertThat(ownerFromGetResponse, is(owner));
    }

    @Test
    public void putOwnerTest() {

        Faker faker = new Faker();
        Owner owner = OwnerController.generateNewRandomOwner();

        ValidatableResponse postResponse = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .body(owner)
                .when().log().all()
                .post("/api/owners")
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        owner.setId(postResponse.extract().jsonPath().getInt("id"));
        owner.setAddress(faker.address().streetAddress());
        owner.setCity(faker.address().city());
        owner.setTelephone(faker.number().digits(10));

        given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .pathParam("ownerId", owner.getId())
                .body(owner).log().all()
                .put("/api/owners/{ownerId}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        ValidatableResponse getResponse = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("ownerId", owner.getId())
                .get("/api/owners/{ownerId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

        Owner actualOwner = getResponse.extract().as(Owner.class);
        assertThat(actualOwner, is(owner));
    }

    @Test
    public void postPetTypeTestWithObject() {

        Faker faker = new Faker();
        PetType type = PetTypeController.generateNewRandomPetType();

        ValidatableResponse response = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .body(type).log().all()
                .when()
                .post("/api/pettypes").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }
//        type.setId(response.extract().jsonPath().getInt("id"));
//        Integer ownerId = response.extract().jsonPath().getInt("id");
//
//        ValidatableResponse getResponse = given().baseUri(EnvReader.getBaseUri())
//                .basePath(EnvReader.getBasePath())
//                .port(EnvReader.getPort())
//                .pathParam("typeId", type.getId())
//                .when()
//                .get("/api/pettypes/{petTypeId}").prettyPeek()
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//        //.body("id", is(owner.getId()));
//
//        PetType typeFromGetResponse = getResponse.extract().as(PetType.class);
//        assertThat(typeFromGetResponse, is(type));
//    }

    @Test
    public void addPet() {

        Owner owner = new Owner();
        owner.setId(202);
        owner.setFirstName("Shizuko");
        owner.setLastName("Schoen");
        owner.setAddress("52643 Franecki Burgs");
        owner.setCity("Lake Kyra");
        owner.setTelephone("2132146992");

        PetType type = new PetType();
        type.setId(28);
        type.setName("deer");

        Faker faker = new Faker();
        Pet pet = PetController.generateNewRandomPet(owner, type);

        ValidatableResponse response = given().baseUri(EnvReader.getBaseUri())
                .port(EnvReader.getPort())
                .basePath(EnvReader.getBasePath())
                .contentType(ContentType.JSON)
                .body(pet)
                .when().log().all()
                .post("/api/pets").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        pet.setId(response.extract().jsonPath().getInt("id"));

        given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("petId", pet.getId())
                .when()
                .get("/api/pets/{petId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(pet.getId()));
  }

    @Test
    public void getPetList() {
        given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                //  .pathParam("petId", 0)
                .when().log().all()
                .get("/api/pets")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void postPetTestWithObject() {
        Owner owner = new Owner();
        owner.setId(202);
        owner.setFirstName("Shizuko");
        owner.setLastName("Schoen");
        owner.setAddress("52643 Franecki Burgs");
        owner.setCity("Lake Kyra");
        owner.setTelephone("2132146992");

        PetType type = new PetType();
        type.setId(28);
        type.setName("deer");
        Faker faker = new Faker();
        Pet pet = PetController.generateNewRandomPet(owner, type);

        ValidatableResponse response = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .body(pet).log().all()
                .when()
                .post("/api/pets").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void addVisit() {

        Owner owner = new Owner();
        owner.setId(202);
        owner.setFirstName("Shizuko");
        owner.setLastName("Schoen");
        owner.setAddress("52643 Franecki Burgs");
        owner.setCity("Lake Kyra");
        owner.setTelephone("2132146992");

        PetType type = new PetType();
        type.setId(28);
        type.setName("deer");

        Pet pet = new Pet();
        pet.setId(97);
        pet.setName("Crystal Ball");
        pet.setType(type);
        pet.setOwner(owner);
        pet.setBirthDate("1988/11/02");

        Faker faker = new Faker();
        Visit visit = VisitController.generateNewRandomVisit(pet, type);

        ValidatableResponse response = given().baseUri(EnvReader.getBaseUri())
                .port(EnvReader.getPort())
                .basePath(EnvReader.getBasePath())
                .contentType(ContentType.JSON)
                .body(visit)
                .when().log().all()
                .post("/api/visits").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        visit.setId(response.extract().jsonPath().getInt("id"));

        given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("visitId", visit.getId())
                .when()
                .get("/api/visits/{visitId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(visit.getId()));
    }
}
