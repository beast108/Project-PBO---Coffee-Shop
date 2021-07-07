-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Jul 2021 pada 12.37
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffeeshop`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pesanan`
--

CREATE TABLE `detail_pesanan` (
  `id_pesanan` int(11) NOT NULL,
  `id_pemesanan` varchar(22) COLLATE utf8mb4_unicode_ci NOT NULL,
  `no_kursi` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tanggal_pemesanan` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `total` int(11) NOT NULL,
  `status` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `detail_pesanan`
--

INSERT INTO `detail_pesanan` (`id_pesanan`, `id_pemesanan`, `no_kursi`, `tanggal_pemesanan`, `total`, `status`) VALUES
(1, 'PMSN-037', '004', '2020-05-17 05:15:55', 150000, 'TELAH DIBAYAR'),
(2, 'PMSN-038', '003', '2020-05-17 11:17:29', 24000, 'TELAH DIBAYAR'),
(3, 'PMSN-039', '002', '2020-05-17 11:26:27', 24000, 'TELAH DIBAYAR'),
(4, 'PMSN-040', '002', '2020-05-17 11:28:11', 24000, 'TELAH DIBAYAR'),
(5, 'PMSN-041', '002', '2020-05-17 14:04:45', 54000, 'TELAH DIBAYAR'),
(6, 'PMSN-042', '002', '2020-05-17 14:07:43', 54000, 'TELAH DIBAYAR'),
(7, 'PMSN-044', '006', '2020-05-18 15:30:07', 93000, 'TELAH DIBAYAR'),
(8, 'PMSN-047', '003', '2020-05-18 15:39:41', 27000, 'TELAH DIBAYAR'),
(9, 'PMSN-048', '002', '2020-05-18 15:47:59', 36000, 'TELAH DIBAYAR'),
(10, 'PMSN-048', '005', '2020-05-18 15:49:15', 12000, 'TELAH DIBAYAR'),
(11, 'PMSN-048', '004', '2020-05-18 15:50:12', 12000, 'TELAH DIBAYAR'),
(12, 'PMSN-048', '006', '2020-05-18 17:08:45', 54000, 'TELAH DIBAYAR'),
(13, 'PMSN-048', '003', '2020-05-18 17:10:09', 54000, 'TELAH DIBAYAR'),
(14, 'PMSN-048', '005', '2020-05-18 17:10:22', 66000, 'TELAH DIBAYAR'),
(15, 'PMSN-049', '001', '2021-07-05 11:41:50', 6000, 'TELAH DIBAYAR'),
(16, 'PMSN-050', '004', '2021-07-05 12:26:52', 6000, 'TELAH DIBAYAR'),
(17, 'PMSN-051', '006', '2021-07-07 10:11:38', 18000, 'TELAH DIBAYAR'),
(18, 'PMSN-052', '005', '2021-07-07 10:29:23', 36000, 'TELAH DIBAYAR');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dompet`
--

CREATE TABLE `dompet` (
  `id_dompet` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `dompet`
--

INSERT INTO `dompet` (`id_dompet`, `id_user`, `jumlah`) VALUES
(3, 1, 113000),
(4, 9, 21000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemesanan`
--

CREATE TABLE `pemesanan` (
  `id_pemesanan` varchar(22) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_kopi` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `pemesanan`
--

INSERT INTO `pemesanan` (`id_pemesanan`, `id_user`, `id_kopi`, `jumlah`) VALUES
('PMSN-048', 1, 1, 1),
('PMSN-048', 1, 4, 2),
('PMSN-049', 1, 2, 1),
('PMSN-050', 1, 1, 1),
('PMSN-051', 9, 2, 1),
('PMSN-051', 9, 1, 2),
('PMSN-052', 9, 3, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `produk`
--

CREATE TABLE `produk` (
  `id_kopi` int(11) NOT NULL,
  `nama_kopi` varchar(150) NOT NULL,
  `harga` int(255) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `produk`
--

INSERT INTO `produk` (`id_kopi`, `nama_kopi`, `harga`, `stok`) VALUES
(1, 'Americano', 6000, 28),
(2, 'MilkShake', 6000, 28),
(3, 'Cappucino', 9000, 15),
(4, 'Mocacinno', 15000, 20);

-- --------------------------------------------------------

--
-- Struktur dari tabel `riwayat_saldo`
--

CREATE TABLE `riwayat_saldo` (
  `idRiwayatSaldo` int(11) NOT NULL,
  `id_dompet` int(11) NOT NULL,
  `riwayat_saldo` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tanggal_transaksi` timestamp NOT NULL DEFAULT current_timestamp(),
  `keterangan` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `riwayat_saldo`
--

INSERT INTO `riwayat_saldo` (`idRiwayatSaldo`, `id_dompet`, `riwayat_saldo`, `tanggal_transaksi`, `keterangan`) VALUES
(12, 3, '+ 25000', '2021-07-05 11:41:23', 'PENGISIAN SALDO'),
(13, 3, '- 6000', '2021-07-05 11:41:50', 'PEMBELIAN'),
(14, 3, '- 6000', '2021-07-05 12:26:52', 'PEMBELIAN'),
(15, 3, '+ 100000', '2021-07-05 12:28:30', 'PENGISIAN SALDO'),
(16, 4, '+ 25000', '2021-07-07 10:11:01', 'PENGISIAN SALDO'),
(17, 4, '- 18000', '2021-07-07 10:11:38', 'PEMBELIAN'),
(18, 4, '+ 50000', '2021-07-07 10:28:57', 'PENGISIAN SALDO'),
(19, 4, '- 36000', '2021-07-07 10:29:23', 'PEMBELIAN');

-- --------------------------------------------------------

--
-- Struktur dari tabel `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `nama_role` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `role`
--

INSERT INTO `role` (`id_role`, `nama_role`) VALUES
(1, 'Admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sandi` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int(11) NOT NULL,
  `dibuat` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `diubah` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `nama`, `email`, `sandi`, `role`, `dibuat`, `diubah`) VALUES
(1, 'Adrianus Wisnu P', 'wisnu123@gmail.com', '200a026ae2ff18ce80a4bb27e696366b', 1, '2021-07-05 11:46:55', '2021-07-05 11:45:11'),
(9, 'Abigail Rakha', 'kudusjaya@gmail.com', 'f8363743077777056ab201cb23932111', 2, '2021-07-07 10:32:08', '2021-07-07 10:31:49');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD PRIMARY KEY (`id_pesanan`);

--
-- Indeks untuk tabel `dompet`
--
ALTER TABLE `dompet`
  ADD PRIMARY KEY (`id_dompet`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD KEY `id_user` (`id_user`,`id_kopi`),
  ADD KEY `id_kopi` (`id_kopi`);

--
-- Indeks untuk tabel `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_kopi`);

--
-- Indeks untuk tabel `riwayat_saldo`
--
ALTER TABLE `riwayat_saldo`
  ADD PRIMARY KEY (`idRiwayatSaldo`),
  ADD KEY `id_user` (`id_dompet`);

--
-- Indeks untuk tabel `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `role` (`role`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  MODIFY `id_pesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT untuk tabel `dompet`
--
ALTER TABLE `dompet`
  MODIFY `id_dompet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `produk`
--
ALTER TABLE `produk`
  MODIFY `id_kopi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `riwayat_saldo`
--
ALTER TABLE `riwayat_saldo`
  MODIFY `idRiwayatSaldo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `dompet`
--
ALTER TABLE `dompet`
  ADD CONSTRAINT `dompet_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD CONSTRAINT `pemesanan_ibfk_1` FOREIGN KEY (`id_kopi`) REFERENCES `produk` (`id_kopi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pemesanan_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `riwayat_saldo`
--
ALTER TABLE `riwayat_saldo`
  ADD CONSTRAINT `riwayat_saldo_ibfk_1` FOREIGN KEY (`id_dompet`) REFERENCES `dompet` (`id_dompet`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
