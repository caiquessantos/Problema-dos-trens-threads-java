/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 28/09/2023
* Ultima alteracao.: 07/10/2023
* Nome.............: Classe do trem 2(trem azul) do Problema dos Trens
* Funcao...........: A classe `trem2` é responsável por 
 controlar o movimento do segundo trem no aplicativo de 
 simulação. Esta classe estende a classe `Thread` para executar 
 a lógica do movimento em uma thread separada.
****************************************************************/

package threads;

import control.controle;
import javafx.application.Platform;

public class trem2 extends Thread {

	private boolean running = true;// Indica se a thread deve continuar em execução
	private controle controle; // Referência à classe controle para interagir com a interface gráfica

	/**
	 * Construtor da classe Trem2.
	 *
	 * @param controle O objeto de controle que permite interagir com a interface
	 *                 gráfica.
	 */
	public trem2(controle controle) {
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
		int processo = 1;
		int outroProcesso = 0;

		// coloca a execução da thread em loop
		while (running) {
			// Lógica do movimento do trem com base nas escolhas do usuário
			if ("AMBOS NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {

				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento

					moverXfrente(130, 0);
					moverYcima(30, -90);

					// entrando na região crítica 1
					trilho1Travamento();
					moverYcima(50, -90);
					moverXfrente(170, 0);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);

					// entrando na região crítica 2
					trilho2Travamento();
					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica 2 e liberando-a para outras threads

					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);

				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância

					moverXfrente(130, 0);
					moverYcima(30, -90);

					// entrando na região crítica 1
					trilho1Estrita();
					moverYcima(50, -90);
					moverXfrente(170, 0);
					controle.setEstritaAlternancia1(0);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);

					// entrando na região crítica 2
					trilho2Estrita();
					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setEstritaAlternancia2(0);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					moverXfrente(130, 0);
					moverYcima(30, -90);

