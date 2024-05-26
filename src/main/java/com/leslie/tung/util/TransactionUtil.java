package com.leslie.tung.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Component
public class TransactionUtil {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void doInTransaction(final Consumer<EntityManager> c) {
        c.accept(entityManager);
    }
}