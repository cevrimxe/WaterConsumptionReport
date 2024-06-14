public class Aile implements IAbone{
    String id;
    String firstName;
    String lastName;
    String type;
    int usage;
    int unpaid;

    public Aile(String id, String firstName, String lastName, String type, int usage, int unpaid) {
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
        bill+=usage*15; // bütün kullanımlar için 15tl ekler.
        if(usage>10) bill+=(usage-10)*5;  // 10 üzeri kullanımlar için 5tl ekler.
        if(usage>20) bill+=(usage-20)*10; // 20 üzeri kullanımlar için 10tl ekler.
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

    // Aile fatura hesaplama tarifesi
    // 1-10 arası her bir metreküp 15 tl
    //11-20 arası herbir metreküp 20 tl
    //21 ve üzeri herbir metreküp 30tl

    // örnek: 35 metreküp su tüketimi varsa
    // 1-10 arası tüketim 10 metreküp  tutar=10*15=150
    // 11-20 arası tüketim 10 metreküp  tutar=10*20=200
    // 21-35 arası tüketim 15 metreküp  tutar= 15*30=450
    //toplam fatura= 150+200+450=800tl
    //varsa eski borç %10 zamlı olarak faturaya eklenecek
    //eğer 60 tl eski borç varsa güncel fatura=800 +60 + (60*10)/100= 866tl
}
