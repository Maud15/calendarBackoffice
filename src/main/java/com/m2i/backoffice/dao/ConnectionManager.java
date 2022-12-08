package com.m2i.backoffice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionManager {

    private static EntityManagerFactory CONNECTION_INSTANCE;
    private static EntityManager ENTITY_MANAGER;

    public static EntityManager getEntityManager() {
        if(CONNECTION_INSTANCE == null) {
            CONNECTION_INSTANCE = Persistence.createEntityManagerFactory("planning_backoffice");
        }
        if(ENTITY_MANAGER == null || !ENTITY_MANAGER.isOpen()) {
            ENTITY_MANAGER = CONNECTION_INSTANCE.createEntityManager();
        }
        return ENTITY_MANAGER;
    }

    public static void closeConnection() {
        try {
            CONNECTION_INSTANCE.close();
        } catch (Exception e) {
            System.err.println("Fermeture de la connexion impossible");
        }
    }
}
