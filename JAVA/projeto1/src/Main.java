void main () {
    IO.println(String.format("Esse é o Screen Match"));
    System.out.println("Filme: O Fantasma do Paraíso");

    int anoDeLancamento = 1974;
    System.out.println("Ano de lançamento: " + anoDeLancamento);
    boolean incluidoNoPlano = true;
    double notaDoFilme = 8.1;
    String tipoPlano = "plus";

    double media = (9.8 + 6.3 + 8.0) / 3;
    System.out.println(media);
    String sinopse;

    sinopse = "Filme crticando a indústria do Rock da época";
    System.out.println(sinopse);

    int classificacao;
    classificacao = (int) (media / 2);
    System.out.println(classificacao);


    if (anoDeLancamento > 2022){
        System.out.println("Lançamento que os clientes estão assistindo");
        }
    else{
        System.out.println("Um clássico que vale a pena rever");
        }
    if (incluidoNoPlano == true && tipoPlano.equals("plus")){
        System.out.println("Filme liberado");
    }
    else{
        System.out.println("Deve pagar a locação");
    }
    }


