import br.com.alura.screenmatch.modelos.Filme;
public class Principal {
    public static void main(String[] args) {
        Filme meuFilme =  new Filme();
        meuFilme.setNome("Kill Bill - Vol 1");
        meuFilme.setAnoDeLancamento(2004);
        meuFilme.setDuracaoEmMinutos(111);

        meuFilme.exibeFichaTecnica();
        meuFilme.avalia( 8 );
        meuFilme.avalia( 9 );
        meuFilme.avalia( 10 );
        System.out.println(meuFilme.getTotalDeAvaliacoes());
        System.out.println(meuFilme.pegaMedia());
    }

}
