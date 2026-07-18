package libraryapi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import libraryapi.model.Autor;
import libraryapi.repository.AutorRepository;

@SpringBootTest
public class TestAutorRepository {
	@Autowired
	private AutorRepository autorRepository;
	
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
}
