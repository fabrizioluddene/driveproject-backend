package it.diriveprojectbe.commons.message;

public enum DataBaseErrorEnum {
    PRROVA("12333");




    private final String sqlState;


    private DataBaseErrorEnum(String sqlState) {
        this.sqlState = sqlState;


    }

    public String getSqlState() {
        return sqlState;
    }
}
