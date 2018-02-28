package com.stream.teste;

import java.util.NoSuchElementException;

public class StreamImpl implements Stream{
	
	private int posicao = 0;
	private String palavra;
	
	public StreamImpl(String palavra) {
		this.palavra = palavra;
	}

	public char getNext() {
		if(hasNext()) {
			return palavra.charAt(posicao ++);
		}
		else {
            throw new NoSuchElementException("Não existem mais elementos " + palavra.length());
        }
	}

	public boolean hasNext() {
		return palavra.length() == posicao ? false : true;
	}
	

}
