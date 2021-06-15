package com.francotte.mareu.service;

import com.francotte.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    List<Meeting> filterMeetingByRoom(String meetingRoom);

    List<Meeting> filterMeetingByTime(int startHourOfMeeting, int startMinutesOfMeeting);
}


