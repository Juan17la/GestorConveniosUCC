package com.gucc.GestorConvenioUcc.service.impl;

import com.gucc.GestorConvenioUcc.dto.RevisionAdministrativaDTO;
import com.gucc.GestorConvenioUcc.entity.RevisionAdministrativa;
import com.gucc.GestorConvenioUcc.mapper.RevisionAdministrativaMapper;
import com.gucc.GestorConvenioUcc.repository.RevisionAdministrativaRepository;
import com.gucc.GestorConvenioUcc.service.RevisionAdministrativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RevisionAdministrativaServiceImpl implements RevisionAdministrativaService {

    private final RevisionAdministrativaRepository revisionAdministrativaRepository;
    private final RevisionAdministrativaMapper revisionAdministrativaMapper;

    @Override
    public RevisionAdministrativaDTO create(RevisionAdministrativaDTO revisionAdministrativaDTO) {
        RevisionAdministrativa revisionAdministrativa = revisionAdministrativaMapper.toEntity(revisionAdministrativaDTO);
        RevisionAdministrativa saved = revisionAdministrativaRepository.save(revisionAdministrativa);
        return revisionAdministrativaMapper.toDto(saved);
    }

    @Override
    public RevisionAdministrativaDTO findById(long id) {
        RevisionAdministrativa revisionAdministrativa = revisionAdministrativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revision Administrativa no encontrada"));
        return revisionAdministrativaMapper.toDto(revisionAdministrativa);
    }

    @Override
    public List<RevisionAdministrativaDTO> findAll() {
        return revisionAdministrativaMapper.toDtoList(revisionAdministrativaRepository.findAll());
    }

    @Override
    public void delete(long id) {
        revisionAdministrativaRepository.deleteById(id);
    }

}
