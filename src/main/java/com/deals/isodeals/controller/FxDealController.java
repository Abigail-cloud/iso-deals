package com.deals.isodeals.controller;

import com.deals.isodeals.annotation.ResponseWrapper;
import com.deals.isodeals.service.dto.FxDTO;
import com.deals.isodeals.service.implementation.FxDealServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@ResponseWrapper
@RequestMapping(path = "/api/v1")
public class FxDealController {
    private final String CONTROLLER_VERSION = "/api/v1";
    private final FxDealServiceImpl fxDealService;
    public FxDealController(FxDealServiceImpl fxDealService){
        this.fxDealService = fxDealService;
    }

    @PostMapping("/create-deals")
    public ResponseEntity<FxDTO> createDeal (@Valid @RequestBody FxDTO fxDTO) throws URISyntaxException {
        FxDTO dealDto = fxDealService.saveDto(fxDTO);
        return ResponseEntity
                .created(new URI(String.format("%s%s%s", CONTROLLER_VERSION, "/create-deals/", dealDto.getUniqueDealId())))
                .body(dealDto);
    }
    @GetMapping("/deals")
    public ResponseEntity<Page<FxDTO>> findAllFxDeals (@PageableDefault(value = 20) Pageable pageable) throws URISyntaxException {
        Page<FxDTO> fxDeal = fxDealService.getAllDealRequest(pageable);
        return ResponseEntity
                .ok()
                .body(fxDeal);
    }
    @GetMapping("/single-deal/{unique-id}")
    public ResponseEntity <Optional<FxDTO>> getDealById (@Valid @PathVariable("unique-id") String uniqueDealId ) throws URISyntaxException {
        Optional<FxDTO> dealDto = fxDealService.getSingleDeal(uniqueDealId);
        return ResponseEntity
                .ok()
                .body(dealDto);
    }
    @PutMapping("/update-deals/{unique-id}")
    public ResponseEntity<FxDTO> updateDeal (@Valid @PathVariable("unique-id") @RequestBody FxDTO fxDeal, String uniqueDealId){
        FxDTO dealDto = fxDealService.updateDeal(fxDeal, uniqueDealId);
        return ResponseEntity
                .ok()
                .body(dealDto);
    }
    @DeleteMapping ("/delete-deal/{unique-id}")
    public ResponseEntity<FxDTO> deleteFxDeal (@Valid @PathVariable("unique-id") String uniqueDealId) throws URISyntaxException {
        FxDTO deleteDeal = fxDealService.deleteDeal(uniqueDealId);
        return ResponseEntity
                .accepted()
                .body( deleteDeal);
    }
}
