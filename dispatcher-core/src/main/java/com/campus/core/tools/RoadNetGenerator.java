package com.campus.core.tools;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class RoadNetGenerator {

    private static final int COLS = 10;
    private static final int ROWS = 5;
    private static final int GAP = 12;
    private static final double SPEED = 1.2;

    public static void main(String[] args) throws Exception {
        try (PrintWriter w = new PrintWriter("roadnet.json", StandardCharsets.UTF_8)) {

            w.println("{");
            w.println("  \"nodes\": [");

            for (int j = 0; j < ROWS; j++) {
                for (int i = 0; i < COLS; i++) {
                    int id = j * COLS + i;
                    w.printf("    { \"id\": %d, \"x\": %d, \"y\": %d }%s%n",
                            id, i * GAP, j * GAP,
                            (id == COLS * ROWS - 1) ? "" : ",");
                }
            }

            w.println("  ],");
            w.println("  \"edges\": [");

            boolean first = true;
            for (int j = 0; j < ROWS; j++) {
                for (int i = 0; i < COLS; i++) {
                    int id = j * COLS + i;
                    int time = (int) Math.round(GAP / SPEED);

                    if (i < COLS - 1) {
                        if (!first) w.println(",");
                        first = false;
                        w.printf("    { \"from\": %d, \"to\": %d, \"dist\": %d, \"time\": %d }",
                                id, id + 1, GAP, time);
                    }
                    if (j < ROWS - 1) {
                        if (!first) w.println(",");
                        first = false;
                        w.printf("    { \"from\": %d, \"to\": %d, \"dist\": %d, \"time\": %d }",
                                id, id + COLS, GAP, time);
                    }
                }
            }

            w.println();
            w.println("  ]");
            w.println("}");
        }
        System.out.println("roadnet.json 生成完成");
    }
}