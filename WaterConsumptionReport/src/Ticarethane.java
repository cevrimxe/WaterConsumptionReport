public class Ticarethane implements IAbone{
    String id;
    String firstName;
    String lastName;
    String type;
    int usage;
    int unpaid;

    public Ticarethane(String id, String firstName, String lastName, String type, int usage, int unpaid) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.usage = usage;
        this.unpaid = unpaid;
    }

    @Override
    public double faturaHesapla() {
        double bill=0;
        bill+=usage*20; // bütün kullanımlar için 20tl ekler.
        if(usage>50) bill+=(usage-50)*10;  // 50 üzeri kullanımlar için 10tl ekler.
        if(usage>100) bill+=(usage-100)*20; // 100 üzeri kullanımlar için 20tl ekler.
        if(usage>200) bill+=(usage-200)*25; // 200 üzeri kullanımlar için 25tl ekler.
        if(unpaid!=0) bill+= (double) unpaid *11/10; // faturaya eski borç miktarını %10 fazlasıyla beraber ekler.
        return bill;
    }

    @Override
    public double eskiBorcMiktari() {
        return unpaid;
    }

    @Override
    public String aboneTuru() {
        return type;
    }

    @Override
    public String getSomeInfos() {
        return id+"\t\t"+firstName+"\t\t"+lastName+"\t\t"+type+"\t"+faturaHesapla();
    }
    // Ticarethane fatura hesaplama tarifesi
    // 1-50 arası her bir metreküp 20 tl
    //51-100 arası herbir metreküp 30 tl
    //101-200 arası herbir metreküp 50 tl
    //201 ve üzeri  herbir metreküp 75 tl

    // örnek: 350 metreküp su tüketimi varsa
    // 1-50 arası tüketim 50 metreküp  tutar=50*20=1000
    // 51-100 arası tüketim 50 metreküp  tutar=50*30=1500
    // 101-200 arası tüketim 100 metreküp  tutar= 100*50=5000
    // 201-350 arası tüketim 150 metreküp  tutar= 150*75=11250
    //toplam fatura= 1000+1500+5000+11250=18750tl
    //varsa eski borç %10 zamlı olarak faturaya eklenecek
    //eğer 200 tl eski borç varsa güncel fatura= 18750 +200 + (200*10)/100= 18970tl
}
