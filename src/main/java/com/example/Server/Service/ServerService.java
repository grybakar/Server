package com.example.Server.Service;

import com.example.Server.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public interface ServerService {

    Server create(Server server);

    Server ping(String ipAddress) throws IOException;

    /**
     * @param limit how much servers i want to show in a list
     * @return limited server list
     */
    List<Server> servers(int limit);

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);




}
