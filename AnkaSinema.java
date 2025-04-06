import java.util.Scanner;
import java.util.ArrayList;

class AnkaSinema {

    //Kullanıcıdan giriş almayı sağlar.
    public static Scanner scanner = new Scanner(System.in);

    // Müşteri Bilgileri
    public static ArrayList<String> ad_soyad = new ArrayList<>();
    public static ArrayList<String> mailler = new ArrayList<>();
    public static int musteri_sayisi = 0;
    public static int max_musteri = 20;



    // Film Bilgileri
    public static ArrayList<String> film_adlari = new ArrayList<>();
    public static ArrayList<String> film_turleri = new ArrayList<>();
    public static ArrayList<Integer> film_sureleri = new ArrayList<>();
    public static int film_sayisi=0;
    public static int max_film_sayisi=10;

    // Biletler (müşterilerin seçtiği filmleri tutar) 2-boyutlu array
    public static ArrayList< ArrayList<String> > biletler = new ArrayList<>();




    //Müşteri Kaydetme Metodu
    public static void musteri_kaydet() {
        if(musteri_sayisi >= max_musteri){
            System.out.println("Maksimum İzleyici sayısına ulaşıldı.");
        }
        else{
            System.out.print("Bir isim girin: ");
            String isim = scanner.nextLine();
            System.out.print("Bir soyad girin: ");
            String soyad = scanner.nextLine();
            String tam_isim = isim + " " + soyad;

            System.out.print("Bir mail girin: ");
            String mail_giris = scanner.nextLine();

            if (ad_soyad.contains(tam_isim) || mailler.contains(mail_giris)) {
                System.out.println("*** Uyarı: **** Bu kullanıcı zaten kayıtlı: " + tam_isim + " | " + " mail: " + mail_giris);
            } else {
                ad_soyad.add(tam_isim);
                mailler.add(mail_giris);
                // Her yeni müşteri için boş film seçim listeleri oluştur
                biletler.add(new ArrayList<>());
                System.out.println("Müşteri girişi başarıyla oluşturuldu Ad Soyad:" + tam_isim + " | " + " mail: " + mail_giris);
                musteri_sayisi += 1;
            }
        }

    }

    //İzleyicileri Gösterme Metodu
    public static void izleyicileri_goruntule() {
        System.out.println("İzleyici listesi:");
        for (int i = 0; i < ad_soyad.size(); i++) {
            System.out.println("[" + (i+1) + "] " + ad_soyad.get(i) + " | " + mailler.get(i));
        }
    }


    //Film Kaydetme Metodu
    public static void film_kaydet() {
        if(film_sayisi >= max_film_sayisi){
            System.out.println("Maksimum film sayısına ulaşıldı.");
        }
        else{
            System.out.print("Filmin İsmi: ");
            String film_isim = scanner.nextLine();

            if (film_adlari.contains(film_isim)) {
                System.out.println("*** Uyarı: **** Bu film zaten kayıtlı:"+ film_isim);
            } else {

                System.out.print("Filmin Türü: ");
                String film_tur = scanner.nextLine();
                System.out.print("Filmin Süresi: ");
                int film_sure = scanner.nextInt();
                scanner.nextLine();

                film_adlari.add(film_isim);
                film_turleri.add(film_tur);
                film_sureleri.add(film_sure);

                System.out.println("Film girişi başarıyla oluşturuldu:" + film_isim + " | " + " türü: " + film_tur + " | " + " süre: " + film_sure + " dakika");
                film_sayisi +=1;
            }
        }

    }

    //Filmleri Gösterme Mehodu
    public static void filmleri_goruntule() {
        System.out.println("Vizyondakiler:");
        for (int i = 0; i < film_adlari.size(); i++) {
            System.out.println("[" + (i+1) + "] " + film_adlari.get(i) + " | Tür: " + film_turleri.get(i) + " | Uzunluk: " + film_sureleri.get(i) + " dakika");
        }
    }

