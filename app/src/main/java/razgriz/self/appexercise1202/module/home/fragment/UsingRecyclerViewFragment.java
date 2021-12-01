package razgriz.self.appexercise1202.module.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import razgriz.self.appexercise1202.R;
import razgriz.self.appexercise1202.module.home.model.GetStatusModel;

public class UsingRecyclerViewFragment extends Fragment {

    public static UsingRecyclerViewFragment newInstance() {
        return new UsingRecyclerViewFragment();
    }

    private RecyclerView recyclerView;
    private ProgressBar progress;

    private GetStatusModel getStatusModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_using_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progress = view.findViewById(R.id.progress);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getStatusModel = new ViewModelProvider(this).get(GetStatusModel.class);
        getStatusModel.getProgress().observe(getViewLifecycleOwner(), isProgress -> {
            if (isProgress) {
                Toast.makeText(requireContext(), R.string.msg_fetching_status, Toast.LENGTH_SHORT).show();
            }
            progress.setVisibility(isProgress ? View.VISIBLE : View.GONE);
        });
        getStatusModel.getError().observe(getViewLifecycleOwner(), error -> Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show());
        getStatusModel.getStatus().observe(getViewLifecycleOwner(), status -> {
//            txtResult.setText(String.format(Locale.getDefault(), "Status:%d\nMessage:%s", status.getStatus(), status.getMessage()))
        });

        getStatusModel.doGetStatus();
    }

//    private void showActivatedDialog(boolean isDayPass, int offset) {
//        Date expiredAt = new Date();
//        if (isDayPass) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(expiredAt);
//            calendar.add(Calendar.DATE, offset);
//            calendar.set(Calendar.HOUR, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//            expiredAt = calendar.getTime();
//        } else {
//            expiredAt = new Date(expiredAt.getTime() + (long) offset * 60 * 60 * 1000);
//        }
//
//        new AlertDialog.Builder(requireContext())
//                .setTitle(R.string.action_activated)
//                .setMessage(getString(R.string.msg_pass_activated, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(expiredAt)))
//                .setPositiveButton(R.string.action_ok, null)
//                .show();
//    }
}
