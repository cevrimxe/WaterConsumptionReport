public class Ogrenci implements IAbone{
    //AHMET ÇEVRİM
    //232106109001
    String id;
    String firstName;
    String lastName;
    String type;
    int usage;
    int unpaid;

    public Ogrenci(String id, String firstName, String lastName, String type, int usage, int unpaid) {
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
        if(usage>5) bill+=(usage-5)*10;  // 5 üzeri kullanımlar için 10tl ekler.
        if(usage>10) bill+=(usage-10)*5; // 10 üzeri kullanımlar için 5tl ekler.
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

    // Öğrenci fatura hesaplama tarifesi
    //ilk 5 metreküp ücretsiz
    // 6-10 arası her bir metreküp 10 tl
    //11-20 arası herbir metreküp 15 tl
    //21 ve üzeri herbir metreküp 20tl


    // örnek: 36 metreküp su tüketimi varsa
    // 5 metreküp ücretsiz tüketim 5 metreküp  tutar=5*0=0
    // 6-10 arası tüketim 5 metreküp  tutar=5*10=50
    // 11-20 arası tüketim 10 metreküp  tutar=10*15=150
    // 21-36 arası tüketim 16 metreküp  tutar= 16*20=320
    //toplam fatura= 50+150+320=550tl
    //varsa eski borç %10 zamlı olarak faturaya eklenecek
    //eğer 60 tl eski borç varsa güncel fatura= 550 +60 + (60*10)/100= 616tl



}
