package br.com.portifolio.pessoa;

import br.com.portifolio.PortifolioApplication;
import br.com.portifolio.config.FlywayConfig;
import br.com.portifolio.model.Pessoa;
import br.com.portifolio.repository.PessoaRepository;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PortifolioApplication.class)
@AutoConfigureMockMvc
@DisplayName("Teste para o repositório de pessoas.")
@Import(FlywayConfig.class)
@TestPropertySource(locations = "classpath:teste.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private Flyway flyway;

	DadosPessoaTeste factory = new DadosPessoaTeste();

	@Test
	@Order(1)
	@DisplayName("Persistencia com sucesso.")
	void saveSucesso() {

		Pessoa pessoa = factory.criarPessoa1();
		Pessoa pessoaSalva = repository.save(pessoa);

		assertThat(pessoaSalva).isNotNull();
		assertThat(pessoaSalva.getId()).isNotNull();
		assertThat(pessoaSalva.getNome()).isEqualTo(pessoa.getNome());
	}

	@Test
	@Order(2)
	@DisplayName("Buscar pessoa por ID com sucesso.")
	void findByIdSucesso() {
		Pessoa pessoaSalva = repository.save(factory.criarPessoa1());

		Optional<Pessoa> pessoaRecuperada = repository.findById(pessoaSalva.getId());

		assertThat(pessoaRecuperada).isPresent();
		pessoaRecuperada.ifPresent(pessoa -> {
			assertThat(pessoa.getId()).isEqualTo(pessoaSalva.getId());
		});
	}

	@Test
	@Order(3)
	@DisplayName("Atualizar pessoa com sucesso.")
	void updateSucesso() {
		Optional<Pessoa> pessoaSalvada = repository.findById(factory.criarPessoa1().getId());

		pessoaSalvada.ifPresent(pessoa -> {
			pessoa.setNome("Thiago Atualizado");
			Pessoa pessoaAtualizada = repository.save(pessoa);

			assertThat(pessoaAtualizada).isNotNull();
			assertThat(pessoaAtualizada.getId()).isEqualTo(pessoa.getId());
			assertThat(pessoaAtualizada.getNome()).isEqualTo("Thiago Atualizado");
		});
	}

	@Test
	@Order(4)
	@DisplayName("Buscar pessoa por ID que não existe.")
	void findByIdNaoExistente() {
		Throwable e = Assertions.catchThrowable(() -> repository.findById(DadosPessoaTeste.ID_PESSOA_FAKE).get());
		assertThat(e).isInstanceOf(NoSuchElementException.class).hasMessageContaining("No value present");
	}

	@Test
	@Order(5)
	@DisplayName("Buscar todos os cadastros de pessoa com sucesso.")
	void findAllSucesso() {

		Pessoa pessoaSalva = repository.save(factory.criarPessoa1());
		Pessoa pessoaSalva2 = repository.save(factory.criarPessoa2());

		ArrayList<Pessoa> listaResultado = new ArrayList<>();
		listaResultado.add(pessoaSalva);
		listaResultado.add(pessoaSalva2);

		List<Pessoa> lista = repository.findAll();
		assertThat(lista).isEqualTo(listaResultado);
	}

	@Test
	@Order(6)
	@DisplayName("Deletar Pessoa com Sucesso.")
	void deleteSucesso() {
		Pessoa pessoaSalva = repository.save(factory.criarPessoa1());
		repository.delete(pessoaSalva);
		Optional<Pessoa> pessoaDeletada = repository.findById(pessoaSalva.getId());
		assertThat(pessoaDeletada).isEmpty();
	}

	@AfterAll
	void tearDown() {
		//flyway.clean();
	}
}
