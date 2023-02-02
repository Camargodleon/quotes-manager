package com.inatel.quotationmanagement.controllers;

import com.inatel.quotationmanagement.dtos.StockCache;
import com.inatel.quotationmanagement.services.StockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StockCacheController {

    @Autowired
    private StockCacheService stockCacheService;

    @DeleteMapping("/stockcache")
    public ResponseEntity<Object> clearCache(){
        stockCacheService.clearCache();
        return ResponseEntity.ok().build();
    }
}
