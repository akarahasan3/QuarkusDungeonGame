package com.codecta.academy.services.impl;

import com.codecta.academy.model.DisneyCharacter;
import com.codecta.academy.model.WelcomeMessage;
import com.codecta.academy.repository.DisneyCharacterRepository;
import com.codecta.academy.services.DisneyService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DisneyServiceImpl implements DisneyService {

    @Inject
    DisneyCharacterRepository disneyCharacterRepository;

    @Override
    public WelcomeMessage welcome() {
        return new WelcomeMessage("Welcome Lands of Disneyland!", "Here you can find....", "Everyday from 8 am to 10 pm");
    }

    @Override
    public List<DisneyCharacter> findAllCharacters() {
        return disneyCharacterRepository.findAll();
    }

    @Override
    public DisneyCharacter findCharacterById(Integer id) {
        return disneyCharacterRepository.findById(id);
    }

    @Override
    public DisneyCharacter addCharacter(DisneyCharacter character) {
        return disneyCharacterRepository.add(character);
    }

    @Override
    public DisneyCharacter updateCharacter(Integer id, DisneyCharacter character) {
        return disneyCharacterRepository.update(id, character);
    }
    @Override
    public List<DisneyCharacter> deleteCharacter(Integer id, DisneyCharacter character){
        return disneyCharacterRepository.delete(id, character);
    }
}
