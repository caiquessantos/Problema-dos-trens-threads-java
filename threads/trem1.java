/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 28/09/2023
* Ultima alteracao.: 07/10/2023
* Nome.............: Classe do trem 1(trem amarelo) do Problema dos Trens
* Funcao...........: A classe `trem1` é responsável por 
 controlar o movimento do primeiro trem no aplicativo de 
 simulação. Esta classe estende a classe `Thread` para executar 
 a lógica do movimento em uma thread separada.
****************************************************************/

package threads;

import control.controle;
import javafx.application.Platform;

public class trem1 extends Thread {

	private boolean running = true; // Indica se a thread deve continuar em execução
	private controle controle; // Referência à classe controle para interagir com a interface gráfica

	/**
	 * Construtor da classe `trem1`.
	 * 
	 * @param controle A referência à classe controle para interação com a interface
	 *                 gráfica.
	 */
	public trem1(controle controle) {
		this.controle = controle;
	}

	/**
	 * Metodo: run
	 * O método `run` é chamado quando a thread é iniciada. Ele controla o movimento
	 * do trem
	 * de acordo com as escolhas do usuário, como tipo de resolução e tipo de
	 * execução.
	 * 
	 * @return void
	 */
	@Override
	public void run() {
		// inicializa as variáveis de controle da solução de Peterson
		int processo = 0;
		int outroProcesso = 1;

		// coloca a execução da thread em loop
		while (running) {
			// Lógica do movimento do trem com base nas escolhas do usuário
			if ("AMBOS NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2().getValue())) {

					// Lógica para movimento com variável de travamento
					moverXfrente(130, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica 1
					trilho1Travamento();
					moverYbaixo(60, 90);
					moverXfrente(160, 0);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYcima(80, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica 2
					trilho2Travamento();
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica 2 e liberando-a para outras threads

					moverYcima(80, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);// colocando o trem na posição inicial para reiniciar a animação

				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância
					moverXfrente(130, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica 1
					trilho1Estrita();
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					controle.setEstritaAlternancia1(1);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYcima(80, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica 2
					trilho2Estrita();
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					controle.setEstritaAlternancia2(1);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYcima(80, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);// colocando o trem na posição inicial para reiniciar a animação

				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					moverXfrente(130, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica 1
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					PetersonSairRC1(processo);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYcima(80, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica 2
					PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					PetersonSairRC2(processo);
					// saindo da região crítica 2 e liberando-a para outras threads

					moverYcima(80, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);// colocando o trem na posição inicial para reiniciar a animação

				}
			}
			if ("AMBOS NA DIREITA".equals(controle.getChoiceBox1().getValue())) {
				moverXtras(160, 0);
				moverYbaixo(30, -90);
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento

					// entrando na região crítica
					trilho2Travamento();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(80, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					trilho1Travamento();
					;
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica e liberando-a para outras threads

				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância

					// entrando na região crítica
					trilho2Estrita();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia2(1);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(80, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					trilho1Estrita();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia1(1);
					// saindo da região crítica e liberando-a para outras threads

				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					// entrando na região crítica
					PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					PetersonSairRC2(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(80, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					PetersonSairRC1(processo);
					// saindo da região crítica e liberando-a para outras threads

				}
				moverYcima(90, 90);
				moverXtras(170, 0);
				controle.setPosicaoTrem1(800, 100);
			}
			if ("AMARELO NA ESQUERDA E AZUL NA DIREITA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento
					moverXfrente(130, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica
					trilho1Travamento();
					;
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					moverYcima(30, 270);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica
					trilho2Travamento();
					;
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					moverYcima(30, 270);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância
					moverXfrente(130, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica
					trilho1Estrita();
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					moverYcima(30, 270);
					controle.setEstritaAlternancia1(1);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica
					trilho2Estrita();
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					moverYcima(30, 270);
					controle.setEstritaAlternancia2(1);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					moverXfrente(130, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					moverYcima(30, 270);
					PetersonSairRC1(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);

					// entrando na região crítica
					PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					moverYcima(30, 270);
					PetersonSairRC2(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);

				}

			}
			if ("AMARELO NA DIREITA E AZUL NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento
					moverXtras(160, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					trilho2Travamento();
					;
					moverYbaixo(60, -90);
					moverXtras(170, 0);
					moverYcima(30, -270);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					trilho1Travamento();
					;
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					moverYcima(30, 90);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(60, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem1(800, 100);
				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância
					moverXtras(160, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					trilho2Estrita();
					moverYbaixo(60, -90);
					moverXtras(170, 0);
					moverYcima(30, -270);
					controle.setEstritaAlternancia2(1);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					trilho1Estrita();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					moverYcima(30, 90);
					controle.setEstritaAlternancia1(1);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(60, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem1(800, 100);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					moverXtras(160, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(60, -90);
					moverXtras(170, 0);
					moverYcima(30, -270);
					PetersonSairRC2(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(50, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);

					// entrando na região crítica
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					moverYcima(30, 90);
					PetersonSairRC1(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYcima(60, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem1(800, 100);

				}
			}
		}
	}

	/**
	 * Metodo: moverXfrente
	 * Método para mover o trem para a frente ao longo do eixo X.
	 * 
	 * @param eixox   A quantidade a ser movida no eixo X.
	 * @param rotacao A rotação do trem.
	 * @return void
	 */
	public void moverXfrente(double eixox, int rotacao) {
		// Implementação do movimento do trem para frente
		controle.setRotacao(1, rotacao);// chama o método para rotacionar o trem
		double x = controle.getPosicaoX(1);

		for (int i = 0; i < eixox; i++) {// incia o loop responsável pela animação
			if (running) {
				if (x >= 800) {
					break;// quebra o loop quando o x do trem01 for maior que a proporção da tela
				}
				if (controle.getVelocidadeTrem1() == 10) {
					while (controle.getVelocidadeTrem1() == 10) {
						try {
							Thread.sleep(0);// faz a imagem parar caso a velocidade seja 10, ou seja, quando for 10 o sleep
															// da thread é maior
							// simulando assim um trem mais lento. Quando for 10 é o mais lento possível,
							// nesse caso, zeramos a vcelocidade.
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				x += 1;
				double finalX = x;
				Platform.runLater(() -> {
					controle.moverX(1, finalX);// chama o metodo responsavel por setar a nova posição X
				});
				try {
					Thread.sleep(controle.getVelocidadeTrem1());// transforma o a velocidade do trem em um sleep para a thread,
																											// simulando assim
					// uma animção
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo: moverXtras
	 * Método para mover o trem para tras ao longo do eixo X.
	 * 
	 * @param eixox   A quantidade a ser movida no eixo X.
	 * @param rotacao A rotação do trem.
	 * @return void
	 */
	public void moverXtras(double eixox, double rotate) {
		// Implementação do movimento do trem para trás
		if (running) {
			controle.setRotacao(1, rotate);// chama o método para rotacionar o trem

			double x = controle.getPosicaoX(1);

			for (int i = 0; i < eixox; i++) {// incia o loop responsável pela animação
				if (x <= -20) {
					break;
				}
				if (controle.getVelocidadeTrem1() == 10) {
					while (controle.getVelocidadeTrem1() == 10) {
						try {
							Thread.sleep(0);// faz a imagem parar caso a velocidade seja 10, ou seja, quando for 10 o sleep
															// da thread é maior
							// simulando assim um trem mais lento. Quando for 10 é o mais lento possível,
							// nesse caso, zeramos a vcelocidade.
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				x -= 1;
				double finalX = x;
				Platform.runLater(() -> {
					controle.moverX(1, finalX);// chama o metodo responsavel por setar a nova posição X
				});
				try {
					Thread.sleep(controle.getVelocidadeTrem1());// transforma o a velocidade do trem em um sleep para a thread,
																											// simulando assim
					// uma animção
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo: moverYcima
	 * Método para mover o trem para cima ao longo do eixo Y.
	 * 
	 * @param eixoY   A quantidade a ser movida no eixo y.
	 * @param rotacao A rotação do trem.
	 * @return void
	 */
	public void moverYcima(double eixoY, double rotacao) {
		// Implementação do movimento do trem para cima
		if (running) {
			controle.setRotacao(1, rotacao);// chama o método para rotacionar o trem

			double y = controle.getPosicaoY(1);

			for (int i = 0; i < eixoY; i++) {// incia o loop responsável pela animação
				if (controle.getVelocidadeTrem1() == 10) {
					while (controle.getVelocidadeTrem1() == 10) {
						try {
							Thread.sleep(0);// faz a imagem parar caso a velocidade seja 10, ou seja, quando for 10 o sleep
															// da thread é maior
							// simulando assim um trem mais lento. Quando for 10 é o mais lento possível,
							// nesse caso, zeramos a vcelocidade.
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				y -= 1;

				double finalY = y;
				Platform.runLater(() -> {
					controle.moverY(1, finalY);// chama o metodo responsavel por setar a nova posição Y
				});
				try {
					Thread.sleep(controle.getVelocidadeTrem1());// transforma o a velocidade do trem em um sleep para a thread,
																											// simulando assim
					// uma animção
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo: moverYbaixo
	 * Método para mover o trem para baixo ao longo do eixo Y.
	 * 
	 * @param eixoy   A quantidade a ser movida no eixo y.
	 * @param rotacao A rotação do trem.
	 * @return void
	 */
	public void moverYbaixo(double eixoy, double rotacao) {
		// Implementação do movimento do trem para baixo
		if (running) {
			controle.setRotacao(1, rotacao);// chama o método para rotacionar o trem
			double y = controle.getPosicaoY(1);

			for (int i = 0; i < eixoy; i++) {// incia o loop responsável pela animação
				if (controle.getVelocidadeTrem1() == 10) {
					while (controle.getVelocidadeTrem1() == 10) {
						try {
							Thread.sleep(0);// faz a imagem parar caso a velocidade seja 10, ou seja, quando for 10 o sleep
															// da thread é maior
							// simulando assim um trem mais lento. Quando for 10 é o mais lento possível,
							// nesse caso, zeramos a vcelocidade.
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				y += 1;
				double finalY = y;

				Platform.runLater(() -> {
					controle.moverY(1, finalY); // chama o metodo responsavel por setar a nova posição Y
				});
				try {
					Thread.sleep(controle.getVelocidadeTrem1());// transforma o a velocidade do trem em um sleep para a thread,
																											// simulando assim
					// uma animção
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo: trilho1Travamento
	 * Método para controlar o acesso a região critica
	 * 
	 * @return void
	 */
	public void trilho1Travamento() {
		if (controle.getVariavelTravamento1() != 0) {
			while (controle.getVariavelTravamento1() != 0) {// caso o valor retornado seja igual a 1, a thread dorme até o
																											// valor
				// ser diferente
				try {
					trem1.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		controle.setVariavelTravamento1(1);// seta a varíavel de travamento para a região crítica para 1 e entra nela
	}

	/**
	 * Metodo: trilho2Travamento
	 * Método para controlar o acesso a região critica
	 * 
	 * @return void
	 */
	public void trilho2Travamento() {
		if (controle.getVariavelTravamento2() != 0) {
			while (controle.getVariavelTravamento2() != 0) {// caso o valor retornado seja igual a 1, a thread dorme até o
																											// valor
				// ser diferente
				try {
					trem1.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		controle.setVariavelTravamento2(1);// seta a varíavel de travamento para a região crítica para 1 e entra nela
	}

	/**
	 * Metodo: trilho1Estrita
	 * Método para controlar o acesso a região critica
	 * 
	 * @return void
	 */
	public void trilho1Estrita() {
		// Implementação do controle de estrita alternância do trilho 1
		while (controle.getEstritaAlternancia1() == 1) {// caso o valor retornado seja igual a 1, a thread dorme até o valor
																										// ser diferente
			try {
				trem1.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo: trilho2Estrita
	 * Método para controlar o acesso a região critica
	 * 
	 * @return void
	 */
	public void trilho2Estrita() {
		// Implementação do controle de estrita alternância do trilho 2
		while (controle.getEstritaAlternancia2() == 1) {// caso o valor retornado seja igual a 1, a thread dorme até o valor
																										// ser diferente
			try {
				trem1.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo: PetersonEntrarRC1
	 * Método para a entrada na região crítica usando a solução de Peterson.
	 * 
	 * @param processo      O processo atual.
	 * @param outroProcesso O outro processo.
	 * @return void
	 * 
	 */
	private void PetersonEntrarRC1(int processo, int outroProcesso) {
		// Implementação da entrada na região crítica 1 com a solução de Peterson
		controle.setBandeira1(processo, true);// passa novos valores para flag
		controle.setTurn1(outroProcesso);// coloca o turno para ser da outra thread

		// Enquanto a bandeira 1 for da outra thread e o turno tambem for dela, a thread
		// atual dorme
		while (controle.getBandeira1(outroProcesso) && controle.getTurn1() == outroProcesso) {
			try {
				trem1.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo: PetersonEntrarRC2
	 * Método para a entrada na região crítica usando a solução de Peterson.
	 * 
	 * @param processo      O processo atual.
	 * @param outroProcesso O outro processo.
	 * @return void
	 * 
	 */
	private void PetersonEntrarRC2(int processo, int outroProcesso) {
		// Implementação da entrada na região crítica 2 com a solução de Peterson
		controle.setBandeira2(processo, true);// passa novos valores para flag
		controle.setTurn2(outroProcesso);// coloca o turno para ser da outra thread

		// Enquanto a bandeira 2 for da outra thread e o turno tambem for dela, a thread
		// atual dorme
		while (controle.getBandeira2(outroProcesso) && controle.getTurn2() == outroProcesso) {
			try {
				trem1.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método para sair da região crítica 1 usando a solução de Peterson.
	 * 
	 * @param processo O processo atual.
	 * @return void
	 */
	private void PetersonSairRC1(int processo) {
		controle.setBandeira1(processo, false);
	}

	/**
	 * Método para sair da região crítica 2 usando a solução de Peterson.
	 * 
	 * @param processo O processo atual.
	 * @return void
	 */
	private void PetersonSairRC2(int processo) {
		controle.setBandeira2(processo, false);
	}

	// getter e setter do controle
	public controle getControle() {
		return controle;
	}

	public void setControle(controle controle) {
		this.controle = controle;
	}

	/**
	 * Metodo: parar
	 * controla o looping principal da thread, fazendo-a parar quando chamado
	 * 
	 * @return void
	 */
	public void parar() {
		running = false;
	}

}
