/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 28/09/2023
* Ultima alteracao.: 05/10/2023
* Nome.............: Classe do trem 1(trem amarelo) do Problema dos Trens
* Funcao...........: A classe `trem1` é responsável por 
 controlar o movimento do primeiro trem no aplicativo de 
 simulação. Esta classe estende a classe `Thread` para executar 
 a lógica do movimento em uma thread separada.
****************************************************************/

package threads;
import control.controle;
import javafx.application.Platform;

public class trem1 extends Thread{

	private boolean running = true; // Indica se a thread deve continuar em execução
	private controle controle; // Referência à classe controle para interagir com a interface gráfica

  /**
  * Construtor da classe `trem1`.
  * 
  * @param controle A referência à classe controle para interação com a interface gráfica.
  */
	public trem1(controle controle) {
		this.controle = controle;
	}

	/**
  * O método `run` é chamado quando a thread é iniciada. Ele controla o movimento do trem
  * de acordo com as escolhas do usuário, como tipo de travamento ou solução de Peterson.
  */
	@Override
	public void run() {
		int processo = 0;
		int outroProcesso = 1;
		while (running) {
			// Lógica do movimento do trem com base nas escolhas do usuário
			if ("AMBOS NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com variável de travamento
					moverXfrente(130, 0);
					moverYbaixo(30, 90);
					
					trilho1Travamento();
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					controle.setVariavelTravamento1(0);
					
					moverYcima(80, 270);
					moverXfrente(190, 0);
					moverYbaixo(30,90);
					
    				trilho2Travamento();
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					controle.setVariavelTravamento2(0);
					
					moverYcima(80, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
					

				} else if("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())){
					// Lógica para movimento com estrita alternância
					moverXfrente(130, 0);
					moverYbaixo(30, 90);
					
					trilho1Estrita();
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					controle.setEstritaAlternancia1(1);
					
					moverYcima(80, 270);
					moverXfrente(190, 0);
					moverYbaixo(30,90);
					
    				trilho2Estrita();
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					controle.setEstritaAlternancia2(1);
				
					moverYcima(80, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson	
					
					moverXfrente(130, 0);
					moverYbaixo(30, 90);
					
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					PetersonSairRC1(processo);
					
					moverYcima(80, 270);
					moverXfrente(190, 0);
					moverYbaixo(30,90);
					
    				PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					PetersonSairRC2(processo);
				
					moverYcima(80, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
					
				}
			}
			if ("AMBOS NA DIREITA".equals(controle.getChoiceBox1().getValue())) {
				moverXtras(160, 0);
				moverYbaixo(30, -90);
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento
					
					trilho2Travamento();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setVariavelTravamento2(0);
					
					moverYcima(80, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					
					trilho1Travamento();;
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setVariavelTravamento1(0);
					
				}else if("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())){
					// Lógica para movimento com estrita alternância
					trilho2Estrita();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia2(1);
					
					moverYcima(80, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					
					trilho1Estrita();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia1(1);
					
				}else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson
					
					PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					PetersonSairRC2(processo);
					
					moverYcima(80, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					PetersonSairRC1(processo);
					
				}
				moverYcima(90, 90);
				moverXtras(170, 0);
				controle.setPosicaoTrem1(800, 100);	
			}
			if ( "AMARELO NA ESQUERDA E AZUL NA DIREITA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento
					moverXfrente(130, 0);
					moverYbaixo(30, 90);
					
					trilho1Travamento();;
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					moverYcima(30, 270);
					controle.setVariavelTravamento1(0);
					
					moverYcima(50, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);
					
    				trilho2Travamento();;
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					moverYcima(30, 270);
					controle.setVariavelTravamento2(0);
					
					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância
					moverXfrente(130, 0);
					moverYbaixo(30, 90);
					
					trilho1Estrita();
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					moverYcima(30, 270);
					controle.setEstritaAlternancia1(1);
					
					moverYcima(50, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);
					
					trilho2Estrita();
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					moverYcima(30, 270);
					controle.setEstritaAlternancia2(1);
					
					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson
					
					moverXfrente(130, 0);
					moverYbaixo(30, 90);
					
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(160, 0);
					moverYcima(30, 270);
					PetersonSairRC1(processo);
					
					moverYcima(50, 270);
					moverXfrente(190, 0);
					moverYbaixo(30, 90);
					
					PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(50, 90);
					moverXfrente(150, 0);
					moverYcima(30, 270);
					PetersonSairRC2(processo);
					
					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setPosicaoTrem1(0, 100);
					
				}

			}
			if ( "AMARELO NA DIREITA E AZUL NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					// Lógica para movimento com variável de travamento
					moverXtras(160, 0);
					moverYbaixo(30, -90);
					
					trilho2Travamento();;
					moverYbaixo(60, -90);
					moverXtras(170, 0);
					moverYcima(30, -270);
					controle.setVariavelTravamento2(0);
					
					moverYcima(50, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					
					trilho1Travamento();;
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					moverYcima(30, 90);
					controle.setVariavelTravamento1(0);
					
					moverYcima(60, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem1(800, 100);	
				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com estrita alternância
					moverXtras(160, 0);
					moverYbaixo(30, -90);
					
					trilho2Estrita();
					moverYbaixo(60, -90);
					moverXtras(170, 0);
					moverYcima(30, -270);
					controle.setEstritaAlternancia2(1);
					
					moverYcima(50, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					
					trilho1Estrita();
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					moverYcima(30, 90);
					controle.setEstritaAlternancia1(1);
					
					moverYcima(60, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem1(800, 100);	
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					// Lógica para movimento com solução de Peterson

					moverXtras(160, 0);
					moverYbaixo(30, -90);
					
					PetersonEntrarRC2(processo, outroProcesso);
					moverYbaixo(60, -90);
					moverXtras(170, 0);
					moverYcima(30, -270);
					PetersonSairRC2(processo);
					
					moverYcima(50, -270);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					
					PetersonEntrarRC1(processo, outroProcesso);
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					moverYcima(30, 90);
					PetersonSairRC1(processo);
					
					moverYcima(60, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem1(800, 100);	
					
				}
			}
		}
	}
	
  /**
  * Método para mover o trem para a frente ao longo do eixo X.
  * 
	* @param eixox    A quantidade a ser movida no eixo X.
  * @param rotacao  A rotação do trem.
  */
	public void moverXfrente(double eixox, int rotacao) {
		// Implementação do movimento do trem para frente
    controle.setRotacao(1, rotacao);
    double x = controle.getPosicaoX(1);
    for (int i = 0; i < eixox; i++) {
      if (running) {
        if (x >= 800) {
          break;
        }
        if (controle.getVelocidadeTrem1() == 10) {
					while (controle.getVelocidadeTrem1() == 10) {
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
        x += 1;
        double finalX = x;
        Platform.runLater(() -> {
					controle.moverX(1, finalX);
        });
      	try {
      		Thread.sleep(controle.getVelocidadeTrem1());
      	} catch (InterruptedException e) {
        	e.printStackTrace();
        }
			}
    }
	}

	/**
  * Método para mover o trem para trás ao longo do eixo X.
  * 
  * @param eixox    A quantidade a ser movida no eixo X.
  * @param rotate   A rotação do trem.
  */
	public void moverXtras(double eixox, double rotate) {
		// Implementação do movimento do trem para trás
		if (running) {
			controle.setRotacao(1, rotate);

			double x = controle.getPosicaoX(1);

			for (int i = 0; i < eixox; i++) {
	            if (x <=-20) {
	                break;
	            }
                if (controle.getVelocidadeTrem1() == 10) {
				while (controle.getVelocidadeTrem1() == 10) {
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
	    x -= 1;
	    double finalX = x;
	    Platform.runLater(() -> {
				controle.moverX(1, finalX);
		  });
		  try {
	      Thread.sleep(controle.getVelocidadeTrem1());
		  } catch (InterruptedException e) {
		    e.printStackTrace();
		  }
		}
	}
	}

  /**
  * Método para mover o trem para cima ao longo do eixo Y.
  * 
  * @param eixoY    A quantidade a ser movida no eixo Y.
  * @param rotacao  A rotação do trem.
  */
	public void moverYcima(double eixoY, double rotacao) {
		// Implementação do movimento do trem para cima
		if (running) {
		  controle.setRotacao(1, rotacao);
		    
		  double y = controle.getPosicaoY(1);
		   

		  for (int i = 0 ; i < eixoY; i++) {
        if (controle.getVelocidadeTrem1() == 10) {
					while (controle.getVelocidadeTrem1() == 10) {
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
	    y -= 1;

	    double finalY = y;
	      Platform.runLater(() -> {
					controle.moverY(1, finalY);
        });
	      try {
	          Thread.sleep(controle.getVelocidadeTrem1());
	      } catch (InterruptedException e) {
	            e.printStackTrace();
	      }
			}
	  }
	}

	/**
  * Método para mover o trem para baixo ao longo do eixo Y.
  * 
  * @param eixoy    A quantidade a ser movida no eixo Y.
  * @param rotacao  A rotação do trem.
  */
	public void moverYbaixo(double eixoy, double rotacao) {
		// Implementação do movimento do trem para baixo
		if (running) {
			controle.setRotacao(1, rotacao);
			double y = controle.getPosicaoY(1);
			  
			for (int i = 0; i < eixoy; i++) {
        if (controle.getVelocidadeTrem1() == 10) {
					while (controle.getVelocidadeTrem1() == 10) {
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				y += 1;
				double finalY = y;
				
				Platform.runLater(() -> {
      		controle.moverY(1, finalY);	
				});
	        try {
		        Thread.sleep(controle.getVelocidadeTrem1());
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
				}
			}
    }
	
		/**
    * Método para controlar o travamento do trilho 1.
    */
	  public void trilho1Travamento(){
			 // Implementação do controle de travamento do trilho 1
		  while(controle.getVariavelTravamento1() == 1){
		    try {
	        trem1.sleep(2);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	  controle.setVariavelTravamento1(1);
		}

		/**
    * Método para controlar o travamento do trilho 2.
    */	
	  public void trilho2Travamento(){
			// Implementação do controle de travamento do trilho 2
		    while(controle.getVariavelTravamento2() == 1){
		      try {
		        trem1.sleep(2);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    }
		    controle.setVariavelTravamento2(1);
		  }

    /**
     * Método para controlar a estrita alternância do trilho 1.
     */
	public void trilho1Estrita() {
		// Implementação do controle de estrita alternância do trilho 1
		while (controle.getEstritaAlternancia1() == 1) {
			try {
				trem1.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	    /**
     * Método para controlar a estrita alternância do trilho 2.
     */
	public void trilho2Estrita() {
		// Implementação do controle de estrita alternância do trilho 2
		while (controle.getEstritaAlternancia2() == 1) {
			try {
				trem1.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

    /**
     * Método para a entrada na região crítica 1 usando a solução de Peterson.
     * 
     * @param processo       O processo atual.
     * @param outroProcesso  O outro processo.
     */
	private void PetersonEntrarRC1(int processo, int outroProcesso) {
		// Implementação da entrada na região crítica 1 com a solução de Peterson
		controle.setBandeira1(processo, true);
		controle.setTurn1(outroProcesso);
		while (controle.getBandeira1(outroProcesso) && controle.getTurn1() == outroProcesso) {
			try {
				trem1.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	    /**
     * Método para a entrada na região crítica 2 usando a solução de Peterson.
     * 
     * @param processo       O processo atual.
     * @param outroProcesso  O outro processo.
     */
	private void PetersonEntrarRC2(int processo, int outroProcesso) {
		// Implementação da entrada na região crítica 2 com a solução de Peterson
		controle.setBandeira2(processo, true);
		controle.setTurn2(outroProcesso);
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
     * @param processo  O processo atual.
     */
	private void PetersonSairRC1(int processo) {
		controle.setBandeira1(processo, false);
	}

	    /**
     * Método para sair da região crítica 2 usando a solução de Peterson.
     * 
     * @param processo  O processo atual.
     */
	private void PetersonSairRC2(int processo) {
		controle.setBandeira2(processo, false);
	}
	public controle getControle() {
		return controle;
	}

	public void setControle(controle controle) {
		this.controle = controle;
	}
	
	public void parar() {
		running = false;
	}
	
}

