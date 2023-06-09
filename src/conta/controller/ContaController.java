package conta.controller;
import conta.repository.ContaRepository;
import java.util.ArrayList;
import conta.model.Conta;

public class ContaController implements ContaRepository{
	private ArrayList<Conta> listaContas = new ArrayList<>();
	int numero = 0;
	
	@Override
	public void procurarPorNumero (int numero) {
		var conta = buscarNaCollection(numero);
		if (conta != null)
			conta.visualizar();
		else
			System.out.println("\nA Conta número: " + numero + "não foi encontrada!");
	}
	
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}
	}
	
	public void cadastrar(Conta conta){
		listaContas.add(conta);
		System.out.println("\nA conta número: " + conta.getNumero() + " foi criada com sucesso!");
	}
	
	public int gerarNumero() {
		return ++numero;
	}
	
	public void atualizar(Conta conta) {
		var buscarConta = buscarNaCollection(conta.getNumero());
		if(buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), conta);
			System.out.println("\nA Conta número: " + conta.getNumero() + " foi atualizada com sucesso!");
		}else
			System.out.println("\nA Conta número: " + conta.getNumero() + " não foi encontrada");
	}
	
	public Conta buscarNaCollection(int numero) {
		for(var conta:listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
	
	public int retornaTipo (int numero) {
		for (var conta : listaContas) {
			if(conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}
		return 0;
	}
	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(listaContas.remove(conta) == true) {
				System.out.println("\nA Conta número: " + numero + "foi deletada com sucesso!");
			} else {
				System.out.println("\nA Conta número: " + numero + "não foi encontrada!");
			}
		}
	}
	
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if (conta.sacar(valor) == true) {
				System.out.println("\nO Saque na Conta Número: " + numero + " foi efetuado com sucesso!");
			} else {
				System.out.println("nA Conta número	" + numero + "não foi encontrada!");
			}
		}
	}
	
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			conta.depositar(valor); 
				System.out.println("\nO Depósito na Conta número " + numero + " foi efetuado com sucesso!");
			}else {
				System.out.println("\nA Conta número " + numero + " não foi encontrada ou a conta destino não é uma conta corrente!");
			}
		}
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if(contaOrigem != null && contaDestino != null) {
			if (contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA transferência foi efetuada com sucesso!");
			}else {
				System.out.println("\nA Conta de origem e/ou Destino não foram encontradas!");
				
			}
		}
	}

}
