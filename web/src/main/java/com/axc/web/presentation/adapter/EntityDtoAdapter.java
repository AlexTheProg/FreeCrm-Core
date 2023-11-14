package com.axc.web.presentation.adapter;

public interface EntityDtoAdapter<ENTITY, DTO> {
    DTO mapEntityToDto(ENTITY entity);

    ENTITY mapDtoToEntity(DTO dto);
}
