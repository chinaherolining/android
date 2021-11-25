package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.ViewModelTest;
import com.example.myapplication.databinding.FragmentMvvmTestBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MvvmTestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MvvmTestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentMvvmTestBinding binding;
    ViewModelTest viewModelTest;

    public MvvmTestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MvvmTestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MvvmTestFragment newInstance(String param1, String param2) {
        MvvmTestFragment fragment = new MvvmTestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mvvm_test, container, false);
        viewModelTest = new ViewModelProvider(getActivity()).get(ViewModelTest.class);//getActivity() 是共享viewmodel的重点
        binding.setData(viewModelTest);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}