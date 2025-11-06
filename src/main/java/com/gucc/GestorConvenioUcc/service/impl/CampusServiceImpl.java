package com.gucc.GestorConvenioUcc.service.impl;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.entity.Campus;
import com.gucc.GestorConvenioUcc.mapper.CampusMapper;
import com.gucc.GestorConvenioUcc.repository.CampusRepository;
import com.gucc.GestorConvenioUcc.service.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusServiceImpl implements CampusService {

    private final CampusRepository campusRepository;
    private final CampusMapper campusMapper; // puedes inyectarlo si lo marcas con @Component o @Mapper(componentModel = "spring")

    @Override
    public CampusDTO create(CampusDTO campusDTO) {
        Campus campus = campusMapper.toEntity(campusDTO);
        Campus saved = campusRepository.save(campus);
        return campusMapper.toDto(saved);
    }

    @Override
    public CampusDTO findById(Long id) {
        Campus campus = campusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campus no encontrado"));
        return campusMapper.toDto(campus);
    }

    @Override
    public List<CampusDTO> findAll() {
        return campusMapper.toDtoList(campusRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        campusRepository.deleteById(id);
    }
}
