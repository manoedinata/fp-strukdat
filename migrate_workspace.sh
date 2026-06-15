#!/bin/bash
# Script to move files and restructure the workspace

cd "$(dirname "$0")" || exit

echo "Creating new directories..."
mkdir -p src/graph src/tree src/model data docs

echo "Creating placeholder files for data and docs..."
touch data/dataset.csv
touch docs/laporan.pdf
touch docs/tracing.pdf

echo "Moving Java files to new packages..."
mv src/app/Main.java src/Main.java 2>/dev/null
mv src/app/DataLoader.java src/DataLoader.java 2>/dev/null

mv src/data/Graph.java src/graph/Graph.java 2>/dev/null
mv src/algorithms/BFS.java src/graph/BFS.java 2>/dev/null
mv src/algorithms/Dijkstra.java src/graph/Dijkstra.java 2>/dev/null
mv src/structures/SimpleQueue.java src/graph/SimpleQueue.java 2>/dev/null

mv src/structures/Trie.java src/tree/Trie.java 2>/dev/null
mv src/structures/MinHeap.java src/tree/MinHeap.java 2>/dev/null

mv src/data/Route.java src/model/Route.java 2>/dev/null
mv src/data/RouteResult.java src/model/RouteResult.java 2>/dev/null
mv src/data/Station.java src/model/Station.java 2>/dev/null

echo "Cleaning up old directories..."
rmdir src/app src/algorithms src/data src/structures 2>/dev/null

echo "Updating packages and imports for files not in context..."

# Update DataLoader.java
if [ -f "src/DataLoader.java" ]; then
    sed -i 's/package app;//' src/DataLoader.java
    sed -i 's/import data./import model./g' src/DataLoader.java
    sed -i 's/import model.Graph;/import graph.Graph;/' src/DataLoader.java
    sed -i 's/import structures./import tree./g' src/DataLoader.java
fi

# Update algorithms (now in graph)
for f in src/graph/BFS.java src/graph/Dijkstra.java; do
    if [ -f "$f" ]; then
        sed -i 's/package algorithms;/package graph;/' "$f"
        sed -i 's/import data./import model./g' "$f"
        sed -i 's/import structures./import tree./g' "$f"
        sed -i 's/import model.Graph;/import graph.Graph;/' "$f"
    fi
done

# Update SimpleQueue
if [ -f "src/graph/SimpleQueue.java" ]; then
    sed -i 's/package structures;/package graph;/' src/graph/SimpleQueue.java
fi

# Update models
for f in src/model/Route.java src/model/RouteResult.java src/model/Station.java; do
    if [ -f "$f" ]; then
        sed -i 's/package data;/package model;/' "$f"
    fi
done

echo "Workspace restructuring complete!"