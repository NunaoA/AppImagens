package view;


import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class FEmpregado {
        private JPanel panelEmpregado;
        private JTextField textFieldID;
        private JTextField textFieldNome;
        private JTextField textFieldSalario;
        private JButton procurarButton;
        private JButton guardarButton;
    private JLabel labelImagem;

            private String path=null;
            private byte[] userImage;
            private Connection con;
            private PreparedStatement ps;
            private ResultSet rs;


            public void setVisible(boolean b)
            {
                JFrame frame = new JFrame("Consulta de funcionários");
                frame.setContentPane(new FEmpregado().panelEmpregado);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(700,600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(b);
            }


        public FEmpregado() {
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String nome=textFieldNome.getText();
                        double salario=Double.valueOf(textFieldSalario.getText());

                        try
                        {
                           //File file=new File(path);
                            //FileInputStream fs=new FileInputStream(file);
                            Class.forName("com.mysql.jdbc.Driver");
                            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempregadosn","root","1234");
                            ps=con.prepareStatement("insert into empregados(nome, salario, foto)" +
                                    " values(?,?,?)");
                            ps.setString(1,nome);
                            ps.setDouble(2,salario);
                            ps.setBytes(3,userImage);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Empregado adicionado com sucesso!");
                            textFieldID.setText("");
                            textFieldNome.setText("");
                            textFieldSalario.setText("");
                            labelImagem.setIcon(null);

                     //   } catch (FileNotFoundException e1) {
                     //       e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                    }
                });

                procurarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser imgChoose=new JFileChooser();
                        imgChoose.showOpenDialog(null);
                        File img=imgChoose.getSelectedFile();

                        path=img.getAbsolutePath();
                        BufferedImage image;
                        try{
                            //colocar a imagem selecionada na label
                            image= ImageIO.read(imgChoose.getSelectedFile());
                            ImageIcon imgIcon=
                                    new ImageIcon(new ImageIcon(image).getImage().
                                            getScaledInstance(250,250, Image.SCALE_DEFAULT));
                            labelImagem.setIcon(imgIcon);

                            //Preparar a imagem e guarda-la na variavel "userimage"
                            // para poder ser guardada na base de dados
                            File imgg=new File(path);
                            FileInputStream fs=new FileInputStream(imgg);
                            ByteArrayOutputStream bos=new ByteArrayOutputStream();
                            byte[] buff=new byte[1024];
                            int nBytes_read=0;

                            while((nBytes_read=fs.read(buff)) !=-1) {
                                bos.write(buff, 0, nBytes_read);
                            }
                            userImage=bos.toByteArray();

                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                });
            }
        }

