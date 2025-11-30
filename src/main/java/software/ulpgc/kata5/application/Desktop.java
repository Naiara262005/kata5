package software.ulpgc.kata5.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.kata5.architecture.viewmodel.Histogram;

import javax.swing.*;
import java.awt.*;

public class Desktop extends JFrame {
    private Desktop(){
        this.setTitle("Histogram");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    public static Desktop with(){
        return new Desktop();
    }
    public Desktop display(Histogram histogram){
        this.getContentPane().add(displayOf(histogram));
        return this;
    }

    private ChartPanel displayOf(Histogram histogram) {
        return new ChartPanel(chartOf(histogram));
    }

    private JFreeChart chartOf(Histogram histogram) {
        return ChartFactory.createHistogram(
                histogram.title(),
                histogram.x(),
                histogram.y(),
                datasetOf(histogram)
        );
    }

    private XYSeriesCollection datasetOf(Histogram histogram) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesOf(histogram));
        return dataset;
    }

    private XYSeries seriesOf(Histogram histogram) {
        XYSeries series = new XYSeries(histogram.legend());
        for (int bin : histogram)
            series.add(bin, histogram.count(bin));
        return series;
    }
}
