package ro.cybersec.service.mapper;


import ro.cybersec.domain.*;
import ro.cybersec.service.dto.LocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {


    @Mapping(target = "warehouses", ignore = true)
    @Mapping(target = "removeWarehouse", ignore = true)
    Location toEntity(LocationDTO locationDTO);

    default Location fromId(Long id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
