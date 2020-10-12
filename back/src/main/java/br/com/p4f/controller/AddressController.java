package br.com.p4f.controller;

import br.com.p4f.dto.AddressDTO;
import br.com.p4f.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(path = "{id}")
    private AddressDTO getById(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }

    @GetMapping
    private Page<AddressDTO> getAll(
            @RequestParam(name = "zipCode", defaultValue = "") String zipCode,
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "address", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return addressService.getAllPaged(zipCode, pageable);
    }

    @PostMapping
    private void create(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.create(addressDTO);
    }

    @PutMapping(path = "{id}")
    private void update(@PathVariable(name = "id") Long id,
                        @Valid @RequestBody AddressDTO addressDTO) {
        addressDTO.setId(id);
        addressService.update(addressDTO);
    }

    @DeleteMapping(path = "{id}")
    private void delete(@PathVariable(name = "id") Long id) {
        addressService.delete(id);
    }


}
