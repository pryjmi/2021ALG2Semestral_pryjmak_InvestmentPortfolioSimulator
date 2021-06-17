package utils;

public class TextMessages {

    public static String helpMessage(){
        return "Pro seznam příkazů napište 'help' nebo 'h'\n"+
        "Příkaz 'check stocks' slouží ke kontrole akcií\n"+
        "Příkaz 'check crypto' slouží ke kontrole kryptoměn\n"+
        "Příkaz 'buy' slouží k nákupu aktiv v portfoliu\n"+
        "Příkaz 'sell' slouží k prodeji aktiv v portfoliu\n"+
        "Příkaz 'load' slouží k načtení prtofolia ze souboru\n"+
        "Příkaz 'home' vás vrátí na domovskou obrazovku\n"+
        "Příkaz 'exit' ukončí program\n";
    }

    public static String MainMenu(){
        return "Pro seznam příkazů napište 'help' nebo 'h'";
    }

    public static String buyAsset(){
        return "Vyberte aktivum\n"+
        "Akcie 'stocks'\n"+
        "Kryptoměny 'crypto'";
    }

    public static String DepositValue(){
        return "Zadejte hodnotu vkladu.";
    }

    public static String Period(){
        return "Zadejte dobu (pouze měsíce), po kterou bude vklad uschován.";
    }

    public static String savePf(){
        return "Portfolio bylo uloženo\n";
    }

}
