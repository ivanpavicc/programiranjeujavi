package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Client;
import repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Client not found with email: " + email));
    }

    public Client findByAddressId(Long id) {
        return clientRepository.findByAddressId(id)
                .orElseThrow(() -> new NoSuchElementException("Client not found with address id: " + id));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client saveClient() {
        try {
            File file = new File("src/main/resources/client.json");
            Client client = objectMapper.readValue(file, Client.class);

            if (client.getEmail() == null) {
                throw new IllegalArgumentException("Email cannot be null");
            }

            return clientRepository.save(client);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading client data from data.json and saving to the database.", e);
        }
    }
}
