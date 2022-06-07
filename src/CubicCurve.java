import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class CubicCurve extends JFrame {

    private JButton drawPlot;
    private JSpinner spinner1;
    public JPanel mainPanel;
    public JPanel performancePane;
    private JButton clearPlot;
    private JTextField inputFormula;
    public ChartPanel chartPanel = null;


    public CubicCurve() {
        this.setTitle("Java Grapher");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        SeriesStatusCheck status = new SeriesStatusCheck();
        XYSeriesCollection dataset = new XYSeriesCollection();
        chartPanel = new ChartPanel(getLineChart(dataset));
        performancePane.add((chartPanel));
        performancePane.updateUI();


        clearPlot.addActionListener(actionEvent ->{
            dataset.removeAllSeries();
            status.removeStatus();
            performancePane.add((chartPanel));
            performancePane.updateUI();
        });

        drawPlot.addActionListener(actionEvent ->{
            String formula = (String) inputFormula.getText();
            if (!status.isKExists(formula)) {
                dataset.addSeries(new PlotBuilder(formula).getDs());
                status.add(formula);
                if (chartPanel == null) {
                    chartPanel = new ChartPanel(getLineChart(dataset));
                    performancePane.add(chartPanel, BorderLayout.CENTER);
                    performancePane.updateUI();
                } else {
                    chartPanel.setChart(getLineChart(dataset));
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Уравнение y="+formula+" уже построено!");
            }
        });

    }
    private JFreeChart getLineChart(XYSeriesCollection ds) {
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Line Chart",
                "X",
                "Y",
                ds, PlotOrientation.VERTICAL, true, true, false);
        return lineChart;
    }

}
