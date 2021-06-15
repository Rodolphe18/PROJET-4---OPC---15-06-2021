package com.francotte.mareu.dialog_filter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.francotte.mareu.R;
import com.francotte.mareu.DI.DI;
import com.francotte.mareu.model.Meeting;
import com.francotte.mareu.service.MeetingApiService;

import java.util.List;

public class TimeDialog extends AppCompatDialogFragment {

    private TimePicker mTimePicker;
    private List<Meeting> mFilterMeetings;
    private MeetingApiService mApiService;
    private RoomDialog.NoticeDialogListener listener;
    private int mMinutes;
    private int mHeures;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_time_dialog, null);

        mApiService = DI.getMeetingApiService();

        builder.setView(view)
                .setTitle("Heure de la réunion :")
                .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mFilterMeetings = mApiService.filterMeetingByTime(mHeures, mMinutes);
                        listener.onRoomDialogPositiveClick(mFilterMeetings);
                    }
                });

        mTimePicker = view.findViewById(R.id.timePicker);

        mTimePicker.setIs24HourView(true);
        // 24H Mode.
        this.mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHeures = hourOfDay;
                mMinutes = minute;
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (RoomDialog.NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface NoticeDialogListener {
        public void onTimeDialogPositiveClick(List<Meeting> meetingsFilterList);
    }


}
