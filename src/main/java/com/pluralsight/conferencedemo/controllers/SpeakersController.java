package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;

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
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    @Autowired
    private SpeakerRepository speakerRepo;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepo.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepo.getOne(id);
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerRepo.saveAndFlush(speaker);
    }
    

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void create(@PathVariable Long id) {
        speakerRepo.deleteById(id);
    }
    

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker create(@PathVariable Long id, @RequestBody Speaker speaker) {
        Speaker existingSpkr = speakerRepo.getOne(id);
        BeanUtils.copyProperties(speaker,existingSpkr,"speaker_id");
        return speakerRepo.saveAndFlush(existingSpkr);
    }
}