package com.danielsolawa.service;

import com.danielsolawa.controller.VendorController;
import com.danielsolawa.domain.Vendor;
import com.danielsolawa.exception.ResourceNotFoundException;
import com.danielsolawa.mapper.VendorMapper;
import com.danielsolawa.model.VendorDTO;
import com.danielsolawa.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getVendorList() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getProperUrl(vendor.getId()));

                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository
                .findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getProperUrl(id));

                    return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveVendorDTO(vendorDTO);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
        return saveVendorDTO(vendorDTO);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository
                .findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }

                    VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                    savedVendorDTO.setVendorUrl(getProperUrl(id));

                    return savedVendorDTO;

                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }


    private VendorDTO saveVendorDTO(VendorDTO vendorDTO){
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        Vendor savedVendor = vendorRepository.save(vendor);

        return vendorMapper.vendorToVendorDTO(savedVendor);
    }


    private String getProperUrl(Long id){
        return VendorController.BASE_URL + "/" + id;
    }
}
