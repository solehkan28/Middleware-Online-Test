package com.sml.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sml.model.DTOResult;
import com.sml.service.MWService;

@RestController
@RequestMapping("/middleware")
public class MWController {
	
	@Autowired
	private MWService iService;
	
	@RequestMapping(value = "/rekaps/{offset}/{limit}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List> doInquiryCustomize(@PathVariable("offset") String offset,
													@PathVariable("limit") String limit) {
		int awal  = Integer.valueOf(offset);
		int akhir = awal + Integer.valueOf(limit);
		if(awal <=0 ){
			List<Object> result = new ArrayList<>();
			result.add(new DTOResult("operasi sukses", "parameter tidak valid"));
			return new ResponseEntity<List>(result,HttpStatus.OK);
		}
		else
			return new ResponseEntity<List>(iService.inquiryByPaging(awal,akhir),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rekaps/",
			method = RequestMethod.GET,
			produces = "application/json")
	private ResponseEntity<List> doInquiryAllLive(HttpServletResponse response) {
		return new ResponseEntity<List>(iService.inquiryAllRekap(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rekaps/",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DTOResult> doCreateRekap() {
			return new ResponseEntity<DTOResult>(new DTOResult("create service",
							"tidak ada keterangan rinci dalam soal"),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rekaps/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DTOResult> doUpdateRekap() {
		return new ResponseEntity<DTOResult>(new DTOResult("update service",
					"tidak ada keterangan rinci dalam soal"),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rekaps/",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DTOResult> doDeleteRekap() {
		return new ResponseEntity<DTOResult>(new DTOResult("delete service",
				"tidak ada keterangan rinci dalam soal"),HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/rekaps/download/",
			method = RequestMethod.GET,
			produces = "application/json")
	private ResponseEntity<List> downloadRekap(HttpServletResponse response) {
		String fileName = "HASIL_REKAP.json";
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		return new ResponseEntity<List>(iService.inquiryAllRekap(),HttpStatus.OK);
	}
	
}
