package com.deals.isodeals.service.mapper;


import com.deals.isodeals.entity.FxEntity;
import com.deals.isodeals.service.dto.FxDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FxMapper extends MapperTemplate<FxEntity, FxDTO> {
}
