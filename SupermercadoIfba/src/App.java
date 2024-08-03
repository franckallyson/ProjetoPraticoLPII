import java.io.*;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.*;

public class App {
    public static final String CAMINHO_ARQUIVO_PRODUTOS = "./SupermercadoIfba/Arquivos/produtos.txt";
    public static final String CAMINHO_ARQUIVO_REV = "./SupermercadoIfba/Arquivos/pilhas_revistas.txt";
    public static List<Produto> produtos = null;
    public static Deque<Revista> revistasEmpilhadas = new ArrayDeque<Revista>();
    public static Deque<Revista> revistasDesempilhadas = new ArrayDeque<Revista>();

    public static void main(String args[]){
        carregaRevistas();
        produtos = carregaProdutos();
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
                    System.out.println("Revistas Empilhadas: \n" + revistasEmpilhadas);
                    System.out.println("Revistas Desempilhadas: \n" + revistasDesempilhadas);
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }while(optionMenu != 5);

        scan.close();
        gravaRevistas(revistasEmpilhadas, revistasDesempilhadas);
    }

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
    public static List<Produto> carregaProdutos(){
        List<Produto> produtos = new ArrayList<Produto>();
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
            produtos.add(produto);
        }

        scan.close();

        return produtos;
    }

    public static void headerMenu(){

        System.out.println((char)27 + "[36m*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println((char)27 + "[36m*      S U P E R  M E R C A D O  I F B A      *");
        System.out.println((char)27 + "[36m*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println((char)27 + "[32m[           TERMINAL ADMINISTRATIVO           ]");

    }

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
                    System.out.print((char)27 + "[32m\nCheckout Finalizado");
                    distribuiBrinde(pedido);
                    break;
            }
        }while(opt != 2);


    }

    public static Produto buscaProduto(Scanner scan, List<Produto> produtos){
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

        for(Produto produto: produtos){
            if(produto.getCodigo() == code){
                return produto;
            }
        }

        System.out.println((char)27 + "[33m\n[WARNING] Produto não encontrado!");

        return null;
    }

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

    public static void distribuiBrinde(Pedido pedido){
        if(pedido.getValorTotalPedido() > 100.0){
            double qtdBrindes = pedido.getValorTotalPedido()/100;
            if(revistasEmpilhadas.isEmpty()){
                System.out.println((char)27 + "[33m\n[AVISO] Sinto muito! Não temos brindes no momento!");
            }else if(revistasEmpilhadas.size() > qtdBrindes){
                System.out.print((char)27 + "[32m\nParabéns! Você ganhou "+ String.format("%.0f", qtdBrindes) + " revista(s) de brinde pois efetuou uma compra ");
                for(int i=1; i < qtdBrindes; i++){
                    Revista revista = revistasEmpilhadas.pollFirst();
                    revistasDesempilhadas.addFirst(revista);
                    System.out.println(revista);
                }
            }else{
                System.out.print((char)27 + "[32m\nParabéns! Você ganhou "+ revistasEmpilhadas.size() + " revista(s) de brinde pois efetuou uma compra ");
                while(!revistasEmpilhadas.isEmpty()){
                    Revista revista = revistasEmpilhadas.pollFirst();
                    revistasDesempilhadas.addFirst(revista);
                    System.out.println(revista);
                }
            }

        }
    }
}
