package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    
    @Autowired
    private SessionRepository sessionRepo;


    @GetMapping
    public List<Session> list() {
        return sessionRepo.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepo.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session) {
        return sessionRepo.saveAndFlush(session);
    }
    

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void create(@PathVariable Long id) {
        sessionRepo.deleteById(id);
    }
    

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session create(@PathVariable Long id, @RequestBody Session session) {
        Session existingSpkr = sessionRepo.getOne(id);
        BeanUtils.copyProperties(session,existingSpkr,"session_id");
        return sessionRepo.saveAndFlush(existingSpkr);
    }
}