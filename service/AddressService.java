package service;

import model.Address;
import repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;

    public Address findByCity(String city) {
        return addressRepository.findByCityIgnoreCase(city)
                .orElseThrow(() -> new NoSuchElementException("Addresses not found with city: " + city));
    }

    public Address saveAddress() {
        try {
            File file = new File("src/main/resources/address.json");
            Address address = objectMapper.readValue(file, Address.class);
            return addressRepository.save(address);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading address data from address.json and saving to the database.", e);
        }
    }
}
