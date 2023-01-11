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
            case "invalidPseudo" -> "Le pseudo doit faire entre 2 et 20 caractères";
            case "invalidEmail" -> "Le format de l'adresse email n'est pas valide";
            default -> "";
        };
    }

    private String getServerDetailsMessage(String errorType, String errorData) {
        String endSentenceRequirements = " does not meet the requirements";
        return switch (errorType) {
            case "pseudo" -> "Pseudo not available : " + errorData;
            case "email" -> "Email already registered";
            case "invalidPassword" -> "Password" + endSentenceRequirements;
            case "invalidPseudo" -> "Pseudo " + errorData + endSentenceRequirements;
            case "invalidEmail" -> "Email " + errorData + endSentenceRequirements;
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
