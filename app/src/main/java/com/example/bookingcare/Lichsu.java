package com.example.bookingcare;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

public class Lichsu extends Fragment {

    private SharedViewModel viewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lichsu, container, false);

        // Khởi tạo ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Hiển thị thông báo từ ViewModel trên giao diện của fragment Lichsu
        TextView tvThongBao = view.findViewById(R.id.tvThongBao);

        viewModel.getThongBaoData().observe(getViewLifecycleOwner(), thongBaoData -> {
            tvThongBao.setText(thongBaoData);
        });

        // Hiển thị thông tin khác từ ViewModel
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

        return view;
    }
}