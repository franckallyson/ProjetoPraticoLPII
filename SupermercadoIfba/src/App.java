import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Classe principal da aplicação do Supermercado IFBA.
 *
 * Esta classe gerencia as operações principais do sistema, incluindo carregamento e gravação de dados,
 * menu principal, checkout, cadastro e consulta de produtos e revistas.
 *
 * @autor franck.allyson, pedro.lopes, ana.carolina, clarisse.santana
 */
public class App {
    public static final String CAMINHO_ARQUIVO_PRODUTOS = "./SupermercadoIfba/Arquivos/produtos.txt";
    public static final String CAMINHO_ARQUIVO_REV = "./SupermercadoIfba/Arquivos/pilhas_revistas.txt";
    public static HashMap<Integer, Produto> produtos = new HashMap<Integer, Produto>();;
    public static Deque<Revista> revistasEmpilhadas = new ArrayDeque<Revista>();
    public static Deque<Revista> revistasDesempilhadas = new ArrayDeque<Revista>();

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String args[]){
        carregaRevistas();
        carregaProdutos();
        Scanner scan = new Scanner(System.in);
        int optionMenu;

        do{
            headerMenu();
            optionMenu = opcoesMenuPrincipal(scan);

            switch (optionMenu){
                case 1:
                    checkout(scan);
                    break;
                case 2:
                    cadastrarRevista(scan, revistasEmpilhadas);
                    break;
                case 3:
                    consultarRevistas(scan);
                    break;
                case 4:
                    cadastrarProdutos(scan);
                    break;
                case 5:
                    System.out.println("\nVolte Sempre!");
                    break;
            }
        }while(optionMenu != 5);

        scan.close();
        gravaRevistas(revistasEmpilhadas, revistasDesempilhadas);
    }

    /**
     * Carrega as revistas empilhadas e desempilhadas a partir do arquivo.
     */
    public static void carregaRevistas(){
        FileInputStream fluxoArquivo;
        ObjectInputStream fluxoObjeto;

        try {
            fluxoArquivo = new FileInputStream(CAMINHO_ARQUIVO_REV);
            fluxoObjeto = new ObjectInputStream(fluxoArquivo);
            revistasEmpilhadas = (Deque<Revista>) fluxoObjeto.readObject();
            revistasDesempilhadas = (Deque<Revista>) fluxoObjeto.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existe!");
        }catch (IOException e) {
            System.out.println("Erro de entrada e saída de dados!");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não foi encontrada em arquivo!");
        }


    }

    /**
     * Grava as revistas empilhadas e desempilhadas no arquivo.
     *
     * @param revEmp Deque de revistas empilhadas.
     * @param revDesemp Deque de revistas desempilhadas.
     */
    public static void gravaRevistas(Deque<Revista> revEmp, Deque<Revista> revDesemp){
        FileOutputStream fluxoArquivo;
        ObjectOutputStream fluxoObjeto;


        try {
            fluxoArquivo =  new FileOutputStream(CAMINHO_ARQUIVO_REV);
            fluxoObjeto = new ObjectOutputStream(fluxoArquivo);
            fluxoObjeto.writeObject(revEmp);
            fluxoObjeto.writeObject(revDesemp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Carrega os produtos a partir do arquivo de produtos.
     */
    public static void carregaProdutos(){
        Scanner scan;
        int code;
        String descricao;
        double preco;

        try {
            FileReader fluxo = new FileReader(CAMINHO_ARQUIVO_PRODUTOS);
            scan = new Scanner(fluxo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StringTokenizer tokenizer;
        while(scan.hasNext()){
            String linha = scan.nextLine();
            tokenizer = new StringTokenizer(linha, ";");

            code = Integer.parseInt(tokenizer.nextToken());
            descricao = tokenizer.nextToken();
            preco = Double.parseDouble(tokenizer.nextToken().replace(",", "."));

            Produto produto = new Produto(code, descricao, preco);
            produtos.put(code, produto);

        }

        scan.close();
    }

    /**
     * Grava um novo produto no arquivo de produtos.
     *
     * @param produto Produto a ser gravado.
     */
    public static void gravaProdutos(Produto produto){
        try {
            FileWriter fluxoArquivo = new FileWriter(CAMINHO_ARQUIVO_PRODUTOS, true);
            PrintWriter fluxo = new PrintWriter(fluxoArquivo, true);
            DecimalFormat df = new DecimalFormat("#0.00");
            fluxo.println(String.format("%04d", produto.getCodigo()) + ";" + produto.getDescricao() + ";" + df.format(produto.getPreco()));
            fluxo.close();
            fluxoArquivo.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Exibe o cabeçalho do menu principal.
     */
    public static void headerMenu(){
        System.out.println();
        System.out.println((char)27 + "[36m*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println((char)27 + "[36m*      S U P E R  M E R C A D O  I F B A      *");
        System.out.println((char)27 + "[36m*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println((char)27 + "[32m[           TERMINAL ADMINISTRATIVO           ]");

    }

    /**
     * Exibe e processa as opções do menu principal.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     * @return Opção escolhida pelo usuário.
     */
    public static int opcoesMenuPrincipal(Scanner scan){

        int option = -1;

        do{
            try{
                System.out.println((char)27 + "[34m\n [ 1 ] Checkout");
                System.out.println((char)27 + "[34m [ 2 ] Cadastrar nova revista");
                System.out.println((char)27 + "[34m [ 3 ] Consultar revistas");
                System.out.println((char)27 + "[34m [ 4 ] Cadastrar novo produto");
                System.out.println((char)27 + "[34m [ 5 ] Sair");
                System.out.print((char)27 + "[97m\n-> Escolha uma opção: ");
                int opcaoUsuario = scan.nextInt();

                if((opcaoUsuario < 1) || (opcaoUsuario > 5)){
                    throw new Exception();
                }

                option = opcaoUsuario;
            }catch (InputMismatchException e){
                scan.nextLine();
                System.out.println((char)27 + "[31m\n[ERRO] Para esta operação, é solicitado um número inteiro [1 - 5]!");
            }catch(Exception e){
                scan.nextLine();
                System.out.println((char)27 + "[33m\n[WARNING] Escolha uma opção válida!");
            }

        }while(option == -1);

        scan.nextLine();
        return option;
    }

    /**
     * Exibe e processa as opções do menu de checkout.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     * @return Opção escolhida pelo usuário.
     */
    public static int menuCheckout(Scanner scan){

        int opt = -1;
        do{
            try{

                System.out.println((char)27 + "[32m[                   CHECKOUT                 ]");
                System.out.println((char)27 + "[34m [ 1 ] Adicionar item");
                System.out.println((char)27 + "[34m [ 2 ] Finalizar Checkout");
                System.out.print((char)27 + "[97m\n -> Opção: ");

                int opcaoUsuario = scan.nextInt();

                if((opcaoUsuario < 1) || (opcaoUsuario > 2)){
                    throw new Exception();
                }

                opt = opcaoUsuario;
            }catch (InputMismatchException e){
                scan.nextLine();
                System.out.println((char)27 + "[31m\n[ERRO] Para esta operação, é solicitado um número inteiro [1 - 2]!");
            }catch(Exception e){
                scan.nextLine();
                System.out.println((char)27 + "[33m\n[WARNING] Escolha uma opção válida!");
            }
        }while(opt == -1);

        scan.nextLine();
        return opt;
    }

    /**
     * Exibe e processa as opções do menu de consulta de revistas.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     * @return Opção escolhida pelo usuário.
     */
    public static int menuConsultaRevistas(Scanner scan){
        int opt = -1;
        do{
            try{
                System.out.println();
                System.out.println((char)27 + "[32m[                CONSULTAR  REVISTAS               ]");
                System.out.println((char)27 + "[34m [ 1 ] Consultar Última Revista empilhada");
                System.out.println((char)27 + "[34m [ 2 ] Consultar última revista desempilhada");
                System.out.println((char)27 + "[34m [ 3 ] Verificar tempo médio de permanencia de revistas");
                System.out.println((char)27 + "[34m [ 4 ] Relação entre Revistas Empilhadas e Ano de publicação");
                System.out.println((char)27 + "[34m [ 5 ] Relação entre Revistas Desempilhadas e Ano de publicação");
                System.out.println((char)27 + "[34m [ 6 ] Voltar ao Menu Principal");
                System.out.print((char)27 + "[97m\n -> Opção: ");

                int opcaoUsuario = scan.nextInt();

                if((opcaoUsuario < 1) || (opcaoUsuario > 6)){
                    throw new Exception();
                }

                opt = opcaoUsuario;
            }catch (InputMismatchException e){
                scan.nextLine();
                System.out.println((char)27 + "[31m\n[ERRO] Para esta operação, é solicitado um número inteiro [1 - 6]!");
            }catch(Exception e){
                scan.nextLine();
                System.out.println((char)27 + "[33m\n[WARNING] Escolha uma opção válida!");
            }
        }while(opt == -1);

        scan.nextLine();
        return opt;
    }

    /**
     * Exibe e processa as opções do menu de relatorio de revistas.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     * @return Opção escolhida pelo usuário.
     */
    public static int menuRelatorioRevistas(Scanner scan){
        int opt = -1;
        do{
            try{
                System.out.print((char)27 + "[34m Deseja uma relatório detalhado? [ 1 = Sim ] [ 2 = Não ]: ");
                int opcaoUsuario = scan.nextInt();
                if(opcaoUsuario < 1 || opcaoUsuario > 2){
                    throw new Exception();
                }
                scan.nextLine();
                opt = opcaoUsuario;
            }catch (InputMismatchException e){
                scan.nextLine();
                System.out.println((char)27 + "[31m\n[ERRO] Para esta operação, é solicitado um número inteiro [1 - 2]!");
            }catch(Exception e){
                scan.nextLine();
                System.out.println((char)27 + "[33m\n[WARNING] Escolha uma opção válida!");
            }
        }while(opt == -1);

        return opt;
    }

    /**
     * Processa o fluxo de checkout.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     */
    public static void checkout(Scanner scan){
        int opt;
        Pedido pedido = new Pedido();
        do{
            opt = menuCheckout(scan);

            switch (opt){
                case 1:

                    Produto produto = buscaProduto(scan, produtos);
                    if(produto != null){
                        adicionarItemCheckout(scan, pedido, produto);
                        System.out.println(pedido);
                    }
                    break;
                case 2:
                    double valorTotal = pedido.getValorTotalPedido();

                    System.out.println((char)27 + "[32m\nCheckout Finalizado! Compra no valor de R$ " + String.format("%.2f", valorTotal) + ".");
                    distribuiBrinde(pedido);
                    break;
            }
        }while(opt != 2);


    }

    /**
     * Consulta e exibe informações das revistas no sistema.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     */
    public static void consultarRevistas(Scanner scan){
        int opt = -1;
        Revista revista;
        do{
            opt = menuConsultaRevistas(scan);
            switch (opt){
                case 1:
                    revista = revistasEmpilhadas.peekFirst();
                    if(revista != null)
                        System.out.println(revista);
                    else
                        System.out.println((char)27 + "[33m\n[WARNING] Não há revistas empilhadas!");
                    break;
                case 2:
                    revista = revistasDesempilhadas.peekFirst();
                    System.out.println(revista);
                    break;
                case 3:
                    double somaTotal = 0;
                    for(Revista rev: revistasDesempilhadas){
                        somaTotal += rev.getHorarios().getTempoEmpilhado();
                    }

                    double media;
                    if (revistasDesempilhadas.size() > 0) {
                        media = somaTotal / revistasDesempilhadas.size();
                    } else {
                        media = 0;
                    }

                    int horas = (int) (media / 3600);
                    int minutos = (int) ((media % 3600) / 60);
                    int segundos = (int) (media % 60);
                    String tempo_formatado = String.format("%02d:%02d:%02d", horas, minutos, segundos);
                    System.out.println((char)27 + "[32m Tempo Médio de Permancencia: " + tempo_formatado);
                    break;
                case 4:
                    if(revistasEmpilhadas.size() > 0){
                        int optRelat = menuRelatorioRevistas(scan);
                        switch (optRelat){
                            case 1:
                                relatorioRevistaEmpilhadas(true);
                                break;
                            case 2:
                                relatorioRevistaEmpilhadas(false);
                                break;
                        }
                    }else{
                        System.out.println((char)27 + "[33m\n[WARNING] Não há revistas empilhadas!");
                    }

                    break;
                case 5:
                    if(revistasDesempilhadas.size() > 0){
                        int optRelatDes = menuRelatorioRevistas(scan);
                        switch (optRelatDes){
                            case 1:
                                relatorioRevistaDesempilhadas(true);
                                break;
                            case 2:
                                relatorioRevistaDesempilhadas(false);
                                break;
                        }
                    }else{
                        System.out.println((char)27 + "[33m\n[WARNING] Não há revistas empilhadas!");
                    }

                    break;

            }
        }while(opt != 6);

    }

    /**
     * Existe uma relação das Revistas Desempilhadas por ano de publicação
     *
     * @param detalhado Boleano para definir se a revista irá ser mostrada detalhadamente
     */
    public static void relatorioRevistaDesempilhadas(boolean detalhado){
        HashMap<Integer, List<Revista>> mapAnos= new HashMap<Integer, List<Revista>>();

        for(Revista revista: revistasDesempilhadas){
            if(!mapAnos.containsKey(revista.getAno())){
                mapAnos.put(revista.getAno(), new ArrayList<Revista>());
            }
            List<Revista> lista = mapAnos.get(revista.getAno());
            lista.add(revista);
            mapAnos.put(revista.getAno(), lista);
        }

        if(detalhado){
            mapAnos.forEach((key, value) -> {
                System.out.println((char)27 + "[95m--------------------");
                System.out.println("Revistas Ano de "+ key);
                System.out.println(value);
                System.out.println("Um total de " + value.size() + " revista(s) de " + key);
            });
        }else{
            mapAnos.forEach((key, value) -> {
                System.out.println((char)27 + "[95m-------------------------");
                System.out.println("Revistas Ano de "+ key);
                for(Revista revista: value){
                    System.out.println("Titulo da Revista: " + revista.getTitulo() + " | Numero Edição: " + revista.getNumerEdicao()+ " | Número do Volume: " + revista.getnVolume());
                }
                System.out.println("Um total de " + value.size() + " revista(s) de " + key);
            });
        }
    }

    /**
     * Existe uma relação das Revistas Empilhadas por ano de publicação
     *
     * @param detalhado Boleano para definir se a revista irá ser mostrada detalhadamente
     */
    public static void relatorioRevistaEmpilhadas(boolean detalhado){
        HashMap<Integer, List<Revista>> mapAnos= new HashMap<Integer, List<Revista>>();

        for(Revista revista: revistasEmpilhadas){
            if(!mapAnos.containsKey(revista.getAno())){
                mapAnos.put(revista.getAno(), new ArrayList<Revista>());
            }
            List<Revista> lista = mapAnos.get(revista.getAno());
            lista.add(revista);
            mapAnos.put(revista.getAno(), lista);
        }

        if(detalhado){
            mapAnos.forEach((key, value) -> {
                System.out.println((char)27 + "[95m--------------------");
                System.out.println("Revistas Ano de "+ key);
                System.out.println(value);
                System.out.println("Um total de " + value.size() + " revista(s) de " + key);
            });
        }else{
            mapAnos.forEach((key, value) -> {
                System.out.println((char)27 + "[95m-------------------------");
                System.out.println("Revistas Ano de "+ key);
                for(Revista revista: value){
                    System.out.println("Titulo da Revista: " + revista.getTitulo() + " | Numero Edição: " + revista.getNumerEdicao()+ " | Número do Volume: " + revista.getnVolume());
                }
                System.out.println("Um total de " + value.size() + " revista(s) de " + key);
            });
        }
    }

    /**
     * Cadastra um novo produto no sistema.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     */
    public static void cadastrarProdutos(Scanner scan){
        int code;
        double preco;
        String descricao;
        try{
            System.out.print((char)27 + "[97m\nDigite o codigo do produto [Inteiro]: ");
            code = scan.nextInt();
            scan.nextLine();
            if(produtos.containsKey(code)){
                System.out.println((char)27 + "[33m\n[AVISO] Código informado já consta em sua base de dados!");
            }else{
                System.out.print((char)27 + "[97m\nDigite a descrição do produto: ");
                descricao = scan.nextLine();

                System.out.print((char)27 + "[97m\nDigite o preco do produto [Formato: X,XX]: ");
                preco = scan.nextDouble();
                scan.nextLine();

                Produto produtoNovo = new Produto(code, descricao, preco);

                produtos.put(code, produtoNovo);
                gravaProdutos(produtoNovo);
            }

        }catch(InputMismatchException e){
            scan.nextLine();
            System.out.println((char)27 + "[31m\n[ERRO] Você digitou um valor inválido, tente novamente!");
        }
    }

    /**
     * Busca produto pelo HashMap
     *
     * @param scan Scanner para receber o codigo do produto pelo usuário
     * @param produtos HashMap contendo os produtos associados aos códigos
     * @return Produto encontrado ou Null
     */
    public static Produto buscaProduto(Scanner scan, HashMap<Integer, Produto> produtos){
        int code;
        while(true){
            try{
                System.out.print((char)27 + "[97m\nDigite o código do produto: ");
                code = scan.nextInt();
                scan.nextLine();
                break;
            }catch (InputMismatchException e){
                scan.nextLine();
                System.out.println((char)27 + "[31m\n[ERRO] Para esta operação, é solicitado um número inteiro [1 - 2]!");
            }
        }

        if(produtos.containsKey(code)){
            return produtos.get(code);
        }

        System.out.println((char)27 + "[33m\n[WARNING] Produto não encontrado!");

        return null;
    }

    /**
     * Adiciona um produto no pedido
     *
     * @param scan Scanner para solicitar informações ao usuário
     * @param pedido Pedido que receberá o produto
     * @param produto Produto a ser inserido no pedido
     */
    public static void adicionarItemCheckout(Scanner scan, Pedido pedido, Produto produto){

        int quantidade;
        while(true){
            try{
                System.out.print((char)27 + "[97m\nDigite a quantidade de itens deste produto: ");
                quantidade = scan.nextInt();
                scan.nextLine();
                break;
            }catch (InputMismatchException e){
                scan.nextLine();
                System.out.println((char)27 + "[31m\n[ERRO] Para esta operação, é solicitado um número inteiro [1 - 2]!");
            }
        }

        pedido.addItem(produto, quantidade);

    }

    /**
     * Cadastra uma nova revista no sistema.
     *
     * @param scan Scanner para leitura da entrada do usuário.
     * @param pilhaRevista Deque de revistas empilhadas.
     */
    public static void cadastrarRevista(Scanner scan, Deque<Revista> pilhaRevista){
        String titulo;
        int numerEdicao;
        int mes;
        int ano;
        int nVolume;
        try{
            System.out.print("Digite o título da revista: ");
            titulo = scan.nextLine();

            System.out.print("Digite o número de edição: ");
            numerEdicao = scan.nextInt();
            scan.nextLine();

            System.out.print("Digite o mês de publicação: ");
            mes = scan.nextInt();
            scan.nextLine();

            System.out.print("Digite o ano de publicação: ");
            ano = scan.nextInt();
            scan.nextLine();

            System.out.print("Digite o número do volume: ");
            nVolume = scan.nextInt();
            scan.nextLine();

            Revista revista = new Revista(titulo, numerEdicao, mes, ano, nVolume);
            revista.getHorarios().setHorarioEmpilhamento(new Date());

            pilhaRevista.addFirst(revista);
        }catch(InputMismatchException e){
            System.out.println((char)27 + "[31m\n[ERRO] Você digitou um valor inválido, tente novamente!");

        }
    }

    /**
     * Função para distribuição das revistas com base no pedido realizado.
     * A cada R$ 100.00 uma revista é entregue, caso a pilha possua.
     *
     * @param pedido Pedido que foi realizado pelos clientes
     */
    public static void distribuiBrinde(Pedido pedido){
        if(pedido.getValorTotalPedido() > 100.0){
            double qtdBrindes = pedido.getValorTotalPedido()/100;
            String stringValorTotal = String.format("%.2f", pedido.getValorTotalPedido());

            if(revistasEmpilhadas.isEmpty()){

                System.out.println((char)27 + "[33m\n[AVISO] Sinto muito! Não temos brindes no momento!");
            }else if(revistasEmpilhadas.size() >= qtdBrindes){
                System.out.print((char)27 + "[32m\nParabéns! Você ganhou "+ String.format("%.0f", qtdBrindes) + " revista(s) de brinde pois efetuou uma compra no valor de R$ " + stringValorTotal + "!");
                System.out.println(stringValorTotal);
                for(int i=1; i < qtdBrindes; i++){
                    Revista revista = revistasEmpilhadas.pollFirst();
                    try {
                        revista.getHorarios().setHorarioDesempilhamento(new Date());
                    } catch (Exception e) {
                        System.out.println();
                    }
                    revistasDesempilhadas.addFirst(revista);
                    System.out.println(revista);
                }
            }else{
                System.out.print((char)27 + "[32m\nParabéns! Você ganhou "+ revistasEmpilhadas.size() + " revista(s) de brinde pois efetuou uma compra no valor de R$ " + stringValorTotal + "!");
                while(!revistasEmpilhadas.isEmpty()){
                    Revista revista = revistasEmpilhadas.pollFirst();
                    try {
                        revista.getHorarios().setHorarioDesempilhamento(new Date());
                    } catch (Exception e) {
                        System.out.println();
                    }
                    revistasDesempilhadas.addFirst(revista);
                    System.out.println(revista);
                }
            }
        }
    }
}
