
---

# Supermercado Ifba - Sistema de Checkout e Cadastro de Revistas

## Descrição do Projeto

Este projeto implementa um sistema para um supermercado que oferece uma revista de brinde para cada R$ 100,00 em compras realizadas. As revistas são oriundas de uma coleção particular dos proprietários e devem ser cadastradas e gerenciadas pelo sistema. O projeto permite a manipulação da pilha de revistas e o processamento de compras para determinar a quantidade de revistas a ser dada como brinde.

## Funcionalidades

- **Cadastro de Produtos**: Gerencia um catálogo de mercadorias com preços fixos.
- **Checkout de Compras**: Permite a realização de compras, calculando o valor total e determinando a quantidade de revistas que o cliente ganhará como brinde.
- **Cadastro de Revistas**: Adiciona novas revistas ao estoque e gerencia a pilha de revistas disponíveis.
- **Relatórios de Revistas**: Gera relatórios detalhados e resumidos sobre as revistas empilhadas e desempilhadas.

## Dados dos Produtos

O sistema inclui um catálogo de produtos com os seguintes itens e preços:

```
0001;Açúcar Refinado 1Kg;2,15
0002;Detergente Líquido Incolor 500ml;1,39
0003;Nuggets Tradicional 300g;5,98
0004;Água Sanitária 2L;3,98
0005;Extrato de Tomate 340g;3,74
0006;Óleo de Soja 900ml;3,10
0007;Amaciante 2L;11,58
0008;Farinha de Trigo 1Kg;2,28
0009;Pão de Forma Tradicional 500g;5,85
0010;Arroz Branco 5Kg;13,98
0011;Feijão 1Kg;3,28
0012;Papel Higiênico 30m 4un;6,45
0013;Azeite Tipo Extravirgem 500ml;17,90
0014;Gim 750ml;89,90
0015;Refrigerante 1,5L;5,49
0016;Barra de Chocolate 135g;5,99
0017;Hambúrguer de Frango e Boi 672g;12,75
0018;Requeijão Cremoso Tradicional 200g;2,99
0019;Bolacha Recheada de Chocolate 136g;1,88
0020;Iogurte de Morango 40g 8un;3,99
0021;Sabão em Pó 1Kg;7,99
0022;Bombons Sortidos 300g;7,98
0023;Lã de Aço 60g 8un;1,86
0024;Sabonete 90g;1,22
0025;Café em Pó Tradicional 500g;11,88
0026;Leite Condensado 395g;3,98
0027;Sal Refinado 1Kg;2,19
0028;Cerveja 350ml;2,39
0029;Leite UHT Integral 1L;2,18
0030;Sorvete Napolitano 1,5L;17,50
0031;Cerveja 500ml;12,70
0032;Macarrão com Ovos Parafuso 500g;2,65
0033;Suco Pronto para Consumo Néctar 1L;4,92
0034;Creme de Avelã 350g;17,90
0035;Macarrão Espaguete 500g;6,49
0036;Uísque 12 Anos 750ml;117,90
0037;Creme de Leite 200g;2,49
0038;Maionese 500g;4,98
0039;Vodca 998ml;29,90
```

## Requisitos

- Java 8 ou superior
- IDE de sua escolha (e.g., IntelliJ IDEA, Eclipse)
- JDK configurado

## Executando o Projeto

1. **Clonar o repositório**:
   ```sh
   git clone https://github.com/seu_usuario/supermercado-ifba.git
   ```

2. **Compilar o projeto**:
   Navegue até o diretório do projeto e compile com:
   ```sh
   javac -d bin src/*.java
   ```

3. **Executar o projeto**:
   Navegue até o diretório `bin` e execute:
   ```sh
   java App
   ```

## Estrutura do Código

- **`App`**: Classe principal que gerencia o fluxo do sistema, incluindo a carga e gravação de dados, manipulação de revistas e processo de checkout.
- **`Produto`**: Representa um produto disponível para compra, com atributos como código, descrição e preço.
- **`Revista`**: Representa uma revista, incluindo título, número de edição, mês, ano de publicação e número de volume.
- **`Pedido`**: Gerencia a lista de itens comprados e calcula o valor total do pedido.

## Contribuições

Sinta-se à vontade para contribuir para o projeto, abrir issues ou enviar pull requests.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
