package calculator.calulation.lesson7_1423_1_7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;


public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }


    RadioButton radioButtonAdd;
    RadioButton radioButtonReplace;
    /*SwitchCompat switchBackStack;
    SwitchCompat switchBackAsRemove;
    SwitchCompat switchDeleteBeforeAdd;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        radioButtonAdd = v.findViewById(R.id.radioButtonAdd);
        radioButtonReplace = v.findViewById(R.id.radioButtonReplace);
        SwitchCompat switchBackStack =  v.findViewById(R.id.switchBackStack);
        SwitchCompat switchBackAsRemove =  v.findViewById(R.id.switchBackAsRemove);
        SwitchCompat switchDeleteBeforeAdd =  v.findViewById(R.id.switchDeleteBeforeAdd);

        switchDeleteBeforeAdd.setChecked(Settings.isDeleteFragment);
        switchBackAsRemove.setChecked(Settings.isBackIsRemove);
        switchBackStack.setChecked(Settings.isBackStack);
        radioButtonReplace.setChecked(Settings.isReplaceFragment);
        radioButtonAdd.setChecked(Settings.isAddFragment);
        radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isAddFragment =  isChecked;
                writeSettings();
            }
        });
        radioButtonReplace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isReplaceFragment =  isChecked;
                writeSettings();
            }
        });
        switchBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackStack=  isChecked;
                writeSettings();
            }
        });
        switchBackAsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackIsRemove=  isChecked;
                writeSettings();
            }
        });

        switchDeleteBeforeAdd .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isDeleteFragment=  isChecked;
                writeSettings();
            }
        });
    }

    private void writeSettings() {
        SharedPreferences sharedPreferences = requireActivity()
                .getSharedPreferences(Settings.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Settings.IS_ADD_FRAGMENT_USED,Settings.isAddFragment);
        editor.putBoolean(Settings.IS_REPLACE_FRAGMENT_USED,Settings.isReplaceFragment);
        editor.putBoolean(Settings.IS_BACK_STACK_USED,Settings.isBackStack);
        editor.putBoolean(Settings.IS_BACK_IS_REMOVE_FRAGMENT,Settings.isBackIsRemove);
        editor.putBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD,Settings.isDeleteFragment);
        editor.apply();
    }
}