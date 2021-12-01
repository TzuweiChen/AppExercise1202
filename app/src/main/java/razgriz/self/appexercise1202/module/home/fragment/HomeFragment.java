package razgriz.self.appexercise1202.module.home.fragment;

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

import java.util.Locale;

import razgriz.self.appexercise1202.R;
import razgriz.self.appexercise1202.helper.CommonHelper;
import razgriz.self.appexercise1202.module.home.activity.UsingRecyclerViewActivity;
import razgriz.self.appexercise1202.module.home.model.GetStatusModel;

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
            CommonHelper.showActivatedDialog(requireContext(), true, 1);
        });

        btnBuyDayPass3.setOnClickListener(view1 -> {
            btnBuyDayPass3.setText(R.string.action_activated);
            btnBuyDayPass3.setEnabled(false);
            CommonHelper.showActivatedDialog(requireContext(), true, 3);
        });

        btnBuyDayPass7.setOnClickListener(view1 -> {
            btnBuyDayPass7.setText(R.string.action_activated);
            btnBuyDayPass7.setEnabled(false);
            CommonHelper.showActivatedDialog(requireContext(), true, 7);
        });

        btnBuyHourPass1.setOnClickListener(view1 -> {
            btnBuyHourPass1.setText(R.string.action_activated);
            btnBuyHourPass1.setEnabled(false);
            CommonHelper.showActivatedDialog(requireContext(), false, 1);
        });

        btnBuyHourPass8.setOnClickListener(view1 -> {
            btnBuyHourPass8.setText(R.string.action_activated);
            btnBuyHourPass8.setEnabled(false);
            CommonHelper.showActivatedDialog(requireContext(), false, 8);
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
}
