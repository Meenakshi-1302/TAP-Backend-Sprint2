package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.ClientDao;
import com.rts.tap.model.Client;
import com.rts.tap.model.ClientOrganization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Client saveClient(Client client) {
		entityManager.merge(client);
		return client;
	}

	@Override
	public ClientOrganization saveClientOrganization(ClientOrganization clientOrganization) {
		entityManager.persist(clientOrganization);
		return clientOrganization;
	}

	@Override
	public Client updateClient(Long id, Client client) {
		client.setClientId(id);
		return entityManager.merge(client);
	}

	@Override
	public void deleteClient(Long id) {
		Client client = entityManager.find(Client.class, id);
		if (client != null) {
			entityManager.remove(client);
		}
	}

	@Override
	public List<Client> viewAllClients() {
		TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
		return query.getResultList();
	}

	@Override
	public Client viewClientById(Long id) {
		return entityManager.find(Client.class, id);
	}

}
