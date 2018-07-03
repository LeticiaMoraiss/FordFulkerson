package fordfulkerson;

import java.util.LinkedList;

public class FordFulkerson {

    static final int tamanho = 12;

    boolean bfs(int rGrafo[][], int origem, int destino, int parent[]) {

        boolean visitado[] = new boolean[tamanho];
        for (int i = 0; i < tamanho; ++i) {
            visitado[i] = false;
        }

        LinkedList<Integer> pilha = new LinkedList<>();
        pilha.add(origem);
        visitado[origem] = true;
        parent[origem] = -1;

        while (!pilha.isEmpty()) {
            int u = pilha.poll();

            for (int v = 0; v < tamanho; v++) {
                if (visitado[v] == false && rGrafo[u][v] > 0) {
                    pilha.add(v);
                    parent[v] = u;
                    visitado[v] = true;
                }
            }
        }

        return (visitado[destino] == true);
    }

    int fordFulkerson(int grafo[][], int s, int t) {
        int u, v;

        int rGrafo[][] = new int[tamanho][tamanho];

        for (u = 0; u < tamanho; u++) {
            for (v = 0; v < tamanho; v++) {
                rGrafo[u][v] = grafo[u][v];
            }
        }

        int parent[] = new int[tamanho];

        int caminhoMax = 0;

        while (bfs(rGrafo, s, t, parent)) {

            int caminho = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                caminho = Math.min(caminho, rGrafo[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGrafo[u][v] -= caminho;
                rGrafo[v][u] += caminho;
            }
            
            System.out.println("\n\t\t\t\t*********** Grafo alterado - Custo minimo do caminho = " + caminho + " ***********");
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    System.out.print("\t" + rGrafo[i][j]);
                }
                System.out.print("\n");
            }

            caminhoMax += caminho;
        }

        return caminhoMax;
    }

    public static void main(String[] args) throws java.lang.Exception {

        int grafo[][] = new int[][]{
            {0, 6, 8, 14, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 4, 0, 6, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 7, 0, 0, 3, 0, 0},
            {0, 0, 1, 0, 0, 0, 8, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 8, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 9, 1, 6, 0},
            {0, 5, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 9, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 9},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        System.out.println("\t\t\t\t*********** Grafo original ***********");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print("\t" + grafo[i][j]);
            }
            System.out.print("\n");
        }

        System.out.println("\n\n");

        FordFulkerson m = new FordFulkerson();

        System.out.println("\nO fluxo maximo do grafo é: " + m.fordFulkerson(grafo, 0, 11));
    }
}
