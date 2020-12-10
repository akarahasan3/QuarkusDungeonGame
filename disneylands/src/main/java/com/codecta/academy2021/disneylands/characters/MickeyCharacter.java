package com.codecta.academy2021.disneylands.characters;

public class MickeyCharacter extends DisneyCharacter {

    public MickeyCharacter()
    {
        imageFilename = "mickey.jpg";
    }

    @Override
    public String getName() {
        return "Mickey";
    }

    @Override
    public String welcomes() {
        return "Hallo!!! Welcome to " + this.getName() + "'s world! Happy you choose me to play! Let's start...!";
    }
}
