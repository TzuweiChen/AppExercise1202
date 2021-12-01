package razgriz.self.appexercise1202.module.home.adapter;

import static razgriz.self.appexercise1202.module.home.model.PassSectionAndTypeModel.IPassAdapterType.FOOTER;
import static razgriz.self.appexercise1202.module.home.model.PassSectionAndTypeModel.IPassAdapterType.PASS_CONTENT;
import static razgriz.self.appexercise1202.module.home.model.PassSectionAndTypeModel.IPassAdapterType.PASS_TYPE_TITLE;
import static razgriz.self.appexercise1202.module.home.model.PassSectionAndTypeModel.IPassAdapterType.STATUS_RESULT;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import razgriz.self.appexercise1202.R;
import razgriz.self.appexercise1202.bean.Pass;
import razgriz.self.appexercise1202.module.home.model.PassSectionAndTypeModel;

public class PassAdapter extends RecyclerView.Adapter<PassAdapterViewHolder> {

    public interface PassClickCallback {
        void onPassBuyClicked(Pass pass);
    }

    private final List<PassSectionAndTypeModel> sectionAndTypeModels;
    private final PassClickCallback callback;

    public PassAdapter(List<PassSectionAndTypeModel> sectionAndTypeModels, PassClickCallback callback) {
        this.sectionAndTypeModels = sectionAndTypeModels;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PassAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case STATUS_RESULT:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_status_result, parent, false);
                return new GetResultViewHolder(itemView);

            case PASS_TYPE_TITLE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pass_type_title, parent, false);
                return new PassTypeTitleViewHolder(itemView);

            case PASS_CONTENT:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pass_content, parent, false);
                return new PassContentViewHolder(itemView);

            case FOOTER:
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_footer, parent, false);
                return new PassAdapterViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PassAdapterViewHolder holder, int position) {

        PassSectionAndTypeModel model = sectionAndTypeModels.get(position);
        switch (model.getType()) {
            case STATUS_RESULT:
                GetResultViewHolder getResultViewHolder = (GetResultViewHolder) holder;
                getResultViewHolder.txtResult.setText(sectionAndTypeModels.get(position).getContent());
                break;

            case PASS_TYPE_TITLE:
                PassTypeTitleViewHolder passTypeTitleViewHolder = (PassTypeTitleViewHolder) holder;
                passTypeTitleViewHolder.txtPassType.setText(sectionAndTypeModels.get(position).getContent());
                break;

            case PASS_CONTENT:
                PassContentViewHolder passContentViewHolder = (PassContentViewHolder) holder;
                Pass pass = sectionAndTypeModels.get(position).getPass();
                if (pass == null) {
                    return;
                }

                passContentViewHolder.txtPassTitle.setText(pass.getType());
                passContentViewHolder.txtPassPrice.setText(pass.getPrice());
                passContentViewHolder.btnBuyPass.setOnClickListener(view -> {
                    if (callback != null) {
                        callback.onPassBuyClicked(pass);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return sectionAndTypeModels.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return sectionAndTypeModels.size();
    }

}

class PassAdapterViewHolder extends RecyclerView.ViewHolder {

    PassAdapterViewHolder(View itemView) {
        super(itemView);
    }
}

class GetResultViewHolder extends PassAdapterViewHolder {

    TextView txtResult;

    GetResultViewHolder(View itemView) {
        super(itemView);

        txtResult = itemView.findViewById(R.id.txtResult);
    }
}

class PassTypeTitleViewHolder extends PassAdapterViewHolder {

    TextView txtPassType;

    PassTypeTitleViewHolder(View itemView) {
        super(itemView);

        txtPassType = itemView.findViewById(R.id.txtPassType);
    }
}

class PassContentViewHolder extends PassAdapterViewHolder {

    TextView txtPassTitle;
    TextView txtPassPrice;
    Button btnBuyPass;

    PassContentViewHolder(View itemView) {
        super(itemView);

        txtPassTitle = itemView.findViewById(R.id.txtPassTitle);
        txtPassPrice = itemView.findViewById(R.id.txtPassPrice);
        btnBuyPass = itemView.findViewById(R.id.btnBuyPass);
    }
}