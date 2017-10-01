package com.kukuruznyak.bettingcompany.entity.tournament;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tournament extends Model {
    private String name;
    private Country country;
    private Calendar beginningDateAndTime;
    private List<Participant> participants;

    public Tournament() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public Calendar getBeginningDateAndTime() {
        return beginningDateAndTime;
    }

    public void setBeginningDateAndTime(Calendar beginningDateAndTime) {
        this.beginningDateAndTime = beginningDateAndTime;
    }

    public void addParticipant(Participant participant) {
        if (this.participants == null) {
            this.participants = new ArrayList<>();
        }
        this.participants.add(participant);
    }
}