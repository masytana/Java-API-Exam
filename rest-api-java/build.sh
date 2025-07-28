#! /bin/bash

BUILD_DIR="build"

trap finish SIGINT

run_build() {
    if [ -d "$BUILD_DIR" ]; then
        rm -rf "$BUILD_DIR"
    fi

    mkdir -p "$BUILD_DIR"

    echo "Compiling..."
    cd src
    javac -d "../$BUILD_DIR" Main.java
    echo "Compilation completed!"

    cd "../$BUILD_DIR"
    java Main & SERVER_PID=$!
    cd ".."
    echo "Type 'r' then Enter to restart or CTRL+C to "
}

finish() {
    if [ -n "$SERVER_PID" ]; then
        kill -TERM "$SERVER_PID" || true
        wait "$SERVER_PID" 2>/dev/null || true
    fi
    echo "Shutting down..."
    exit 0
}

run_build

while true; do
    read -r input

    if [ "$input" = "r" ]; then
        echo "Restarting server..."
        if [ -n "$SERVER_PID" ]; then
            kill -TERM "$SERVER_PID"
            wait "$SERVER_PID"
        fi
        run_build
    else
        continue
    fi
done
