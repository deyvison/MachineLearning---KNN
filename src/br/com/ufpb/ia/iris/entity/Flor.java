package br.com.ufpb.ia.iris.entity;

public class Flor {
	
	private Double sepallength, sepalwidth, petallength, petalwidth;
	private Integer label;
	
	public Flor(Double sepallength, Double sepalwidth, Double petallength, Double petalwidth, Integer label) {
		
		this.sepallength = sepallength;
		this.sepalwidth = sepalwidth;
		this.petallength = petallength;
		this.petalwidth = petalwidth;
		this.label = label;
		
	}
	
	public Flor(Double sepallength, Double sepalwidth, Double petallength, Double petalwidth){
		this(sepallength, sepalwidth, petallength, petalwidth, null);
	}

	public Double getSepallength() {
		return sepallength;
	}

	public void setSepallength(Double sepallength) {
		this.sepallength = sepallength;
	}

	public Double getSepalwidth() {
		return sepalwidth;
	}

	public void setSepalwidth(Double sepalwidth) {
		this.sepalwidth = sepalwidth;
	}

	public Double getPetallength() {
		return petallength;
	}

	public void setPetallength(Double petallength) {
		this.petallength = petallength;
	}

	public Double getPetalwidth() {
		return petalwidth;
	}

	public void setPetalwidth(Double petalwidth) {
		this.petalwidth = petalwidth;
	}

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Flor [sepallength=" + sepallength + ", sepalwidth=" + sepalwidth + ", petallength=" + petallength
				+ ", petalwidth=" + petalwidth + ", label=" + label + "]";
	}
}
