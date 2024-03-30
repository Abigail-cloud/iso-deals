package com.deals.isodeals.service;

import com.deals.isodeals.service.dto.FxDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FxDealService {
FxDTO saveDto(FxDTO fxDTO);
    Page<FxDTO> getAllDealRequest(Pageable pageable);
    FxDTO getSingleDeal(String uniqueDealId);
    FxDTO updateDeal(FxDTO fxDTO, String uniqueDealId);
    FxDTO deleteDeal(String uniqueDealId);
}
