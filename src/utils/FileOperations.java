package utils;

import app.Crypto;
import app.Stocks;
import utils.Portfolio;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileOperations {

    public static Portfolio loadFile(String fileName) throws IOException {
        if (fileName.endsWith(".txt")) {
            return loadTxt(fileName);
        } else if (fileName.endsWith(".csv")) {
            return loadCsv(fileName);
        } else if (fileName.endsWith(".pdf")) {
            return loadPDF(fileName);
        } else {
            return loadBin(fileName);
        }
    }

    private static Portfolio loadTxt(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            String name = line.split(" ")[0];
            br.readLine();
            String[] parts = line.split(" ");
            switch (name) {
                case "KRYPTOMĚNA":
                    return new Crypto(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                case "AKCIE":
                    return new Stocks(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
            }
            return null;
        }
    }

    private static Portfolio loadCsv(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            String name = line.split(",")[0];
            br.readLine();
            String[] parts = line.split(",");
            switch (name) {
                case "KRYPTOMĚNA":
                    return new Crypto(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                case "AKCIE":
                    return new Stocks(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
            }
            return null;
        }
    }

    private static Portfolio loadPDF(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        PDDocument d = PDDocument.load(f);
        PDFTextStripper ts = new PDFTextStripper();
        String text = ts.getText(d);
        System.out.println(text);
        d.close();
        return null;
    }

    private static Portfolio loadBin(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (DataInputStream bin = new DataInputStream(new FileInputStream(f))) {
            String name = bin.readUTF();
            bin.readInt();
            bin.readInt();
            bin.readInt();
            bin.readLong();
            switch (name) {
                case "KRYPTOMĚNA":
                    return new Crypto(bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readInt(), bin.readDouble(), bin.readDouble());
                case "AKCIE":
                    return new Stocks(bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readInt(), bin.readDouble(), bin.readDouble());
            }
            return null;
        }
    }

    public static void SaveInfo(String fileName, Portfolio port) throws IOException {
        switch (fileName.substring(fileName.length() - 4)) {
            case ".txt":
                saveTxt(fileName, port); break;
            case ".csv":
                saveCsv(fileName, port); break;
            case ".pdf":
                savePDF(fileName, port); break;
            default:
                saveBin(fileName, port); break;
        }
    }

    private static void saveTxt(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        f.createNewFile();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            pw.println(port.getName() + " " + LocalTime.now().toString() + " " + LocalDate.now().toString());
            pw.println(String.format("%15s %15s %15s %15s %15s %15s %7s %10s %4s", "Akt. Hodnota Aktiva", "Akt. Počet Aktiv", "Akt. Hod. Investice", "Počát. Hod. Aktiva", "Počát. Počet Aktiv", "Počát. Hod. Investice", "Měsíc", "Zisk/Ztráta", "%"));
            pw.println(String.format("%15f %15f %15f %15f %15f %15f %7d %10f %4f", port.getActValue(), port.getActQuantity(), port.getActSum(), port.getStartValue(), port.getStartQuantity(), port.getStartDeposit(), port.getMonth(), port.getTimelineProfit(), port.getTimelineProfitPercent()));
        }
    }

    private static void saveCsv(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            pw.println(port.getName() + "," + LocalTime.now().toString() + "," + LocalDate.now().toString());
            pw.println("Akt. Hodnota Aktiva,Akt. Počet Aktiv,Akt. Hod. Investice,Počát. Hod. Aktiva,Počát. Počet Aktiv,Počát. Hod. Investice,Měsíc,Zisk/Ztráta,%");
            pw.println(String.format("%f,%f,%f,%f,%f,%f,%d,%f,%f", port.getActValue(), port.getActQuantity(), port.getActSum(), port.getStartValue(), port.getStartQuantity(), port.getStartDeposit(), port.getMonth(), port.getTimelineProfit(), port.getTimelineProfitPercent()));
        }
    }

    private static void savePDF(String fileName, Portfolio port) throws IOException {
        PDDocument d = new PDDocument();
        PDPage p = new PDPage();
        d.addPage(p);
        PDPageContentStream stream = new PDPageContentStream(d, p);
        PDFont font = PDType1Font.HELVETICA;
        stream.setFont(font, 14);
        stream.beginText();
        stream.moveTextPositionByAmount(10, 775);
        stream.showText("Test");
        stream.endText();
        stream.close();
        d.save(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        d.close();
    }

    private static void saveBin(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(f))) {
            out.writeUTF(port.getName());
            out.writeLong(LocalTime.now().toSecondOfDay());
            out.writeInt(LocalDate.now().getDayOfMonth());
            out.writeInt(LocalDate.now().getMonthValue());
            out.writeInt(LocalDate.now().getYear());
            out.writeDouble(port.getActValue());
            out.writeDouble(port.getActQuantity());
            out.writeDouble(port.getActSum());
            out.writeDouble(port.getStartValue());
            out.writeDouble(port.getStartQuantity());
            out.writeDouble(port.getStartDeposit());
            out.writeInt(port.getMonth());
            out.writeDouble(port.getTimelineProfit());
            out.writeDouble(port.getTimelineProfitPercent());

        }
    }
}
