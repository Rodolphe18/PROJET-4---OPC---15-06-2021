package com.francotte.mareu.events;

import com.francotte.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    public DeleteMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}


