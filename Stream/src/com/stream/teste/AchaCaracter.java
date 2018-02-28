package com.stream.teste;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AchaCaracter {
	
	String palavra;
	Stream stream ;
	
	char anteriorAnterior = ' ';
	char anterior = ' ';
	char atual = ' ';
	
	public AchaCaracter(String palavra) {
		this.palavra = palavra;
		this.stream = new StreamImpl(palavra);
	}

	public char caracter() {
		while(stream.hasNext()) {
			
			atualizaCaracteres(stream.getNext());
			if(encontrouCaracter())
				return this.atual;
		}
		
		return this.atual;
	}
	
	private boolean isVogal(String vogal) {
        Pattern pattern = Pattern.compile("[AEIOUaeiou]|[^\\w\\p{Punct}\\p{Space}]");
        Matcher matcher = pattern.matcher(vogal);
        return matcher.matches();
    }
	
	private void atualizaCaracteres(char atual) {
		
		this.anteriorAnterior = this.anterior;
		this.anterior = this.atual;
		this.atual = atual;
		
	}
	
	private boolean encontrouCaracter() {
		if(!isVogal(Character.toString(anterior))) {
			if(isVogal(Character.toString(anteriorAnterior)) && isVogal(Character.toString(atual))) {
				if(!caracterRepete(atual))
					return true;
			}
					
		
	}
		return false;	
		
	}
	boolean caracterRepete(char c) {
		
		int counter = 0;
		String s = this.palavra;
		
		for( int i=0; i< s.length(); i++ ) {
		    if( s.charAt(i) == '$' ) {
		        counter++;
		    } 
		}
		if(counter < 1)
			return true;
		
		return false;
	}
	
}
