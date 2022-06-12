import org.jfree.data.xy.XYSeries;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class PlotBuilder {

    public XYSeries getDs() {
        return createDataset();
    }
    private XYSeries ds;



    private String inputFormula;

    private ScriptEngine engine;

    PlotBuilder () {
        setEngine();
    }

    PlotBuilder (String inputFormula) {
        setInputFormula(inputFormula);
        setEngine();
    }

    public void setInputFormula(String inputFormula) {
        this.inputFormula = inputFormula;
    }

    private void setEngine() {
        this.engine = new ScriptEngineManager().getEngineByName("JavaScript");
    }

    private double getY(int x) {
        try {
            this.engine.eval("x="+x);
            return (double) this.engine.eval(this.inputFormula);
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
            return 0.0;
        }
    }

    private String reformatEquation(String inputEquation) {
        String equation;
        equation = inputEquation.replace("Math.pow(x,", "x^")
                .replace(")","")
                .replace("Math.","");
        if (equation.contains("(") && !equation.contains(")")){
            equation = equation.replace("(","");
        }
        return equation;
    }

    private XYSeries createDataset() {
        String title = "y="+this.inputFormula;
        title = this.reformatEquation(title);
        XYSeries series = new XYSeries(title);
        for (int x=-10; x <= 10; x++){
            series.add(x, getY(x));
        }
        return series;
    }
}
