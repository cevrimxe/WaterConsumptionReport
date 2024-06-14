public class Emekli implements IAbone{
    String id;
    String firstName;
    String lastName;
    String type;
    int usage;
    int unpaid;

    public Emekli(String id, String firstName, String lastName, String type, int usage, int unpaid) {
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
        bill+=usage*10; // bütün kullanımlar için 10tl ekler.
        if(usage>10) bill+=(usage-10)*5;  // 10 üzeri kullanımlar için 5tl ekler.
        if(usage>20) bill+=(usage-20)*5; // 20 üzeri kullanımlar için 5tl ekler.
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
        return id+"\t\t"+firstName+"\t\t"+lastName+"\t\t"+type+"\t\t"+faturaHesapla();
    }
    // Emekli fatura hesaplama tarifesi
    // 1-10 arası her bir metreküp 10 tl
    //11-20 arası herbir metreküp 15 tl
    //21 ve üzeri herbir metreküp 20tl

    // örnek: 28 metreküp su tüketimi varsa
    // 1-10 arası tüketim 10 metreküp  tutar=10*10=100
    // 11-20 arası tüketim 10 metreküp  tutar=10*15=150
    // 21-28 arası tüketim 8 metreküp  tutar= 8*20=160
    //toplam fatura= 100+150+160=410tl
    //varsa eski borç %10 zamlı olarak faturaya eklenecek
    //eğer 60 tl eski borç varsa güncel fatura= 410 +60 + (60*10)/100= 476tl
}
