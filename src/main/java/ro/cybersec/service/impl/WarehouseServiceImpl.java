package ro.cybersec.service.impl;

import ro.cybersec.service.WarehouseService;
import ro.cybersec.domain.Warehouse;
import ro.cybersec.repository.WarehouseRepository;
import ro.cybersec.service.dto.WarehouseDTO;
import ro.cybersec.service.mapper.WarehouseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Warehouse}.
 */
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private final Logger log = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public WarehouseDTO save(WarehouseDTO warehouseDTO) {
        log.debug("Request to save Warehouse : {}", warehouseDTO);
        Warehouse warehouse = warehouseMapper.toEntity(warehouseDTO);
        warehouse = warehouseRepository.save(warehouse);
        return warehouseMapper.toDto(warehouse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WarehouseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Warehouses");
        return warehouseRepository.findAll(pageable)
            .map(warehouseMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseDTO> findOne(Long id) {
        log.debug("Request to get Warehouse : {}", id);
        return warehouseRepository.findById(id)
            .map(warehouseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Warehouse : {}", id);
        warehouseRepository.deleteById(id);
    }
}
