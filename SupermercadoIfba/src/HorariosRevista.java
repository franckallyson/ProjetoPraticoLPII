import java.io.Serializable;
import java.util.Date;

public class HorariosRevista implements Serializable {

    private Date horarioEmpilhamento;
    private Date horarioDesempilhamento;
    private int tempoEmpilhado;

    public HorariosRevista() {
    }

    public Date getHorarioEmpilhamento() {
        return horarioEmpilhamento;
    }

    public void setHorarioEmpilhamento(Date horarioEmpilhamento) {
        this.horarioEmpilhamento = horarioEmpilhamento;
    }

    public Date getHorarioDesempilhamento() {
        return horarioDesempilhamento;
    }

    public void setHorarioDesempilhamento(Date horarioDesempilhamento) {
        this.horarioDesempilhamento = horarioDesempilhamento;
    }

    public int getTempoEmpilhado() {
        return tempoEmpilhado;
    }
}
