package com.inatel.quotationmanagement.controllers;

import com.inatel.quotationmanagement.dtos.StockCache;
import com.inatel.quotationmanagement.services.StockCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "StockCache")
@RestController
@RequestMapping("/")
public class StockCacheController {

    @Autowired
    private StockCacheService stockCacheService;

    @ApiOperation("Deletes all cached stock information.")
    @DeleteMapping("/stockcache")
    public ResponseEntity<Object> clearCache(){
        stockCacheService.clearCache();
        return ResponseEntity.noContent().build();
    }
}
