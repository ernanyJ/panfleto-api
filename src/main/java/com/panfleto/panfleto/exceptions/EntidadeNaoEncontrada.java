package com.panfleto.panfleto.exceptions;

public class EntidadeNaoEncontrada extends RuntimeException {
    String message;

    public EntidadeNaoEncontrada(String message){
        this.message = message;
    }
}
