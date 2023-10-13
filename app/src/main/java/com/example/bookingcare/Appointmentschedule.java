package com.example.bookingcare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class Appointmentschedule extends Fragment {

    private Button XacNhan, Huy;
    private SharedViewModel viewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointment_schedule, container, false);

        // Khôi phục dữ liệu từ SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String caNhan = sharedPreferences.getString("caNhan", "");
        String tenBenhNhan = sharedPreferences.getString("tenBenhNhan", "");
        String gioiTinh = sharedPreferences.getString("gioiTinh", "");
        String soDienThoai = sharedPreferences.getString("soDienThoai", "");
        String diaChi = sharedPreferences.getString("diaChi", "");
        String gioKham = sharedPreferences.getString("gioKham", "");

        // Khởi tạo ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Set dữ liệu vào ViewModel
        viewModel.setCaNhanData(caNhan);
        viewModel.setTenBenhNhanData(tenBenhNhan);
        viewModel.setGioiTinhData(gioiTinh);
        viewModel.setSoDienThoaiData(soDienThoai);
        viewModel.setDiaChiData(diaChi);
        viewModel.setGioKhamData(gioKham);

        // Hiển thị dữ liệu trên giao diện
        TextView tvCaNhan = view.findViewById(R.id.tvCaNhan);
        TextView tvTenBenhNhan = view.findViewById(R.id.tvTenBenhNhan);
        TextView tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
        TextView tvSoDienThoai = view.findViewById(R.id.tvSoDienThoai);
        TextView tvDiaChi = view.findViewById(R.id.tvDiaChi);
        TextView tvGioKham = view.findViewById(R.id.tvGioKham);

        viewModel.getCaNhanData().observe(getViewLifecycleOwner(), caNhanData -> tvCaNhan.setText(caNhanData));
        viewModel.getTenBenhNhanData().observe(getViewLifecycleOwner(), tenBenhNhanData -> tvTenBenhNhan.setText("Họ tên bệnh nhân: " + tenBenhNhanData));
        viewModel.getGioiTinhData().observe(getViewLifecycleOwner(), gioiTinhData -> tvGioiTinh.setText("Giới tính: " + gioiTinhData));
        viewModel.getSoDienThoaiData().observe(getViewLifecycleOwner(), soDienThoaiData -> tvSoDienThoai.setText("Số điện thoại: " + soDienThoaiData));
        viewModel.getDiaChiData().observe(getViewLifecycleOwner(), diaChiData -> tvDiaChi.setText("Địa chỉ: " + diaChiData));
        viewModel.getGioKhamData().observe(getViewLifecycleOwner(), gioKhamData -> tvGioKham.setText("Giờ khám: " + gioKhamData));

        XacNhan = view.findViewById(R.id.XacNhan);
        Huy = view.findViewById(R.id.Huy);

        // Thiết lập sự kiện nhấn nút "Xác nhận" để chuyển đến fragment Lichsu
        XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setThongBaoData("Đã thăm khám thành công");

                // Tạo một instance của fragment Lichsu
                Fragment lichsuFragment = new Lichsu();

                // Bắt đầu giao dịch fragment để chuyển đến fragment Lichsu
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, lichsuFragment);
                transaction.addToBackStack(null); // Để có thể quay lại fragment trước đó
                transaction.commit();
            }
        });

        // Thiết lập sự kiện nhấn nút "Hủy" để chuyển đến fragment Lichsu
        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setThongBaoData("Hủy thăm khám thành công");
                // Tạo một instance của fragment Lichsu
                Fragment lichsuFragment = new Lichsu();

                // Bắt đầu giao dịch fragment để chuyển đến fragment Lichsu
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, lichsuFragment);
                transaction.addToBackStack(null); // Để có thể quay lại fragment trước đó
                transaction.commit();

            }
        });

        return view;
    }
}