package com.my.app.face_catch.services.builder;

import com.my.app.face_catch.Property;
import com.my.app.face_catch.db.dao.PersonnelDao;
import com.my.app.face_catch.services.visual_algorithms.VisualCompareAlgorithm;
import com.my.app.face_catch.services.visual_algorithms.VisualSearchAlgorithm;
/**
* Builder for visual services
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
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
    /**
     * Constructor. Is not ready by default.
     * @param nothing
     * @return nothing
     */
	public VusialServiceBuilder() {
		vsaReady = false;
		vcaReady = false;
		importReady = false;
		exportReady = false;
		daoReady = false;
	}
    /**
     * State of builder.
     * @param nothing
     * @return boolean flag that shows that all properties of builder were set.
     */
	public boolean isReady() {
		return vsaReady && vcaReady && importReady && exportReady && daoReady;
	}
    /**
     * {@link VusialServiceBuilder#vsa}
     */
	public VisualSearchAlgorithm getVsa() {
		return vsa;
	}
	public void setVsa(VisualSearchAlgorithm vsa) {
		if (vsa != null) {
			vsaReady = true;
		}
		this.vsa = vsa;
	}
    /**
     * {@link VusialServiceBuilder#vca}
     */
	public VisualCompareAlgorithm getVca() {
		return vca;
	}
	public void setVca(VisualCompareAlgorithm vca) {
		if (vca != null) {
			vcaReady = true;
		}
		this.vca = vca;
	}
    /**
     * {@link VusialServiceBuilder#importPath}
     */
	public String getImportPath() {
		return importPath;
	}
	public void setImportPath(String importPath) {
		if (importPath != null) {
			importReady = true;
		}
		this.importPath = importPath;
	}
    /**
     * {@link VusialServiceBuilder#exportPath}
     */
	public String getExportPath() {
		return exportPath;
	}
	public void setExportPath(String exportPath) {
		if (exportPath != null) {
			exportReady = true;
		}
		this.exportPath = exportPath;
	}
    /**
     * {@link VusialServiceBuilder#personnelDao}
     */
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
