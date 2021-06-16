package com.francotte.mareu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.francotte.mareu.DI.DI;
import com.francotte.mareu.model.Meeting;
import com.francotte.mareu.service.MeetingApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.add_meeting_title)
    TextView mAddMeetingTitle;
    @BindView(R.id.objectLyt)
    TextInputLayout objectInput;
    @BindView(R.id.participantsLyt)
    TextInputLayout participantsInput;
    @BindView(R.id.meeting_room_spinner)
    Spinner roomInput;
    @BindView(R.id.create)
    MaterialButton addButton;
    @BindView(R.id.arrow_back)
    ImageView mArrowBack;
    @BindView(R.id.timePicker)
    TimePicker timePicker;

    private MeetingApiService mApiService;
    private int mHeures = 0;
    private int mMinutes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingApiService();
        init();

        timePicker.setIs24HourView(true);
        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHeures = hourOfDay;
                mMinutes = minute;
            }
        });

        mArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void init() {
        objectInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });
    }

    @OnClick(R.id.create)
    void createMeeting() {
        Meeting meeting = new Meeting(
                (int) System.currentTimeMillis(),
                objectInput.getEditText().getText().toString(),
                participantsInput.getEditText().getText().toString(),
                roomInput.getSelectedItem().toString(),
                mHeures,
                mMinutes);
        mApiService.createMeeting(meeting);
        finish();
    }
}



