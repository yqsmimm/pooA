package thread;

public class Residuo {

	    private TipoResiduo tipo;
	    private long timestamp;

	    public Residuo(TipoResiduo tipo) {
	        this.tipo = tipo;
	        this.timestamp = System.currentTimeMillis();
	    }

	    public TipoResiduo getTipo() {
	        return tipo;
	    }

	    public long getTimestamp() {
	        return timestamp;
	    }
	}


