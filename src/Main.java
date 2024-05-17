import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Jogo jogo;
    private static JButton[][] botoes;

    public static void main(String[] args) {
        jogo = new Jogo();

        // Criar o frame (janela)
        JFrame frame = new JFrame("Bem vindo!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel inicio = new JPanel();
        JButton buttonjogar = new JButton("Jogar");
        JButton buttonsair = new JButton("Sair");

        inicio.add(buttonjogar);
        inicio.add(buttonsair);
        frame.add(inicio);
        frame.setVisible(true);

        buttonjogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameJogo = new JFrame("Jogo Do Galo");
                frameJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameJogo.setSize(600, 600);
                
                // Criar um painel com grid layout
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 3));

                botoes = new JButton[3][3];

                // Inicializar os botões
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        JButton button = new JButton("");
                        button.setFont(new Font("Arial", Font.PLAIN, 100));
                        final int linha = i;
                        final int coluna = j;
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (jogo.marcarPosicao(linha, coluna)) {
                                    button.setText(String.valueOf(jogo.getJogadorAtual()));
                                    if (jogo.verificarVencedor()) {
                                        JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogadorAtual() + " venceu!");
                                        reiniciarJogo();
                                    } else if (jogo.isEmpate()) {
                                        JOptionPane.showMessageDialog(null, "Empate!");
                                        reiniciarJogo();
                                    } else {
                                        jogo.trocarJogador();
                                    }
                                }
                            }
                        });
                        botoes[i][j] = button;
                        panel.add(button);
                    }
                }
                frameJogo.add(panel);
                frameJogo.setVisible(true);
            }
        });

        buttonsair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private static void reiniciarJogo() {
        jogo.inicializarTabuleiro();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
            }
        }
    }
}
