package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entities.Client;
import com.example.demo.entities.SousTraitant;
import com.example.demo.entities.Distributeur;
import com.example.demo.entities.FabricantOrigine;
import com.example.demo.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // 🔄 Convertir DTO en entité Client ou sous-classe
    public Client fromDTO(ClientDTO dto) {
        Client client;

        switch (dto.getType()) {
            case "SousTraitant":
                client = new SousTraitant();
                break;
            case "Distributeur":
                client = new Distributeur();
                break;
            case "Fabricant":
                client = new FabricantOrigine();
                break;
            default:
                client = new Client();
                break;
        }

        client.setId(dto.getId()); // utile pour update
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setAdresse(dto.getAdresse());

        return client;
    }

    // 🔄 Convertir entité Client en DTO
    public ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setEmail(client.getEmail());
        dto.setTelephone(client.getTelephone());
        dto.setAdresse(client.getAdresse());

        if (client instanceof SousTraitant) {
            dto.setType("SousTraitant");
        } else if (client instanceof Distributeur) {
            dto.setType("Distributeur");
        } else if (client instanceof FabricantOrigine) {
            dto.setType("Fabricant");
        } else {
            dto.setType("Client");
        }

        return dto;
    }

    // ✅ Créer ou enregistrer un client
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    // ✅ Récupérer tous les clients
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // ✅ Récupérer un client par ID
    public Client findById(Long id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id : " + id));
    }

    // ✅ Supprimer un client
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec l'id : " + id);
        }
        clientRepository.deleteById(id);
    }

    // ✅ Mettre à jour un client
    public Client update(Long id, ClientDTO dto) {
        Client existing = findById(id);

        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setEmail(dto.getEmail());
        existing.setTelephone(dto.getTelephone());
        existing.setAdresse(dto.getAdresse());

        return clientRepository.save(existing);
    }

    // ✅ Récupérer tous les clients sous forme de DTO
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    // ✅ Récupérer un client par ID sous forme de DTO
    public ClientDTO getClientById(Long id) {
        Client client = findById(id);
        return toDTO(client);
    }

    // ✅ Filtrer les clients par type
    public List<ClientDTO> getClientsByType(String type) {
        return clientRepository.findAll().stream()
            .filter(client -> {
                if (type.equalsIgnoreCase("SousTraitant") && client instanceof SousTraitant) return true;
                if (type.equalsIgnoreCase("Distributeur") && client instanceof Distributeur) return true;
                if (type.equalsIgnoreCase("Fabricant") && client instanceof FabricantOrigine) return true;
                return false;
            })
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
