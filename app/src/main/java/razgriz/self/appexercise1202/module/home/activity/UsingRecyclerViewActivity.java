package razgriz.self.appexercise1202.module.home.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import razgriz.self.appexercise1202.R;
import razgriz.self.appexercise1202.module.home.fragment.UsingRecyclerViewFragment;

public class UsingRecyclerViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_fragment_container);

        Fragment fragment = UsingRecyclerViewFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, fragment.getClass().getName()).commit();
    }
}
