package ro.cybersec.service.impl;

import ro.cybersec.service.ProductOrderedService;
import ro.cybersec.domain.ProductOrdered;
import ro.cybersec.repository.ProductOrderedRepository;
import ro.cybersec.service.dto.ProductOrderedDTO;
import ro.cybersec.service.mapper.ProductOrderedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProductOrdered}.
 */
@Service
@Transactional
public class ProductOrderedServiceImpl implements ProductOrderedService {

    private final Logger log = LoggerFactory.getLogger(ProductOrderedServiceImpl.class);

    private final ProductOrderedRepository productOrderedRepository;

    private final ProductOrderedMapper productOrderedMapper;

    public ProductOrderedServiceImpl(ProductOrderedRepository productOrderedRepository, ProductOrderedMapper productOrderedMapper) {
        this.productOrderedRepository = productOrderedRepository;
        this.productOrderedMapper = productOrderedMapper;
    }

    @Override
    public ProductOrderedDTO save(ProductOrderedDTO productOrderedDTO) {
        log.debug("Request to save ProductOrdered : {}", productOrderedDTO);
        ProductOrdered productOrdered = productOrderedMapper.toEntity(productOrderedDTO);
        productOrdered = productOrderedRepository.save(productOrdered);
        return productOrderedMapper.toDto(productOrdered);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductOrderedDTO> findAll() {
        log.debug("Request to get all ProductOrdereds");
        return productOrderedRepository.findAll().stream()
            .map(productOrderedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProductOrderedDTO> findOne(Long id) {
        log.debug("Request to get ProductOrdered : {}", id);
        return productOrderedRepository.findById(id)
            .map(productOrderedMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductOrdered : {}", id);
        productOrderedRepository.deleteById(id);
    }
}
