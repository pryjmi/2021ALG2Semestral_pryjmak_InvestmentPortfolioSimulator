package app;

import utils.Utils;


public class Portfolio {

    private static String name;
    private static double randomPercent;

    private static String stocksInfo;
    private static double actStockSum;
    private static double startStockValue = ((Math.random() * 5 - (-0.5))) + (-0.5);
    private static double actStockValue = 0;
    private static double startStockQuantity;
    private static double actStockQuantity;
    private static double profitPercentStock;
    private static double profitStock;

    private static String cryptoInfo;
    private static double actCryptoSum;
    private static double startDeposit;
    private static double startCryptoValue = ((Math.random() * 5 - (-0.5))) + (-0.5);
    private static double actCryptoValue = 0;
    private static double startCryptoQuantity;
    private static double actCryptoQuantity;
    private static double profitPercentCrypto;
    private static double profitCrypto;

    public void checkName(int id) {
        switch (id) {
            case 1: {
                name = "AKCIE";
                break;
            }
            case 2: {
                name = "KRYPTOMĚNY";
                break;
            }
            default:
                break;
        }
    }

    /*
    public void setData(double randomPercent, double startAssetValue, double startAssetQuantity, double actAssetValue) {
        Portfolio.randomPercent = randomPercent;
        Portfolio.startStockValue = startAssetValue;
    }
     */

    public String getName() {
        return name;
    }
    public double getStartDeposit() {
        return startDeposit;
    }
    public double getRandomPercent() {
        return randomPercent;
    }

    public double getStartStockValue() {
        return startStockValue;
    }
    public double getStartStockQuantity() {
        return startStockQuantity;
    }
    public double getActStockValue() {
        return actStockValue;
    }
    public double getActStockQuantity() {
        return actStockQuantity;
    }
    public double getActStockSum() {
        return actStockSum;
    }
    public String getStocksInfo() {
        return stocksInfo;
    }
    public double getProfitStock() {
        profitStock = actStockSum - startDeposit;
        return profitStock;
    }
    public double getProfitPercentStock() {
        profitPercentStock = ((actStockSum * 100) / startDeposit) - 100;
        return profitPercentStock;
    }

    public double getStartCryptoValue(){
        return startCryptoValue;
    }
    public double getStartCryptoQuantity(){
        return startCryptoQuantity;
    }
    public double getActCryptoValue(){
        return actCryptoValue;
    }
    public double getActCryptoQuantity(){
        return actCryptoQuantity;
    }
    public double getActCryptoSum(){
        return actCryptoSum;
    }
    public String getCryptoInfo() {
        return cryptoInfo;
    }
    public double getProfitCrypto() {
        profitCrypto = actCryptoSum - startDeposit;
        return profitCrypto;
    }
    public double getProfitPercentCrypto() {
        profitPercentCrypto = ((actCryptoSum * 100) / startDeposit) - 100;
        return profitPercentCrypto;
    }

    public void setDeposit(double sum) {
        startDeposit = sum;
        actStockSum = sum;
        actCryptoSum = sum;
    }

    public void actStockQuantity() {
        startStockQuantity = actStockSum / startStockValue;
    }

    public void actCryptoQuantity() {
        startCryptoQuantity = actCryptoSum / startCryptoValue;
    }

    public void countPercentStocks(int months) {
        if (actStockValue == 0) {
            actStockValue = startStockValue;
            actStockQuantity = startStockQuantity;
        }

        String[][] infoS = new String[4][2];

        for (int i = 0; i < months; i++) {
            randomPercent = ((Math.random() * (0.2 - (-0.1))) + (-0.1)) + 1;
            actStockValue = actStockValue * randomPercent;
            actStockSum = actStockValue * actStockQuantity;
            System.out.println(i + 1 + " month(s) passed");
            Utils.percentCondition();
            Utils.Wait();
        }

        infoS[0][0] = "Typ aktiva ";
        infoS[0][1] = name;
        infoS[1][0] = "\nAktuální hodnota jednoho aktiva: ";
        infoS[1][1] = String.valueOf(actStockValue);
        infoS[2][0] = "\nAktuální počet aktiv: ";
        infoS[2][1] = String.valueOf(actStockQuantity);
        infoS[3][0] = "\nAktuální hodnota investice: ";
        infoS[3][1] = String.valueOf(actStockSum);
        StringBuilder sb = new StringBuilder();
        for (String[] strings : infoS) {
            for (int j = 0; j < infoS[0].length; j++) {
                sb.append(strings[j]);
            }
        }
        stocksInfo = sb.toString();

    }

    public void countPercentCrypto(int months) {
        if (actCryptoValue == 0) {
            actCryptoValue = startCryptoValue;
            actCryptoQuantity = startCryptoQuantity;
        }

        String[][] infoC = new String[4][2];

        for (int i = 0; i < months; i++) {
            randomPercent = ((Math.random() * (2 - (-1))) + (-1)) + 1;
            actCryptoValue = actCryptoValue * randomPercent;
            actCryptoSum = actCryptoValue * actCryptoQuantity;
            System.out.println(i + 1 + " month(s) passed");
            Utils.percentCondition();
            Utils.Wait();
        }

        infoC[0][0] = "Typ aktiva ";
        infoC[0][1] = name;
        infoC[1][0] = "\nAktuální hodnota jednoho aktiva: ";
        infoC[1][1] = String.valueOf(actCryptoValue);
        infoC[2][0] = "\nAktuální počet aktiv: ";
        infoC[2][1] = String.valueOf(actCryptoQuantity);
        infoC[3][0] = "\nAktuální hodnota investice: ";
        infoC[3][1] = String.valueOf(actCryptoSum);
        StringBuilder sb = new StringBuilder();
        for (String[] strings : infoC) {
            for (int j = 0; j < infoC[0].length; j++) {
                sb.append(strings[j]);
            }
        }
        cryptoInfo = sb.toString();
    }

    /* public void countPercentCryptoMonth() {
        if (actCryptoValue == 0) {
            actCryptoValue = startCryptoValue;
            actCryptoQuantity = startCryptoQuantity;
        }

        String[][] info = new String[4][2];

        double randomPercent = ((Math.random() * (2 - (-1))) + (-1)) + 1;
        actCryptoValue = actCryptoValue * randomPercent;
        actCryptoSum = actCryptoValue * actCryptoQuantity;
        if (randomPercent < 1) {
            System.out.println("Asset value changed by " + ANSI_RED + ((randomPercent - 1) * 100) + "%" + ANSI_RESET);
        } else {
            System.out.println("Asset value changed by " + ANSI_GREEN + ((randomPercent - 1) * 100) + "%" + ANSI_RESET);
        }
        info[0][0] = "Typ aktiva ";
        info[0][1] = name;
        info[1][0] = "\nAktuální hodnota jednoho aktiva: ";
        info[1][1] = String.valueOf(actCryptoValue);
        info[2][0] = "\nAktuální počet aktiv: ";
        info[2][1] = String.valueOf(actCryptoQuantity);
        info[3][0] = "\nAktuální hodnota investice: ";
        info[3][1] = String.valueOf(actCryptoSum);
        StringBuilder sb = new StringBuilder();
        for (String[] strings : info) {
            for (int j = 0; j < info[0].length; j++) {
                sb.append(strings[j]);
            }
        }
        cryptoInfo = sb.toString();
    }
     */

    public void depositMore(double sum) {
        actCryptoSum += sum;
        actCryptoQuantity += sum / actCryptoValue;
    }

}
