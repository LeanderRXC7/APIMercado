package br.cefet.mercado.controller;

import br.cefet.mercado.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private long count = 0;
    private List<Produto> produtos = new ArrayList<>();

    @GetMapping({"", "/"})
    public List<Produto> getProdutos() {
        return produtos;
    }

    @GetMapping("/{id}")
    public Produto getProdutosById(@PathVariable long id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    @PostMapping({"", "/"})
    public Produto createProduto(@RequestBody Produto produto) {
        produtos.add(produto);
        count++;
        return produto;
    }

    @DeleteMapping("/{id}")
    public boolean deleteProdutoById(@PathVariable Long id) {
        Produto produto = getProdutosById(id);
        count--;
        return produtos.remove(produto);
    }

    @PutMapping({"", "/"})
    public Produto alterarProduto(@RequestBody Produto produto) {
        Produto prod = getProdutosById(produto.getId());
        prod.setNome(produto.getNome());
        prod.setValor(produto.getValor());
        prod.setDisponivel(produto.isDisponivel());
        return prod;
    }

    @GetMapping({"/searchtext", "/searchtext/"})
    public List<Produto> getProdutosBySearchTextVazio() {
        return produtos;
    }

    @GetMapping("/searchtext/{searchText}")
    public List<Produto> getProdutosBySearchText(@PathVariable String searchText) {
        List<Produto> encontrados = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getNome().toLowerCase().contains(searchText.toLowerCase())) {
                encontrados.add(produto);
            }
        }
        return encontrados;
    }

}
