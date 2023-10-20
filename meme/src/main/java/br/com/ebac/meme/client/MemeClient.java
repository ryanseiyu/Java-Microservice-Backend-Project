package br.com.ebac.meme.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ebac.meme.entity.CategoriaMeme;
import br.com.ebac.meme.entity.Usuario;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class MemeClient {
    @Autowired
    private ICategoriaClient iCategoriaClient;

    @Autowired
    private IUsuarioClient iUsuarioClient;

    @FeignClient(name = "categoria")
    interface ICategoriaClient {
        @RequestMapping(path = "/categorias-meme", method = RequestMethod.GET)
        @ResponseBody
        Iterable<CategoriaMeme> encontrarTodasCategorias();

        @RequestMapping(path = "/categorias-meme/{id}", method = RequestMethod.GET)
        @ResponseBody
        CategoriaMeme encontrarCategoriaPorId(@PathVariable("id") Long id);
    }

    @FeignClient(name = "usuario")
    interface IUsuarioClient {
        @RequestMapping(path = "/usuarios", method = RequestMethod.GET)
        @ResponseBody
        Iterable<Usuario> encontrarTodos();

        @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.GET)
        @ResponseBody
        Usuario encontrarPorId(@PathVariable("id") Long id);
    }

    public Iterable<CategoriaMeme> EncontrarTodasCategorias() {
        return iCategoriaClient.encontrarTodasCategorias();
    }

    public Iterable<Usuario> EncontrarTodosUsuarios() {
        return iUsuarioClient.encontrarTodos();
    }

    public Usuario EncontrarUsuarioPorId(Long id) {
        return iUsuarioClient.encontrarPorId(id);
    }

    public CategoriaMeme EncontrarCategoriaPorID(Long id) {
        return iCategoriaClient.encontrarCategoriaPorId(id);
    }
}
