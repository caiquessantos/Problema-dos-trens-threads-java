/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 28/09/2023
* Ultima alteracao.: 05/10/2023
* Nome.............: Classe do trem 2(trem azul) do Problema dos Trens
* Funcao...........: A classe `trem1` é responsável por 
 controlar o movimento do primeiro trem no aplicativo de 
 simulação. Esta classe estende a classe `Thread` para executar 
 a lógica do movimento em uma thread separada.
****************************************************************/

package threads;
import control.controle;
import javafx.application.Platform;

public class trem2 extends Thread {

	private boolean running = true;
	private controle controle; 
	  
	/**
	* Construtor da classe Trem2.   
	*
	* @param controle O objeto de controle que permite interagir com a interface gráfica.
	*/
	public trem2(controle controle) {
		this.controle = controle;
	}
	


  /**
  * Método de execução da thread Trem2.
  */
	@Override
	public void run() {
		int processo = 1;
		int outroProcesso = 0;
		while (running) {
			if ("AMBOS NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {

				if ( "VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
					.getValue())) {

						moverXfrente(130, 0);
						moverYcima(30, -90);
						
						trilho1Travamento();
						moverYcima(50, -90);
						moverXfrente(170, 0);
						controle.setVariavelTravamento1(0);
						
						moverYbaixo(80, 90);
						moverXfrente(170, 0);
						moverYcima(30, 270);
						
						trilho2Travamento();
						moverYcima(50, 270);
						moverXfrente(170, 0);
						controle.setVariavelTravamento2(0);
						
						moverYbaixo(80, 90);
						moverXfrente(170, 0);
						controle.setPosicaoTrem2(0, 270);		
									
				} else if("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())){
					moverXfrente(130, 0);
					moverYcima(30, -90);
					
					trilho1Estrita();
					moverYcima(50, -90);
					moverXfrente(170, 0);
					controle.setEstritaAlternancia1(0);
					
					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);
					
					trilho2Estrita();
					moverYcima(50, 270);
					moverXfrente(170, 0);
					controle.setEstritaAlternancia2(0);
					
					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);	
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					
					moverXfrente(130, 0);
					moverYcima(30, -90);
					
					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, -90);
					moverXfrente(170, 0);
					PetersonSairRC1(processo);
					
					moverYbaixo(80, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);
					
					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(50, 270);
					moverXfrente(170, 0);
					PetersonSairRC2(processo);
					
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
					
							trilho2Travamento();
							moverYcima(50, 90);
							moverXtras(170, 0);
							controle.setVariavelTravamento2(0);
							
							moverYbaixo(80, 270);
							moverXtras(170, 0);
							moverYcima(30, 90);
		
