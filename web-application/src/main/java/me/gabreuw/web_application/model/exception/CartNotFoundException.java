package me.gabreuw.web_application.model.exception;

import java.text.MessageFormat;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(final Long id){
        super(MessageFormat.format("Could not find cart with id: {0}", id));
    }

}
