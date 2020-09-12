package Paquete1;

public class ClaseB {

    public ClaseA claseA;

    public ClaseB() {

        claseA = new ClaseA();
        String s1 = claseA.a;
        String s2 = claseA.b;
        String s3 = claseA.c;

        claseA.metodoDefult();
        claseA.metodoProtected();
        claseA.metodoPublico();
    }
}
