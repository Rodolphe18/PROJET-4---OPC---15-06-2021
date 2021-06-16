package com.francotte.mareu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.francotte.mareu.DI.DI;
import com.francotte.mareu.dialog_filter.RoomDialog;
import com.francotte.mareu.dialog_filter.TimeDialog;
import com.francotte.mareu.events.DeleteMeetingEvent;
import com.francotte.mareu.model.Meeting;
import com.francotte.mareu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListMeetingActivity extends AppCompatActivity implements RoomDialog.NoticeDialogListener, TimeDialog.NoticeDialogListener {

    // UI Components

    @BindView(R.id.add_meeting)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private MeetingApiService mApiService;
    private List<Meeting> mMeetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        ButterKnife.bind(this);

        mApiService = DI.getMeetingApiService();
        this.configureRecyclerView();

        mFloatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddMeetingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_meeting_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        switch (pItem.getItemId()) {
            case R.id.option_1_time:
                openTimeDialog();
                return true;
            case R.id.option_2_room:
                openRoomDialog();
                return true;
            case R.id.option_3_init:
                initList();
            default:
                return super.onOptionsItemSelected(pItem);
        }
    }


    private void configureRecyclerView() {
        this.mMeetings = new ArrayList<>();
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initList() {
        mMeetings = mApiService.getMeetings();
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onDeleteNeighbour(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mMeeting);
        initList();
    }

    @Override
    public void onRoomDialogPositiveClick(List<Meeting> meetingsFilterList) {
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(meetingsFilterList));
    }

    private void openRoomDialog() {
        RoomDialog roomDialog = new RoomDialog();
        roomDialog.show(getSupportFragmentManager(), "room dialog");
    }


    @Override
    public void onTimeDialogPositiveClick(List<Meeting> meetingsFilterList) {
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(meetingsFilterList));
    }

    private void openTimeDialog() {
        TimeDialog timeDialog = new TimeDialog();
        timeDialog.show(getSupportFragmentManager(), "time dialog");
    }
}




