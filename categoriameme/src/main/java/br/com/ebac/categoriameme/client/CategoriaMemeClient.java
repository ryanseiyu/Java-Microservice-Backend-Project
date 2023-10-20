package br.com.ebac.categoriameme.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ebac.categoriameme.entity.Usuario;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class CategoriaMemeClient {
    @Autowired
    private CategoriaClient categoriaClient;

    @FeignClient(name = "usuario")
    interface CategoriaClient {
        @RequestMapping(path = "/usuarios", method = RequestMethod.GET)
        @ResponseBody
        Iterable<Usuario> encontrarTodos();

        @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.GET)
        @ResponseBody
        Usuario encontrarPorId(@PathVariable("id") Long id);
    }

    public Iterable<Usuario> EncontrarTodos() {
        return categoriaClient.encontrarTodos();
    }

    public Usuario EncontrarPorId(Long id) {
        return categoriaClient.encontrarPorId(id);
    }
}
