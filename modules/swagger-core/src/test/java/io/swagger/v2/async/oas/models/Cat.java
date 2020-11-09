package io.swagger.v2.async.oas.models;

import io.swagger.v2.async.oas.models.composition.Pet;
import io.swagger.v2.aas.annotations.media.Schema;

@Schema//(parent = Pet.class)
public interface Cat extends Pet {
    Integer getClawCount();

    void setClawCount(Integer name);
}