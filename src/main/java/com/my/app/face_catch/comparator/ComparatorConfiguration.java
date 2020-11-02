package com.my.app.face_catch.comparator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComparatorConfiguration {

	@Bean(name="matrixCmp")
	public MatrixComparator matrixComparator() {
		return (MatrixComparator) new MatrixComparatorImpl(100, 100);
	}
}
