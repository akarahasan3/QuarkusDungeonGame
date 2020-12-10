package com.codecta.academy2021.disneylands.lands;

public class FantasyLand implements Disneyland{
    @Override
    public String getName() {
        return "Fantasy Land";
    }

    @Override
    public String getDescription() {
        return "Fantasyland is one of the \"themed lands\" at all of the Magic Kingdom-style parks run by The Walt Disney "
                + "Company around the world. Each Fantasyland has a castle, as well as several gentle rides themed after Disney movies.";
    }
}
