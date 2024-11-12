package com.rts.tap.service;

import com.rts.tap.model.Client;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ClientService {

	Client saveClient(Client client,MultipartFile organizationLogo) throws IOException;


	Client updateClient(Long id);

	void deleteClient(Long id);

	List<Client> viewAllClients();

	Client viewClientById(Long id);
}
