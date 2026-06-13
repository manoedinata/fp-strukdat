package app;

import data.Graph;
import data.Route;
import data.Station;
import structures.Trie;

/**
 * DataLoader — populates the graph with 25 Jakarta-area stations and 42 routes.
 * Dataset is self-created (not from external library).
 *
 * Station attributes: id, name, city, type, lat, lon
 * Route    attributes: fromId, toId, timeMinutes, costRupiah, lineName, transportType
 */
public class DataLoader {

    public static void load(Graph graph, Trie trie) {
        loadStations(graph, trie);
        loadRoutes(graph);
    }

    private static void loadStations(Graph graph, Trie trie) {
        Station[] stations = {
            new Station( 0, "Gambir",             "Jakarta Pusat",   "KA",    -6.1763,  106.8307),
            new Station( 1, "Manggarai",           "Jakarta Selatan", "KRL",   -6.2103,  106.8504),
            new Station( 2, "Jakarta Kota",        "Jakarta Utara",   "KRL",   -6.1375,  106.8135),
            new Station( 3, "Tanah Abang",         "Jakarta Pusat",   "KRL",   -6.1875,  106.8122),
            new Station( 4, "Duri",                "Jakarta Barat",   "KRL",   -6.1614,  106.7921),
            new Station( 5, "Sudirman",            "Jakarta Selatan", "KRL",   -6.2007,  106.8228),
            new Station( 6, "Pasar Minggu",        "Jakarta Selatan", "KRL",   -6.2930,  106.8419),
            new Station( 7, "Depok",               "Depok",           "KRL",   -6.3913,  106.8185),
            new Station( 8, "Bogor",               "Bogor",           "KA",    -6.5950,  106.7920),
            new Station( 9, "Bekasi",              "Bekasi",          "KRL",   -6.2428,  107.0015),
            new Station(10, "Jatinegara",          "Jakarta Timur",   "KRL",   -6.2147,  106.8706),
            new Station(11, "Cikini",              "Jakarta Pusat",   "KRL",   -6.1945,  106.8401),
            new Station(12, "Gondangdia",          "Jakarta Pusat",   "KRL",   -6.1847,  106.8347),
            new Station(13, "Juanda",              "Jakarta Pusat",   "KRL",   -6.1654,  106.8303),
            new Station(14, "Sawah Besar",         "Jakarta Pusat",   "KRL",   -6.1537,  106.8279),
            new Station(15, "Kemayoran",           "Jakarta Pusat",   "KRL",   -6.1556,  106.8511),
            new Station(16, "Pasar Senen",         "Jakarta Pusat",   "KRL",   -6.1770,  106.8453),
            new Station(17, "Gang Sentiong",       "Jakarta Pusat",   "KRL",   -6.1625,  106.8558),
            new Station(18, "Rajawali",            "Jakarta Utara",   "KRL",   -6.1467,  106.8571),
            new Station(19, "Ancol",               "Jakarta Utara",   "KRL",   -6.1312,  106.8601),
            new Station(20, "Tanjung Priok",       "Jakarta Utara",   "KRL",   -6.1087,  106.8777),
            new Station(21, "BNI City",            "Jakarta Selatan", "MRT",   -6.2009,  106.8235),
            new Station(22, "Blok M",              "Jakarta Selatan", "MRT",   -6.2441,  106.7975),
            new Station(23, "Lebak Bulus",         "Jakarta Selatan", "MRT",   -6.2892,  106.7740),
            new Station(24, "Bundaran HI",         "Jakarta Pusat",   "MRT",   -6.1944,  106.8229),
        };

        for (Station s : stations) {
            graph.addStation(s);
            trie.insert(s.getName(), s.getId());
        }
    }

    private static void loadRoutes(Graph graph) {
        // ── KRL Bogor Line (Manggarai → Bogor) ──────────────────────────────
        addBoth(graph,  1,  6, 14, 4000, "KRL Bogor Line");
        addBoth(graph,  6,  7, 18, 4000, "KRL Bogor Line");
        addBoth(graph,  7,  8, 30, 4000, "KRL Bogor Line");
        addBoth(graph,  1, 11,  5, 3000, "KRL Bogor Line");
        addBoth(graph, 11, 12,  4, 3000, "KRL Bogor Line");
        addBoth(graph, 12, 13,  3, 3000, "KRL Bogor Line");
        addBoth(graph, 13, 14,  3, 3000, "KRL Bogor Line");
        addBoth(graph, 14,  2,  5, 3000, "KRL Bogor Line");

        // ── KRL Bekasi Line (Manggarai → Bekasi) ────────────────────────────
        addBoth(graph,  1, 10,  8, 4000, "KRL Bekasi Line");
        addBoth(graph, 10,  9, 20, 5000, "KRL Bekasi Line");

        // ── KRL Loop Line (Tanah Abang – Jatinegara) ────────────────────────
        addBoth(graph,  3,  5,  6, 3000, "KRL Loop Line");
        addBoth(graph,  5,  1,  5, 3000, "KRL Loop Line");
        addBoth(graph,  1, 16,  8, 3000, "KRL Loop Line");
        addBoth(graph, 16, 17,  4, 3000, "KRL Loop Line");
        addBoth(graph, 17, 18,  5, 3000, "KRL Loop Line");
        addBoth(graph, 18, 19,  5, 3000, "KRL Loop Line");
        addBoth(graph, 19, 20,  7, 3000, "KRL Loop Line");

        // ── KRL Tanjung Priok Branch ─────────────────────────────────────────
        addBoth(graph,  2, 15,  8, 3500, "KRL Tanjung Priok Branch");
        addBoth(graph, 15, 18,  6, 3500, "KRL Tanjung Priok Branch");

        // ── KRL Rangkasbitung (Tanah Abang – Duri) ──────────────────────────
        addBoth(graph,  3,  4,  8, 3500, "KRL Rangkasbitung Line");
        addBoth(graph,  4,  2, 10, 3500, "KRL Rangkasbitung Line");

        // ── KA Intercity (Gambir hubs) ───────────────────────────────────────
        addBoth(graph,  0, 13,  5, 2000, "Feeder Gambir-Juanda");
        addBoth(graph,  0,  8, 70,35000, "KA Pangrango");
        addBoth(graph,  0,  9, 55,25000, "KA Jarak Jauh");

        // ── MRT North-South Line ─────────────────────────────────────────────
        addBoth(graph, 24, 21,  3, 4000, "MRT North-South");
        addBoth(graph, 21, 22, 10, 6000, "MRT North-South");
        addBoth(graph, 22, 23, 10, 6000, "MRT North-South");

        // ── Cross-modal connections ───────────────────────────────────────────
        addBoth(graph,  5, 21,  2,    0, "Interchange Sudirman-BNI City");
        addBoth(graph, 24,  5,  2,    0, "Interchange BundaranHI-Sudirman");
        addBoth(graph,  3, 24, 10, 5000, "TransJakarta Corridor 1");
        addBoth(graph, 22,  3, 18, 3500, "TransJakarta Blok M-Tanah Abang");
    }

    /** Helper: add route in both directions */
    private static void addBoth(Graph g, int a, int b, int time, int cost, String line) {
        g.addRoute(new Route(a, b, time, cost, line, "KRL"));
        g.addRoute(new Route(b, a, time, cost, line, "KRL"));
    }
}
