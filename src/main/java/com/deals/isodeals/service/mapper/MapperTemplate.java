package com.deals.isodeals.service.mapper;

import java.util.List;

public interface MapperTemplate<E, T>{
    E toEntity(T dto);
    T toDto(E entity);
    List<E> toEntity(List<T> dtoList);
    List<T> toDto(List<E> entityList);
}
