import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HeapSortVisual extends JPanel {

    private int[] array;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    public HeapSortVisual(int size) {

        array = new int[size];

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(500) + 20;
        }

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    // Desenha barras
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = WIDTH / array.length;

        for (int i = 0; i < array.length; i++) {

            int height = array[i];

            int x = i * barWidth;
            int y = HEIGHT - height;

            // Cor das barras
            g.setColor(Color.CYAN);

            g.fillRect(x, y, barWidth - 2, height);
        }
    }

    // Atualiza visualização
    private void updateDisplay() {

        repaint();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Heap Sort
    public void heapSort() {

        int n = array.length;

        // Construir heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        // Extrair elementos
        for (int i = n - 1; i > 0; i--) {

            swap(0, i);

            updateDisplay();

            heapify(i, 0);
        }
    }

    // Heapify
    private void heapify(int n, int i) {

        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {

            swap(i, largest);

            updateDisplay();

            heapify(n, largest);
        }
    }

    // Troca valores
    private void swap(int a, int b) {

        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Main
    public static void main(String[] args) {

        JFrame frame = new JFrame("Heap Sort Visual");

        HeapSortVisual panel = new HeapSortVisual(120);

        frame.add(panel);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        // Executa algoritmo em thread separada
        new Thread(() -> {
            panel.heapSort();
        }).start();
    }
}