package com.danielsolawa.controller;

import com.danielsolawa.model.VendorDTO;
import com.danielsolawa.model.VendorListDTO;
import com.danielsolawa.service.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */

@Api(description = "This is a controller for the Vendor model")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Returns a list of all vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getVendorList(){
        return new VendorListDTO(vendorService.getVendorList());
    }


    @ApiOperation(value = "Returns a specified vendor by the given id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable String id){
        return vendorService.getVendorById(Long.valueOf(id));
    }


    @ApiOperation(value = "Creates a new vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @ApiOperation(value = "Updates a vendor, using put method")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendor(Long.valueOf(id), vendorDTO);
    }

    @ApiOperation(value = "Updates a vendor, using patch method")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendor(Long.valueOf(id), vendorDTO);
    }

    @ApiOperation(value = "Deletes a specified vendor by the given id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVendorById(@PathVariable String id){
        vendorService.deleteVendorById(Long.valueOf(id));
    }
}
