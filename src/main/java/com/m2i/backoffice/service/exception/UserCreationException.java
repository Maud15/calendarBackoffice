package com.m2i.backoffice.service.exception;

public class UserCreationException extends Exception {

    private final String uiMessage;
    private final String serverMessage;

    public UserCreationException(String errorType, String errorData) {
        super("Error while creating User");
        uiMessage = getUiDetailsMessage(errorType,errorData);
        serverMessage = getServerDetailsMessage(errorType,errorData);
    }

    private String getUiDetailsMessage(String errorType, String errorData) {
        return switch (errorType) {
            case "pseudo" -> "L'identifiant " + errorData + " n'est pas disponible";
            case "email" -> "Il existe déjà un compte avec l'email : " + errorData;
            case "invalidPassword" -> "Le mot de passe doit contenir au moins 8 caratères, dont 1 minuscule, 1 majuscule et 1 chiffre";
            default -> "";
        };
    }

    private String getServerDetailsMessage(String errorType, String errorData) {
        return switch (errorType) {
            case "pseudo" -> "Pseudo " + errorData + " is not available";
            case "email" -> "There is already an account with this email";
            case "invalidPassword" -> "Password does not meet the requirements";
            default -> "unknown error";
        };
    }

    public String getUiMessage() {
        return uiMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }
}
