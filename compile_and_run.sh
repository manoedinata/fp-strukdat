#!/bin/bash
# ─────────────────────────────────────────────────
#  Public Transport Planner — Build & Run Script
#  Requires: JDK (javac + java) installed
# ─────────────────────────────────────────────────

set -e
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
SRC="$SCRIPT_DIR/src"
OUT="$SCRIPT_DIR/out"

echo " Compiling..."
mkdir -p "$OUT"

javac -d "$OUT" \
  "$SRC/data/Station.java" \
  "$SRC/data/Route.java" \
  "$SRC/data/RouteResult.java" \
  "$SRC/data/Graph.java" \
  "$SRC/structures/MinHeap.java" \
  "$SRC/structures/SimpleQueue.java" \
  "$SRC/structures/Trie.java" \
  "$SRC/algorithms/Dijkstra.java" \
  "$SRC/algorithms/BFS.java" \
  "$SRC/app/DataLoader.java" \
  "$SRC/app/TransportApp.java"

echo " Compilation successful!"
echo ""
echo " Starting Public Transport Planner..."
echo ""

java -cp "$OUT" app.TransportApp
