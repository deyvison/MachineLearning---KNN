package br.com.ufpb.ia.iris.main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.ufpb.ia.iris.entity.Flor;
import br.com.ufpb.ia.iris.io.IrisIO;

public class KNN {
	
	private int K;
	private List<Flor> treinamento;
	private List<Flor> dados;
	private List<Integer> gabarito;
	
	private IrisIO io;
	
	public KNN(){
		this.treinamento = new ArrayList<>();
		this.dados = new ArrayList<>();
		this.gabarito = new ArrayList<>();
		this.io = new IrisIO();
	}
	
	public void treinar() throws IOException{
		this.carregarTreinamento();
		this.carregarDadosTeste();
		this.carregarGabarito();
	}
	
	public void classificar(int k){
		this.K = k;
		List<Double> distancias = new ArrayList<>();
		int contador = 1;
		for (Flor dado : this.dados) {
			
			distancias.clear();
			
			for (Flor treinamento : this.treinamento) {
				distancias.add(this.calcularDistancia(dado, treinamento));
			}
			List<Flor> KMenoresDistancias = new ArrayList<>();
			
			for(int i = 0 ; i < this.K ; i++){
				
				double menorDistancia = Collections.min(distancias);
				int indiceMenorDistancia = distancias.indexOf(menorDistancia);

				Flor florMenorDistancia = treinamento.get(indiceMenorDistancia);
				KMenoresDistancias.add(florMenorDistancia);
				
				distancias.remove(indiceMenorDistancia);
			}
			
			ArrayList<Integer> cont = new ArrayList<Integer>(Arrays.asList(0,0,0));
			
			for(Flor flor : KMenoresDistancias){
				Integer label = flor.getLabel();
				cont.set(label, cont.get(label)+1);
			}
			
			//System.out.println("Caso :"+contador);
			//System.out.println(cont.get(0));
			//System.out.println(cont.get(1));
			//System.out.println(cont.get(2));
			
			int max = Collections.max(cont);
			//System.out.println("Maior: "+max);
			int pos = cont.indexOf(max);
			//System.out.println("Posicao do maior: "+pos);
			//System.out.println("---------------------------------");
			dado.setLabel(pos);
			contador++;
		}
		
	}
	
	public double calcularDistancia(Flor dado, Flor treinamento){
		double x1,y1,w1,z1,x2,y2,w2,z2;
		
		x1 = dado.getSepallength();
		y1 = dado.getSepalwidth();
		w1 = dado.getPetallength();
		z1 = dado.getPetalwidth();
		
		x2 = treinamento.getSepallength();
		y2 = treinamento.getSepalwidth();
		w2 = treinamento.getPetallength();
		z2 = treinamento.getPetalwidth();
		
		double x = Math.pow((x2-x1), 2);
		double y = Math.pow((y2-y1), 2);
		double w = Math.pow((w2-w1), 2);
		double z = Math.pow((z2-z1), 2);
		
		Double calc = Math.sqrt(x + y + w + z);
		
		return calc;
	}
	
	private void carregarTreinamento() throws IOException{
		this.treinamento = this.io.carregarDados("dados/treinamento.csv");
	}
	
	private void carregarDadosTeste() throws IOException{
		this.dados = this.io.carregarDados("dados/teste.csv");
	}
	
	private void carregarGabarito() throws IOException{
		this.gabarito = this.io.carregarGabarito("dados/rotulos-teste.txt");
	}

	public void percentualDeAcertos() {
		
		int acertos = 0;
		for(int i = 0 ; i < dados.size() ; i++){
			if(dados.get(i).getLabel() == gabarito.get(i)){
				acertos++;
			}
		}
		Double percentual = ((double)acertos / (double)gabarito.size()) *100;
		
		DecimalFormat df = new DecimalFormat("#.00");
		String format = df.format(percentual);
		
		System.out.println("Quantidade de acertos: "+acertos+" de "+gabarito.size());
		System.out.println("Percentual de acertos: "+format+"%");
	}

	public void gravarResultado() {
		ArrayList<Integer> resultado = new ArrayList<>();
		for(Flor f : this.dados){
			resultado.add(f.getLabel());
		}
		this.io.gravarResultado("dados/resultado.txt", resultado);
	}
}