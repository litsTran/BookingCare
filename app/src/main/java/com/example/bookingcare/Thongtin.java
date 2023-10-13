package com.example.bookingcare;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Thongtin extends Fragment {
    private EditText etFullName, etDOB, etAddress, etPhoneNumber;
    private RadioGroup radioGender;
    private Button btnSave;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thongtin, container, false);

        etFullName = view.findViewById(R.id.etFullName);
        etDOB = view.findViewById(R.id.etDOB);
        radioGender = view.findViewById(R.id.radioGender);
        etAddress = view.findViewById(R.id.etAddress);
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        btnSave = view.findViewById(R.id.btnSave);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", getActivity().MODE_PRIVATE);
        etFullName.setText(sharedPreferences.getString("fullName", ""));
        etDOB.setText(sharedPreferences.getString("dob", ""));
        int genderId = sharedPreferences.getInt("gender", R.id.rbMale);
        ((RadioButton) view.findViewById(genderId)).setChecked(true);
        etAddress.setText(sharedPreferences.getString("address", ""));
        etPhoneNumber.setText(sharedPreferences.getString("phoneNumber", ""));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate user input
                if (validateInput()) {
                    String fullNameInput = etFullName.getText().toString().trim();

                    if (fullNameInput.matches("^[a-zA-Z ]+$")) {
                    } else {
                        etFullName.setError("Định dạng tên đầy đủ không hợp lệ. Vui lòng chỉ sử dụng chữ cái và dấu cách.");
                    }
                    String dobInput = etDOB.getText().toString().trim();

                    if (dobInput.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                    } else {
                        // Invalid DOB format
                        etDOB.setError("Định dạng ngày tháng hợp lệ. Vui lòng sử dụng số.");
                    }
                    String addressInput = etAddress.getText().toString().trim();
                    if (addressInput.matches("^[a-zA-Z0-9 ]+$")) {
                    } else {
                        // Invalid Address format
                        etAddress.setError("Định dạng địa chỉ không hợp lệ. Vui lòng chỉ sử dụng chữ cái, số và dấu cách.");
                    }
                    String phoneNumberInput = etPhoneNumber.getText().toString().trim();
                    if (phoneNumberInput.matches("^[0-9]{10}$")) {
                    } else {
                        etPhoneNumber.setError("Định dạng số điện thoại không hợp lệ. Vui lòng nhập 10 chữ số.");
                    }
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fullName", etFullName.getText().toString());
                editor.putString("dob", etDOB.getText().toString());
                editor.putInt("gender", radioGender.getCheckedRadioButtonId());
                editor.putString("address", etAddress.getText().toString());
                editor.putString("phoneNumber", etPhoneNumber.getText().toString());
                editor.apply();

                Toast.makeText(getActivity(), "Điền thông tin thành công.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private boolean validateInput() {
        String fullName = etFullName.getText().toString().trim();
        String dobInput = etDOB.getText().toString().trim();
        String addressInput = etAddress.getText().toString().trim();
        String phoneNumberInput = etPhoneNumber.getText().toString().trim();
        if (fullName.isEmpty()) {
            dobInput.isEmpty();
            addressInput.isEmpty();
            phoneNumberInput.isEmpty();
            etFullName.setError("Điền đầy đủ họ và tên.");
            etDOB.setError("Điền đầy đủ ngày/tháng/năm sinh.");
            etAddress.setError("Điền đầy đủ địa chỉ.");
            etPhoneNumber.setError("Điền đầy đủ số điện thoại.");

            return false;
        }
        return true;
    }

}
