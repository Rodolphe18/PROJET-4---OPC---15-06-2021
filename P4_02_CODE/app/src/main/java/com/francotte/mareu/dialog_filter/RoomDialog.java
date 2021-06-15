package com.francotte.mareu.dialog_filter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;


import com.francotte.mareu.R;
import com.francotte.mareu.DI.DI;
import com.francotte.mareu.model.Meeting;
import com.francotte.mareu.service.MeetingApiService;

import java.util.List;


public class RoomDialog extends AppCompatDialogFragment {

    private Spinner roomInput;
    private List<Meeting> mFilterMeetings;
    private MeetingApiService mApiService;
    private NoticeDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_room_dialog, null);

        mApiService = DI.getMeetingApiService();

        builder.setView(view)
                .setTitle("Salle :")
                .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mRoom = roomInput.getSelectedItem().toString();
                        mFilterMeetings = mApiService.filterMeetingByRoom(mRoom);
                        listener.onRoomDialogPositiveClick(mFilterMeetings);
                    }
                });

        roomInput = view.findViewById(R.id.meeting_room_spinner);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface NoticeDialogListener {
        public void onRoomDialogPositiveClick(List<Meeting> meetingsFilterList);
    }

}
