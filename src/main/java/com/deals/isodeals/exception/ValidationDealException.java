package com.deals.isodeals.exception;

import com.deals.isodeals.service.validation.ValidatorError;

import java.util.List;

public class ValidationDealException extends RuntimeException{
    private final Long serialVersionUID = 1L;
    private final List<ValidatorError> validatorErrors;
    public ValidationDealException (List<ValidatorError> validatorErrors, String message){
        super(message);
        this.validatorErrors = validatorErrors;
    }
    public List<ValidatorError> getValidationErrors(){
        return validatorErrors;
    }
}
