package it.diriveprojectbe.project.api.excpetion;

import it.diriveprojectbe.commons.message.ApplicationCodeEnum;

public class NoUserFoundException extends Exception {

    public NoUserFoundException(){
        super(ApplicationCodeEnum.NOTFOUND.getMessage());
    }
}
