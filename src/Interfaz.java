import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interfaz {
    JFrame calc, hist;
    JButton calcular, historial, suma, resta, mult, div, perc;
    JButton[][] digitos;
    JTextField operacion;
    int filas = 5;
    int columns = 4;

    public Interfaz(){
        calcFrame();
    }

    public void calcFrame(){
        int cont = 1;
        int x = 10;
        int y = 150;

        calc = new JFrame();
        calc.setBackground(Color.CYAN);
        calc.setSize(440, 700);
        calc.getContentPane().setBackground(Color.BLACK);
        calc.setTitle("CASIO Poderosa - HL310");
        calc.setLocationRelativeTo(null);
        calc.setResizable(false);
        calc.setLayout(null);
        calc.setVisible(true);
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        operacion = new JTextField();
        operacion.setBounds(10, 30, 400, 70);
        operacion.setHorizontalAlignment(JTextField.RIGHT);
        operacion.setFont(new Font("Arial", Font.BOLD, 28));
        calc.add(operacion);

        digitos = new JButton[filas][columns];

        for(int j = 1; j < columns; j++){
            for(int i = 1; i < filas; i++){
                if(cont == 10){
                    digitos[i][j] = new JButton("C");
                    digitos[i][j].setBackground(Color.YELLOW);
                }else if(cont == 11){
                    digitos[i][j] = new JButton("0");
                    digitos[i][j].setBackground(Color.CYAN);
                }else if(cont == 12){
                    digitos[i][j] = new JButton("E");
                    digitos[i][j].setBackground(Color.YELLOW);
                }else{
                    digitos[i][j] = new JButton(String.valueOf(cont));
                    digitos[i][j].setBackground(Color.CYAN);
                }

                //se instancia una clase de tipo ControlBtns (definida abajo) para manejar el comportamiento de los botones en esta matriz
                ControlBtns bt = new ControlBtns();
                digitos[i][j].addActionListener(bt);
                digitos[i][j].setName(String.valueOf(cont));
                digitos[i][j].setBounds(x,y, 100, 100);
                digitos[i][j].setFont(new Font("Courier", Font.BOLD, 28));
                calc.add(digitos[i][j]);

                //contadores importantes
                y += 100;
                cont += 3;
            }

            //contadores importantes
            cont = j;
            cont ++;
            x += 100;
            y = 150;
        }

        //Fuera del for, se definen los botones "operadores" y su respectivo comportamiento
        suma = new JButton("+");
        suma.setBackground(Color.GREEN);
        suma.setBounds(310,150,100,100);
        suma.setFont(new Font("Arial", Font.BOLD, 28));
        calc.add(suma);
        suma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion.setText(operacion.getText() + "+");
            }
        });

        resta = new JButton("-");
        resta.setBackground(Color.GREEN);
        resta.setBounds(310,250,100,100);
        resta.setFont(new Font("Arial", Font.BOLD, 34));
        calc.add(resta);
        resta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion.setText(operacion.getText() + "-");
            }
        });

        mult = new JButton("*");
        mult.setBackground(Color.GREEN);
        mult.setBounds(310,350,100,100);
        mult.setFont(new Font("Arial", Font.BOLD, 30));
        calc.add(mult);
        mult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion.setText(operacion.getText() + "*");
            }
        });

        div = new JButton("/");
        div.setBackground(Color.GREEN);
        div.setBounds(310,450,100,100);
        div.setFont(new Font("Arial", Font.BOLD, 28));
        calc.add(div);
        div.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion.setText(operacion.getText() + "*");
            }
        });

        perc = new JButton("%");
        perc.setBackground(Color.GREEN);
        perc.setBounds(310, 550, 100, 100);
        perc.setFont(new Font("Arial", Font.BOLD, 28));
        calc.add(perc);
        perc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion.setText(operacion.getText() + "%");
            }
        });

        //ESTA ES LA SECCIÓN MÁS IMPORTANTE!!!!!!!
        calcular = new JButton("=");
        calcular.setBackground(Color.WHITE);
        calcular.setBounds(210,550,100,100);
        calcular.setFont(new Font("Arial", Font.BOLD, 28));
        calc.add(calcular);
        calcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aquí se involucrarían los Sockets y el árbol. Ver línea 141 de la clase Interfaz");
            }
        });

        historial = new JButton("Historial");
        historial.setBackground(Color.WHITE);
        historial.setBounds(10,550, 200, 100);
        historial.setFont(new Font("Arial", Font.BOLD, 22));
        calc.add(historial);
        historial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                histFrame();
            }
        });

        SwingUtilities.updateComponentTreeUI(calc);
    }

    private class ControlBtns implements ActionListener{ //clase para el comportamiento de los botones de la matriz
        @Override
        public void actionPerformed(ActionEvent e) {

            for(int i=1; i<filas; i++){
                for(int j=1; j<columns; j++){

                    if(e.getSource().equals(digitos[i][j])){

                        if(digitos[i][j].getText().equals("C")){
                            operacion.setText("");
                        }else if(digitos[i][j].getText().equals("E")){
                            operacion.setText("Esto borraría un elemento");
                        }else{
                            operacion.setText(operacion.getText() + digitos[i][j].getText()); // añade números en el JTextField
                        }
                    }
                }
            }
        }
    }
    public void histFrame(){ // función para crear la ventana del historial
        hist = new JFrame();
        hist.setBackground(Color.CYAN);
        hist.setSize(440, 700);
        hist.getContentPane().setBackground(Color.BLACK);
        hist.setTitle("HISTORIAL");
        hist.setLocationRelativeTo(null);
        hist.setResizable(false);
        hist.setLayout(null);
        hist.setVisible(true);
        hist.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
