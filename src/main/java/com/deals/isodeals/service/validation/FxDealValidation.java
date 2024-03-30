package com.deals.isodeals.service.validation;

import com.deals.isodeals.annotation.Validator;
import com.deals.isodeals.service.dto.FxDTO;
import org.springframework.util.StringUtils;

import java.util.Currency;
import java.util.List;

@Validator
public class FxDealValidation {

    public List<ValidatorError> validateNewDeal(FxDTO dealDTO) {
        ValidationBuilder validation = new ValidationBuilder();
        if( dealDTO== null) {
            validation.addError("error.deal.null");
        }else {
            if(!StringUtils.hasText(dealDTO.getFromIsoCurrency()) || !isValidISOCurrencyCode(dealDTO.getFromIsoCurrency())) {
                validation.addError("error.deal.currency.from.not.valid", dealDTO.getFromIsoCurrency());
            }
            if(!StringUtils.hasText(dealDTO.getToIsoCurrency()) || !isValidISOCurrencyCode(dealDTO.getToIsoCurrency())) {
                validation.addError("error.deal.currency.to.not.valid", dealDTO.getToIsoCurrency());
            }
            if(validation.isClean() && dealDTO.getToIsoCurrency().equals(dealDTO.getFromIsoCurrency())) {
                validation.addError("error.deal.currency.same", dealDTO.getToIsoCurrency());
            }
            if(dealDTO.getDealAmount() == null) {
                validation.addError("error.deal.amount.not.valid");
            }
        }
        return validation.build();
    }

    public ValidatorError addListingError(int index) {
        ValidatorError validatorError = new ValidatorError();
        validatorError.setErrorKey("error.index");
        validatorError.setArguments(new Object[]{index + 1});
        return validatorError;
    }
//calls to boolean method are always inverted... what does that mean?
    private boolean isValidISOCurrencyCode(String currencyCode) {
        return Currency.getAvailableCurrencies().stream().anyMatch(c -> c.getCurrencyCode().equals(currencyCode));
    }

}
//    private boolean isValidISOCurrencyCode(String currencyCode) {
//        return Currency.getAvailableCurrencies().stream().noneMatch(c -> c.getCurrencyCode().equals(currencyCode));
//    }