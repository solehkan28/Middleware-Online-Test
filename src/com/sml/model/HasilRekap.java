package com.sml.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HasilRekap implements Serializable,RowMapper{
	private static final long serialVersionUID = -8840406844877458198L;
	private String REGIONAL; 
	private String JML_BSC; 
	private String JML_SITE; 
	private String JML_CELL; 
	private String SITE_BARU; 
	private String SITE_LAMA; 
	
	public HasilRekap(String REGIONAL,String JML_BSC,
					  String JML_SITE,String JML_CELL,String SITE_BARU,
					  String SITE_LAMA) {
		this.REGIONAL = REGIONAL;
		this.JML_BSC = JML_BSC;
		this.JML_SITE = JML_SITE;
		this.JML_CELL = JML_CELL;
		this.SITE_BARU = SITE_BARU;
		this.SITE_LAMA = SITE_LAMA;
	}
	
	public HasilRekap() {}
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		HasilRekap result = new HasilRekap(rs.getString("REGIONAL"),
				rs.getString("JML_BSC"), rs.getString("JML_SITE"),
				rs.getString("JML_CELL"), rs.getString("SITE_BARU"),
				rs.getString("SITE_LAMA"));
		return result;
	}

	public String getREGIONAL() {
		return REGIONAL;
	}

	public void setREGIONAL(String rEGIONAL) {
		REGIONAL = rEGIONAL;
	}

	public String getJML_BSC() {
		return JML_BSC;
	}

	public void setJML_BSC(String jML_BSC) {
		JML_BSC = jML_BSC;
	}

	public String getJML_SITE() {
		return JML_SITE;
	}

	public void setJML_SITE(String jML_SITE) {
		JML_SITE = jML_SITE;
	}

	public String getJML_CELL() {
		return JML_CELL;
	}

	public void setJML_CELL(String jML_CELL) {
		JML_CELL = jML_CELL;
	}

	public String getSITE_BARU() {
		return SITE_BARU;
	}

	public void setSITE_BARU(String sITE_BARU) {
		SITE_BARU = sITE_BARU;
	}

	public String getSITE_LAMA() {
		return SITE_LAMA;
	}

	public void setSITE_LAMA(String sITE_LAMA) {
		SITE_LAMA = sITE_LAMA;
	}
}
