/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 28/09/2023
* Ultima alteracao.: 07/10/2023
* Nome.............: Classe de controle do Problema dos Trens
* Funcao...........: A classe `controle` é responsável por
 controlar a interface gráfica e a lógica do aplicativo. Ela
 implementa a interface `Initializable` para inicializar
 componentes de interface quando o FXML é carregado.
****************************************************************/

package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import threads.*;

public class controle implements Initializable {

	// Componentes de interface gráfica
	@FXML
	private ImageView imgInicial;
	@FXML
	private ImageView imgCenario;
	@FXML
	private ImageView trem01;
	@FXML
	private ImageView trem02;
	@FXML
	private ImageView trem01velocidade;
	@FXML
	private ImageView trem02velocidade;
	@FXML
	private Button botaoIniciar;
	@FXML
	private Button botaoVoltar;
	@FXML
	private ChoiceBox<String> choiceBox1;
	@FXML
	private ChoiceBox<String> choiceBox2;
	@FXML
	private Slider velocidadeTrem1;
	@FXML
	private Slider velocidadeTrem2;

	// Threads para controle dos trens
	private trem1 trem1;
	private trem2 trem2;

	// Variáveis de coontrole da rotação
	private double rotacao;

	// Variáveis de controle
	private int variavelTravamento1 = 0;
	private int variavelTravamento2 = 0;
	private int EstritaAlternancia1 = 0;
	private int EstritaAlternancia2 = 0;
	private boolean[] bandeira1 = new boolean[2];
	private int turn1;
	private boolean[] bandeira2 = new boolean[2];
	private int turn2;

	/**
	 * Método chamado durante a inicialização do FXML.
	 * Configura as opções iniciais dos ChoiceBox e visibilidade dos componentes.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Configura opções do ChoiceBox1
		choiceBox1.setItems(FXCollections.observableArrayList("AMBOS NA ESQUERDA", "AMBOS NA DIREITA",
				"AMARELO NA ESQUERDA E AZUL NA DIREITA", "AMARELO NA DIREITA E AZUL NA ESQUERDA"));
		choiceBox1.setValue("AMBOS NA ESQUERDA");

		// Configura opções do ChoiceBox2
		choiceBox2.setItems(
				FXCollections.observableArrayList("SOLUCAO DE PETERSON", "ESTRITA ALTERNANCIA", "VARIAVEL DE TRAVAMENTO"));
		choiceBox2.setValue("SOLUCAO DE PETERSON");

		// Configura visibilidade dos componentes
		trem01.setVisible(false);
		trem02.setVisible(false);
		botaoVoltar.setVisible(false);
		botaoVoltar.setDisable(true);
	}

	/**
	 * Metodo: voltar
	 * Método chamado quando o botão "Voltar" é clicado.
	 * Interrompe as threads dos trens e redefine a interface gráfica para o estado
	 * inicial.
	 * 
	 * @param Event Evento de clique do mouse
	 * @return void
	 */
	@FXML
	private void voltar(ActionEvent Event) {
		// chama o método parar para finalizar a animação da thread
		trem1.parar();
		trem2.parar();

		// Reinicia as variáveis de controle
		this.setVariavelTravamento1(0);
		this.setVariavelTravamento2(0);
		this.setEstritaAlternancia1(0);

		this.setEstritaAlternancia2(0);
		this.bandeira1 = new boolean[2];
		this.bandeira2 = new boolean[2];

		// Configura a visualização dos elementos gráficos, fazendo voltar a
		// configuração de inicio da aplicação
		botaoVoltar.setVisible(false);
		botaoVoltar.setDisable(true);
		choiceBox1.setVisible(true);
		choiceBox2.setVisible(true);
		botaoIniciar.setVisible(true);
		imgInicial.setVisible(true);
		imgCenario.setVisible(false);
		trem01.setScaleX(1);
		trem02.setScaleX(1);
		velocidadeTrem1.setValue(5);
		velocidadeTrem2.setValue(5);
	}

