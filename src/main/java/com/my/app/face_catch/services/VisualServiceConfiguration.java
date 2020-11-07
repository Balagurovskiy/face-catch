package com.my.app.face_catch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.my.app.face_catch.Property;
import com.my.app.face_catch.db.dao.PersonnelDao;
import com.my.app.face_catch.services.builder.VusialServiceBuilder;
import com.my.app.face_catch.services.visual_algorithms.VisualCompareAlgorithm;
import com.my.app.face_catch.services.visual_algorithms.VisualDataSource;
import com.my.app.face_catch.services.visual_algorithms.VisualSearchAlgorithm;
/**
* Configurations for visual services.
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
@Configuration
public class VisualServiceConfiguration {

	@Autowired
	Property property;
	
	private static final String IMAGE_SOURCE = "opencv.input";
	private static final String IMAGE_TARGET = "opencv.output";
	

	private VusialServiceBuilder createBuilder(VisualSearchAlgorithm vsa, VisualCompareAlgorithm vca , VisualDataSource vds) {
		VusialServiceBuilder builder = new VusialServiceBuilder();
		builder.setVisualSearchAlgorithm(vsa);
		builder.setVisualCompareAlgorithm(vca);
		builder.setVisualDataSource(vds);
		builder.setImportPath(property.getProperty(IMAGE_SOURCE));
		builder.setExportPath(property.getProperty(IMAGE_TARGET));
		return builder;
	}
	
	@Bean(name="imageServiceCascade")
	public VisualService imageServiceWithCascades(@Qualifier("cascadeFDA")VisualSearchAlgorithm vsa,
													@Qualifier("matrixCA")VisualCompareAlgorithm vca,
													 @Qualifier("personnelDS")VisualDataSource vds) {
		return new ImageService( createBuilder(vsa, vca, vds) );
	}
	
	@Lazy
	@Bean(name="imageServiceCaffe")
	public VisualService imageServiceWithCafee(@Qualifier("caffeFDA")VisualSearchAlgorithm vsa,
												@Qualifier("matrixCA")VisualCompareAlgorithm vca,
												 @Qualifier("personnelDS")VisualDataSource vds) {
	return new ImageService( createBuilder(vsa, vca, vds) );
	}
	
	@Lazy
	@Bean(name="cameraServiceCascade")
	public VisualService cameraServiceWithCascades(@Qualifier("cascadeFDA")VisualSearchAlgorithm vsa) {
		VusialServiceBuilder builder = new VusialServiceBuilder();
		builder.setVisualSearchAlgorithm(vsa);
		return new CameraService(builder);
	}
	@Lazy
	@Bean(name="cameraServicecCaffe")
	public VisualService cameraServiceWithCaffe(@Qualifier("caffeFDA")VisualSearchAlgorithm vsa) {
		VusialServiceBuilder builder = new VusialServiceBuilder();
		builder.setVisualSearchAlgorithm(vsa);
		return new CameraService(builder);
	}
}
