package KuisUTP;

import java.util.*;
//Muhammad Faiz Najmuddin
//255150707111013
class Akun {
    String nama, jenis;
    int saldo;

    Akun(String jenis, String nama) {
        this.jenis = jenis;
        this.nama = nama;
        this.saldo = 0;
    }
}

public class Easytabungan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Akun> daftarAkun = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!sc.hasNextLine()) break;
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ");
            String perintah = parts[0];

            if (perintah.equals("CREATE")) {
                String jenis = parts[1];
                String nama = parts[2];
                daftarAkun.add(new Akun(jenis, nama));
                System.out.println(jenis + " " + nama + " berhasil dibuat");

            } else if (perintah.equals("SAVE")) {
                String nama = parts[1];
                int jumlah = Integer.parseInt(parts[2]);
                Akun a = cari(daftarAkun, nama);
                if (a != null) {
                    a.saldo += jumlah;
                    System.out.println("Saldo " + nama + ": " + a.saldo);
                }

            } else if (perintah.equals("TAKE")) {
                String nama = parts[1];
                int jumlahInput = Integer.parseInt(parts[2]);
                Akun a = cari(daftarAkun, nama);

                if (a != null) {

                    int admin = a.jenis.equals("BEASISWA") ? 1000 : 0;
                    
                    int yangDipotong = jumlahInput - admin;

                    if (a.saldo < yangDipotong) {
                        System.out.println("Saldo " + nama + " tidak cukup");
                    } else {
                        a.saldo -= yangDipotong;
                        System.out.println("Saldo " + nama + ": " + a.saldo);
                    }
                }

            } else if (perintah.equals("CHECK")) {
                String nama = parts[1];
                Akun a = cari(daftarAkun, nama);
                if (a != null) {
                    System.out.println(a.nama + " | " + a.jenis + " | saldo: " + a.saldo);
                }
            }
        }
        sc.close();
    }

    static Akun cari(List<Akun> list, String nama) {
        for (Akun a : list) {
            if (a.nama.equals(nama)) return a;
        }
        return null;
    }
}
