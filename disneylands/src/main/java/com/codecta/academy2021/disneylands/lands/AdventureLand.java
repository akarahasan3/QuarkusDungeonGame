package com.codecta.academy2021.disneylands.lands;

public class AdventureLand implements Disneyland {
    @Override
    public String getName() {
        return "Adventure Land";
    }

    @Override
    public String getDescription() {
        return "Adventureland is one of the \"themed lands\" at the many Disneyland-style theme parks run by the Walt Disney Company around the world."
                + " It is themed to resemble the remote jungles in Africa, Asia, South America, the South Pacific and the Caribbean.";
    }
}
