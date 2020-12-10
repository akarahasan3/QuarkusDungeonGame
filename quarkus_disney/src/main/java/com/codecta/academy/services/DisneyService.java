package com.codecta.academy.services;

import com.codecta.academy.model.DisneyCharacter;
import com.codecta.academy.model.WelcomeMessage;

import java.util.List;

public interface DisneyService {
    WelcomeMessage welcome();

    List<DisneyCharacter> findAllCharacters();
    DisneyCharacter findCharacterById(Integer id);
    DisneyCharacter addCharacter(DisneyCharacter character);
    DisneyCharacter updateCharacter(Integer id, DisneyCharacter character);
    List<DisneyCharacter> deleteCharacter(Integer id, DisneyCharacter character);
}
