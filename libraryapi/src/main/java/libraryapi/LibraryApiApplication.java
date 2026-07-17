package libraryapi;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import libraryapi.model.Autor;
import libraryapi.repository.AutorRepository;

@SpringBootApplication
public class LibraryApiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(LibraryApiApplication.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);
		salvandoAutor(repository);
	}
	
	public static void salvandoAutor(AutorRepository autorRepository) {
		Calendar data = Calendar.getInstance();
		data.set(1980,Calendar.JULY,14);
		 Date dataNascimento = data.getTime();
		 
		Autor autor = new Autor();
		autor.setNome("Jose da Silva");
		autor.setNacionalidade("Brasileira");
		autor.setData_nascimento(dataNascimento);
		Autor autorSalvo = autorRepository.save(autor);
		
		System.out.println(autorSalvo);
	}

}
