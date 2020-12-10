package com.codecta.academy2021.disneylands.characters;

public class MinnieCharacter extends DisneyCharacter {
    public MinnieCharacter()
    {
        imageFilename = "minnie.png";
    }
    @Override
    public String getName() {
        return "Mini";
    }

    @Override
    public String welcomes() {
        return "Hallo my beautiful girl! Happy you have choose " + this.getName() + " to play with! Let's start...!";
    }
}
