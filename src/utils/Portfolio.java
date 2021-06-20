package utils;

import java.util.List;

public abstract class Portfolio {

    public abstract String getName();

    public abstract List<double[]> getInfoTable();

    protected abstract double getRandomPercent();

    protected abstract double generateStartValue();

    protected abstract double generateStartQuantity();

    protected abstract double generateActQuantity();

    protected abstract double generateTimelineProfitPercent();

    protected abstract double generateProfitPercent();

    protected abstract List<double[]> loadInfoTable();

    protected abstract List<double[]> loadInfoTableFile();

    public abstract double getActValue ();
    public abstract double getActQuantity ();
    public abstract double getActSum ();
    public abstract double getStartValue ();
    public abstract double getStartQuantity ();
    public abstract double getStartDeposit ();
    public abstract double getTimelineProfit();
    public abstract double getTimelineProfitPercent();
    public abstract int getMonth ();
    public abstract String timeLineChrono ();
    public abstract String timeLineProfit ();
    public abstract String getReport ();
}
