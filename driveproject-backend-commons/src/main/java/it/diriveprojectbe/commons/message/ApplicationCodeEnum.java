package it.diriveprojectbe.commons.message;

public enum ApplicationCodeEnum {
    SUCCESS("APP-0000","SUCCESS"),
    FIELD("APP-0001","FIELD"),
    UNIQUECONSTRAINT("DBC-0001","unique constraint violated"),
    APPLICATIONERROR("APP-9999","application erro"),
    NOTFOUND("DBC-0003","not found"),
    JWTISEXPIRED("JWT-0001","JWT token is expired"),
    JWTNOTPRESENTDB("JWT-0002","JWT token not present in store"),
    JWTNOTPRESENTHEADER("JWT-0003","JWT token not present in request Header"),
    JWTISNOTVALID("JWT-0004","JWT token is not valid");



    private final String code;
    private final String message;

    private ApplicationCodeEnum(String code,String message) {
        this.code = code;
        this.message = message;

    }

    public String getCode() {
        return code;
    }
    public String getMessage(){return message;}



}
