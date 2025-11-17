package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.entity.Campus;
import com.gucc.GestorConvenioUcc.mapper.CampusMapper;
import com.gucc.GestorConvenioUcc.repository.CampusRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository repository;
    private final CampusMapper mapper;

    public CampusDTO create(CampusDTO request){
        Campus newCampus = mapper.toEntity(request);
        Campus saved = repository.save(newCampus);
        return mapper.toDTO(saved);
    }
}

