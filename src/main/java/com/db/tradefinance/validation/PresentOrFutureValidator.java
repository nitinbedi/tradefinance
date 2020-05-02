package com.db.tradefinance.validation;

import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.controller.mvc.TradeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Date;

public class PresentOrFutureValidator
        implements ConstraintValidator<PresentOrFuture, Date> {
    private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);

    public final void initialize(final PresentOrFuture annotation) {}

    public final boolean isValid(final Date value,
                                 final ConstraintValidatorContext context) {
        LOG.info("***** check future validations ******* ");
        // Only use the date for comparison
        return (DateUtils.presentOrFutureDate(value));

    }
}