	/**
	 * Metodo: moverTrem
	 * Método chamado quando o botão "Iniciar" é clicado.
	 * Configura a posição inicial dos trens com base na escolha do usuário e inicia
	 * as threads dos trens.
	 * 
	 * @return void
	 */
	@FXML
	private void moverTrem() {
		// configura a visualizacao dos elementos graficos
		choiceBox1.setVisible(false);
		choiceBox2.setVisible(false);
		botaoIniciar.setVisible(false);
		imgInicial.setVisible(false);
		imgCenario.setVisible(true);
		botaoVoltar.setVisible(true);
		botaoVoltar.setDisable(false);

		// configura as variáveis de controle
		this.setVariavelTravamento1(0);
		this.setVariavelTravamento2(0);
		this.setEstritaAlternancia1(0);
		this.setEstritaAlternancia2(0);
		this.bandeira1 = new boolean[2];
		this.bandeira2 = new boolean[2];

		// Instancia os trens
		trem1 = new trem1(this);
		trem2 = new trem2(this);

		// Configura a posição inicial dos trens com base na escolha do usuário, fazendo
		// o espelhamento da imagem se necessário
		if ("AMBOS NA ESQUERDA".equals(this.getChoiceBox1().getValue())) {
			trem01.setVisible(true);
			trem02.setVisible(true);
			trem01.setX(0);
			trem01.setY(100);
			trem02.setX(0);
			trem02.setY(270);
		}
		if ("AMBOS NA DIREITA".equals(this.getChoiceBox1().getValue())) {
			trem01.setVisible(true);
			trem02.setVisible(true);
			trem01.setScaleX(-1);
			trem02.setScaleX(-1);
			trem01.setX(800);
			trem01.setY(100);
			trem02.setX(800);
			trem02.setY(270);
		}
		if ("AMARELO NA ESQUERDA E AZUL NA DIREITA".equals(this.getChoiceBox1().getValue())) {
			trem01.setVisible(true);
			trem02.setVisible(true);
			trem02.setScaleX(-1);
			trem01.setX(0);
			trem01.setY(100);
			trem02.setX(800);
			trem02.setY(270);
		}
		if ("AMARELO NA DIREITA E AZUL NA ESQUERDA".equals(this.getChoiceBox1().getValue())) {
			trem01.setVisible(true);
			trem02.setVisible(true);
			trem01.setScaleX(-1);
			trem01.setX(800);
			trem01.setY(100);
			trem02.setX(0);
			trem02.setY(270);
		}

		// Transforma as duas threads em daemon, para serem finalizadas ao fechamento da
		// aplicação
		trem1.setDaemon(true);
		trem2.setDaemon(true);

		// Starta as threads
		trem1.start();
		trem2.start();
	}

	/**
	 * Metodo: moverX
	 * Método chamado para fazer a transição do trem para um ponto X do eixo X
	 * 
	 * @param trem    O trem que será movido pelo eixo X
	 * @param posicao A quantidade de X que ele será movido
	 * @return void
	 */
	public void moverX(int trem, double posicao) {
		Platform.runLater(() -> {
			switch (trem) {
				case 1:
					if (trem01 != null) { // Verifique se trem01 está inicializado
						trem01.setX(posicao);// coloca o trem01 na sua nova posição
					}
					break;

				case 2:
					if (trem02 != null) { // Verifique se trem02 está inicializado
						trem02.setX(posicao);// coloca o trem02 na sua nova posição
					}
					break;
			}
		});
	}

	/**
	 * Metodo: moverX
	 * Método chamado para fazer a transição do trem para um ponto X do eixo X
	 * 
	 * @param trem    O trem que será movido pelo eixo Y
	 * @param posicao A quantidade de Y que ele será movido
	 * @return void
	 */
	public void moverY(int trem, double posicao) {
		Platform.runLater(() -> {
			switch (trem) {
				case 1:
					if (trem01 != null) { // Verifique se trem01 está inicializado
						trem01.setY(posicao); // coloca o trem01 na sua nova posição
					}
					break;
				case 2:
					if (trem02 != null) { // Verifique se trem02 está inicializado
						trem02.setY(posicao); // coloca o trem02 na sua nova posição
					}
					break;
			}
		});
	}

	// getter da choiceBox1
	public ChoiceBox<String> getChoiceBox1() {
		return choiceBox1;
	}

	// setter da choiceBox1
	public void setChoiceBox1(ChoiceBox<String> choiceBox1) {
		this.choiceBox1 = choiceBox1;
	}

	// getter da choiceBox2
	public ChoiceBox<String> getChoiceBox2() {
		return choiceBox2;
	}

	// setter da choiceBox2
	public void setChoiceBox2(ChoiceBox<String> choiceBox2) {
		this.choiceBox2 = choiceBox2;
	}

	// getter da rotação do trem
	public double getRotacao() {
		return rotacao;
	}

	/**
	 * Metodo: setRotacao
	 * Método chamado para fazer a rotação dos trens ao longo da animação
	 * 
	 * @param trem    O trem que será rotacionado
	 * @param rotacao A quantidade de graus que ele sofrerá a rotação
	 * @return void
	 */
	public void setRotacao(int trem, double rotacao) {
		Platform.runLater(() -> {
			switch (trem) { // verifica qual trem que será rotacionado
				case 1:
					trem01.setRotate(rotacao);// faz a rotação do trem01
					break;
				case 2:
					trem02.setRotate(rotacao);// faz a rotação do trem02
					break;
			}
		});
	}

