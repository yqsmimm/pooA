package thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MainSimulacao {

    public static void main(String[] args) {

        Queue<Residuo> fila = new LinkedList<>();
        ArrayList<Residuo> reciclados = new ArrayList<>();
        ArrayList<Residuo> naoReciclados = new ArrayList<>();

        GeradoresLixo gerador = new GeradoresLixo(fila);
        Coletora coletora = new Coletora(fila, reciclados, naoReciclados);

        System.out.println("\n=== SIMULAÇÃO DE 3 MINUTOS - COLETA DE LIXO ===\n");
        System.out.println("Iniciando gerador de resíduos e coletora...\n");

        long inicioSimulacao = System.currentTimeMillis();

        gerador.start();
        coletora.start();

        try {
            gerador.join();
        } catch (InterruptedException e) {
        }

        coletora.finalizarColeta();

        try {
            coletora.join();
        } catch (InterruptedException e) {
        }

        long fimSimulacao = System.currentTimeMillis();

        System.out.println("\n=== RESULTADOS DA SIMULAÇÃO ===\n");

        System.out.println("Tempo total de simulação: " + (fimSimulacao - inicioSimulacao) + " ms\n");

        System.out.println("RESÍDUOS GERADOS (Contadores globais):");
        System.out.println("Papel = " + Contadores.papel);
        System.out.println("Plástico = " + Contadores.plastico);
        System.out.println("Vidro = " + Contadores.vidro);
        System.out.println("Metal = " + Contadores.metal);
        System.out.println("Orgânico = " + Contadores.organico);
        System.out.println("Não Reciclável = " + Contadores.naoReciclavel);

        System.out.println("\n CLASSIFICAÇÃO FEITA PELA COLETORA:");
        System.out.println("Reciclados = " + reciclados.size());
        System.out.println("Não reciclados = " + naoReciclados.size());

        System.out.println("\n TEMPO TOTAL DA COLETA:");
        System.out.println(coletora.getTempoTotalColeta() + " ms");

        System.out.println("\n ANÁLISE AMBIENTAL AUTOMÁTICA:");

        int totalGerado = Contadores.papel + Contadores.plastico + Contadores.vidro +
                Contadores.metal + Contadores.organico + Contadores.naoReciclavel;

        double taxaReciclagem = (reciclados.size() * 100.0) / totalGerado;

        if (taxaReciclagem >= 60) {
            System.out.println("A simulação foi SUSTENTÁVEL. Alta taxa de reciclagem ("
                    + String.format("%.2f", taxaReciclagem) + "%).");
        } else if (taxaReciclagem >= 30) {
            System.out.println("A simulação foi MODERADA. Taxa de reciclagem razoável ("
                    + String.format("%.2f", taxaReciclagem) + "%).");
        } else {
            System.out.println("A simulação NÃO FOI SUSTENTÁVEL. Baixa taxa de reciclagem ("
                    + String.format("%.2f", taxaReciclagem) + "%).");
        }

        System.out.println("\n=== FIM DA SIMULAÇÃO ===\n");
    }
}
