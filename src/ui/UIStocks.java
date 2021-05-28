package ui;

import app.Portfolio;
import utils.TextMessages;
import utils.Utils;

import static utils.Utils.*;

public class UIStocks {

    public void manAccStocks() {
        Portfolio Stocks = new Portfolio();
        Stocks.checkName(1);
        System.out.println(Utils.Description());
        System.out.println(TextMessages.DepositValue());
        Stocks.setDeposit(sc.nextDouble());
        Stocks.actStockQuantity();
        System.out.println(TextMessages.Period());
        Stocks.countPercentStocks(sc.nextInt());
        dealClosedStocks();
        TextMessages.savePf();
    }

}
