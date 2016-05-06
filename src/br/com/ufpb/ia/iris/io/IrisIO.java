package br.com.ufpb.ia.iris.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import br.com.ufpb.ia.iris.entity.Flor;

public class IrisIO {
	
	private BufferedReader leitor;
	
	public IrisIO() {}
	
	public List<Flor> carregarDados(String nomeArquivo) throws IOException{
		
		this.leitor = null;
		int cont = 0;
		List<Flor> info = new ArrayList<>();
		
		try{
			leitor =  new BufferedReader(new FileReader(nomeArquivo));
			String linha = null;
			do{
				linha = leitor.readLine();
				if(linha != null){
					cont++;
					if(cont != 1){
						Flor f = this.criarFlor(linha);
						info.add(f);
					}
				}
			}while(linha != null);
		}catch(Exception E){
			E.printStackTrace();
		}finally{
			if(leitor != null) leitor.close();
		}
		return info;
	}

	private Flor criarFlor(String linha){
		
		String[] dados = linha.split(",");
		double sepallength = Double.parseDouble(dados[0]);
		double sepalwidth = Double.parseDouble(dados[1]);
		double petallength = Double.parseDouble(dados[2]);
		double petalwidth = Double.parseDouble(dados[3]);
		
		if(dados.length == 5){
			int label = Integer.parseInt(dados[4]);
			return new Flor(sepallength,sepalwidth, petallength, petalwidth, label);
		}else{
			return new Flor(sepallength,sepalwidth, petallength, petalwidth);
		}
	}
	
	public List<Integer> carregarGabarito(String nomeArquivo) throws IOException{
		ArrayList<Integer> gabarito = new ArrayList<>();
		this.leitor = null;
		
		try{
			leitor =  new BufferedReader(new FileReader(nomeArquivo));
			String linha = null;
			
			do{
				linha = leitor.readLine();
				if(linha != null){
					gabarito.add(Integer.parseInt(linha));
				}
			}while(linha != null);
		}catch(Exception E){
			E.printStackTrace();
		}finally{
			if(leitor != null) leitor.close();
		}
		return gabarito;
	}
	
	public void gravarResultado(String nomeArquivo, List<Integer> resultado){
		PrintWriter gravador = null;
		
		try{
			gravador = new PrintWriter(new FileWriter(nomeArquivo));
			for(int i = 0 ; i < resultado.size() ; i++){
				if(i != resultado.size()-1){
					gravador.println(resultado.get(i));
				}else{
					gravador.print(resultado.get(i));
				}
			}
			
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			if(gravador != null) gravador.close();
		}
	}
}
