package si.fri.rso.napitnina.models.converters;

import si.fri.rso.napitnina.lib.Tip;
import si.fri.rso.napitnina.models.entities.TipEntity;

public class TipConverter {

    public static Tip toDto(TipEntity entity) {

        Tip dto = new Tip();
        dto.setId(entity.getId());
        dto.setClient(entity.getClient());
        dto.setOrder(entity.getOrder());
        dto.setTipValue(entity.getTipValue());

        return dto;
    }

    public static TipEntity toEntity(Tip dto) {

        TipEntity entity = new TipEntity();
        entity.setId(dto.getId());
        entity.setClient(dto.getClient());
        entity.setOrder(dto.getOrder());
        entity.setTipValue(dto.getTipValue());

        return entity;
    }
}