package com.inatel.quotationmanagement.repositories;

import com.inatel.quotationmanagement.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    @Query(value = "SELECT * FROM stock_app s WHERE s.stock_id = :id", nativeQuery = true)
    Stock getByStockId(@Param("id") String id);
}
