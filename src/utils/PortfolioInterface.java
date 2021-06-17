package utils;

import java.io.IOException;
import java.util.List;

public interface PortfolioInterface {

    public String getName(int index);

    public List<double[]> getHistory(int index);

    public void saveInfo(String fileName, int index);

    public boolean buy(int name, double deposit, int months);

    public boolean sell(int index, double withdraw); //true = probehlo, false = portfolio neexistuje nebo vyber > zustatek

    public boolean loadInfo(String fileName) throws IOException;

    public boolean[] checkPortfolios();

    public String timeLineChrono (int index);

    public String timeLineProfit (int index);
}
