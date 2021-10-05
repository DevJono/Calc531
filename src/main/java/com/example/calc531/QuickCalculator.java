package com.example.calc531;

public class QuickCalculator {
    public double oneRepMax;

    double s1w1(){
        return roundTo2Point5(oneRepMax*0.65*0.9);
    }

    double s2w1(){
        return roundTo2Point5(oneRepMax*0.75*0.9);
    }

    double s3w1(){
        return roundTo2Point5(oneRepMax*0.85*0.9);
    }

    double s1w2(){
        return roundTo2Point5(oneRepMax*0.70*0.9);
    }

    double s2w2(){
        return roundTo2Point5(oneRepMax*0.80*0.9);
    }

     double s3w2(){
        return roundTo2Point5(oneRepMax*0.90*0.9);
    }

     double s1w3(){
        return roundTo2Point5(oneRepMax*0.75*0.9);
    }

     double s2w3(){
        return roundTo2Point5(oneRepMax*0.85*0.9);
    }

     double s3w3(){
        return roundTo2Point5(oneRepMax*0.95*0.9);
    }

     double s1w4(){
        return roundTo2Point5(oneRepMax*0.4*0.9);
    }

     double s2w4(){
        return roundTo2Point5(oneRepMax*0.5*0.9);
    }

     double s3w4(){
        return roundTo2Point5(oneRepMax*0.6*0.9);
    }

     double roundTo2Point5(double n) {
        return 2.5*(Math.round(n/2.5));
    }
}
