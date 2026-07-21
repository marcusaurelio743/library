package libraryapi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import libraryapi.enums.GeneroLivro;
import libraryapi.model.Autor;
import libraryapi.model.Livro;
import libraryapi.repository.AutorRepository;
import libraryapi.repository.LivroRepository;

@SpringBootTest
public class TestAutorRepository {
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	@Test
	public void testSalvar() {
		Calendar data = Calendar.getInstance();
		data.set(1997,Calendar.APRIL,13);
		 Date dataNascimento = data.getTime();
		 
		Autor autor = new Autor();
		autor.setNome("Maria dos Santos");
		autor.setNacionalidade("Brasileira");
		autor.setData_nascimento(dataNascimento);
		Autor autorSalvo = autorRepository.save(autor);
		
		System.out.println(autorSalvo);
	}
	@Test
	public void atualizarTest() {
		Optional<Autor> autor =autorRepository.findById(2L);
		if(autor.isPresent()) {
			autor.get().setNacionalidade("Americana");
			autor.get().setNome("Josep American");
			autorRepository.save(autor.get());
			
		}
	}
	
	@Test
	public void testBuscarTodos() {
		List<Autor> autores = autorRepository.findAll();
		
		autores.forEach(System.out::println);
	}
	
	@Test
	public void testContarQuantidade() {
		System.out.println("Quantidade de autores registrado: "+autorRepository.count());
	}
	
	@Test
	public void testDeletar() {
		System.out.println("Apagando um registro!!!");
		autorRepository.deleteById(1L);
	}
	
	@Test
	public void InsertAutorLivros() {
		Calendar data = Calendar.getInstance();
		data.set(1997,Calendar.APRIL,13);
		 Date dataNascimento = data.getTime();
		 
		Autor autor = new Autor();
		autor.setNome("Jose dos santos Pereira");
		autor.setNacionalidade("Brasileira");
		autor.setData_nascimento(dataNascimento);
		Autor autorSalvo = autorRepository.save(autor);
		
		Livro livro = new Livro();
		livro.setAutor(autorSalvo);
		livro.setDataPublicacao(Calendar.getInstance().getTime());
		livro.setGenero(GeneroLivro.MISTERIO);
		livro.setIsbn("YYYG&*");
		livro.setPreco(34.6);
		livro.setTitulo("Capa de misterio");
		
		Livro livro2 = new Livro();
		livro2.setAutor(autorSalvo);
		livro2.setDataPublicacao(Calendar.getInstance().getTime());
		livro2.setGenero(GeneroLivro.FICCAO);
		livro2.setIsbn("YYYG&*");
		livro2.setPreco(34.6);
		livro2.setTitulo("Capa de Fição");
		
		autorSalvo.getLivros().add(livro);
		autorSalvo.getLivros().add(livro2);
		
		livroRepository.saveAll(autorSalvo.getLivros());
		
	}
}
