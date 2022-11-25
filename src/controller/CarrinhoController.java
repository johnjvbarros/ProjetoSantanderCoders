package controller;

import model.Carrinho;
import view.CarrinhoView;
import view.LojaView;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoController {

    private CarrinhoView carrinhoView;
    private LojaController lojaController;
    private LojaView view;

    public void menuCarrinho() {

        carrinhoView = new CarrinhoView();
        view = new LojaView();
        lojaController = new LojaController();
        Boolean continuar = true;

        while (continuar) {
            String opcao = carrinhoView.opcao();
            switch (opcao) {
                case "1" -> adicionarCarrinho();
                case "2" -> view.listarProduto();
                case "3" -> lojaController.pesquisarProduto();
                case "4" -> listarCarrinho();
                case "5" -> retirarProduto();
//                case "6" -> finalizaCompra();
                case "0" -> {
                    System.out.println("Saindo!");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void retirarProduto() {
        carrinhoView.listarCarrinhoView();
        Integer id = Integer.valueOf(view.pegarIdProduto());
        Map<String, Object> remover = Carrinho.listaCompra.get(id);
        Carrinho.listaCompra.remove(remover);
        System.out.println("Item Removido do Carrinho!\n");
    }

    private void adicionarCarrinho() {

        String id = view.pegarIdProduto();
        Integer qtde = Integer.valueOf(carrinhoView.pegarQuantidade());
        Map<String, Object> produto = new HashMap<>();
        LojaController lojaController = new LojaController();
        produto = lojaController.pesquisaProdutoId(Integer.valueOf(id));
        if(produto.isEmpty()){
            carrinhoView.produtoNaoEncontrado();
            return;
        }
        int qtdeLoja = (Integer)produto.get("quantidade");
        if(qtdeLoja < qtde){
            carrinhoView.qtdeAcimaLimite(qtdeLoja);
            return;
        }
        produto.put("quantidade",qtde);

        Carrinho.listaCompra.add(produto);
        System.out.println("Produto: " + produto.get("produto") + " adicionado no carrinho");

    }

    private void listarCarrinho() {

        carrinhoView.listarCarrinhoView();

    }
}
