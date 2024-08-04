import java.io.Serializable;
import java.util.Date;

/**
 * Representação dos horários relacionados a uma revista.
 *
 * Esta classe armazena os horários de empilhamento e desempilhamento de uma revista,
 * além de calcular o tempo total empilhado.
 *
 * @autor franck.allyson, pedro.lopes, ana.carolina, clarisse.santana
 */
public class HorariosRevista implements Serializable {

    private Date horarioEmpilhamento;
    private Date horarioDesempilhamento;
    private double tempoEmpilhado;

    /**
     * Construtor padrão da classe HorariosRevista.
     *
     * Inicializa os horários de empilhamento e desempilhamento como null,
     * e o tempo empilhado como 0.
     */
    public HorariosRevista() {
        horarioEmpilhamento = null;
        horarioDesempilhamento = null;
        tempoEmpilhado = 0d;
    }

    /**
     * Obtém o horário de empilhamento.
     *
     * @return O horário de empilhamento.
     */
    public Date getHorarioEmpilhamento() {
        return horarioEmpilhamento;
    }

    /**
     * Define o horário de empilhamento.
     *
     * @param horarioEmpilhamento O horário de empilhamento.
     */
    public void setHorarioEmpilhamento(Date horarioEmpilhamento) {
        this.horarioEmpilhamento = horarioEmpilhamento;
    }

    /**
     * Obtém o horário de desempilhamento.
     *
     * @return O horário de desempilhamento.
     */
    public Date getHorarioDesempilhamento() {
        return horarioDesempilhamento;
    }

    /**
     * Define o horário de desempilhamento.
     *
     * @param horarioDesempilhamento O horário de desempilhamento.
     * @throws Exception Se o horário de empilhamento não estiver definido.
     */
    public void setHorarioDesempilhamento(Date horarioDesempilhamento) throws Exception {
        if(horarioEmpilhamento == null){
            throw new Exception("Erro ao definir o horário de desempilhamento! Não existe horário de empilhamento!");
        }

        this.horarioDesempilhamento = horarioDesempilhamento;

        long diferenca = horarioDesempilhamento.getTime() - horarioEmpilhamento.getTime();

        tempoEmpilhado = (double)diferenca / 1000;
    }

    /**
     * Obtém o tempo total empilhado em segundos.
     *
     * @return O tempo total empilhado em segundos.
     */
    public double getTempoEmpilhado() {
        return tempoEmpilhado;
    }

    /**
     * Retorna uma representação em string dos horários de empilhamento e desempilhamento,
     * incluindo o tempo total empilhado formatado como horas, minutos e segundos.
     *
     * @return Uma string representando os horários e o tempo total empilhado.
     */
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
