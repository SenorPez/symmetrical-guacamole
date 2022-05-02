package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.validators.HullSpacesValidator;
import com.senorpez.avt.shipdesigner.validators.ValidationResult;

import java.util.ArrayList;
import java.util.List;

class Ship {
    private int hullSpaces;

    private boolean valid = false;
    private final List<String> validationErrors = new ArrayList<>();

    Ship build() {
        ValidationResult hullSpacesValidation = HullSpacesValidator.validate(hullSpaces);
        valid = hullSpacesValidation.valid();
        validationErrors.addAll(hullSpacesValidation.validationErrors());
        return this;
    }

    boolean isValid() {
        return valid;
    }

    List<String> getValidationErrors() {
        return validationErrors;
    }

    Ship setHullSpaces(final int hullSpaces) {
        this.hullSpaces = hullSpaces;
        return this;
    }
}
