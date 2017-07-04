package com.sml.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class GlobalVariable {
	
	public static GlobalVariable instance;
	public List<String> dataRegion = new ArrayList<String>();
	public static GlobalVariable getInstance() {
		if(instance==null)
			instance =  new GlobalVariable();
		return instance;
	}

}
