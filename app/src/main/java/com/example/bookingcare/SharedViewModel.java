package com.example.bookingcare;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> caNhanData = new MutableLiveData<>();
    private MutableLiveData<String> tenBenhNhanData = new MutableLiveData<>();
    private MutableLiveData<String> gioiTinhData = new MutableLiveData<>();
    private MutableLiveData<String> soDienThoaiData = new MutableLiveData<>();
    private MutableLiveData<String> diaChiData = new MutableLiveData<>();
    private MutableLiveData<String> gioKhamData = new MutableLiveData<>();
    private MutableLiveData<String> thongBaoData = new MutableLiveData<>();


    public void setCaNhanData(String caNhan) {
        caNhanData.setValue(caNhan);
    }

    public LiveData<String> getCaNhanData() {
        return caNhanData;
    }

    public void setTenBenhNhanData(String tenBenhNhan) {
        tenBenhNhanData.setValue(tenBenhNhan);
    }

    public LiveData<String> getTenBenhNhanData() {
        return tenBenhNhanData;
    }

    public void setGioiTinhData(String gioiTinh) {
        gioiTinhData.setValue(gioiTinh);
    }

    public LiveData<String> getGioiTinhData() {
        return gioiTinhData;
    }

    public void setSoDienThoaiData(String soDienThoai) {
        soDienThoaiData.setValue(soDienThoai);
    }

    public LiveData<String> getSoDienThoaiData() {
        return soDienThoaiData;
    }

    public void setDiaChiData(String diaChi) {
        diaChiData.setValue(diaChi);
    }

    public LiveData<String> getDiaChiData() {
        return diaChiData;
    }

    public void setGioKhamData(String gioKham) {
        gioKhamData.setValue(gioKham);
    }

    public LiveData<String> getGioKhamData() {
        return gioKhamData;
    }

    public void setThongBaoData(String thongBao) {
        thongBaoData.setValue(thongBao);
    }

    public LiveData<String> getThongBaoData() {
        return thongBaoData;
    }

}
