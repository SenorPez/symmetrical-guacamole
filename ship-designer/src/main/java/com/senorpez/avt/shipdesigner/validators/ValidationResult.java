package com.senorpez.avt.shipdesigner.validators;

import java.util.List;

public record ValidationResult(boolean valid, List<String> validationErrors) {}
