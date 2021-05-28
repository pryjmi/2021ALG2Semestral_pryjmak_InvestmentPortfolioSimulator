package ui;

import app.Portfolio;
import utils.TextMessages;
import utils.Utils;

import static utils.Utils.*;

public class UICrypto {

    public void manAccCrypto() {
        Portfolio Crypto = new Portfolio();
        Crypto.checkName(2);
        System.out.println(Utils.Description());
        System.out.println(TextMessages.CryptoMenu());
        menuChoice = sc.nextLine();
        switch (menuChoice) {
            case "classic": {
                cryptoClassic(Crypto);
                break;
            }
            /*
            case "month": {
                cryptoMonth(Crypto);
                break;
            }
             */
            default:
                break;
        }
    }

    private void cryptoClassic(Portfolio Crypto) {
        System.out.println(TextMessages.DepositValue());
        Crypto.setDeposit(sc.nextDouble());
        Crypto.actCryptoQuantity();
        System.out.println(TextMessages.Period());
        Crypto.countPercentCrypto(sc.nextInt());
        dealClosedCrypto();
        TextMessages.savePf();
    }

    /*
    private void cryptoMonth(Portfolio Crypto) {
        System.out.println(TextMessages.DepositValue());
        Crypto.getDeposit(sc.nextDouble());
        Crypto.actCryptoQuantity();
        Crypto.countPercentCryptoMonth();
        Utils.showInfoCrypto();
        System.out.println(TextMessages.cryptoMonthChoice());
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                cryptoContInvestNoDeposit(Crypto);
                break;
            }
            case 2: {
                cryptoContInvestWithDeposit(Crypto);
                break;
            }
            case 3: {
                dealClosedCrypto();
                saveCryptoPf(Crypto.getName(), Crypto.getActStockValue(), Crypto.getActStockQuantity(), Crypto.getActStockSum());
                break;
            }
            default:
                break;
        }
    }

    private void cryptoContInvestNoDeposit(Portfolio Crypto) {
        Crypto.countPercentCryptoMonth();
        Utils.showInfoCrypto();
        System.out.println(TextMessages.cryptoMonthChoice());
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                cryptoContInvestNoDeposit(Crypto);
                break;
            }
            case 2: {
                cryptoContInvestWithDeposit(Crypto);
                break;
            }
            case 3: {
                dealClosedCrypto();
                saveCryptoPf(Crypto.getName(), Crypto.getActStockValue(), Crypto.getActStockQuantity(), Crypto.getActStockSum());
                break;
            }
            default:
                break;
        }
    }

    private void cryptoContInvestWithDeposit(Portfolio Crypto) {
        System.out.println(TextMessages.DepositValue());
        Crypto.depositMore(sc.nextDouble());
        Crypto.countPercentCryptoMonth();
        Utils.showInfoCrypto();
        System.out.println(TextMessages.cryptoMonthChoice());
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                cryptoContInvestNoDeposit(Crypto);
                break;
            }
            case 2: {
                cryptoContInvestWithDeposit(Crypto);
                break;
            }
            case 3: {
                dealClosedCrypto();
                saveCryptoPf(Crypto.getName(), Crypto.getActStockValue(), Crypto.getActStockQuantity(), Crypto.getActStockSum());
                break;
            }
            default:
                break;
        }
    }
     */

}
