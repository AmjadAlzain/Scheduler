package com.example.scheduler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.scheduler.methods.SchedulerApp;

public class fragmentSchedulerHome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scheduler_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button stackBTN = view.findViewById(R.id.stackBTN);
        Button queueBTN = view.findViewById(R.id.queueBTN);
        Button linkedListBTN = view.findViewById(R.id.linkedListBTN);
        TextView execTimeTV = view.findViewById(R.id.executionTimeTV);

        SchedulerApp schedulerApp = new SchedulerApp();
        schedulerApp.readInput(getActivity());
        schedulerApp.loadSchedulers();

        stackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long stackexecTime = schedulerApp.executeStack();
                execTimeTV.setText(String.valueOf(stackexecTime));
            }
        });

        queueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long queueexecTime = schedulerApp.executeQueue();
                execTimeTV.setText(String.valueOf(queueexecTime));
            }
        });

        linkedListBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long linkedListExecTime = schedulerApp.executeLinkedList();
                execTimeTV.setText(String.valueOf(linkedListExecTime));
            }
        });

    }
}