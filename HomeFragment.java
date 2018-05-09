package com.example.user.smartloans.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.user.smartloans.Activity.AddmoneyActivity;
import com.example.user.smartloans.Activity.BalanceActivity;
import com.example.user.smartloans.Activity.ForgotActivity;
import com.example.user.smartloans.Activity.LoginActivity;
import com.example.user.smartloans.Activity.ScanActivity;
import com.example.user.smartloans.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "HomeFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
private TextView txtView;
  private Button btnAddmoney;
   private TextView  txtViewbal;
   private Button btnScanQR;
       private Button  btnWallet;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
     View view =  inflater.inflate(R.layout.fragment_home, container, false);


     txtView = view.findViewById(R.id.txtView);
     btnAddmoney = view.findViewById(R.id.btnAddmoney);
     txtViewbal = view.findViewById(R.id.txtViewbal);
     btnScanQR = view.findViewById(R.id.btnScanQR);
     btnWallet = view.findViewById(R.id.btnWallet);


     txtViewbal.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(getActivity(),BalanceActivity.class);
             startActivity(intent);
         }
     });

     btnAddmoney.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
//           startActivity(new Intent(getActivity(),AddmoneyActivity.class));
             View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.addmoney, null);
             final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, 400);

// define your view here that found in popup_layout
// for example let consider you have a button
             popupWindow.setOutsideTouchable(true);

             Button btnAddmoney = (Button) popupView.findViewById(R.id.btnAddmoney);

// finally show up your popwindow
             popupWindow.showAsDropDown(popupView, 0, 0);
         }
     });
     btnScanQR.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(getActivity(),ScanActivity.class);
             startActivity(intent);
         }
     });
     btnWallet.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             getFragmentManager().beginTransaction().replace(R.id.framelayout,new WalletFragment()).commit();
//             Intent intent = new Intent(getActivity(),AddmoneyActivity.class);
//             startActivity(intent);
         }
     });

     return view;
    }


}
