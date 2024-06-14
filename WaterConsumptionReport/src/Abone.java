
import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class Abone {

    public static void main(String[] args) throws IOException {
        IAbone[] abone = new IAbone[220];
        try {
            File fileR = new File("src/tuketim.txt");
            File fileW = new File("src/tuketimRaporu.txt");
            FileReader fr = new FileReader(fileR);
            FileWriter fw = new FileWriter(fileW);
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter br2 = new BufferedWriter(fw);
            String str;

            String firstLine =br.readLine(); // Başlık satırını atlamak için kullanılmıştır.
            int i=0;
            while ((str = br.readLine()) != null) {
                String[] parts =str.split(" "); // Satırı parçalara ayırarak atamalar yapılıyor.
                String id= parts[0];
                String firstName= parts[1];
                String lastName= parts[2];
                String type = parts[3];
                int usage = Integer.parseInt(parts[4]);
                String unpaid= parts[5];
                int unpaid2= Objects.equals(unpaid, "YOK") ? 0 : Integer.parseInt(unpaid);
                abone[i] = AboneFactory.createAbone(type, id, firstName, lastName, usage, unpaid2); // Switch case ile de yapılabilirdi daha temiz ve güncellenilebilir olması için Factory kullandım.
                i++;
            }
            //
            double totalBills=0;
            HashMap<String, Integer> typeNumbers = new HashMap<>();
            HashMap<String, Double> typeBills=new HashMap<>();
            HashMap<String, Integer> missingBillCounts = new HashMap<>();
            HashMap<String, Double> maxBills = new HashMap<>();
            HashMap<String, IAbone> maxBillAbone=new HashMap<>();
            for (int j = 0; j < abone.length; j++) {
                IAbone currentAbone=abone[j];
                String aboneTuru= currentAbone.aboneTuru();
                double fatura= currentAbone.faturaHesapla();
                totalBills+=fatura;

                typeNumbers.put(aboneTuru, typeNumbers.getOrDefault(aboneTuru,0)+1);
                typeBills.put(aboneTuru,typeBills.getOrDefault(aboneTuru,0.0)+fatura);
                missingBillCounts.put(aboneTuru,missingBillCounts.getOrDefault(aboneTuru,0)+(currentAbone.eskiBorcMiktari() > 0 ? 1 : 0));
                if (fatura > maxBills.getOrDefault(aboneTuru, 0.0)) {
                    maxBills.put(aboneTuru, fatura);
                    maxBillAbone.put(aboneTuru,currentAbone);
                }
            }

            br2.write("FATURA İSTATİSTİKLERİ\n");
            br2.newLine();

            br2.write("1- TOPLAM FATURA TUTARI: " + totalBills + "\n");
            br2.newLine();

            br2.write("2- ABONE SAYILARI\n");
            br2.write("AİLE\t\t:" + typeNumbers.get("Aile") + "\n");
            br2.write("ÖĞRENCİ\t\t:" + typeNumbers.get("Öğrenci") + "\n");
            br2.write("EMEKLİ\t\t:" + typeNumbers.get("Emekli") + "\n");
            br2.write("TİCARETHANE\t:" + typeNumbers.get("Ticarethane") + "\n");
            br2.newLine();

            br2.write("3- ABONE TÜRLERİNE GÖRE TOPLAM FATURA TUTARLARI\n");
            br2.write("AİLE\t\t:" + typeBills.get("Aile") + "\n");
            br2.write("ÖĞRENCİ\t\t:" + typeBills.get("Öğrenci") + "\n");
            br2.write("EMEKLİ\t\t:" + typeBills.get("Emekli") + "\n");
            br2.write("TİCARETHANE\t:" + typeBills.get("Ticarethane") + "\n");
            br2.newLine();

            br2.write("4- ABONE TÜRLERİNE GÖRE GEÇMİŞ DÖNEMDEN BORCU OLAN ABONE SAYILARI\n");
            br2.write("AİLE\t\t:" + missingBillCounts.get("Aile") + "\n");
            br2.write("ÖĞRENCİ\t\t:" + missingBillCounts.get("Öğrenci") + "\n");
            br2.write("EMEKLİ\t\t:" + missingBillCounts.get("Emekli") + "\n");
            br2.write("TİCARETHANE\t:" + missingBillCounts.get("Ticarethane") + "\n");
            br2.newLine();

            br2.write("5- ABONE TÜRLERİNE GÖRE EN YÜKSEK FATURA ÖDEYEN ABONE BİLGİLERİ\n");
            br2.write("ABONE NO\tADI\t\tSOYADI\t\tABONE TÜRÜ\tFATURA TUTARI\n");
            br2.write(maxBillAbone.get("Aile").getSomeInfos() + "\n");
            br2.write(maxBillAbone.get("Öğrenci").getSomeInfos() + "\n");
            br2.write(maxBillAbone.get("Emekli").getSomeInfos() + "\n");
            br2.write(maxBillAbone.get("Ticarethane").getSomeInfos() + "\n");
            br2.newLine();

            br.close();
            br2.close();
        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}