package madtest.common.oauth2.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import madtest.common.oauth2.Client;
import madtest.common.oauth2.service.ClientService;

/**
 * Created by qct on 2015/7/28.
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public Client createClient(Client client) {
        return null;
    }

    @Override
    public Client updateClient(Client client) {
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {

    }

    @Override
    public Client findOne(Long clientId) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Client findByClientId(String clientId) {
        return null;
    }

    @Override
    public Client findByClientSecret(String clientSecret) {
        return null;
    }
}
