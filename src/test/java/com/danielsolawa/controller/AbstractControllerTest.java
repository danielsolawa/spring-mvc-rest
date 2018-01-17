package com.danielsolawa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */


public abstract class AbstractControllerTest {

    protected static String asJson(Object o){
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
