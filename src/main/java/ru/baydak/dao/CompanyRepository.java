package ru.baydak.dao;

import jakarta.persistence.EntityManager;
import ru.baydak.entity.Company;

public class CompanyRepository extends RepositoryBase<Integer, Company> {

    public CompanyRepository(EntityManager entityManager) {
        super(Company.class, entityManager);
    }
}
