package com.atguigu.bean;

import org.apache.solr.client.solrj.beans.Field;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_SKU_SOLR {

	//使用Field注解，将属性名与solr配置文件中配置的字段名绑定。否则无法自动绑定。
	@Field("id")
	private int id;
	@Field("shp_id")
	private int shp_id;
	@Field("kc")
	private int kc;
	@Field("jg")
	private Double jg;
	@Field("chjshj")
	private Date chjshj;
	@Field("sku_mch")
	private String sku_mch;
	@Field("kcdz")
	private String kcdz;
	@Field("shp_tp")
	private String shp_tp;

	public String getShp_tp() {
		return shp_tp;
	}

	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}

	public String getKcdz() {
		return kcdz;
	}

	public void setKcdz(String kcdz) {
		this.kcdz = kcdz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public int getKc() {
		return kc;
	}

	public void setKc(int kc) {
		this.kc = kc;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public Double getJg() {
		return jg;
	}

	public void setJg(Double jg) {
		this.jg = jg;
	}

}
