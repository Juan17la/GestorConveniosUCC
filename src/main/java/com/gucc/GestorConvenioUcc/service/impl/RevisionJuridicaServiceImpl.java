package com.gucc.GestorConvenioUcc.service.impl;

import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;
import com.gucc.GestorConvenioUcc.entity.RevisionJuridica;
import com.gucc.GestorConvenioUcc.mapper.RevisionJuridicaMapper;
import com.gucc.GestorConvenioUcc.repository.RevisionJuridicaRepository;
import com.gucc.GestorConvenioUcc.service.RevisionJuridicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RevisionJuridicaServiceImpl implements RevisionJuridicaService {

    private final RevisionJuridicaRepository revisionJuridicaRepository;
    private final RevisionJuridicaMapper revisionJuridicaMapper;

    @Override
    public RevisionJuridicaDTO create(RevisionJuridicaDTO revisionJuridicaDTO) {
        RevisionJuridica revisionJuridica = revisionJuridicaMapper.toEntity(revisionJuridicaDTO);
        RevisionJuridica saved = revisionJuridicaRepository.save(revisionJuridica);
        return revisionJuridicaMapper.toDto(saved);
    }

    @Override
    public RevisionJuridicaDTO findById(long id) {
        RevisionJuridica revisionJuridica = revisionJuridicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revision Juridica no encontrada"));
        return revisionJuridicaMapper.toDto(revisionJuridica);
    }

    @Override
    public List<RevisionJuridicaDTO> findAll() {
        return revisionJuridicaMapper.toDtoList(revisionJuridicaRepository.findAll());
    }

    @Override
    public void delete(long id) {
        revisionJuridicaRepository.deleteById(id);
    }

}
