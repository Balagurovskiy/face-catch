package com.my.app.face_catch.services.builder;

import com.my.app.face_catch.services.visual_algorithms.VisualCompareAlgorithm;
import com.my.app.face_catch.services.visual_algorithms.VisualDataSource;
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
	private VisualSearchAlgorithm visualSearchAlgorithm;
	
	private boolean vcaReady;
	private VisualCompareAlgorithm visualCompareAlgorithm;
	
	private boolean importReady;
	private String importPath;
	
	private boolean exportReady;
	private String exportPath;
	
	private boolean vdsReady;
	private VisualDataSource visualDataSource;
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
		vdsReady = false;
	}
    /**
     * State of builder.
     * @param nothing
     * @return boolean flag that shows that all properties of builder were set.
     */
	public boolean isReady() {
		return vsaReady && vcaReady && importReady && exportReady && vdsReady;
	}
    /**
     * {@link VusialServiceBuilder#visualSearchAlgorithm}
     */
	public VisualSearchAlgorithm getVisualSearchAlgorithm() {
		return visualSearchAlgorithm;
	}
	public void setVisualSearchAlgorithm(VisualSearchAlgorithm vsa) {
		if (vsa != null) {
			vsaReady = true;
		}
		this.visualSearchAlgorithm = vsa;
	}
    /**
     * {@link VusialServiceBuilder#visualCompareAlgorithm}
     */
	public VisualCompareAlgorithm getVisualCompareAlgorithm() {
		return visualCompareAlgorithm;
	}
	public void setVisualCompareAlgorithm(VisualCompareAlgorithm vca) {
		if (vca != null) {
			vcaReady = true;
		}
		this.visualCompareAlgorithm = vca;
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
	public VisualDataSource getVisualDataSource() {
		return visualDataSource;
	}

	public void setVisualDataSource(VisualDataSource visualDataSource) {
		if (visualDataSource != null) {
			vdsReady = true;
		}
		this.visualDataSource = visualDataSource;
	}
	
	
}
