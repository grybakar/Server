package com.example.Server.Service.implementation;

import com.example.Server.Repository.ServerRepository;
import com.example.Server.Service.ServerService;
import com.example.Server.enumeration.Status;
import com.example.Server.exception.IpAddressNotFoundException;
import com.example.Server.exception.ServerNotFoundException;
import com.example.Server.model.Server;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Random;

import static com.example.Server.enumeration.Status.*;
import static java.lang.Boolean.*;
import static org.springframework.data.domain.PageRequest.*;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serviceRepository;


    @Override
    public Server create(Server server) {
        log.info("Saving a new Server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serviceRepository.save(server);
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/server/image/" + imageNames[new Random().nextInt(4)])
                .toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IpAddress: {}", ipAddress);
        Server serverByIpAddress = serviceRepository
                .findByIpAddress(ipAddress)
                .orElseThrow(() -> new IpAddressNotFoundException("IpAddress: " + ipAddress + " was not found"));

        InetAddress address = InetAddress.getByName(ipAddress);
        serverByIpAddress.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serviceRepository.save(serverByIpAddress);
        return serverByIpAddress;
    }

    @Override
    public List<Server> servers(int limit) {
        log.info("Finding all servers:");
        return serviceRepository.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Finding server by id: {}:", id);
        return serviceRepository
                .findById(id)
                .orElseThrow(() -> new ServerNotFoundException("Server with id: " + id + " not found"));
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serviceRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        serviceRepository.deleteById(id);
        return TRUE;
    }


}
