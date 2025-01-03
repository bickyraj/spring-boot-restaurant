package com.bickyraj.demo.repository.postgres;

import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.model.AccountModel;
import com.bickyraj.demo.repository.AccountRepository;
import com.bickyraj.demo.repository.jpa.JpaAccountRepository;
import io.micrometer.observation.annotation.Observed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgreSqlAccountRepository implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;
    private final EntityManager entityManager;

    @Autowired
    public PostgreSqlAccountRepository(JpaAccountRepository jpaAccountRepository, EntityManager entityManager) {
        this.jpaAccountRepository = jpaAccountRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Account> findByUsername(String username, LockModeType lockMode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountModel> cq = cb.createQuery(AccountModel.class);
        Root<AccountModel> root = cq.from(AccountModel.class);
        Predicate predicate = cb.equal(root.get("username"), username);
        cq.where(predicate);
        try {
            AccountModel ac = entityManager.createQuery(cq).setLockMode(lockMode).getSingleResult();
            return Optional.of(ac.toEntity());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(Account account) {
        try {
            jpaAccountRepository.save(AccountModel.fromEntity(account));
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException("Email or username already exists");
        }
        return true;
    }

    @Override
    public Boolean updateBalance(Account account, Double amount) {
        account.setBalance(account.getBalance() - amount);
        jpaAccountRepository.save(AccountModel.fromEntity(account));
        return true;
    }

    @Override
    public boolean existsByEmailAndUsername(String email, String username) {
        return jpaAccountRepository.existsByEmail(email, username);
    }

    @Override
    @Observed(name = "account.repository.time")
    public List<Account> all() {
        return jpaAccountRepository.findAll().stream().map(AccountModel::toEntity).toList();
    }
}