	/**
	 * Metodo: getPosicaoX
	 * Método chamado para fazer o getter da posicao no eixo X dos trens
	 * 
	 * @param trem O trem que é desejado saber a posição X
	 * @return a posição do trem no eixo x
	 */
	public double getPosicaoX(int trem) {
		double posicao = 0;
		switch (trem) {// verifica qual o trem específico
			case 1:
				posicao = trem01.getX();
				break;
			case 2:
				posicao = trem02.getX();
				break;
		}
		return posicao; // retorna a posição X do mesmo
	}

	/**
	 * Metodo: getPosicaoY
	 * Método chamado para fazer o getter da posicao no eixo Y dos trens
	 * 
	 * @param trem O trem que é desejado saber a posição Y
	 * @return a posição do trem no eixo Y
	 */
	public double getPosicaoY(int trem) {
		double posicao = 0;
		switch (trem) {// verifica qual o trem específico
			case 1:
				posicao = trem01.getY();
				break;
			case 2:
				posicao = trem02.getY();
				break;
		}
		return posicao; // retorna a posição Y do mesmo
	}

	/**
	 * Metodo: getVelocidadeTrem1
	 * Método chamado para fazer o getter da velocidade do trem 1
	 * 
	 * @return a velocidade do trem 1
	 */
	public int getVelocidadeTrem1() {
		double aux = velocidadeTrem1.getValue();// pega o valor do slider e armazena em uma vriável auxiliar
		int retorno = (int) aux; // transforma o valor em inteiro
		return retorno; // retorna o valor
	}

	/**
	 * Metodo: getVelocidadeTrem1
	 * Método chamado para fazer o getter da velocidade do trem 2
	 * 
	 * @return a velocidade do trem 2
	 */
	public int getVelocidadeTrem2() {
		double aux = velocidadeTrem2.getValue();// pega o valor do slider e armazena em uma vriável auxiliar
		int retorno = (int) aux;// transforma o valor em inteiro
		return retorno;// retorna o valor
	}

	/**
	 * Metodo: sePosicaoTrem1
	 * Método chamado para colocar o trem01 em alguma posição específica
	 * 
	 * @param x A posição no eixo X que será colocado o mesmo
	 * @param y A posição no eixo y que será colocado o mesmo
	 * @return void
	 */
	public void setPosicaoTrem1(int x, int y) {
		trem01.setX(x);
		trem01.setY(y);
	}

	/**
	 * Metodo: sePosicaoTrem2
	 * Método chamado para colocar o trem02 em alguma posição específica
	 * 
	 * @param x A posição no eixo X que será colocado o mesmo
	 * @param y A posição no eixo y que será colocado o mesmo
	 * @return void
	 */
	public void setPosicaoTrem2(int x, int y) {
		trem02.setX(x);
		trem02.setY(y);
	}

	// Getters e setters para variáveis de controle

	public int getVariavelTravamento1() {
		return variavelTravamento1;
	}

	public void setVariavelTravamento1(int variavelTravamento1) {
		this.variavelTravamento1 = variavelTravamento1;
	}

	public int getVariavelTravamento2() {
		return variavelTravamento2;
	}

	public void setVariavelTravamento2(int variavelTravamento2) {
		this.variavelTravamento2 = variavelTravamento2;
	}

	public int getEstritaAlternancia1() {
		return EstritaAlternancia1;
	}

	public void setEstritaAlternancia1(int estritaAlternancia1) {
		EstritaAlternancia1 = estritaAlternancia1;
	}

	public int getEstritaAlternancia2() {
		return EstritaAlternancia2;
	}

	public void setEstritaAlternancia2(int estritaAlternancia2) {
		EstritaAlternancia2 = estritaAlternancia2;
	}

	public boolean getBandeira1(int processo) {
		return bandeira1[processo];
	}

	public void setBandeira1(int processo, boolean valor) {
		bandeira1[processo] = valor;
	}

	public boolean getBandeira2(int processo) {
		return bandeira2[processo];
	}

	public void setBandeira2(int processo, boolean valor) {
		bandeira2[processo] = valor;
	}

	public int getTurn1() {
		return turn1;
	}

	public void setTurn1(int turn) {
		this.turn1 = turn;
	}

	public int getTurn2() {
		return turn2;
	}

	public void setTurn2(int turn) {
		this.turn2 = turn;
	}

}
