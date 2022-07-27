package com.p2p.condominium.repository;

import com.p2p.condominium.document.ExpenseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends ReactiveMongoRepository<ExpenseDocument, String> {
}
