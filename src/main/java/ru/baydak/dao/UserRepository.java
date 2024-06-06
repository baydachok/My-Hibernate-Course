package ru.baydak.dao;

import jakarta.persistence.EntityManager;
import ru.baydak.entity.User;

public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
