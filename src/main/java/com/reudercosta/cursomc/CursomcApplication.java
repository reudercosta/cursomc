package com.reudercosta.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reudercosta.cursomc.domain.Categoria;
import com.reudercosta.cursomc.domain.Cidade;
import com.reudercosta.cursomc.domain.Cliente;
import com.reudercosta.cursomc.domain.Endereco;
import com.reudercosta.cursomc.domain.Estado;
import com.reudercosta.cursomc.domain.ItemPedido;
import com.reudercosta.cursomc.domain.Pagamento;
import com.reudercosta.cursomc.domain.PagamentoComBoleto;
import com.reudercosta.cursomc.domain.PagamentoComCartão;
import com.reudercosta.cursomc.domain.Pedido;
import com.reudercosta.cursomc.domain.Produto;
import com.reudercosta.cursomc.domain.enums.EstadoPagamento;
import com.reudercosta.cursomc.domain.enums.TipoCliente;
import com.reudercosta.cursomc.repositories.CategoriaRepository;
import com.reudercosta.cursomc.repositories.CidadeRepository;
import com.reudercosta.cursomc.repositories.ClienteRepository;
import com.reudercosta.cursomc.repositories.EnderecoRepository;
import com.reudercosta.cursomc.repositories.EstadoRepository;
import com.reudercosta.cursomc.repositories.ItemPedidoRepository;
import com.reudercosta.cursomc.repositories.PagamentoRepository;
import com.reudercosta.cursomc.repositories.PedidoRepository;
import com.reudercosta.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}
