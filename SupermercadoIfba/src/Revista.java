import java.io.Serializable;

/**
 * Representação de uma revista.
 *
 * Esta classe contém informações sobre uma revista, incluindo o título, número da edição,
 * mês e ano de publicação, número do volume e horários relacionados.
 *
 * @autor franck.allyson, pedro.lopes, ana.carolina, clarisse.santana
 */
public class Revista implements Serializable {
    private String titulo;
    private Integer numerEdicao;
    private Integer mes;
    private Integer ano;
    private Integer nVolume;

    private HorariosRevista horarios;

    /**
     * Construtor da Classe Revista.
     *
     * @param titulo O título da revista.
     * @param numerEdicao O número da edição da revista.
     * @param mes O mês de publicação da revista.
     * @param ano O ano de publicação da revista.
     * @param nVolume O número do volume da revista.
     */
    public Revista(String titulo, Integer numerEdicao, Integer mes, Integer ano, Integer nVolume) {
        this.titulo = titulo;
        this.numerEdicao = numerEdicao;
        this.mes = mes;
        this.ano = ano;
        this.nVolume = nVolume;
        horarios = new HorariosRevista();
    }

    /**
     * Obtém o título da revista.
     *
     * @return O título da revista.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título da revista.
     *
     * @param titulo O título da revista.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o número da edição da revista.
     *
     * @return O número da edição da revista.
     */
    public Integer getNumerEdicao() {
        return numerEdicao;
    }

    /**
     * Define o número da edição da revista.
     *
     * @param numerEdicao O número da edição da revista.
     */
    public void setNumerEdicao(Integer numerEdicao) {
        this.numerEdicao = numerEdicao;
    }

    /**
     * Obtém o mês de publicação da revista.
     *
     * @return O mês de publicação da revista.
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * Define o mês de publicação da revista.
     *
     * @param mes O mês de publicação da revista.
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * Obtém o ano de publicação da revista.
     *
     * @return O ano de publicação da revista.
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * Define o ano de publicação da revista.
     *
     * @param ano O ano de publicação da revista.
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    /**
     * Obtém o número do volume da revista.
     *
     * @return O número do volume da revista.
     */
    public Integer getnVolume() {
        return nVolume;
    }

    /**
     * Define o número do volume da revista.
     *
     * @param nVolume O número do volume da revista.
     */
    public void setnVolume(Integer nVolume) {
        this.nVolume = nVolume;
    }

    /**
     * Obtém os horários relacionados à revista.
     *
     * @return Os horários relacionados à revista.
     */
    public HorariosRevista getHorarios() {
        return horarios;
    }

    /**
     * Define os horários relacionados à revista.
     *
     * @param horarios Os horários relacionados à revista.
     */
    public void setHorarios(HorariosRevista horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "\nTitulo da Revista: '" + titulo +
                " | Número de Edição: " + numerEdicao +
                " | Mês de Publicação: " + mes +
                " | Ano de Publicação: " + ano +
                " | Número do Volume: " + nVolume +
                " | Horários: " + horarios +
                '}';
    }
}
