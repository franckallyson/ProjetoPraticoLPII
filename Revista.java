public class Revista {
    private String titulo;
    private Integer numerEdicao;
    private Integer mes;
    private Integer ano;
    private Integer nVolume;

    public Revista() {}

    public Revista(String titulo, Integer numerEdicao, Integer mes, Integer ano, Integer nVolume) {
        this.titulo = titulo;
        this.numerEdicao = numerEdicao;
        this.mes = mes;
        this.ano = ano;
        this.nVolume = nVolume;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumerEdicao() {
        return numerEdicao;
    }

    public void setNumerEdicao(Integer numerEdicao) {
        this.numerEdicao = numerEdicao;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getnVolume() {
        return nVolume;
    }

    public void setnVolume(Integer nVolume) {
        this.nVolume = nVolume;
    }
}
