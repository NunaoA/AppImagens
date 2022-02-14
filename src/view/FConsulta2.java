package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FConsulta2 {


    //variaveis
    private String path=null;
    private byte[] userimage;
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private JTextField textFieldId;
    private JTextField textFieldNome;
    private JTextField textFieldSalario;
    private JLabel labelImagem;
    private JButton buttonPrimeiro;
    private JButton buttonAnterior;
    private JButton buttonProximo;
    private JButton buttonUltimo;
    private JPanel panelConsulta;

    public void Connection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempregadosn","root","1234");
            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery("select id, nome, foto, salario from empregados");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean b)
    {
        JFrame frame = new JFrame("Consulta de funcionários");
        frame.setContentPane(new FConsulta2().panelConsulta);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }

    public FConsulta2(){
        Connection();
        buttonPrimeiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    rs.first();
                    textFieldId.setText(String.valueOf(rs.getInt("id")));
                    textFieldNome.setText(rs.getString("nome"));
                    textFieldSalario.
                            setText(String.valueOf(rs.getDouble("salario")));
                    Blob blob=rs.getBlob("foto");
                    byte[] imageBytes=blob.getBytes(1,(int)blob.length());
                    ImageIcon imgIcon= new ImageIcon(new ImageIcon(imageBytes).
                            getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT));
                    labelImagem.setIcon(imgIcon);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if(!rs.isFirst()) {
                        rs.previous();
                        textFieldId.setText(String.valueOf(rs.getInt("id")));
                        textFieldNome.setText(rs.getString("nome"));
                        textFieldSalario.
                                setText(String.valueOf(rs.getDouble("salario")));
                        Blob blob = rs.getBlob("foto");
                        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                        ImageIcon imgIcon = new ImageIcon(new ImageIcon(imageBytes).
                                getImage().getScaledInstance(250, 250,
                                Image.SCALE_DEFAULT));
                        labelImagem.setIcon(imgIcon);
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        buttonProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(rs.isLast()) {
                        rs.next();
                        textFieldId.setText(String.valueOf(rs.getInt("id")));
                        textFieldNome.setText(rs.getString("nome"));
                        textFieldSalario.
                                setText(String.valueOf(rs.getDouble("salario")));
                        Blob blob = rs.getBlob("foto");
                        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                        ImageIcon imgIcon = new ImageIcon(new ImageIcon(imageBytes).
                                getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
                        labelImagem.setIcon(imgIcon);
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    rs.last();
                    textFieldId.setText(String.valueOf(rs.getInt("id")));
                    textFieldNome.setText(rs.getString("nome"));
                    textFieldSalario.
                            setText(String.valueOf(rs.getDouble("salario")));
                    Blob blob=rs.getBlob("foto");
                    byte[] imageBytes=blob.getBytes(1,(int)blob.length());
                    ImageIcon imgIcon= new ImageIcon(new ImageIcon(imageBytes).
                            getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT));
                    labelImagem.setIcon(imgIcon);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
