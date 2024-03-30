package com.deals.isodeals.service.implementation;

import com.deals.isodeals.entity.FxEntity;
import com.deals.isodeals.exception.ValidationDealException;
import com.deals.isodeals.repository.FxRepository;
import com.deals.isodeals.service.FxDealService;
import com.deals.isodeals.service.dto.FxDTO;
import com.deals.isodeals.service.mapper.FxMapper;
import com.deals.isodeals.service.validation.FxDealValidation;
import com.deals.isodeals.service.validation.ValidatorError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FxDealServiceImpl implements FxDealService {
    private final FxRepository dealsRepository;
    private final FxMapper dealMapper;
    private final FxDealValidation dealValidation;

    public FxDealServiceImpl(FxRepository dealsRepository, FxMapper dealMapper, FxDealValidation dealValidation ){
        this.dealsRepository = dealsRepository;
        this.dealMapper = dealMapper;
        this.dealValidation = dealValidation;
    }

    @Override
    public FxDTO saveDto(FxDTO fxDTO) {
        List<ValidatorError> validatorErrors = dealValidation.validateNewDeal(fxDTO);
        if(!CollectionUtils.isEmpty(validatorErrors)) throw new ValidationDealException(validatorErrors, "error.validation");
        FxEntity isoDeals = dealMapper.toEntity(fxDTO);
        isoDeals.setUniqueDealId(UUID.randomUUID().toString());
        isoDeals.setDealTimestamp(new Date().toInstant());
        isoDeals = dealsRepository.save(isoDeals);
        return dealMapper.toDto(isoDeals);
    }

    @Override
    public Page<FxDTO> getAllDealRequest(Pageable pageable) {
        return null;
    }

    @Override
    public FxDTO getSingleDeal(String uniqueDealId) {
        return null;
    }

    @Override
    public FxDTO updateDeal(FxDTO fxDTO, String uniqueDealId) {
        return null;
    }

    @Override
    public FxDTO deleteDeal(String uniqueDealId) {
        return null;
    }
}
