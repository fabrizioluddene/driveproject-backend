package it.diriveprojectbe.api.excpetion;

public class NoUserFoundException extends Exception {

    public NoUserFoundException(){
        super("no user found!");
    }
}
