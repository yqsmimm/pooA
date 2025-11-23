package thread;

public class Contadores {

    public static Integer papel = 0;
    public static Integer plastico = 0;
    public static Integer vidro = 0;
    public static Integer metal = 0;
    public static Integer organico = 0;
    public static Integer naoReciclavel = 0;

    public static synchronized void incrementar(TipoResiduo tipo) {

        switch (tipo) {
            case PAPEL:
                papel++;
                break;

            case PLASTICO:
                plastico++;
                break;

            case VIDRO:
                vidro++;
                break;

            case METAL:
                metal++;
                break;

            case ORGANICO:
                organico++;
                break;

            case NAO_RECICLAVEL:
                naoReciclavel++;
                break;
        }
    }
}
