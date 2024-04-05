package com.deals.isodeals.service.implementation;

import com.deals.isodeals.entity.FxEntity;
import com.deals.isodeals.exception.CustomApiException;
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

import java.util.*;

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
        return dealsRepository.findAll(pageable).map(dealMapper::toDto);
    }

    @Override
    public Optional<FxDTO> getSingleDeal(String uniqueDealId) {
        return dealsRepository.getIsoEntityByUniqueDealId(uniqueDealId).map(dealMapper ::toDto);
    }

    @Override
    public FxDTO updateDeal(FxDTO fxDTO, String uniqueDealId) {
        Optional<FxDTO> fxDeal = dealsRepository.getIsoEntityByUniqueDealId(uniqueDealId).map(dealMapper::toDto);
        List<ValidatorError> validatorErrorList = dealValidation.validateNewDeal(fxDTO);
        if (!CollectionUtils.isEmpty(validatorErrorList)) throw new ValidationDealException(validatorErrorList, "Deal is empty or incorrect");
        FxDTO updateFx = fxDeal.get();
        updateFx.setFromIsoCurrency(fxDTO.getFromIsoCurrency());
        updateFx.setToIsoCurrency(fxDTO.getToIsoCurrency());
        updateFx.setDealAmount(fxDTO.getDealAmount());
        updateFx.setDealTimestamp(new Date().toInstant());
//            Now let's convert FX-DTO to FXEntity
        FxEntity entity = dealMapper.toEntity(updateFx);
        dealsRepository.save(entity);
        return updateFx;
    }

    @Override
    public FxDTO deleteDeal(String uniqueDealId) {
        FxEntity fx = dealsRepository.getIsoEntityByUniqueDealId(uniqueDealId).orElseThrow(()->new CustomApiException("Deal not found" + uniqueDealId));
        fx.setDeleted(true);
        dealsRepository.save(fx);
         return dealMapper.toDto(fx);
    }
}
