package com.poc.twitter;

import java.util.UUID;

public class Tweet{
    private final UUID id;
    private final String location;
    private final String payload;

    public Tweet(final UUID id, final String location, final String payload){
        this.id = id;
        this.location = location;
        this.payload = payload;
    }

    public UUID getId(){
        return id;
    }

    public String getLocation(){
        return location;
    }

    public String getPayload(){
        return payload;
    }
}