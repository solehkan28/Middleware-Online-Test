package com.sml.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sml.model.HasilRekap;

public interface MWService {
	void tuningDB();
	List<String>inquiryRegion();
	List<HasilRekap>inquiryAllRekap();
	List<HasilRekap>inquiryAllCustome();
	List<HasilRekap>inquiryByPaging(int awal,int akhir);
}
