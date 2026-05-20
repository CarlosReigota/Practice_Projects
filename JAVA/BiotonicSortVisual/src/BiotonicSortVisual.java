import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BiotonicSortVisual extends JPanel {

    private int[] array;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;

    private int activeA = -1;
    private int activeB = -1;

    public BiotonicSortVisual(int size) {

        array = new int[size];

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(650) + 20;
        }

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    // Renderização
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = WIDTH / array.length;

        for (int i = 0; i < array.length; i++) {

            int x = i * barWidth;
            int y = HEIGHT - array[i];

            // Barras ativas
            if (i == activeA || i == activeB) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.GREEN);
            }

            g.fillRect(x, y, barWidth - 1, array[i]);
        }
    }

    // Atualização visual
    private void updateDisplay() {

        repaint();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Swap
    private void swap(int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Bitonic Sort
    public void bitonicSort(int low, int cnt, boolean dir) {

        if (cnt > 1) {

            int k = cnt / 2;

            // Crescente
            bitonicSort(low, k, true);

            // Decrescente
            bitonicSort(low + k, k, false);

            // Merge
            bitonicMerge(low, cnt, dir);
        }
    }

    // Merge Bitônico
    private void bitonicMerge(int low, int cnt, boolean dir) {

        if (cnt > 1) {

            int k = cnt / 2;

            for (int i = low; i < low + k; i++) {

                activeA = i;
                activeB = i + k;

                compare(i, i + k, dir);

                updateDisplay();
            }

            bitonicMerge(low, k, dir);
            bitonicMerge(low + k, k, dir);
        }
    }

    // Comparação
    private void compare(int i, int j, boolean dir) {

        if ((array[i] > array[j]) == dir) {
            swap(i, j);
        }
    }

    // Main
    public static void main(String[] args) {

        int size = 256;

        JFrame frame = new JFrame("Bitonic Sort Visual");

        BiotonicSortVisual panel = new BiotonicSortVisual(size);

        frame.add(panel);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(() -> {

            panel.bitonicSort(0, panel.array.length, true);

            panel.activeA = -1;
            panel.activeB = -1;

            panel.repaint();

        }).start();
    }
}