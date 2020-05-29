package com.certant.app.exceptions;

public class PokedexException extends Exception {
    private Exception originalException;
    private String businessError;
    private String technicalError;
    private String parseError;

    public PokedexException() {
    }

    public PokedexException(Exception originalException) {
        this.originalException = originalException;
    }

    public Exception getOriginalException() {
        return originalException;
    }

    public void setOriginalException(Exception originalException) {
        this.originalException = originalException;
    }

    public String getBusinessError() {
        return businessError;
    }

    public void setBusinessError(String businessError) {
        this.businessError = businessError;
    }

    public String getTechnicalError() {
        return technicalError;
    }

    public void setTechnicalError(String technicalError) {
        this.technicalError = technicalError;
    }

    public String getParseError() {
        return parseError;
    }

    public void setParseError(String parseError) {
        this.parseError = parseError;
    }
}