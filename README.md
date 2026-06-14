# 🚌 Public Transport Planner

Sebuah aplikasi konsol interaktif berbasis Java untuk merencanakan dan mensimulasikan rute jaringan transportasi umum (KRL, MRT, Bus, KA, LRT, TransJakarta) di wilayah Jakarta dan sekitarnya. Proyek ini dibuat untuk mendemonstrasikan pengaplikasian tingkat lanjut dari berbagai struktur data dan algoritma.

## 🚀 Fitur Utama

- **Pencarian Stasiun (Trie)**: Memudahkan pencarian stasiun baik secara nama penuh (*exact match*) maupun hanya berbekal awalan (*prefix match*).
- **Rute Tercepat (Dijkstra)**: Mencari rute dengan total akumulasi waktu perjalanan paling efisien.
- **Rute Termurah (Dijkstra)**: Mencari rute hemat dengan total biaya karcis paling murah (dapat diaktifkan di menu).
- **Transit Minimum (BFS)**: Mencari rute dengan pergantian kereta atau kendaraan (transit) sesedikit mungkin.
- **Analisis Perbandingan Rute (HOTS)**: Membandingkan ketiga tipe rute (tercepat, termurah, minim transit) dan menghasilkan wawasan analisis (*trade-offs*) otomatis berdasarkan hasil *multi-objective graph*.
- **Simulasi Rute Virtual**: Tambahkan jalur sementara yang belum pernah ada untuk melihat dampak efisiensi jaringan, rute ini akan dihapus secara otomatis usai simulasi.
- **Manajemen Data (CRUD)**: Fasilitas untuk menambah, memperbarui, dan menghapus data stasiun beserta rute antarkeduanya secara dinamis (Insert/Update/Delete).

## 🛠️ Struktur Data & Algoritma

- **Graph (Adjacency List)**: Menjadi fondasi representasi jaringan stasiun dan koneksinya.
- **Trie**: Menyediakan proses pencarian string/nama stasiun yang *ultra-fast* tanpa perlu melakukan perulangan biasa.
- **MinHeap**: Mengoptimalkan struktur antrean prioritas pada pencarian rute terpendek.
- **Algoritma Dijkstra**: Menghitung *shortest path* berdasarkan kriteria dengan bobot nilai (waktu dan biaya).
- **Algoritma Breadth-First Search (BFS)**: Menghitung jalur dengan kriteria unweighted untuk optimasi jumlah lintasan.

## 📁 Struktur Direktori

```text
src/
├── app/
│   ├── TransportApp.java   # Aplikasi Konsol Utama & Menu Interaktif
│   └── DataLoader.java     # Utility untuk injeksi mock/dummy data awal
├── algorithms/
│   ├── BFS.java            # Logic pencarian transit terminimum
│   └── Dijkstra.java       # Logic optimasi waktu dan biaya
├── data/
│   ├── Graph.java          # Representasi Graph (Stations & Routes)
│   ├── Route.java          # Model Data Rute
│   ├── RouteResult.java    # Model Format Hasil Rute
│   └── Station.java        # Model Data Stasiun
└── structures/
    └── Trie.java           # Logika struktur data tree Trie
```

## 💻 Cara Menjalankan

1. Pastikan mesin Anda sudah terinstall **Java Development Kit (JDK)**.
2. Pindah ke direktori basis kode (`src`):
   ```bash
   cd src
   ```
3. Lakukan kompilasi program pada aplikasi utama (karena ketergantungan class, disarankan menggunakan IDE atau meng-compile keseluruhan file):
   ```bash
   javac app/TransportApp.java
   ```
4. Jalankan aplikasi:
   ```bash
   java app.TransportApp
   ```

## 📝 Kontributor

Dibuat sebagai Proyek Akhir Mata Kuliah Struktur Data (`fp-strukdat`).