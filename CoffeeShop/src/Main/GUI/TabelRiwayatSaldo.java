package Main.GUI;

import Main.Controller.DataDompet;
import Main.Controller.Koneksi;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TabelRiwayatSaldo {
    NumberFormat nf = NumberFormat.getInstance(new Locale("da", "DK"));

    JFrame window 	        = new JFrame("Riwayat Saldo");
    String[][] datas        = new String[500][4];
    String[] kolom          = {"#", "Riwayat", "Pada Tanggal", "keterangan"};
    JTable tTable           = new JTable(datas, kolom);
    JScrollPane scrollPane  = new JScrollPane(tTable);

    ResultSet resultSet;
    Statement statement;

    public TabelRiwayatSaldo(){
        initComponents();
        loadData();
    }

    private void initComponents() {
        window.getContentPane().setBackground(new Color(28, 27, 27));
        tTable.setBackground(new Color(247, 252, 255));
        TableColumnModel columnModel = tTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(3).setPreferredWidth(70);
        tTable.setEnabled(false);
        tTable.setFont(new Font("Arial", Font.PLAIN,12));
        tTable.setRowHeight(20);
        window.add(scrollPane);
        scrollPane.setBounds(70, 70, 400, 400);
        scrollPane.setBackground(new Color(247, 252, 255));
        window.setSize(470, 420);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    private void loadData(){
        Koneksi koneksi = new Koneksi();
        try{
            statement = koneksi.getConnection().createStatement();
            String sql = "SELECT * FROM riwayat_saldo WHERE id_dompet ='" + DataDompet.getIdDompet() +"'";
            resultSet = statement.executeQuery(sql);

            int row = 0;
            while (resultSet.next()){
                datas[row][0] = String.valueOf(row+1);
                datas[row][1] = resultSet.getString("riwayat_saldo");
                datas[row][2] = resultSet.getString("tanggal_transaksi");
                datas[row][3] = resultSet.getString("keterangan");
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