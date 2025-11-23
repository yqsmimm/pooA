package thread;

import java.util.ArrayList;
import java.util.Queue;

public class Coletora extends Thread {

    private Queue<Residuo> fila;
    private ArrayList<Residuo> reciclados;
    private ArrayList<Residuo> naoReciclados;

    private boolean continuar = true;

    private long tempoInicio;
    private long tempoFim;

    public Coletora(Queue<Residuo> fila,
            ArrayList<Residuo> reciclados,
            ArrayList<Residuo> naoReciclados) {

        this.fila = fila;
        this.reciclados = reciclados;
        this.naoReciclados = naoReciclados;

    }

    @Override
    public void run() {
        tempoInicio = System.currentTimeMillis();

        while (continuar || !fila.isEmpty()) {

            Residuo r = null;

            synchronized (fila) {

                while (fila.isEmpty() && continuar) {
                    try {
                        fila.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                if (!fila.isEmpty()) {
                    r = fila.poll();

                }
            }

            if (r != null) {
                classificarResiduo(r);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        tempoFim = System.currentTimeMillis();

    }

    private void classificarResiduo(Residuo r) {
        switch (r.getTipo()) {
            case PAPEL:
            case PLASTICO:
            case VIDRO:
            case METAL:
                reciclados.add(r);
                break;

            case ORGANICO:
            case NAO_RECICLAVEL:
                naoReciclados.add(r);
                break;
        }
    }

    public void finalizarColeta() {
        continuar = false;
        synchronized (fila) {
            fila.notifyAll();
        }
    }

    public long getTempoTotalColeta() {
        return tempoFim - tempoInicio;
    }

}