							trilho1Travamento();
							moverYcima(50, 90);
							moverXtras(170, 0);
							controle.setVariavelTravamento1(0);
					
				}else if("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())){
					trilho2Estrita();
					moverYcima(50, 90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia2(0);
					
					moverYbaixo(80, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					trilho1Estrita();
					moverYcima(50, 90);
					moverXtras(170, 0);
					controle.setEstritaAlternancia1(0);
				}else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {

					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(50, 90);
					moverXtras(170, 0);
					PetersonSairRC2(processo);
					
					moverYbaixo(80, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, 90);
					moverXtras(170, 0);
					PetersonSairRC1(processo);
					
				}
				moverYbaixo(80, -90);
				moverXtras(170, 0);
				controle.setPosicaoTrem2(800, 270);	
			}
			if ("AMARELO NA ESQUERDA E AZUL NA DIREITA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					moverXtras(160, 0);
					moverYcima(30, 90);
					
					trilho2Travamento();
					moverYcima(60, 90);
					moverXtras(170, 0);
					moverYbaixo(30, 270);
					controle.setVariavelTravamento2(0);
					
					moverYbaixo(50, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					trilho1Travamento();
					moverYcima(50, 90);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					controle.setVariavelTravamento1(0);
					
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem2(800, 270);		
				} else if("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					moverXtras(160, 0);
					moverYcima(30, 90);
					
					trilho2Estrita();
					moverYcima(60, 90);
					moverXtras(170, 0);
					moverYbaixo(30, 270);
					controle.setEstritaAlternancia2(0);
					
					moverYbaixo(50, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					trilho1Estrita();
					moverYcima(50, 90);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					controle.setEstritaAlternancia1(0);
					
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem2(800, 270);		
				}else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					moverXtras(160, 0);
					moverYcima(30, 90);
					
					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(60, 90);
					moverXtras(170, 0);
					moverYbaixo(30, 270);
					PetersonSairRC2(processo);
					
					moverYbaixo(50, 270);
					moverXtras(170, 0);
					moverYcima(30, 90);

					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, 90);
					moverXtras(170, 0);
					moverYbaixo(30, -90);
					PetersonSairRC1(processo);
					
					moverYbaixo(50, -90);
					moverXtras(170, 0);
					controle.setPosicaoTrem2(800, 270);	
				}
			}
			if ( "AMARELO NA DIREITA E AZUL NA ESQUERDA".equals(controle.getChoiceBox1().getValue())) {
				if ("VARIAVEL DE TRAVAMENTO".equals(controle.getChoiceBox2()
						.getValue())) {
					moverXfrente(130, 0);
					moverYcima(30, -90);
					
					trilho1Travamento();;
					moverYcima(50, -90);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setVariavelTravamento1(0);
					
					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);
					
					trilho2Travamento();
					moverYcima(50, 270);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setVariavelTravamento2(0);
					
					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);		
				} else if ("ESTRITA ALTERNANCIA".equals(controle.getChoiceBox2().getValue())) {
					moverXfrente(130, 0);
					moverYcima(30, -90);
					
					trilho1Estrita();
					moverYcima(50, -90);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setEstritaAlternancia1(0);
					
					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);
					
					trilho2Estrita();
					moverYcima(50, 270);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					controle.setEstritaAlternancia2(0);
					
					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);
				} else if ("SOLUCAO DE PETERSON".equals(controle.getChoiceBox2().getValue())) {
					moverXfrente(130, 0);
					moverYcima(30, -90);
					
					PetersonEntrarRC1(processo, outroProcesso);
					moverYcima(50, -90);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					PetersonSairRC1(processo);
					
					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					moverYcima(30, 270);
					
					PetersonEntrarRC2(processo, outroProcesso);
					moverYcima(50, 270);
					moverXfrente(170, 0);
					moverYbaixo(30, 90);
					PetersonSairRC2(processo);
					
					moverYbaixo(50, 90);
					moverXfrente(170, 0);
					controle.setPosicaoTrem2(0, 270);
				}
			}
		}
	}
	

	/**
  * Método para mover o trem para a direita ao longo do eixo X.
  *
  * @param eixox    A quantidade de movimento ao longo do eixo X.
  * @param rotacao  O valor de rotação do trem.
  */
	public void moverXfrente(double eixox, int rotacao) {
		if (running) {
	        
			controle.setRotacao(2, rotacao);
	        double x = controle.getPosicaoX(2);
	        
	        for (int i = 0; i < eixox; i++) {
	            if (x >= 800) {
                    // A posição final atingiu o limite da tela
                    break; // Sai do loop para interromper a animação
	            }
                if (controle.getVelocidadeTrem2() == 10) {
					while (controle.getVelocidadeTrem2() == 10) {
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
	            	controle.moverX(2, finalX);
	            }
	            );
	            try {
	            	Thread.sleep(controle.getVelocidadeTrem2());
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		}
	}


	/**
  * Método para mover o trem para a esquerda ao longo do eixo X.
  *
  * @param eixox    A quantidade de movimento ao longo do eixo X.
  * @param rotate   O valor de rotação do trem.
  */
	public void moverXtras(double eixox, double rotate) {
		if (running) {
			controle.setRotacao(2, rotate);

			double x = controle.getPosicaoX(2);

			for (int i = 0; i < eixox; i++) {
	            if (x <=-20) {
                    // A posição final atingiu o limite da tela
                    break; // Sai do loop para interromper a animação
	            }
                if (controle.getVelocidadeTrem2() == 10) {
					while (controle.getVelocidadeTrem2() == 10) {
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
		    	  controle.moverX(2, finalX);
		      });
		      try {
		          Thread.sleep(controle.getVelocidadeTrem2());
		      } catch (InterruptedException e) {
		          e.printStackTrace();
		      }
		  }
		}
	}
	
  /**
  * Método para mover o trem para cima ao longo do eixo Y.
  *
  * @param eixoY    A quantidade de movimento ao longo do eixo Y.
  * @param rotacao  O valor de rotação do trem.
  */
	public void moverYcima(double eixoY, double rotacao) {
	    controle.setRotacao(2, rotacao);
	    
	    double y = controle.getPosicaoY(2);

	    for (int i = 0 ; i < eixoY; i++) {
            if (controle.getVelocidadeTrem2() == 10) {
				while (controle.getVelocidadeTrem2() == 10) {
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
	        	controle.moverY(2,finalY);
	        });
	        try {
	            Thread.sleep(controle.getVelocidadeTrem2());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

	/**
  * Método para mover o trem para baixo ao longo do eixo Y.
  *
  * @param eixoy    A quantidade de movimento ao longo do eixo Y.
  * @param rotacao  O valor de rotação do trem.
  */
	public void moverYbaixo(double eixoy, double rotacao) {
		if (running) {
			
			controle.setRotacao(2, rotacao);
			double y = controle.getPosicaoY(2);
			  
			for (int i = 0; i < eixoy; i++) {
                if (controle.getVelocidadeTrem2() == 10) {
					while (controle.getVelocidadeTrem2() == 10) {
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
					controle.moverY(2,finalY);
				});
		        try {
		              Thread.sleep(controle.getVelocidadeTrem2());
		        } catch (InterruptedException e) {
		               e.printStackTrace();
		        }
			}
		}
    }

		/**
    * Método para garantir a travamento nos trilhos.
    */
	  public void trilho1Travamento(){
			while(controle.getVariavelTravamento1() == 1){
				try {
					trem1.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			controle.setVariavelTravamento1(1);
		}
	public void trilho2Travamento(){
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
     * Método para garantir a estrita alternância no trilho 1.
     */
		public void trilho1Estrita() {
			while (controle.getEstritaAlternancia1() != 1) {
				try {
					trem2.sleep(2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

    /**
    * Método para garantir a estrita alternância no trilho 2.
    */
		public void trilho2Estrita() {
			while (controle.getEstritaAlternancia2() != 1) {
				try {
					trem2.sleep(2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/**
    * Método para entrada na Região Crítica 1 usando a Solução de Peterson.
    *
    * @param processo      O número do processo atual.
    * @param outroProcesso O número do outro processo.
    */
		private void PetersonEntrarRC1(int processo, int outroProcesso) {
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
    * Método para entrada na Região Crítica 2 usando a Solução de Peterson.
    *
    * @param processo      O número do processo atual.
    * @param outroProcesso O número do outro processo.
    */
		private void PetersonEntrarRC2(int processo, int outroProcesso) {
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
    * Método para sair da Região Crítica 1 usando a Solução de Peterson.
    *
    * @param processo O número do processo atual.
    */
		private void PetersonSairRC1(int processo) {
			controle.setBandeira1(processo, false);
		}

    /**
    * Método para sair da Região Crítica 2 usando a Solução de Peterson.
    *
    * @param processo O número do processo atual.
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
