package geragrafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.ui.RectangleInsets; 
import org.jfree.ui.RectangleInsets;
//import org.jfree.chart.util.Rotation;
import org.jfree.util.Rotation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * @author gabri
 */
public class GeraGraficos {

    // criar o dataset para o grafico de barra ou o grafico de linha
    public CategoryDataset createDataSet(ArrayList<Grafico> lista) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Grafico listagrafico : lista) {
            dataSet.addValue(listagrafico.getY(), listagrafico.getX(), listagrafico.getGrupo());
        }
        return dataSet;
    }

    // criar o piedataset para o grafico de listagrafico
    public PieDataset createPieDataSet(ArrayList<Grafico> lista) {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Grafico listagrafico : lista) {
            dataSet.setValue(listagrafico.getX(), listagrafico.getY());
        }
        return dataSet;
    }

    // criar o grafico de barras
    public JFreeChart createBarChart(CategoryDataset dataSet, String titulo, String x, String y) {
        JFreeChart graficoBarras = ChartFactory.createBarChart(titulo, x, y, dataSet, PlotOrientation.VERTICAL, true,
                false, false);
        graficoBarras.setBackgroundPaint(null);
        graficoBarras.setBorderVisible(false);
        graficoBarras.setBorderPaint(null);
        CategoryPlot plot = graficoBarras.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.red);
        plot.setRangeGridlinePaint(Color.black);

        return graficoBarras;
    }

    // criar o grafico de linhas
    public JFreeChart createRowChart(CategoryDataset dataSet, String titulo, String x, String y) {
        JFreeChart graficoLinhas = ChartFactory.createLineChart(titulo, x, y, dataSet, PlotOrientation.VERTICAL, true,
                true, false);
        graficoLinhas.setBackgroundPaint(null);
        graficoLinhas.setBorderVisible(false);
        graficoLinhas.setBorderPaint(null);
        CategoryPlot plot = graficoLinhas.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.red);
        plot.setRangeGridlinePaint(Color.black);
        return graficoLinhas;
    }

    // criar o grafico de listagrafico
    public JFreeChart createPieChart(PieDataset dataSet, String titulo) {
        // JFreeChart graficoGrafico = ChartFactory.createPieChart(titulo, dataSet);
        // graficoGrafico.setBackgroundPaint(Color.white);
        JFreeChart graficoGrafico3D = ChartFactory.createPieChart3D(titulo, dataSet, true, true, false);
        graficoGrafico3D.setBackgroundPaint(null);
        graficoGrafico3D.setBorderPaint(null);
        PiePlot3D plot = (PiePlot3D) graficoGrafico3D.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInsets(RectangleInsets.ZERO_INSETS);
        plot.setDarkerSides(true);
        // plot.setBaseSectionOutlinePaint(new Color(0, 0, 0, 0));
        plot.setOutlinePaint(new Color(0, 0, 0, 0));
        plot.setStartAngle(0.0);
        plot.setInteriorGap(0.10);
        // plot.setLabelGenerator(null);
        plot.setOutlineVisible(false);
        plot.setLabelBackgroundPaint(Color.white);
        // plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelPadding(RectangleInsets.ZERO_INSETS);
        plot.setLabelFont(new Font("Dialog", Font.PLAIN, 12));
        plot.setLabelPaint(Color.gray);
        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{2}"));
        plot.setForegroundAlpha(0.5f);
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        return graficoGrafico3D;
        // return graficoGrafico;
    }

    // criar o grafico de barras completo em um painel
    public ChartPanel criarPainelGraficoBarras(ArrayList<Grafico> lista, String titulo, String x, String y) {
        CategoryDataset dataSet = this.createDataSet(lista);
        JFreeChart grafico = this.createBarChart(dataSet, titulo, x, y);
        ChartPanel painelDoGrafico = new ChartPanel(grafico);
        painelDoGrafico.setPreferredSize(new Dimension(600, 600));
        return painelDoGrafico;
    }

    // criar o grafico de linha completo em um painel
    public ChartPanel criarPainelGraficoLinha(ArrayList<Grafico> lista, String titulo, String x, String y) {
        CategoryDataset dataSet = this.createDataSet(lista);
        JFreeChart grafico = this.createRowChart(dataSet, titulo, x, y);
        ChartPanel painelDoGrafico = new ChartPanel(grafico);
        painelDoGrafico.setPreferredSize(new Dimension(600, 600));
        return painelDoGrafico;
    }

    // criar o grafico listagrafico completo em um painel
    public ChartPanel criarPainelGraficoPizza(ArrayList<Grafico> lista, String titulo) {
        PieDataset pDataSet = this.createPieDataSet(lista);
        JFreeChart grafico = this.createPieChart(pDataSet, titulo);
        ChartPanel painelDoGrafico = new ChartPanel(grafico);
        painelDoGrafico.setPreferredSize(new Dimension(600, 600));
        return painelDoGrafico;
    }

    // criar o grafico barras completo em um arquivo png
    public void criarPngGraficoBarras(ArrayList<Grafico> lista, String titulo, String x, String y) {
        CategoryDataset dataSet = this.createDataSet(lista);
        JFreeChart grafico = this.createBarChart(dataSet, titulo, x, y);
        try {
            System.out.println("Criando o grafico...");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            OutputStream png = new FileOutputStream("GraficoBarras" + now.format(formatter) + ".png");
            ChartUtilities.writeChartAsPNG(png, grafico, 300, 300);
            png.close();
        } catch (IOException io) {
            System.out.println("Error" + io.getMessage());
        }
        System.out.println("Gráfico Criado");
    }

    // criar o grafico coluna completo em um arquivo png
    public void criarPngGraficoLinhas(ArrayList<Grafico> lista, String titulo, String x, String y) {
        CategoryDataset dataSet = this.createDataSet(lista);
        JFreeChart grafico = this.createRowChart(dataSet, titulo, x, y);
        try {
            System.out.println("Criando o grafico...");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            OutputStream png = new FileOutputStream("GraficoLinhas" + now.format(formatter) + ".png");
            ChartUtilities.writeChartAsPNG(png, grafico, 300, 300);
            png.close();
        } catch (IOException io) {
            System.out.println("Error" + io.getMessage());
        }
        System.out.println("Gráfico Criado");
    }

    // criar o grafico coluna completo em um arquivo png
    public void criarPngGraficoPizza(ArrayList<Grafico> lista, String titulo) {
        PieDataset pDataSet = this.createPieDataSet(lista);
        JFreeChart grafico = this.createPieChart(pDataSet, titulo);
        try {
            System.out.println("Criando o grafico...");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            OutputStream png = new FileOutputStream("GraficoPizza" + now.format(formatter) + ".png");
            ChartUtilities.writeChartAsPNG(png, grafico, 300, 300);
            png.close();
        } catch (IOException io) {
            System.out.println("Error" + io.getMessage());
        }
        System.out.println("Gráfico Criado");
    }

}
