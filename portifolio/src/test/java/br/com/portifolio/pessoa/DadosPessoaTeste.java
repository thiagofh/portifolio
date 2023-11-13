package br.com.portifolio.pessoa;

import br.com.portifolio.model.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class DadosPessoaTeste {

	public static final Long ID_PESSOA_1 = 1L;
	public static final Long ID_PESSOA_2 = 2L;
	public static final Long ID_PESSOA_FAKE = 3L;

	public Pessoa criarPessoa1() {

		Pessoa pessoa = new Pessoa();
		pessoa.setId(ID_PESSOA_1);
		pessoa.setNome("Thiago");
		pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));
		pessoa.setCpf("123.456.789-01");
		pessoa.setFuncionario(Boolean.TRUE);

		return pessoa;
	}
	
	public Pessoa criarPessoa2() {

		Pessoa pessoa = new Pessoa();
		pessoa.setId(ID_PESSOA_2);
		pessoa.setNome("Ana");
		pessoa.setDataNascimento(LocalDate.of(1980, 1, 1));
		pessoa.setCpf("123.456.789-02");
		pessoa.setFuncionario(Boolean.FALSE);

		return pessoa;
	}

}
