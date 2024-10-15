package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class PongController {

    @GetMapping(path = "/data")
    public @ResponseBody String pong(){
        try{
            return pingHost();
        } catch (UnknownHostException e) {
            return "ping failed";
        }

    }

    private String pingHost() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        String hostname = inetAddress.getHostName();
        return String.format("pong using FeignClient" + " IP: %s, Hostname: %s", ip, hostname);
    }
}
