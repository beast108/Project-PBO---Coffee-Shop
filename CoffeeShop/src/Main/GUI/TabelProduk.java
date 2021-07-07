package Main.GUI;

import Main.Controller.Koneksi;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;

public class TabelProduk {
    JFrame window 	        = new JFrame("Tabel Produk");
    String[][] datas        = new String[100][3];
    String[] kolom          = {"Nama Kopi","Harga", "Stok"};
    JTable tTable           = new JTable(datas,kolom);
    JScrollPane scrollPane  = new JScrollPane(tTable);
    ResultSet resultSet;
    Statement statement;

    public TabelProduk() {
        initComponents();
        loadData();
    }

    private void initComponents(){
        window.getContentPane().setBackground(new Color(28, 27, 27));
        TableColumnModel columnModel = tTable.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(150);
            columnModel.getColumn(1).setPreferredWidth(60);
            columnModel.getColumn(2).setPreferredWidth(10);
            window.add(scrollPane,BorderLayout.CENTER);
                tTable.setEnabled(false);
                tTable.setFont(new Font("Arial", Font.BOLD,17));
                tTable.setRowHeight(30);
            scrollPane.setBounds(70, 70, 400, 400);

        window.setSize(470, 420);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    private void loadData(){
        Koneksi koneksi = new Koneksi();
        try{
            statement = koneksi.getConnection().createStatement();
            String sql = "SELECT * FROM produk";
            resultSet = statement.executeQuery(sql);
            NumberFormat nf = NumberFormat.getInstance(new Locale("da", "DK"));
            int row = 0;
            while (resultSet.next()){
                datas[row][0] = resultSet.getString("nama_kopi");
                datas[row][1] = "Rp." + nf.format(resultSet.getInt("harga"));
                datas[row][2] = resultSet.getString("stok");
                row++;
            }
            statement.close();

        } catch (SQLException sqlError) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + sqlError);
        } catch (ClassNotFoundException classError) {
            JOptionPane.showMessageDialog(null, "Driver tidak ditemukan !!");
        }
    }
}