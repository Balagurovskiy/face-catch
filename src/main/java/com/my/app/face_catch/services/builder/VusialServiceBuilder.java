package com.my.app.face_catch.services.builder;

import com.my.app.face_catch.db.dao.PersonnelDao;
import com.my.app.face_catch.services.visual_algorithms.VisualCompareAlgorithm;
import com.my.app.face_catch.services.visual_algorithms.VisualSearchAlgorithm;

public class VusialServiceBuilder {
	private boolean vsaReady;
	private VisualSearchAlgorithm vsa;
	
	private boolean vcaReady;
	private VisualCompareAlgorithm vca;
	
	private boolean importReady;
	private String importPath;
	
	private boolean exportReady;
	private String exportPath;
	
	private boolean daoReady;
	private PersonnelDao personnelDao;
	
	public VusialServiceBuilder() {
		vsaReady = false;
		vcaReady = false;
		importReady = false;
		exportReady = false;
		daoReady = false;
	}
	
	public boolean isReady() {
		return vsaReady && vcaReady && importReady && exportReady && daoReady;
	}
	
	public VisualSearchAlgorithm getVsa() {
		return vsa;
	}
	public void setVsa(VisualSearchAlgorithm vsa) {
		if (vsa != null) {
			vsaReady = true;
		}
		this.vsa = vsa;
	}
	public VisualCompareAlgorithm getVca() {
		return vca;
	}
	public void setVca(VisualCompareAlgorithm vca) {
		if (vca != null) {
			vcaReady = true;
		}
		this.vca = vca;
	}
	
	public String getImportPath() {
		return importPath;
	}
	public void setImportPath(String importPath) {
		if (importPath != null) {
			importReady = true;
		}
		this.importPath = importPath;
	}
	
	public String getExportPath() {
		return exportPath;
	}
	public void setExportPath(String exportPath) {
		if (exportPath != null) {
			exportReady = true;
		}
		this.exportPath = exportPath;
	}

	public PersonnelDao getPersonnelDao() {
		return personnelDao;
	}

	public void setPersonnelDao(PersonnelDao personnelDao) {
		if (personnelDao != null) {
			daoReady = true;
		}
		this.personnelDao = personnelDao;
	}
	
	
}
