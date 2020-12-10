package com.codecta.academy2021.disneylands.lands;

public class MickeyTown implements  Disneyland {
    @Override
    public String getName() {
        return "Mickey Town";
    }

    @Override
    public String getDescription() {
        return "Mickey's Toontown is a themed land at Disneyland and Tokyo Disneyland, two theme parks operated by Walt Disney Parks & Resorts."
                + " At Tokyo Disneyland, this land is named Toontown";
    }
}
