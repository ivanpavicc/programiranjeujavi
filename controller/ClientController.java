package controller;

import model.Client;
import service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{email}")
    public Client getClientByEmail(@PathVariable String email) {
        return clientService.findByEmail(email);
    }

    @PostMapping("/save")
    public Client createClient() {
        return clientService.saveClient();
    }

    @GetMapping("/address/{addressId}")
    public Client getClientByAddressId(@PathVariable Long addressId) {
        return clientService.findByAddressId(addressId);
    }
}

