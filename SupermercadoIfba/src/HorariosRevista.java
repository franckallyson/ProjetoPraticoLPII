import java.io.Serializable;
import java.util.Date;

public class HorariosRevista implements Serializable {

    private Date horarioEmpilhamento;
    private Date horarioDesempilhamento;
    private double tempoEmpilhado;

    public HorariosRevista() {
        horarioEmpilhamento = null;
        horarioDesempilhamento = null;
        tempoEmpilhado = 0d;
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

        long diferenca = horarioDesempilhamento.getTime() - horarioEmpilhamento.getTime();

        tempoEmpilhado = (double)diferenca / 1000;
    }

    public double getTempoEmpilhado() {
        return tempoEmpilhado;
    }

    @Override
    public String toString() {
        int horas = (int) (tempoEmpilhado / 3600);
        int minutos = (int) ((tempoEmpilhado % 3600) / 60);
        int segundos = (int) (tempoEmpilhado % 60);
        String tempo_formatado = String.format("%02d:%02d:%02d", horas, minutos, segundos);

        return "Empilhamento: " + horarioEmpilhamento +
                ", Desempilhamento: " + horarioDesempilhamento +
                ",  Tempo de permanencia na pilha: " + tempo_formatado;
    }
}
