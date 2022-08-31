package br.com.springboot.curso_jdevtreinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdevtreinamento.model.Usuario;
import br.com.springboot.curso_jdevtreinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */

	@Autowired // Injeão de dependencia
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET) // a URl onde sera passado o parametro
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso Spring Boot API " + name + "!";
	}

	// Outra rota
	@RequestMapping(value = "/olamundo/{name}", method = RequestMethod.GET) // O que é passado na url é gravado no banco
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String name) {

		Usuario usuario = new Usuario();
		usuario.setNome(name);
		usuarioRepository.save(usuario); // save é um metodo que salva a informação, tem varios
		return "Olá Mundo: " + name;
	}
	//Listar todos
	// A Anotation GetMapping é a memsa que o RequestMapping a diferença é que a
	// GeTMapping não precisa passar o parametro
	@GetMapping(value = "/listartodos") // Coloque a rota -> Primeiro Método de API, BUSCAR TODOS
	@ResponseBody // Retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll(); // executa a consulta do banco de dados
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);// Retorna a lista em JSON

	}

	// salvar no banco
	@PostMapping(value = "salvar") // Mapeia a URL
	@ResponseBody // Descrição da resposta
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // recebe os dados para salvar
		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}
	
	
	//ATUULZIAR 
	@PutMapping(value = "atualizar") // Mapeia a URL
	@ResponseBody // Descrição da resposta
	public ResponseEntity<Usuario> atualizar (@RequestBody Usuario usuario) { // recebe os dados para salvar
		Usuario user = usuarioRepository.saveAndFlush(usuario); //salva e roda diretamente no banco de dados

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}

	// Deletando pelo id
	@DeleteMapping(value = "delete") // Mapeia a url
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long iduser) {

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("Usuario " + iduser + " deletado com sucesso", HttpStatus.OK);

	}

	// Buscar pelo id
	@GetMapping(value="buscaruserid")//Mapeia a url
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name="iduser") Long iduser){ //receba os dados da consulta
    	
		Usuario usuario = usuarioRepository.findById(iduser).get();   	
		
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
	
	
	

}
