package mdatum.udc.com.m_datum.events;

/**
 * Created by jaime on 11/12/17.
 */

public class MensajesDeEvento {

    private String evento;

    private String transaccion;

    private long encuestaId;

    private String dispatcher;

    private String nextAction;

    private Object object;




    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public long getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(long encuestaId) {
        this.encuestaId = encuestaId;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
