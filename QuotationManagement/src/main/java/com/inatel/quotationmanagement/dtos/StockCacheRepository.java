package com.inatel.quotationmanagement.dtos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCacheRepository extends JpaRepository<StockCache, String> {
}
