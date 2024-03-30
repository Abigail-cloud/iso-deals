package com.deals.isodeals.controller;

import com.deals.isodeals.annotation.ResponseWrapper;
import com.deals.isodeals.service.dto.FxDTO;
import com.deals.isodeals.service.implementation.FxDealServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@ResponseWrapper
@RequestMapping(path = "/api/v1")
public class FxDealController {
    private final String CONTROLLER_VERSION = "/api/v1";
    private FxDealServiceImpl isoDealService;
    public FxDealController(FxDealServiceImpl isoDealService){
        this.isoDealService = isoDealService;
    }

    @PostMapping("/create-deals")
    public ResponseEntity<FxDTO> createDeal (@Valid @RequestBody FxDTO fxDTO) throws URISyntaxException {
        FxDTO dealDto = isoDealService.saveDto(fxDTO);
        return ResponseEntity
                .created(new URI(String.format("%s%s%s", CONTROLLER_VERSION, "/create-deals/", dealDto.getUniqueDealId())))
                .body(dealDto);
    }
}
