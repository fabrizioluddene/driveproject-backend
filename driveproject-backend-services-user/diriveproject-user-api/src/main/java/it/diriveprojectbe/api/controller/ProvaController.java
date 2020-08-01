package it.diriveprojectbe.api.controller;

import it.diriveprojectbe.api.UserApi;
import jdk.nashorn.internal.runtime.UserAccessorProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ProvaController implements UserApi {
    @Override
    public ResponseEntity<String> prova() {
        return  new ResponseEntity<>("prova", HttpStatus.OK);
    }
}