					// entrando na região crítica 1
					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, -90);
					moverXfrente(170, 0);
					PetersonSairRC1(processo);
					// saindo da região crítica 1 e liberando-a para outras threads

					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);

					// entrando na região crítica 2
					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(50, 270);
					moverXfrente(170, 0);
					PetersonSairRC2(processo);
					// saindo da região crítica 2 e liberando-a para outras threads

					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);
				}
			}
			if ("AMBOS NA DIREITA".equals(controle.getChoiceBox1().getValue())) {
				moverXtras(160, 0);
				moverYcima(30, 90);
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento

					// entrando na região crítica
					trilho2Travamento();
					moverYcima(50, 90);
					moverXtras(170, 0);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(80, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					trilho1Travamento();
					moverYcima(50, 90);
					moverXtras(170, 0);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica e liberando-a para outras threads

				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância

					// entrando na região crítica
					trilho2Estrita();
					moverYcima(50, 90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(80, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					trilho1Estrita();
					moverYcima(50, 90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia1(0);
					// saindo da região crítica e liberando-a para outras threads
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					// entrando na região crítica
					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(50, 90);
					moverXtras(170, 0);
					PetersonSairRC2(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(80, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, 90);
					moverXtras(170, 0);
					PetersonSairRC1(processo);
					// saindo da região crítica e liberando-a para outras threads

				}
				moverYbaixo(80, -90);
				moverXtras(170, 0);
				controle.setPosicaoTrem2(800, 270);
			}
			if ("AMARELO NA ESQUERDA E AZUL NA DIREITA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento

					moverXtras(160, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					trilho2Travamento();
					moverYcima(60, 90);
					moverXtras(170, 0);
					moverYbaixo(30, 270);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					trilho1Travamento();
					moverYcima(50, 90);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem2(800, 270);
				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância
					moverXtras(160, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					trilho2Estrita();
					moverYcima(60, 90);
					moverXtras(170, 0);
					moverYbaixo(30, 270);
					controle.setEstritaAlternancia2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					trilho1Estrita();
					moverYcima(50, 90);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					controle.setEstritaAlternancia1(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem2(800, 270);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					moverXtras(160, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(60, 90);
					moverXtras(170, 0);
					moverYbaixo(30, 270);
					PetersonSairRC2(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					// entrando na região crítica
					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, 90);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					PetersonSairRC1(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem2(800, 270);
				}
			}
			if ("AMARELO NA DIREITA E AZUL NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento
					moverXfrente(130, 0);
					moverYcima(30, -90);

					// entrando na região crítica
					trilho1Travamento();
					;
					moverYcima(50, -90);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setVariavelTravamento1(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);

					// entrando na região crítica
					trilho2Travamento();
					moverYcima(50, 270);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setVariavelTravamento2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);
				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância
					moverXfrente(130, 0);
					moverYcima(30, -90);

					// entrando na região crítica
					trilho1Estrita();
					moverYcima(50, -90);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setEstritaAlternancia1(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);

					// entrando na região crítica
					trilho2Estrita();
					moverYcima(50, 270);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setEstritaAlternancia2(0);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson
					moverXfrente(130, 0);
					moverYcima(30, -90);

					// entrando na região crítica
					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, -90);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					PetersonSairRC1(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);

					// entrando na região crítica
					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(50, 270);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					PetersonSairRC2(processo);
					// saindo da região crítica e liberando-a para outras threads

					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);
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
		if (running) {

			controle.setRotacao(2, rotacao);// chama o método para rotacionar o trem
			double x = controle.getPosicaoX(2);

			for (int i = 0; i < eixox; i++) {// incia o loop responsável pela animação
				if (x >= 800) {
					// A posição final atingiu o limite da tela
					break; // Sai do loop para interromper a animação
				}
				if (controle.getVelocidadeTrem2() == 10) {
					while (controle.getVelocidadeTrem2() == 10) {
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
					controle.moverX(2, finalX);// chama o metodo responsavel por setar a nova posição X
				});
				try {
					Thread.sleep(controle.getVelocidadeTrem2());// transforma o a velocidade do trem em um sleep para a thread,
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
	public void moverXtras(double eixox, double rotacao) {
		if (running) {
			controle.setRotacao(2, rotacao);// chama o método para rotacionar o trem

			double x = controle.getPosicaoX(2);

			for (int i = 0; i < eixox; i++) {// incia o loop responsável pela animação
				if (x <= -20) {
					// A posição final atingiu o limite da tela
					break; // Sai do loop para interromper a animação
				}
				if (controle.getVelocidadeTrem2() == 10) {
					while (controle.getVelocidadeTrem2() == 10) {
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
					controle.moverX(2, finalX);// chama o metodo responsavel por setar a nova posição X
				});
				try {
					Thread.sleep(controle.getVelocidadeTrem2());// transforma o a velocidade do trem em um sleep para a thread,
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
		controle.setRotacao(2, rotacao);// chama o método para rotacionar o trem

		double y = controle.getPosicaoY(2);

		for (int i = 0; i < eixoY; i++) {// incia o loop responsável pela animação
			if (controle.getVelocidadeTrem2() == 10) {
				while (controle.getVelocidadeTrem2() == 10) {
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
				controle.moverY(2, finalY);// chama o metodo responsavel por setar a nova posição Y
			});
			try {
				Thread.sleep(controle.getVelocidadeTrem2());// transforma o a velocidade do trem em um sleep para a thread,
																										// simulando assim
				// uma animção
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		if (running) {

			controle.setRotacao(2, rotacao);// chama o método para rotacionar o trem
			double y = controle.getPosicaoY(2);

			for (int i = 0; i < eixoy; i++) {// incia o loop responsável pela animação
				if (controle.getVelocidadeTrem2() == 10) {
					while (controle.getVelocidadeTrem2() == 10) {
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
					controle.moverY(2, finalY); // chama o metodo responsavel por setar a nova posição Y
				});
				try {
					Thread.sleep(controle.getVelocidadeTrem2());// transforma o a velocidade do trem em um sleep para a thread,
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
		while (controle.getVariavelTravamento1() == 1) {// caso o valor retornado seja igual a 1, a thread dorme até o valor
																										// ser diferente
			try {
				trem1.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		while (controle.getVariavelTravamento2() == 1) {// caso o valor retornado seja igual a 1, a thread dorme até o valor
																										// ser diferente
			try {
				trem1.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		while (controle.getEstritaAlternancia1() != 1) {// caso o valor retornado seja diferente a 1, a thread dorme até o
																										// valor ser igual a 1
			try {
				trem2.sleep(2);
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
		while (controle.getEstritaAlternancia2() != 1) {// caso o valor retornado seja diferente a 1, a thread dorme até o
																										// valor ser igual a 1
			try {
				trem2.sleep(2);
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
