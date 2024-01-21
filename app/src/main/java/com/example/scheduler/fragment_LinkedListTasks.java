package com.example.scheduler;

import static com.example.scheduler.fragment_viewTasks.linkedListTasks;
import static com.example.scheduler.fragment_viewTasks.stackTasks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class fragment_LinkedListTasks extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__linkedlist_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView responseTime = view.findViewById(R.id.output1TV);
        TextView executionTime = view.findViewById(R.id.output2TV);
        TextView avgExecutionTime = view.findViewById(R.id.output3TV);
        TableLayout linkedListTable = view.findViewById(R.id.linkedListTasksTable);
        String[][] tasks = linkedListTasks;

        TableRow headerRow = new TableRow(getActivity());
        TextView headerTask = createHeaderTextView("Task");
        TextView headerOutput = createHeaderTextView("Output");
        TextView headerExecutionTime = createHeaderTextView("Execution Time");

        headerRow.addView(headerTask);
        headerRow.addView(headerOutput);
        headerRow.addView(headerExecutionTime);

        linkedListTable.addView(headerRow);

        for (int i = 0; i < tasks.length; i++) {
            TableRow tableRow = new TableRow(getActivity());

            for (int j = 0; j < tasks[i].length; j++) {
                TextView textView = new TextView(getActivity());
                textView.setText(tasks[i][j]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(10);
                textView.setPadding(5, 5, 5, 5);

                // Add TextView to TableRow
                tableRow.addView(textView);
            }

            // Add TableRow to TableLayout
            linkedListTable.addView(tableRow);
        }

        responseTime.setText(String.valueOf(fragment_viewTasks.responseTimeLL));
        executionTime.setText(String.valueOf(fragment_viewTasks.execTimeLL));
        avgExecutionTime.setText(Double.toString(fragment_viewTasks.avgExecLL));
    }

    private TextView createHeaderTextView(String text) {
        TextView headerTextView = new TextView(getActivity());
        headerTextView.setText(text);
        headerTextView.setGravity(Gravity.CENTER);
        headerTextView.setTextSize(12);
        headerTextView.setPadding(6, 6, 6, 6);
        // Customize additional header styling if needed
        headerTextView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        headerTextView.setTextColor(getResources().getColor(android.R.color.white));
        return headerTextView;
    }
}