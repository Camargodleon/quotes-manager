package com.inatel.quotationmanagement.repositories;

import com.inatel.quotationmanagement.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
