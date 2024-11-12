package com.rts.tap.dao;

import com.rts.tap.model.Client;
import com.rts.tap.model.ClientOrganization;

import java.util.List;

public interface ClientDao {
	
	Client saveClient(Client client);

	Client updateClient(Long id, Client client);

	void deleteClient(Long id);

	List<Client> viewAllClients();

	Client viewClientById(Long id);
	
	ClientOrganization saveClientOrganization(ClientOrganization clientOrganization);
}
