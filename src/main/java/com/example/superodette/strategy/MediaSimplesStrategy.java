package com.example.superodette.strategy;

import org.springframework.stereotype.Component;

@Component
public class MediaSimplesStrategy implements MediaStrategy {
    @Override
    public double calcular(double nota1, double nota2) {
        return (nota1 + nota2) / 2;
    }
}