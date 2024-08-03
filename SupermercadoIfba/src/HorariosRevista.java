import java.io.Serializable;
import java.util.Date;

public class HorariosRevista implements Serializable {

    private Date horarioEmpilhamento;
    private Date horarioDesempilhamento;
    private double tempoEmpilhado;

    public HorariosRevista() {
        horarioEmpilhamento = null;
        horarioDesempilhamento = null;
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

    public void setHorarioDesempilhamento(Date horarioDesempilhamento) throws Exception {
        if(horarioEmpilhamento == null){
            throw new Exception("Erro ao definir o horário de desempilhamento! Não existe horário de empilhamento!");
        }

        this.horarioDesempilhamento = horarioDesempilhamento;
        long diferenca = horarioEmpilhamento.getTime() - horarioDesempilhamento.getTime();
        tempoEmpilhado = diferenca / 1000 / 60/ 60;
    }

    public double getTempoEmpilhado() {
        return tempoEmpilhado;
    }

    @Override
    public String toString() {
        return "HorariosRevista{" +
                "horarioEmpilhamento=" + horarioEmpilhamento +
                ", horarioDesempilhamento=" + horarioDesempilhamento +
                ", tempoEmpilhado=" + tempoEmpilhado +
                '}';
    }
}
