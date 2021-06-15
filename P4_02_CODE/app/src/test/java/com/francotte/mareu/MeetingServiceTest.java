package com.francotte.mareu;

import com.francotte.mareu.DI.DI;
import com.francotte.mareu.model.Meeting;
import com.francotte.mareu.service.DummyMeetingGenerator;
import com.francotte.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder((expectedMeetings.toArray())));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meetingToCreate = new Meeting(6, "anniversaire", "rodolphe@lamzone.com", "Salle G", 18, 30);
        service.createMeeting(meetingToCreate);
        assertTrue(service.getMeetings().contains(meetingToCreate));
    }

    @Test
    public void filterMeetingByRoomWithSuccess() {
        ArrayList<String> rooms = new ArrayList<>();
        rooms.add("Salle A");
        List<Meeting> expectedMeetings = service.filterMeetingByRoom("Salle A");
        assertEquals(1, expectedMeetings.size());
    }

    @Test
    public void filterMeetingByTimeWithSuccess() {
        List<Meeting> expectedMeetings = service.filterMeetingByTime(14, 30);
        assertEquals(1, expectedMeetings.size());
    }

}