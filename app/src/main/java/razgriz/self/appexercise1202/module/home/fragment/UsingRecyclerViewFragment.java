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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import razgriz.self.appexercise1202.R;
import razgriz.self.appexercise1202.bean.Pass;
import razgriz.self.appexercise1202.helper.CommonHelper;
import razgriz.self.appexercise1202.module.home.adapter.PassAdapter;
import razgriz.self.appexercise1202.module.home.model.GetStatusModel;
import razgriz.self.appexercise1202.module.home.model.PassSectionAndTypeModel;

public class UsingRecyclerViewFragment extends Fragment implements PassAdapter.PassClickCallback {

    public static UsingRecyclerViewFragment newInstance() {
        return new UsingRecyclerViewFragment();
    }

    private RecyclerView recyclerView;
    private ProgressBar progress;

    private GetStatusModel getStatusModel;

    private List<PassSectionAndTypeModel> sectionAndTypeModels;
    private PassAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_using_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progress = view.findViewById(R.id.progress);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sectionAndTypeModels = new ArrayList<>();

        getStatusModel = new ViewModelProvider(this).get(GetStatusModel.class);
        getStatusModel.getProgress().observe(getViewLifecycleOwner(), isProgress -> {
            if (isProgress) {
                Toast.makeText(requireContext(), R.string.msg_fetching_status, Toast.LENGTH_SHORT).show();
            }
            progress.setVisibility(isProgress ? View.VISIBLE : View.GONE);
        });
        getStatusModel.getError().observe(getViewLifecycleOwner(), error -> Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show());
        getStatusModel.getStatus().observe(getViewLifecycleOwner(), status -> {
            sectionAndTypeModels.add(new PassSectionAndTypeModel(PassSectionAndTypeModel.IPassAdapterType.STATUS_RESULT, String.format(Locale.getDefault(), "Status:%d\nMessage:%s", status.getStatus(), status.getMessage())));

            // TODO: 2021/12/2 call API to fetch pass content, then show recycler view
            sectionAndTypeModels.add(new PassSectionAndTypeModel(PassSectionAndTypeModel.IPassAdapterType.FOOTER));
            adapter = new PassAdapter(sectionAndTypeModels, UsingRecyclerViewFragment.this);
            recyclerView.setAdapter(adapter);
        });

        getStatusModel.doGetStatus();
    }

    @Override
    public void onPassBuyClicked(Pass pass) {
        CommonHelper.showActivatedDialog(requireContext(), CommonHelper.isPassTypeDayPass(pass.getType()), pass.getDuration());
    }
}
