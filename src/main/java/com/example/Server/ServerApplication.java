package com.example.Server;

import com.example.Server.Repository.ServerRepository;
import com.example.Server.enumeration.Status;
import com.example.Server.model.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(ServerRepository serverRepository) {
        return args -> {
            serverRepository.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
            serverRepository.save(new Server(null, "192.168.1.58", "Fedora Linux", "16 GB", "Dell Tower", "http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
            serverRepository.save(new Server(null, "192.168.1.21", "MS 2008", "32 GB", "Web Server", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
            serverRepository.save(new Server(null, "192.168.1.14", "Red Hat Linux", "64 GB", "Mail Server", "http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
        };
    }
}
