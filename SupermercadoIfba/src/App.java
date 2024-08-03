import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.*;

public class App {
    public static final String CAMINHO_ARQUIVO_PRODUTOS = "./SupermercadoIfba/Arquivos/produtos.txt";
    public static List<Produto> produtos = null;

    public static void main(String args[]){
        produtos = carregaProdutos();
        int optionMenu;
        do{
            headerMenu();
            optionMenu = opcoesMenuPrincipal();

            switch (optionMenu){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }while(optionMenu != 5);

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

    public static int opcoesMenuPrincipal(){
        Scanner scan = new Scanner(System.in);
        int option = -1;

        do{
            try{
                System.out.println((char)27 + "[34m\n [ 1 ] Checkout");
                System.out.println((char)27 + "[34m [ 2 ] Cadastrar nova revista");
                System.out.println((char)27 + "[34m [ 3 ] Consultar revistas");
                System.out.println((char)27 + "[34m [ 4 ] Cadastrar novo produto");
                System.out.println((char)27 + "[34m [ 5 ] Cadastrar novo produto");
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
                System.out.println((char)27 + "[33m\n[WARNING] Escolha uma opção válida!");
            }

        }while(option == -1);

        scan.close();

        return option;
    }
}
