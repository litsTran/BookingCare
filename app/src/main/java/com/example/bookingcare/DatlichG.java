package com.example.bookingcare;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.regex.Pattern;

public class DatlichG extends Fragment {
    private EditText etFullName, a123, a;
    private RadioGroup genderRG,radioGendera,radioGender;
    private Button btnXacNhan;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_datlich_g, container, false);

        // Đặt sự kiện cho nút xác nhận đặt lịch
        btnXacNhan = view.findViewById(R.id.btn1);
        etFullName = view.findViewById(R.id.etFullName);
        a123 = view.findViewById(R.id.a123);
        a = view.findViewById(R.id.a);
        genderRG = view.findViewById(R.id.genderRG);
        radioGender = view.findViewById(R.id.radioGender);
        radioGendera = view.findViewById(R.id.radioGendera);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = etFullName.getText().toString();
                String phoneNumber = a123.getText().toString();
                String address = a.getText().toString();

                boolean isFormValid = true;

                if (TextUtils.isEmpty(fullName) || !isValidFullName(fullName)) {
                    etFullName.setError("Tên không hợp lệ");
                    isFormValid = false;
                }

                if (TextUtils.isEmpty(phoneNumber) || !isValidPhoneNumber(phoneNumber)) {
                    a123.setError("Số điện thoại không hợp lệ");
                    isFormValid = false;
                }

                if (TextUtils.isEmpty(address) || !isValidAddress(address)) {
                    a.setError("Địa chỉ không hợp lệ");
                    isFormValid = false;
                }

                if (isFormValid) {
                    // Nếu thông tin đã đầy đủ và hợp lệ, thực hiện xác nhận đặt lịch
                    confirmBooking();
                } else {
                    // Hiển thị thông báo lỗi nếu có trường nào đó trống hoặc không hợp lệ
                    Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin và kiểm tra lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private boolean isValidFullName(String fullName) {
        // Kiểm tra tên không chứa số hoặc ký tự đặc biệt (ngoại trừ dấu cách)
        return Pattern.matches("^[a-zA-Z\\s]+$", fullName);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Kiểm tra số điện thoại chỉ chứa chính xác 10 số
        return Pattern.matches("^[0-9]{10}$", phoneNumber);
    }

    private boolean isValidAddress(String address) {
        // Kiểm tra địa chỉ không chứa ký tự đặc biệt ngoại trừ '/'
        return Pattern.matches("^[a-zA-Z0-9\\s/]+$", address);
    }

    private void confirmBooking() {
        // Lấy dữ liệu từ các trường nhập liệu
        String caNhan = ((RadioButton) view.findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();
        String tenBenhNhan = etFullName.getText().toString();
        String gioiTinh = ((RadioButton) view.findViewById(radioGendera.getCheckedRadioButtonId())).getText().toString();
        String soDienThoai = a123.getText().toString();
        String diaChi = a.getText().toString();
        String gioKham = ((RadioButton) view.findViewById(genderRG.getCheckedRadioButtonId())).getText().toString();
        // Lưu dữ liệu vào SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("caNhan", caNhan);
        editor.putString("tenBenhNhan", tenBenhNhan);
        editor.putString("gioiTinh", gioiTinh);
        editor.putString("soDienThoai", soDienThoai);
        editor.putString("diaChi", diaChi);
        editor.putString("gioKham", gioKham);
        editor.apply();

        // Chuyển dữ liệu sang Fragment Xác nhận đặt lịch
        Appointmentschedule fragment = new Appointmentschedule();
        fragment.setArguments(null);

        // Thay thế Fragment hiện tại bằng Fragment Xác nhận đặt lịch
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Để có thể quay lại Fragment Đặt lịch
        transaction.commit();
    }
}