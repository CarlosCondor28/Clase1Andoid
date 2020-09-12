package Paquete2;

import Paquete1.ClaseA;

public class ClaseC extends ClaseA{
    public ClaseA claseA;

    public ClaseC() {
        claseA = new ClaseA();
        String s1 = claseA.a;
        ClaseC claseC = new ClaseC();
        String s2 =claseC.b;

        claseA.metodoPublico();
        claseC.metodoProtected();
        claseC.metodoProtected();

    }
}