    //Film Bileti Alma Metodu
    public static void filmbileti_al(){
        System.out.print("Vizyonda " + film_sayisi + " film var, film adları aşağıda verilmiştir.\n");
        //filmleri listele
        filmleri_goruntule();

        System.out.print("Bilet alınacak Filmi Seçin: ");
        int film_secim = scanner.nextInt();
        scanner.nextLine();

        if(film_secim >= 1 && film_secim <= film_adlari.size()){
            String secilen_film = film_adlari.get(film_secim - 1);
            System.out.println("Seçtiğiniz film: " + secilen_film);


            //müşterileri listele
            izleyicileri_goruntule();
            System.out.print("Bilet alınacak Kişiyi Seçin: ");
            int musteri_secim = scanner.nextInt();
            scanner.nextLine();

            if(musteri_secim >= 1 && musteri_secim <= ad_soyad.size()){
                String secilen_musteri = ad_soyad.get(musteri_secim - 1);
                System.out.println("Seçtiğiniz kişi: " + secilen_musteri);
                ArrayList<String> musteri_biletleri = biletler.get(musteri_secim - 1);

                if (musteri_biletleri.size() >= max_film_sayisi) {
                    System.out.println("Maksimum seçilebilir film sayısına ulaştınız.");
                } else {
                    musteri_biletleri.add(secilen_film);
                    System.out.println("*** " + secilen_musteri + " için \"" + secilen_film + "\" bileti başarıyla eklendi: ");
                }

            } else {
                System.out.println("Geçersiz müşteri seçimi yaptınız.");
            }



        } else {
            System.out.println("Geçersiz film seçimi yaptınız.");
        }

    }

    //Biletleri Gösterme Metodu
    public static void biletleri_goruntule() {
        System.out.println("Satılan Tüm Biletler:");
        for (int i = 0; i < ad_soyad.size(); i++) {
            System.out.println("\nİzleyici: " + ad_soyad.get(i));
            ArrayList<String> musteri_biletleri = biletler.get(i);
            if (musteri_biletleri.isEmpty()) {
                System.out.println("Henüz film seçimi yapılmadı.");
            } else {
                for (int j = 0; j < musteri_biletleri.size(); j++) {
                    String film_ismi = musteri_biletleri.get(j);
                    System.out.println("Bilet [" + (j+1) + "]: " + film_ismi);
                }
            }

        }
    }

    public static void main(String[] args) {
        // Başlangıçta direkt iki kişi kayıtlı.
        ad_soyad.add("Ali Yılmaz");
        mailler.add("ali@mail.com");
        // Başlangıç müşterileri için boş film seçim listeleri oluştur
        biletler.add(new ArrayList<>());
        musteri_sayisi += 1;

        ad_soyad.add("Zeynep Kaya");
        mailler.add("zeynep@mail.com");
        // Başlangıç müşterileri için boş film seçim listeleri oluştur
        biletler.add(new ArrayList<>());
        musteri_sayisi += 1;

        // Başlangıçta direkt iki sinema filmi kayıtlı
        film_adlari.add("Hızlı ve Öfkeli");
        film_turleri.add("Aksiyon");
        film_sureleri.add(120);
        film_sayisi += 1;

        film_adlari.add("Daha Hızlı Daha Öfkeli");
        film_turleri.add("Aksiyon");
        film_sureleri.add(146);
        film_sayisi += 1;


        while (true) {
            System.out.print("\n*** AnkaSinemaya Hoşgeldiniz. ***\n");

            System.out.println("1-İzleyici Kaydet\n2-İzleyicileri Göster\n3-Film Kaydet\n4-Filmleri Göster\n5-Film Bileti Al\n6-Satılan Biletleri Göster\n7-Çıkış");

            System.out.print("Yapmak istediğiniz işlemin numarasını girin:");
            int islem = scanner.nextInt();
            scanner.nextLine();

            switch (islem){
                case 1:
                    musteri_kaydet();
                    break;
                case 2:
                    izleyicileri_goruntule();
                    break;
                case 3:
                    film_kaydet();
                    break;
                case 4:
                    filmleri_goruntule();
                    break;
                case 5:
                    filmbileti_al();
                    break;
                case 6:
                    biletleri_goruntule();
                    break;
                case 7:
                    System.out.println("Çıkılıyor...\n");
                    System.exit(0);

                default:
                    System.out.println("Geçersiz seçim");

                    return;
            }

        }
    }
}