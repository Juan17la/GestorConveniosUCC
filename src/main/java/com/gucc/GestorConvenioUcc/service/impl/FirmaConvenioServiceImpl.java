package com.gucc.GestorConvenioUcc.service.impl;

import com.gucc.GestorConvenioUcc.dto.FirmaConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.FirmaConvenio;
import com.gucc.GestorConvenioUcc.mapper.FirmaConvenioMapper;
import com.gucc.GestorConvenioUcc.repository.FirmaConvenioRepository;
import com.gucc.GestorConvenioUcc.service.FirmaConvenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirmaConvenioServiceImpl implements FirmaConvenioService {

    private final FirmaConvenioMapper firmaConvenioMapper;
    private final FirmaConvenioRepository firmaConvenioRepository;


    @Override
    public FirmaConvenioDTO create(FirmaConvenioDTO FirmaConvenioDTO) {
        FirmaConvenio firmaConvenio = firmaConvenioMapper.toEntity(FirmaConvenioDTO);
        FirmaConvenio saved = firmaConvenioRepository.save(firmaConvenio);
        return firmaConvenioMapper.toDto(saved);
    }

    @Override
    public FirmaConvenioDTO findById(long id) {
        FirmaConvenio firmaConvenio = firmaConvenioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro ninguna Firma de Convenio"));
        return firmaConvenioMapper.toDto(firmaConvenio);
    }

    @Override
    public List<FirmaConvenioDTO> findAll() {
        return firmaConvenioMapper.toDtoList(firmaConvenioRepository.findAll());
    }

    @Override
    public void delete(long id) {
        firmaConvenioRepository.deleteById(id);
    }
}
