package up.mi.chaabnia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class FlowAlgorithms extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Performance Comparison");

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Performance Comparison");

        XYChart.Series<Number, Number> quicksortSeries = new XYChart.Series<>();
        quicksortSeries.setName("Quicksort");

        XYChart.Series<Number, Number> priorityQueueSeries = new XYChart.Series<>();
        priorityQueueSeries.setName("Priority Queue");

        int[] sizes = { 10, 100, 1000, 10000, 100000 }; // Add more sizes as needed

        for (int size : sizes) {
            int[] data = generateRandomData(size);

            long quicksortTime = measureQuicksortTime(data.clone());
            long priorityQueueTime = measurePriorityQueueTime(data.clone());

            quicksortSeries.getData().add(new XYChart.Data<>(size, quicksortTime));
            priorityQueueSeries.getData().add(new XYChart.Data<>(size, priorityQueueTime));
        }

        lineChart.getData().addAll(quicksortSeries, priorityQueueSeries);

        Scene scene = new Scene(lineChart, 800, 600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private long measureQuicksortTime(int[] data) {
        long startTime = System.currentTimeMillis();
        quicksort(data, 0, data.length - 1);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private long measurePriorityQueueTime(int[] data) {
        long startTime = System.currentTimeMillis();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Arrays.asList(toIntegerArray(data)));
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private Integer[] toIntegerArray(int[] data) {
        Integer[] result = new Integer[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }

    private int[] generateRandomData(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt();
        }
        return data;
    }

    private void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);
            quicksort(array, low, partitionIndex - 1);
            quicksort(array, partitionIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
