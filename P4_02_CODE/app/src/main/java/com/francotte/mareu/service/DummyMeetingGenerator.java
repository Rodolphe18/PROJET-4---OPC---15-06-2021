package com.francotte.mareu.service;

import com.francotte.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "pot de départ", "jean@lamzone.com , jack@lamzone.com", "Salle A",
                    14, 30),
            new Meeting(2, "anniversaire", "jean@lamzone.com , jack@lamzone.com", "Salle D",
                    15, 45),
            new Meeting(3, "pot d'arrivée'", "jean@lamzone.com , jack@lamzone.com", "Salle J",
                    17, 15),
            new Meeting(4, "point hebdomadaire", "jean@lamzone.com , jack@lamzone.com", "Salle B",
                    9, 30),
            new Meeting(5, "plénière", "jean@lamzone.com , jack@lamzone.com", "Salle F",
                    11, 30));

}

