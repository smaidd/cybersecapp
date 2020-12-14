package ro.cybersec.service.mapper;


import ro.cybersec.domain.*;
import ro.cybersec.service.dto.WarehouseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Warehouse} and its DTO {@link WarehouseDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface WarehouseMapper extends EntityMapper<WarehouseDTO, Warehouse> {

    @Mapping(source = "location.id", target = "locationId")
    WarehouseDTO toDto(Warehouse warehouse);

    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "removeProducts", ignore = true)
    Warehouse toEntity(WarehouseDTO warehouseDTO);

    default Warehouse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        return warehouse;
    }
}
