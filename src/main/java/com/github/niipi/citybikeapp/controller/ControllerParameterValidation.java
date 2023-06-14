package com.github.niipi.citybikeapp.controller;

import java.security.InvalidParameterException;

public class ControllerParameterValidation {

    public ControllerParameterValidation() {
        // empty constructor
    }

    // Evaluate numeric request parameters for page and size, ensure parameter is within acceptable numeric range
    int makeGivenParameterAcceptable(int parameter) {
        return makeGivenParameterAcceptable(parameter, 100);
    }

    int makeGivenParameterAcceptable(int parameter, int maxValue) {
        int acceptableParameter;
        if (parameter < 0) {
            acceptableParameter = 0;
        }
        else if (parameter > maxValue) {
            acceptableParameter = 100;
        }
        else {
            acceptableParameter = parameter;
        }
        return acceptableParameter;
    }

    // Evaluate string parameter for station names, throw an exception for invalid parameters
    void isStringParameterValid(String parameterName, String parameterValue) {
        // Acceptable string patterns contain alphanumeric characters, - and parentheses, and are between 1-256 characters long
        String pattern = "^[a-zA-Z0-9() åäöÅÄÖ-]{1,255}$";
        if (!parameterValue.matches(pattern)) {
            throw new InvalidParameterException("Invalid parameter for station request was given: " + parameterName + " = " + parameterValue);
        }
    }
}