package com.my.app.face_catch.comparator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
* Configuration for pixel comparison implementation
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
@Configuration
public class ComparatorConfiguration {

	/**
	* Create MatrixComparator instance with 100x100 pixel matrix size
	*/
	@Bean(name="matrixCmp")
	public MatrixComparator matrixComparator() {
		return (MatrixComparator) new MatrixComparatorImpl(100, 100);
	}
}
