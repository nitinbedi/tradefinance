package com.db.tradefinance.validation;

import com.db.tradefinance.model.ErrorObject;
import com.db.tradefinance.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class TradeValidations implements Validator {
    private static final Logger LOG = LoggerFactory.getLogger(TradeValidations.class);
    @Override
    public boolean supports(Class<?> clazz) {
        LOG.info("inside the supports method"+clazz.getName());
        boolean assign = Trade.class.isAssignableFrom(clazz);
        LOG.info("inside the assgi  method"+assign);
        return assign;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Trade)
        {
            LOG.info("Instance of trade object ************* ");
        }
        LOG.info("inside the validation method &&&&&&&&&&& ");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "tradeId", "trade.mandatory","trade required.");
    }

    public void bindErrors(Object c, Errors errors) {
        BindingResult br = (BindingResult)errors;
        if (c instanceof Trade)
        {
            LOG.info("Instance of trade object ************* ");
        }
        LOG.info("inside the validation method &&&&&&&&&&& ");
        Trade trade =(Trade)c;
        if (trade.getErrors()!=null) {
            for (ErrorObject errorObject : trade.getErrors()) {
                ObjectError error = new ObjectError(errorObject.getPath(), errorObject.getErrorDesc());
                br.addError(error);

            }
        }
    }
}
