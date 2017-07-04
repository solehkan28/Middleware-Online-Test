package com.sml.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sml.model.HasilRekap;
import com.sml.service.MWService;

@Service("mWService")
public class MWRepository implements MWService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JdbcTemplate jt;
    
    @Autowired
    private GlobalVariable gv;
	
    @Override
    public void tuningDB() {
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("SELECT ENGINE FROM information_schema.TABLES");
    	sb.append("where TABLE_SCHEMA = 'TEST_MIDDLEWARE' AND ENGINE IS NOT NULL AND TABLE_NAME = 'SUMBER_DATA'");
    	String result = jt.queryForObject(sb.toString(), String.class);
    	
    	if(result.toUpperCase().equals("InnoDB")){
    		System.out.println("in here alter "+result);
    		jt.execute("ALTER TABLE SUMBER_DATA ENGINE = MyISAM");
    	}else{
    		System.out.println(" no thing to do "+result);
    		// nothing to do
		}
    }
    
	@Override
	public List inquiryAllRekap() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select x.*");
		sb.append(" from ( ");
		sb.append(" select ");
		sb.append(" @num := @num + 1 as row_number,y.* ");
		sb.append(" from ");
		sb.append(" ( ");
		sb.append(" select ");
		sb.append(" A.REGION AS REGIONAL, ");
		sb.append(" COUNT(DISTINCT A.BSC_NAME) AS JML_BSC, ");
		sb.append(" COUNT(DISTINCT A.SITE_NAME) AS JML_SITE, ");
		sb.append(" COUNT(DISTINCT A.CELL_NAME) AS JML_CELL, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS = 'NEW SITE' THEN 1 ELSE 0 END) AS SITE_BARU, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS = 'EXISTING' THEN 1 ELSE 0 END) AS SITE_LAMA, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS NOT IN('NEW SITE','EXISTING') THEN 1 ELSE 0 END) AS LAINNYA ");
		sb.append(" FROM SUMBER_DATA A, ");
		sb.append(" (SELECT @num := 0) paging GROUP BY A.REGION ");
		sb.append(" ) ");
		sb.append(" as y) as x ");
		logger.info(sb.toString());
		return jt.query(sb.toString(), new HasilRekap());
	}
	
	@Override
	public List<String> inquiryRegion() {
		return jt.queryForList("select distinct(region) from sumber_data", String.class);
	}
	
	@Override
	public List<HasilRekap> inquiryByPaging(int awal,int akhir) {
		List<HasilRekap> result = new ArrayList<HasilRekap>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select x.*");
		sb.append(" from ( ");
		sb.append(" select ");
		sb.append(" @num := @num + 1 as row_number,y.* ");
		sb.append(" from ");
		sb.append(" ( ");
		sb.append(" select ");
		sb.append(" A.REGION AS REGIONAL, ");
		sb.append(" COUNT(DISTINCT A.BSC_NAME) AS JML_BSC, ");
		sb.append(" COUNT(DISTINCT A.SITE_NAME) AS JML_SITE, ");
		sb.append(" COUNT(DISTINCT A.CELL_NAME) AS JML_CELL, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS = 'NEW SITE' THEN 1 ELSE 0 END) AS SITE_BARU, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS = 'EXISTING' THEN 1 ELSE 0 END) AS SITE_LAMA, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS NOT IN('NEW SITE','EXISTING') THEN 1 ELSE 0 END) AS LAINNYA ");
		sb.append(" FROM SUMBER_DATA A, ");
		sb.append(" (SELECT @num := 0) paging GROUP BY A.REGION");
		sb.append(" ) ");
		sb.append(" as y) as x ");
		sb.append(" where  row_number between ? and ? ");
		
		logger.info(sb.toString());
		return jt.query(sb.toString(),new Object[]{awal,akhir}, new HasilRekap());
	}
	
	@Override
	public List<HasilRekap> inquiryAllCustome() {
		List<HasilRekap> result = new ArrayList<HasilRekap>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select x.*");
		sb.append(" from ( ");
		sb.append(" select ");
		sb.append(" @num := @num + 1 as row_number,y.* ");
		sb.append(" from ");
		sb.append(" ( ");
		sb.append(" select ");
		sb.append(" A.REGION AS REGIONAL, ");
		sb.append(" COUNT(DISTINCT A.BSC_NAME) AS JML_BSC, ");
		sb.append(" COUNT(DISTINCT A.SITE_NAME) AS JML_SITE, ");
		sb.append(" COUNT(DISTINCT A.CELL_NAME) AS JML_CELL, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS = 'NEW SITE' THEN 1 ELSE 0 END) AS SITE_BARU, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS = 'EXISTING' THEN 1 ELSE 0 END) AS SITE_LAMA, ");
		sb.append(" SUM(CASE WHEN A.SITE_STATUS NOT IN('NEW SITE','EXISTING') THEN 1 ELSE 0 END) AS LAINNYA ");
		sb.append(" FROM SUMBER_DATA A, ");
		sb.append(" (SELECT @num := 0) paging WHERE REGION = ? ");
		sb.append(" ) ");
		sb.append(" as y) as x ");
		
		logger.info(sb.toString());
		for (int i = 0; i <= gv.dataRegion.size(); i++) {
			result.add((HasilRekap)jt.
					queryForObject(sb.toString(),new Object[]{i}, new HasilRekap()));
		}
		return result;
	}
	
}
