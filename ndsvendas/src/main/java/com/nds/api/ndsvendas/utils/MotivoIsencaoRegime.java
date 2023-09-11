package com.nds.api.ndsvendas.utils;

import java.util.Dictionary;
import java.util.Hashtable;

import org.springframework.stereotype.Component;

@Component
public class MotivoIsencaoRegime {

	 Dictionary<String, String> regimes = new Hashtable<String, String>();
	
	
	 public MotivoIsencaoRegime () {
		 	regimes.put("M00", "Regime Simplificado");
			regimes.put("M02", "Transmissão de bens e serviço não sujeita");
			regimes.put("M04", "IVA – Regime de Exclusão");
			regimes.put("M10", "Isento nos termos da alínea a) do nº1 do artigo 12.º do CIVA");
			regimes.put("M11", "Isento nos termos da alínea b) do nº1 do artigo 12.º do CIVA");
			regimes.put("M11", "Isento nos termos da alínea c) do nº1 do artigo 12.º do CIVA");
			regimes.put("M12", "Isento nos termos da alínea d) do nº1 do artigo 12.º do CIVA");
	 }


	public Dictionary<String, String> getRegimes() {
		return regimes;
	}
	 
	 
}
