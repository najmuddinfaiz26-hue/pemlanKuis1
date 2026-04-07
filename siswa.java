package KuisUTP;

import java.util.*;
//Muhammad Faiz Najmuddin
//255150707111013
class Kendaraan {
    String kode;
    String jenis;
    String nama;
    int harga;
    boolean tersedia;

    Kendaraan(String jenis, String kode, String nama, int harga) {
        this.jenis = jenis;
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.tersedia = true; 
    }
}

public class MediumSiswa {

    static List<Kendaraan> daftarKendaraan = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            String perintah = parts[0];

            if (perintah.equals("ADD")) {
                String jenis = parts[1];
                String kode = parts[2];
                String nmKendaraan = parts[3];
                int hargaPerHari = Integer.parseInt(parts[4]);

                if (cariKendaraan(kode) != null) {
                    System.out.println("Kendaraan sudah terdaftar");
                } else {
                    daftarKendaraan.add(new Kendaraan(jenis, kode, nmKendaraan, hargaPerHari));
                    System.out.println(jenis + " " + kode + " berhasil ditambahkan");
                }

            } else if (perintah.equals("RENT")) {
                String kode = parts[1];
                int hari = Integer.parseInt(parts[2]);
                boolean adaPromo = parts.length > 3 && parts[3].equalsIgnoreCase("PROMO");

                Kendaraan k = cariKendaraan(kode);

                if (k == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else if (!k.tersedia) {
                    System.out.println("Kendaraan sedang disewa");
                } else {
                    long total = (long) k.harga * hari;

                    if (adaPromo) {
                        if (k.jenis.equals("CAR")) total -= 20000;
                        else if (k.jenis.equals("BIKE")) total -= 10000;
                    }

                    if (total < 0) total = 0;

                    k.tersedia = false;
                    System.out.println("Total sewa " + kode + ": " + total);
                }

    } else if (perintah.equals("RETURN")) {
                String kode = parts[1];
                Kendaraan k = cariKendaraan(kode);

                if (k == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                   } else if (k.tersedia) {
                    System.out.println("Kendaraan belum disewa");
                  } else {
                    k.tersedia = true;
                    System.out.println(kode + " berhasil dikembalikan");
                }

                   } else if (perintah.equals("DETAIL")) {
                String kode = parts[1];
                Kendaraan k = cariKendaraan(kode);

                if (k == null) {
                    System.out.println("Kendaraan tidak ditemukan");
            } else {
                    String status = k.tersedia ? "TERSEDIA" : "DISEWA";
                    System.out.println(k.kode + " | " + k.jenis + " | " + k.nama
                            + " | harga: " + k.harga + " | status: " + status);
                }

            } else if (perintah.equals("COUNT")) {
                System.out.println("Total kendaraan: " + daftarKendaraan.size());
            }
        }
        sc.close();
    }

    static Kendaraan cariKendaraan(String kode) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.kode.equals(kode)) {
                return k;
            }
        }
        return null;
    }
}
