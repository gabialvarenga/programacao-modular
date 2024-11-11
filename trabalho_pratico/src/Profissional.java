public class Profissional extends Pessoa {

    private String papel;
    private String alocacaoTempo;

    public Profissional(String nome, String papel, String alocacaoTempo) {
        super(nome);
        this.papel = papel;
        this.alocacaoTempo = alocacaoTempo;
    }

    public String getPapel() {
        return papel;
    }

    public String getAlocacaoTempo() {
        return alocacaoTempo;
    }


}