import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MergeSortVisual extends JPanel {

    private int[] array;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;

    public MergeSortVisual(int size) {

        array = new int[size];

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(400) + 20;
        }

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    // Desenha as barras
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = WIDTH / array.length;

        for (int i = 0; i < array.length; i++) {

            g.setColor(Color.GREEN);

            int x = i * barWidth;
            int y = HEIGHT - array[i];

            g.fillRect(x, y, barWidth - 2, array[i]);
        }
    }

    // Atualiza a tela
    private void updateDisplay() {
        repaint();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Merge Sort
    public void mergeSort(int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);

            merge(left, mid, right);
        }
    }

    // Merge
    private void merge(int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = array[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = array[mid + 1 + j];

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }

            k++;

            updateDisplay();
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;

            updateDisplay();
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;

            updateDisplay();
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Merge Sort Visual");

        MergeSortVisual panel = new MergeSortVisual(100);

        frame.add(panel);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Rodar ordenação em thread separada
        new Thread(() -> {
            panel.mergeSort(0, panel.array.length - 1);
        }).start();
    }
}