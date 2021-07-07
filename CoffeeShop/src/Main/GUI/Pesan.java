package Main.GUI;

import Main.Controller.DataProduk;
import Main.Controller.Koneksi;
import Main.Controller.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Pesan extends JFrame{
	Koneksi koneksi = new Koneksi();
	ResultSet resultSet;
	Statement statement;

	DataProduk produk;
	List<DataProduk> produks=new ArrayList<>();

	boolean pesan = false;

	JFrame window	 = new JFrame("PESAN KOPI");
	JLabel lKopi	 = new JLabel("Kopi");
	JComboBox<Object> cKopi	 = new JComboBox<>();
	JLabel lJmlh	 = new JLabel("Jumlah");
	JTextField fjmlh = new JTextField();
	JButton bPesan	 = new JButton("Pesan");
	JButton bKembali = new JButton("Kembali");

	public Pesan(){
		if(UserSession.getId_user() == null){
			JOptionPane.showMessageDialog(null, "Silahkan login terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
			window.setVisible(false);
			new Login();
		}else {
			initComponents();
			initListeners();
			loadKopi();
		}
	}

	private void initComponents() {
		window.getContentPane().setBackground(new Color(28, 27, 27));

		window.add(lKopi);
		lKopi.setBounds(50,25,120,25);
		lKopi.setForeground(new Color(255, 255, 255));
			window.add(cKopi);
			cKopi.setBounds(110,25,210,25);

		window.add(lJmlh);
		lJmlh.setBounds(50,60,120,25);
		lJmlh.setForeground(new Color(255, 255, 255));
			window.add(fjmlh);
			fjmlh.setBounds(110,60,210,25);

		window.add(bPesan);
		bPesan.setBounds(110,100,115,25);
		bPesan.setForeground(new Color(255,255,255));
		bPesan.setBackground(new Color(58, 133, 86));

		window.add(bKembali);
		bKembali.setBounds(230,100,90,25);
		bKembali.setForeground(new Color(255,255,255));
		bKembali.setBackground(new Color(145, 141, 58));

		window.setLayout(null);
		window.setSize(390, 200);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
	}
	
	private void initListeners(){
		DataProduk produk = new DataProduk();
		TabelProduk tabelProduk = new TabelProduk();
		bPesan.addActionListener(e -> {
			produk.setHarga(produks.get(cKopi.getSelectedIndex()).getHarga());
			produk.setStok(produks.get(cKopi.getSelectedIndex()).getStok());
			DataProduk.setTotal(produks.get(cKopi.getSelectedIndex()).getHarga() * Integer.parseInt(fjmlh.getText()));

			pesanKopi(produks.get(cKopi.getSelectedIndex()).getIdKopi(), fjmlh.getText());
			if (pesan = true) {
				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "Berhasil Memesan!");
				int result = JOptionPane.showConfirmDialog(null, "Ingin Memesan Lagi?", "INFO", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					tabelProduk.window.setVisible(false);
					window.setVisible(false);
					new Pesan();
				} else {
					int result2 = JOptionPane.showConfirmDialog(null, "Ingin Membayar?", "INFO", JOptionPane.YES_NO_OPTION);
					if (result2 == JOptionPane.YES_OPTION) {
						tabelProduk.window.setVisible(false);
						window.setVisible(false);
						new Bayar();
					} else {
						tabelProduk.window.setVisible(false);
						window.setVisible(false);
						new MenuUtama();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "gagal Memesan!");
			}

		});

		bKembali.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabelProduk.window.setVisible(false);
				window.setVisible(false);
				new MenuUtama();
			}
		});
	}


	private void pesanKopi(String vid_kopi, String vjumlah){
		int jumlah = Integer.parseInt(vjumlah);
		String kode;
		kurangStok(vid_kopi,jumlah);
		try{
			if(UserSession.getIdPemesanan() == null) {
				statement = koneksi.getConnection().createStatement();
				String sqlMax = "SELECT max(id_pemesanan) as max_kode FROM pemesanan";
				resultSet = statement.executeQuery(sqlMax);
				if (resultSet.next()) {
					String kode_pmsn = resultSet.getString("max_kode");
					if(kode_pmsn == null ){
						kode = "PMSN-001";
						UserSession.setIdPemesanan(kode);
					}else {
						String kode_pmsn_bersih = kode_pmsn.substring(5, 8);
						int no_urut = Integer.parseInt(kode_pmsn_bersih);
						no_urut += 1;

						String pmsn = "PMSN-";
						kode = pmsn + String.format("%03d", no_urut);

						UserSession.setIdPemesanan(kode);
					}
					statement.executeUpdate("INSERT INTO pemesanan VALUES('" + kode + "','" + UserSession.getId_user() + "','" + vid_kopi + "','" + jumlah + "')");
					statement.executeUpdate("UPDATE produk set stok='"+kurangStok(vid_kopi,jumlah)+"' WHERE id_kopi='"+vid_kopi+"'");
					pesan = true;
				}
			}else{
				statement.executeUpdate("INSERT INTO pemesanan VALUES('" + UserSession.getIdPemesanan() + "','" + UserSession.getId_user() + "','" + vid_kopi + "','" + jumlah + "')");
				pesan = true;
			}
			resultSet.close();

		}catch (SQLException sqlError) {
			JOptionPane.showMessageDialog(rootPane, "Data Gagal Ditampilkan" + sqlError);
		} catch (ClassNotFoundException classError) {
			JOptionPane.showMessageDialog(rootPane, "Driver tidak ditemukan !!");
		}catch (NumberFormatException e){
			System.err.println("error"+e);
		}
	}

	private int kurangStok(String vid, int vjumlah){
		int stok = 0;
		try{
			statement = koneksi.getConnection().createStatement();
			String query = "SELECT * FROM produk WHERE id_kopi='"+ vid +"'";
			resultSet = statement.executeQuery(query);
			resultSet.next();
			stok = resultSet.getInt("stok");
			stok -= vjumlah;
		}catch (SQLException sqlError) {
			JOptionPane.showMessageDialog(rootPane, "Data Gagal Ditampilkan" + sqlError);
		} catch (ClassNotFoundException classError) {
			JOptionPane.showMessageDialog(rootPane, "Driver tidak ditemukan !!");
		}catch (NumberFormatException e){
			System.err.println("error"+e);
		}
		return stok;
	}

	private List<DataProduk> getAllProduk() {
		try {
			statement = koneksi.getConnection().createStatement();
			String sql = "SELECT * FROM produk";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				produk = new DataProduk();
				produk.setIdKopi(resultSet.getString("id_kopi"));
				produk.setNamaKopi(resultSet.getString("nama_kopi"));
				produk.setHarga(resultSet.getInt("harga"));
				produk.setStok(resultSet.getInt("stok"));
				produks.add(produk);
			}
		} catch (SQLException sqlError) {
			JOptionPane.showMessageDialog(rootPane, "Data Gagal Ditampilkan" + sqlError);
		} catch (ClassNotFoundException classError) {
			JOptionPane.showMessageDialog(rootPane, "Driver tidak ditemukan !!");
		}
		return produks;
	}

	private void loadKopi() {
		cKopi.removeAllItems();
		List<DataProduk> produks = getAllProduk();
		for (DataProduk data : produks) {
			cKopi.addItem(data.getNamaKopi().toString());
		}
	}
}