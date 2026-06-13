package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Directed weighted graph using adjacency list.
 * Implemented from scratch using OOP — no java.util.HashMap or library graph.
 *
 * Internally uses two parallel arrays:
 *   stations[]  — stores Station nodes indexed by id
 *   adjList[]   — stores list of Route edges for each node
 */
public class Graph {

    private static final int MAX_NODES = 100;

    private Station[]      stations;
    private List<Route>[]  adjList;
    private int            numStations;
    private int            numRoutes;

    @SuppressWarnings("unchecked")
    public Graph() {
        stations    = new Station[MAX_NODES];
        adjList     = new ArrayList[MAX_NODES];
        numStations = 0;
        numRoutes   = 0;
        for (int i = 0; i < MAX_NODES; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // ─── STATION (NODE) OPERATIONS ────────────────────────────────────────────

    public boolean addStation(Station s) {
        if (s.getId() < 0 || s.getId() >= MAX_NODES) return false;
        if (stations[s.getId()] != null) return false; // duplicate
        stations[s.getId()] = s;
        numStations++;
        return true;
    }

    public Station getStation(int id) {
        if (id < 0 || id >= MAX_NODES) return null;
        return stations[id];
    }

    public boolean removeStation(int id) {
        if (id < 0 || id >= MAX_NODES || stations[id] == null) return false;
        stations[id] = null;
        adjList[id].clear();
        // Also remove all edges pointing to this station
        for (int i = 0; i < MAX_NODES; i++) {
            if (stations[i] != null) {
                adjList[i].removeIf(r -> r.getToId() == id);
            }
        }
        numStations--;
        return true;
    }

    public boolean updateStation(int id, String name, String city, String type) {
        if (id < 0 || id >= MAX_NODES || stations[id] == null) return false;
        if (name != null && !name.isEmpty()) stations[id].setName(name);
        if (city != null && !city.isEmpty()) stations[id].setCity(city);
        if (type != null && !type.isEmpty()) stations[id].setType(type);
        return true;
    }

    // ─── ROUTE (EDGE) OPERATIONS ──────────────────────────────────────────────

    public boolean addRoute(Route r) {
        int from = r.getFromId();
        int to   = r.getToId();
        if (from < 0 || from >= MAX_NODES || stations[from] == null) return false;
        if (to   < 0 || to   >= MAX_NODES || stations[to]   == null) return false;
        adjList[from].add(r);
        numRoutes++;
        return true;
    }

    public boolean removeRoute(int fromId, int toId) {
        if (fromId < 0 || fromId >= MAX_NODES || stations[fromId] == null) return false;
        int before = adjList[fromId].size();
        adjList[fromId].removeIf(r -> r.getToId() == toId);
        int after  = adjList[fromId].size();
        if (before > after) { numRoutes -= (before - after); return true; }
        return false;
    }

    public boolean updateRoute(int fromId, int toId, int newTime, int newCost) {
        if (fromId < 0 || fromId >= MAX_NODES || stations[fromId] == null) return false;
        for (Route r : adjList[fromId]) {
            if (r.getToId() == toId) {
                if (newTime > 0) r.setTimeMinutes(newTime);
                if (newCost > 0) r.setCostRupiah(newCost);
                return true;
            }
        }
        return false;
    }

    public List<Route> getNeighbors(int id) {
        if (id < 0 || id >= MAX_NODES || stations[id] == null) return new ArrayList<>();
        return adjList[id];
    }

    // ─── DISPLAY ──────────────────────────────────────────────────────────────

    public void printAllStations() {
        System.out.println("\n══════════════════════════════════════════════════════════════════");
        System.out.printf("  %-4s %-25s %-20s %-6s%n", "ID", "Nama Stasiun", "Kota", "Tipe");
        System.out.println("──────────────────────────────────────────────────────────────────");
        for (int i = 0; i < MAX_NODES; i++) {
            if (stations[i] != null) {
                System.out.printf("  %-4d %-25s %-20s %-6s%n",
                        stations[i].getId(), stations[i].getName(),
                        stations[i].getCity(), stations[i].getType());
            }
        }
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.printf("  Total: %d stasiun%n%n", numStations);
    }

    public void printGraph() {
        System.out.println("\n══════════════════ STRUKTUR GRAPH ══════════════════");
        for (int i = 0; i < MAX_NODES; i++) {
            if (stations[i] != null && !adjList[i].isEmpty()) {
                System.out.printf("  [%2d] %-25s →%n", i, stations[i].getName());
                for (Route r : adjList[i]) {
                    Station dest = stations[r.getToId()];
                    System.out.printf("       ├── %-25s | %3d mnt | Rp%,7d | %s%n",
                            dest != null ? dest.getName() : "?",
                            r.getTimeMinutes(), r.getCostRupiah(), r.getLineName());
                }
            }
        }
        System.out.println("════════════════════════════════════════════════════");
        System.out.printf("  Total: %d edge (rute)%n%n", numRoutes);
    }

    public int getNumStations() { return numStations; }
    public int getNumRoutes()   { return numRoutes; }
    public int getMaxNodes()    { return MAX_NODES; }
}
