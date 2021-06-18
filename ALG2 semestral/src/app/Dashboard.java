package app;

import utils.FileOperations;
import utils.PortfolioInterface;

import java.io.IOException;
import java.util.List;

public class Dashboard implements PortfolioInterface {

    private utils.Portfolio stock = null; //0

    private utils.Portfolio crypto = null; //1

    @Override
    public String getName(int index) {
        if (index == 0){
            return stock.getName();
        } else if (index == 1){
            return crypto.getName();
        }
        return null;
    }

    @Override
    public List<double[]> getHistory(int index) {
        if (index == 0) {
            return stock.getHistory();
        } else if (index == 1) {
            return crypto.getHistory();
        }
        return null;
    }

    @Override
    public void saveInfo(String fileName, int index) throws IOException {
        switch (index) {
            case 0: FileOperations.SaveInfo(fileName,stock); break;
            case 1: FileOperations.SaveInfo(fileName, crypto); break;
        }
    }

    @Override
    public boolean buy(int name, double deposit, int months) {
        switch (name){
            case 0: //stocks
                if (stock == null){
                    stock = new Stocks(deposit, months); return true;
                } else return false;
            case 1: //crypto
                if (crypto == null){
                    crypto = new Crypto(deposit, months); return true;
                } else return false;
            default : break;
        }
        return false;
    }

    @Override
    public boolean sell(int index, double withdraw) {
        switch (index) {
            case 0: if (stock != null){
                if (withdraw <= stock.getActSum()) {
                    stock.sell(withdraw);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
            case 1: if (crypto != null){
                if (withdraw <= crypto.getActSum()) {
                    crypto.sell(withdraw);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean loadInfo(String fileName) throws IOException {
        utils.Portfolio port = FileOperations.loadFile(fileName);
        if (port.getName().equals("AKCIE")) {
            if (stock != null) {
                stock = port;
                return true;
            } else return false;
        } else if (port.getName().equals("KRYPTOMĚNA")) {
            if (crypto != null) {
                crypto = port;
                return true;
            } else return false;
        }
        return false;
    }

    @Override
    public boolean[] checkPortfolios() {
        boolean[] check = {false, false};
        if (stock != null) {
            check[0] = true;
        }
        if (crypto != null) {
            check[1] = true;
        }
        return check;
    }

    @Override
    public String timeLineChrono(int index) {
        switch (index){
            case 0: return stock.timeLineChrono();
            case 1: return crypto.timeLineChrono();
        }
        return null;
    }

    @Override
    public String timeLineProfit(int index) {
        switch (index){
            case 0: return stock.timeLineProfit();
            case 1: return crypto.timeLineProfit();
        }
        return null;
    }

    @Override
    public String getReport(int index) {
        return null;
    }


}
