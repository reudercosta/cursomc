package com.reudercosta.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reudercosta.cursomc.domain.ItemPedido;
import com.reudercosta.cursomc.domain.PagamentoComBoleto;
import com.reudercosta.cursomc.domain.Pedido;
import com.reudercosta.cursomc.domain.enums.EstadoPagamento;
import com.reudercosta.cursomc.repositories.ClienteRepository;
import com.reudercosta.cursomc.repositories.ItemPedidoRepository;
import com.reudercosta.cursomc.repositories.PagamentoRepository;
import com.reudercosta.cursomc.repositories.PedidoRepository;
import com.reudercosta.cursomc.repositories.ProdutoRepository;
import com.reudercosta.cursomc.services.excpetions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	

	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: " + id + "Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(obj.getCliente().getId()));
			ip.setPreco(ip.getProduto().getPreço());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		
		
		System.out.println("==>"+obj);
		emailService.sendOrderConfirmationEmail(obj);
		
		return obj;
	}
}
