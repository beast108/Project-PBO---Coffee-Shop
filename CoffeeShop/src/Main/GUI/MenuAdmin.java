package Main.GUI;

import Main.Controller.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin {
    JFrame window 		= new JFrame("MENU ADMIN");
    JButton bKuser 	    = new JButton("Kelola User");
    JButton bKproduk 	= new JButton("Kelola Produk");
    JButton bKembali 	= new JButton("Kembali");

    public MenuAdmin(){
        if(UserSession.getId_user() == null){
            JOptionPane.showMessageDialog(null, "Silahkan login terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            window.setVisible(false);
            new Login();
        }else if(UserSession.getRole() != 1) {
            JOptionPane.showMessageDialog(null, "Akses tidak diberikan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            window.setVisible(false);
            new Login();
        }else{
            initComponents();
            initListeners();
        }
    }

    private void initComponents(){
        window.getContentPane().setBackground(new Color(28, 27, 27));

        window.add(bKuser);
            bKuser.setBounds(47, 80, 200, 30);
            bKuser.setForeground(new Color(255, 255, 255));
            bKuser.setBackground(new Color(82, 77, 64));

        window.add(bKproduk);
            bKproduk.setBounds(47, 120, 200, 30);
            bKproduk.setForeground(new Color(255,255,255));
            bKproduk.setBackground(new Color(82, 77, 64));

        window.add(bKembali);
            bKembali.setBounds(47, 160, 200, 30);
            bKembali.setForeground(new Color(255,255,255));
            bKembali.setBackground(new Color(82, 77, 64));

        window.setLayout(null);
        window.setSize(300, 300);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
    }

    private void initListeners(){
        bKuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                new KelolaUser();
            }
        });

        bKproduk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                new KelolaProduk();
            }
        });

        bKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                new MenuUtama();
            }
        });
    }
}