package controller;

import service.AddressService;
import model.Address;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{city}")
    public Address getAddressByCity(@PathVariable String city) {
        return addressService.findByCity(city);
    }

    @PostMapping
    public Address saveAddress() {
        return addressService.saveAddress();
    }
}
