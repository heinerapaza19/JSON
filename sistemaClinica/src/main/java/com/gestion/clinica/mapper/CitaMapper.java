package com.gestion.clinica.mapper;

import com.gestion.clinica.dto.CitaDTO;
import com.gestion.clinica.entity.CitaEntity;
import com.gestion.clinica.entity.PacienteEntity;\nimport com.gestion.clinica.entity.MedicoEntity;\n
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    @Mapping(source = "paciente.id", target = "pacienteId")\n    @Mapping(source = "paciente.nombre", target = "pacienteNombre") // Ajustar si el campo representativo no es "nombre"\n    @Mapping(source = "medico.id", target = "medicoId")\n    @Mapping(source = "medico.nombre", target = "medicoNombre") // Ajustar si el campo representativo no es "nombre"\n
    CitaDTO toDTO(CitaEntity entity);
    
    @Mapping(target = "paciente", source = "pacienteId")\n    @Mapping(target = "medico", source = "medicoId")\n
    CitaEntity toEntity(CitaDTO dto);

    List<CitaDTO> toDTOList(List<CitaEntity> entityList);

    default PacienteEntity mapPacienteIdToEntity(Long id) {
        if (id == null) return null;
        PacienteEntity entity = new PacienteEntity();
        entity.setId(id);
        return entity;
    }

    default MedicoEntity mapMedicoIdToEntity(Long id) {
        if (id == null) return null;
        MedicoEntity entity = new MedicoEntity();
        entity.setId(id);
        return entity;
    }

}
