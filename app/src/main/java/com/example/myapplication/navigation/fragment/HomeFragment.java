package com.example.myapplication.navigation.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.navigation.NavigationViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    NavigationViewModel navigationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         navigationViewModel = new ViewModelProvider(getActivity()).get(NavigationViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setData(navigationViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
//                EditText edittext = getView().findViewById(R.id.edittext);
//                String str = edittext.getText().toString();
//                if(TextUtils.isEmpty(str)){
//                    Toast.makeText(getActivity(),"请输入名字",Toast.LENGTH_LONG).show();
//                    return;
//
//                }
//                Bundle bundle = new Bundle();
//                bundle.putString("my_name",str);
                controller.navigate(R.id.action_homeFragment2_to_detailFragment2);
//                controller.navigate(R.id.action_homeFragment2_to_detailFragment2,bundle);
            }
        });
        if(navigationViewModel.getNumber() != null && navigationViewModel.getNumber().getValue() >0 ) {
            binding.seekBar.setProgress(navigationViewModel.getNumber().getValue());
        }else{
            navigationViewModel.getNumber();
            binding.seekBar.setProgress(0);
        }
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                navigationViewModel.getNumber().setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}