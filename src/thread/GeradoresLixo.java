package thread;

import java.util.Queue;
import java.util.Random;

public class GeradoresLixo extends Thread {

	private Queue<Residuo> fila;
	private Random random;

	public GeradoresLixo(Queue<Residuo> fila) {
		this.fila = fila;
		this.random = new Random();
	}

	@Override
	public void run() {

		long inicio = System.currentTimeMillis();
		long duracao = 3 * 60 * 1000;

		while (System.currentTimeMillis() - inicio < duracao) {

			// sortear tipo
			TipoResiduo tipo = sortearTipo();

			//criar resíduo
			Residuo r = new Residuo(tipo);

			//adicionar na fila 
			synchronized (fila) {
			fila.add(r);
			}

		
			Contadores.incrementar(tipo);

			// simula tempo de produção
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private TipoResiduo sortearTipo() {
		TipoResiduo[] tipos = TipoResiduo.values();
	    return tipos[random.nextInt(tipos.length)];
	}
}
