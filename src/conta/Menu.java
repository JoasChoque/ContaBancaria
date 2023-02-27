package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		Scanner leia = new Scanner(System.in);
		
		int opcao,numero,agencia,tipo,aniversario,numeroDestino;
		String titular;
		float saldo,limite,valor;
		
		System.out.println("\nCriar contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 126, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		
		while(true) {
			System.out.println(Cores.TEXT_YELLOW+Cores.ANSI_BLACK_BACKGROUND+
					"***********************************************");
			System.out.println("                                               ");
			System.out.println("           BANCO DO BRAZIL COM Z               ");
			System.out.println("                                               ");
			System.out.println("***********************************************");
			System.out.println("                                               ");
			System.out.println("         1 - Criar conta                       ");
			System.out.println("         2 - Listar todas as Contas            ");
			System.out.println("         3 - Buscar Conta por Número           ");
			System.out.println("         4 - Atualizar dados da Conta          ");
			System.out.println("         5 - Apagar Conta                      ");
			System.out.println("         6 - Sacar                             ");
			System.out.println("         7 - Depositar                         ");
			System.out.println("         8 - Transferir valores entre Contas   ");
			System.out.println("         9 - Sair                              ");
			System.out.println("                                               ");
			System.out.println("***********************************************");
			System.out.println("Entre com a opção desejada:                    ");
			System.out.println("                                               "+Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
				
			}catch(InputMismatchException e) {
				System.out.println("\nDigite valores inteiros");
				leia.nextLine();
				opcao = 0;
			}
			
			
			if(opcao == 9) {
				System.out.println("\n Banco do Brazil com Z - O seu Futuro começa aqui!");
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Criar Contas \n\n");
				
				System.out.println("Digite o Número da Agência: ");
				agencia = leia.nextInt();
				
				System.out.println("Digite o Nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o tipo da Conta (1- CC ou 2- CP): ");
					tipo = leia.nextInt();
				}while(tipo<1 && tipo>2);
				
				System.out.println("Digite o saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				
				switch(tipo) {
					case 1 ->{
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia,tipo,titular,saldo,limite));
					}
					
					case 2 ->{
						System.out.println("Digite o dia do Aniversário da conta:");
						aniversario = leia.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}
				keyPress();
				break;
				
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Listar todas as Contas");
				contas.listarTodas();
				keyPress();
				break;
				
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Consultar dados da Conta - por número \n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);
				
				keyPress();
				break;
				
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o numero da Conta: ");
				numero = leia.nextInt();
				
				if(contas.buscarNaCollection(numero)!=null) {
					System.out.println("Digite o número da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o saldo da Conta (R$): ");
					saldo = leia.nextFloat();
					
					tipo = contas.retornaTipo(numero);
					
					switch(tipo) {
					case 1->{
						System.out.println("Digite o limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero,agencia,tipo,titular,saldo,limite));
					}
					
					case 2 ->{
						System.out.println("Digite o dia de aniversário da conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					
					default ->{
						System.out.println("Tipo de conta inválido");
					}
					
					}
				}
				else {
					System.out.println("Conta não encontrada");
				}
				keyPress();
				break;
				
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Apagar a Conta\n\n");
				
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				keyPress();
				break;
				
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Saque\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$): ");
					valor = leia.nextFloat();
				}while(valor<=0);
				
				contas.sacar(numero, valor);
				
				keyPress();
				break;
				
			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Depósito\n\n");
				
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor do depósito (R$): ");
					valor = leia.nextFloat();
				}while(valor<=0);
				
				contas.depositar(numero, valor);
				
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Transferência entre Contas\n\n");
				
				System.out.println("Digite o número da Conta de Origem: ");
				numero = leia.nextInt();
				
				System.out.println("Digite o número da Conta de Destino: ");
				numeroDestino=leia.nextInt();
				
				do {
					System.out.println("Digite o valor da transferência: ");
					valor = leia.nextFloat();
				}while(valor<=0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD+"\nOpção Inválida!\n");
				keyPress();
				break;
			}
		}

	}
	
	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET+"\n\nPressione Enter para Continuar...");
			System.in.read();
		} catch(IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}

}
