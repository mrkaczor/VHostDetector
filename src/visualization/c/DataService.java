/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization.c;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import research.m.HostModel;
import research.m.HostsHolder;
import server.c.Server;
import server.m.Console;
import utils.Tuple;

/**
 *
 * @author Mateusz
 */
public class DataService {

    private HostsHolder _hosts;

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static DataService getInstance() {
        return DataServiceHolder.INSTANCE;
    }

    private static class DataServiceHolder {

        private static final DataService INSTANCE = new DataService();
    }
    // </editor-fold>

    private DataService() {
        _hosts = null;
    }
    // </editor-fold>

    public void setData(HostsHolder data) {
        _hosts = data;
    }

    public void clearData() {
        _hosts = null;
    }

    public void showAvgByCountry() {
        if (_hosts != null) {

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            List<Tuple<String, Double, Integer>> avg = new LinkedList<>();

            for (HostModel host : _hosts.getHosts()) {
                int index = -1;
                for (Tuple<String, Double, Integer> a : avg) {
                    if (a.item1.equals(host.getCountryCode())) {
                        index = avg.indexOf(a);
                        break;
                    }
                }

                if (index != -1) {
                    for (Tuple<String, Double, Integer> a : avg) {

                    }
                    Tuple<String, Double, Integer> oldTuple = avg.get(index);
                    Tuple<String, Double, Integer> newTuple = new Tuple<>(host.getCountryCode(),
                            (oldTuple.item2 * oldTuple.item3 + host.getDiscoveredVHosts().size()) / (oldTuple.item3 + 1),
                            oldTuple.item3 + 1);
                    avg.set(index, newTuple);
                } else {
                    avg.add(new Tuple<>(host.getCountryCode(), 1. * host.getDiscoveredVHosts().size(), 1));
                }
            }
            for (Tuple<String, Double, Integer> a : avg) {
                Server.getInstance().log(Console.MESSAGE, a.item1 + ": " + a.item3 + " => " + a.item2, false);
                dataset.addValue((Number) a.item2, 0, a.item1);
            }

            Plot plot = new CategoryPlot(dataset, new CategoryAxis("Państwo"), new NumberAxis("Średnia ilość vhostów"), new BarRenderer());
            JFreeChart chart = new JFreeChart(plot);
            chart.addSubtitle(new TextTitle("Średnia ilość wirtualnych hostów w kraju"));
            chart.removeLegend();

            JFrame f = new JFrame();
            f.add(new ChartPanel(chart));
            f.setBounds(0, 0, 530, 550);
            f.setVisible(true);
            f.repaint();
        }
    }

    private class ChartPanel extends JPanel {

        private final JFreeChart chart;

        public ChartPanel(JFreeChart chart) {
            this.chart = chart;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); //To change body of generated methods, choose Tools | Templates.
            chart.draw((Graphics2D) g, new Rectangle(500, 500));
        }

    }
}
