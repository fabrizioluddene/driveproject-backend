package it.diriveprojectbe.commons.message;

public enum ApplicationCodeEnum {
    SUCCESS("0000"),
    FIELD("0001"),
    UNIQUECONSTRAINT("0002"),
    APPLICATIONERROR("9999");
    private final String code;

    private ApplicationCodeEnum(String code) {
        this.code = code;

    }

    public String getCode() {
        return code;
    }


}
