package com.francotte.mareu.model;


public class Meeting {
    private int id;
    private String subject;
    private String participants;
    private String meetingRoom;
    private int startHourOfMeeting;
    private int startMinutesOfMeeting;


    public Meeting(int id, String subject, String participants, String meetingRoom, int startHourOfMeeting, int startMinutesOfMeeting) {

        this.id = id;
        this.subject = subject;
        this.participants = participants;
        this.startHourOfMeeting = startHourOfMeeting;
        this.startMinutesOfMeeting = startMinutesOfMeeting;
        this.meetingRoom = meetingRoom;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getParticipants() {
        return participants;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public int getStartHourOfMeeting() {
        return startHourOfMeeting;
    }

    public int getStartMinutesOfMeeting() {
        return startMinutesOfMeeting;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public void setStartHourOfMeeting(int startHourOfMeeting) {
        this.startHourOfMeeting = startHourOfMeeting;
    }

    public void setStartMinutesOfMeeting(int startMinutesOfMeeting) {
        this.startMinutesOfMeeting = startMinutesOfMeeting;
    }

}




