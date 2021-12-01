package razgriz.self.appexercise1202.module.user.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import razgriz.self.appexercise1202.R;
import razgriz.self.appexercise1202.module.user.activity.UsingRecyclerViewActivity;
import razgriz.self.appexercise1202.module.user.model.GetStatusModel;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private TextView txtResult;
    private Button btnBuyDayPass1;
    private Button btnBuyDayPass3;
    private Button btnBuyDayPass7;
    private Button btnBuyHourPass1;
    private Button btnBuyHourPass8;
    private Button btnIntentToRecyclerView;
    private ProgressBar progress;

    private GetStatusModel getStatusModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progress = view.findViewById(R.id.progress);
        btnBuyDayPass1 = view.findViewById(R.id.btnBuyDayPass1);
        btnBuyDayPass3 = view.findViewById(R.id.btnBuyDayPass3);
        btnBuyDayPass7 = view.findViewById(R.id.btnBuyDayPass7);
        btnBuyHourPass1 = view.findViewById(R.id.btnBuyHourPass1);
        btnBuyHourPass8 = view.findViewById(R.id.btnBuyHourPass8);
        btnIntentToRecyclerView = view.findViewById(R.id.btnIntentToRecyclerView);
        txtResult = view.findViewById(R.id.txtResult);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBuyDayPass1.setOnClickListener(view1 -> {
            btnBuyDayPass1.setText(R.string.action_activated);
            btnBuyDayPass1.setEnabled(false);
            showActivatedDialog(true, 1);
        });

        btnBuyDayPass3.setOnClickListener(view1 -> {
            btnBuyDayPass3.setText(R.string.action_activated);
            btnBuyDayPass3.setEnabled(false);
            showActivatedDialog(true, 3);
        });

        btnBuyDayPass7.setOnClickListener(view1 -> {
            btnBuyDayPass7.setText(R.string.action_activated);
            btnBuyDayPass7.setEnabled(false);
            showActivatedDialog(true, 7);
        });

        btnBuyHourPass1.setOnClickListener(view1 -> {
            btnBuyHourPass1.setText(R.string.action_activated);
            btnBuyHourPass1.setEnabled(false);
            showActivatedDialog(false, 1);
        });

        btnBuyHourPass8.setOnClickListener(view1 -> {
            btnBuyHourPass8.setText(R.string.action_activated);
            btnBuyHourPass8.setEnabled(false);
            showActivatedDialog(false, 8);
        });

        btnIntentToRecyclerView.setOnClickListener(view1 -> {
            requireActivity().startActivity(new Intent(requireContext(), UsingRecyclerViewActivity.class));
        });

        getStatusModel = new ViewModelProvider(this).get(GetStatusModel.class);
        getStatusModel.getProgress().observe(getViewLifecycleOwner(), isProgress -> {
            if (isProgress) {
                Toast.makeText(requireContext(), R.string.msg_fetching_status, Toast.LENGTH_SHORT).show();
            }
            progress.setVisibility(isProgress ? View.VISIBLE : View.GONE);
        });
        getStatusModel.getError().observe(getViewLifecycleOwner(), error -> Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show());
        getStatusModel.getStatus().observe(getViewLifecycleOwner(), status -> txtResult.setText(String.format(Locale.getDefault(), "Status:%d\nMessage:%s", status.getStatus(), status.getMessage())));

        getStatusModel.doGetStatus();
    }

    private void showActivatedDialog(boolean isDayPass, int offset) {
        Date expiredAt = new Date();
        if (isDayPass) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(expiredAt);
            calendar.add(Calendar.DATE, offset);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            expiredAt = calendar.getTime();
        } else {
            expiredAt = new Date(expiredAt.getTime() + (long) offset * 60 * 60 * 1000);
        }

        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.action_activated)
                .setMessage(getString(R.string.msg_pass_activated, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(expiredAt)))
                .setPositiveButton(R.string.action_ok, null)
                .show();
    }
}
