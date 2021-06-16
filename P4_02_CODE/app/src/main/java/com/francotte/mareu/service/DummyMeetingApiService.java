package com.francotte.mareu.service;

import android.content.Context;

import com.francotte.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
    private List<Meeting> mMeetingFilterList;

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public List<Meeting> filterMeetingByRoom(String meetingRoom) {
        mMeetingFilterList = new ArrayList<Meeting>();
        for (Meeting meeting : mMeetings) {
            if (meeting.getMeetingRoom().equals(meetingRoom)) {
                mMeetingFilterList.add(meeting);
            }
        }
        return mMeetingFilterList;
    }

    @Override
    public List<Meeting> filterMeetingByTime(int startHourOfMeeting, int startMinutesOfMeeting) {
        mMeetingFilterList = new ArrayList<Meeting>();
        for (Meeting meeting : mMeetings) {
            if ((meeting.getStartHourOfMeeting() == startHourOfMeeting) && (meeting.getStartMinutesOfMeeting() == startMinutesOfMeeting)) {
                mMeetingFilterList.add(meeting);
            }
        }
        return mMeetingFilterList;
    }

}
