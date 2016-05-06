package br.com.ufpb.ia.iris.main;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		KNN knn = new KNN();
		
		knn.treinar();
		knn.classificar(5);
		knn.percentualDeAcertos();
		knn.gravarResultado();
	}
}
