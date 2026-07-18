package libraryapi;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import libraryapi.enums.GeneroLivro;
import libraryapi.model.Autor;
import libraryapi.model.Livro;
import libraryapi.repository.AutorRepository;
import libraryapi.repository.LivroRepository;

@SpringBootTest
public class TestLivroRepository {
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AutorRepository autorRepository;
	
	@Test
	public void testSalvarLivro() {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.AUGUST, 17);
		Date datapublicacao = cal.getTime();
		
		Autor autor = autorRepository.findById(2L).orElse(null);
		
		Livro livro = new Livro();
		livro.setDataPublicacao(datapublicacao);
		livro.setGenero(GeneroLivro.FANTASIA);
		livro.setIsbn("89YYYY67");
		livro.setPreco(100.0);
		livro.setTitulo("Meu primeiro Livro");
		livro.setAutor(autor);
		
		livroRepository.save(livro);
		
		
	}
	
	@Test
	public void testSalvarLivroComNovoAutor() {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.AUGUST, 17);
		Date datapublicacao = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(1900, Calendar.JANUARY, 9);
		Date datanacimento = cal2.getTime();
		
		Autor autor = new Autor();
		autor.setData_nascimento(datanacimento);
		autor.setNome("JoseMaria");
		autor.setNacionalidade("Portuguesa");
		autor = autorRepository.save(autor);
		
		Livro livro = new Livro();
		livro.setDataPublicacao(datapublicacao);
		livro.setGenero(GeneroLivro.FICCAO);
		livro.setIsbn("89YYYY00");
		livro.setPreco(100.0);
		livro.setTitulo("Meu Segundo Livro");
		livro.setAutor(autor);
		
		livroRepository.save(livro);
		
		
	}
	@Test
	@Transactional
	public void testBuscacomLazer() {
		//usando esse transactional do spring mesmo com a janela aberta e estando em laz ele consegue buscar o autor
		Livro livro = livroRepository.findById(2L).orElse(null);
		
		System.out.println("Livro Titulo:");
		System.out.println(livro.getTitulo());
		System.out.println("Autor:");
		System.out.println(livro.getAutor().getNome());
	}
}
