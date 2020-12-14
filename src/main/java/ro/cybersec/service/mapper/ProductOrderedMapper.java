package ro.cybersec.service.mapper;


import ro.cybersec.domain.*;
import ro.cybersec.service.dto.ProductOrderedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductOrdered} and its DTO {@link ProductOrderedDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, WarehouseMapper.class, OrderMapper.class})
public interface ProductOrderedMapper extends EntityMapper<ProductOrderedDTO, ProductOrdered> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "sellingCompany.id", target = "sellingCompanyId")
    @Mapping(source = "order.id", target = "orderId")
    ProductOrderedDTO toDto(ProductOrdered productOrdered);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "sellingCompanyId", target = "sellingCompany")
    @Mapping(source = "orderId", target = "order")
    ProductOrdered toEntity(ProductOrderedDTO productOrderedDTO);

    default ProductOrdered fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductOrdered productOrdered = new ProductOrdered();
        productOrdered.setId(id);
        return productOrdered;
    }
}
