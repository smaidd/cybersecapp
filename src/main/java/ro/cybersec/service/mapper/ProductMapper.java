package ro.cybersec.service.mapper;


import ro.cybersec.domain.*;
import ro.cybersec.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {WarehouseMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {


    @Mapping(target = "removeWarehouse", ignore = true)
    @Mapping(target = "productOrdered", ignore = true)
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
