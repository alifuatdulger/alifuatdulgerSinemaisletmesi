import java.util.Scanner;
import java.util.ArrayList;

class AnkaSinema {

    //Kullanıcıdan giriş almayı sağlar.
    public static Scanner scanner = new Scanner(System.in);

    // Müşteri Bilgileri
    public static ArrayList<String> ad_soyad = new ArrayList<>();
    public static ArrayList<String> mailler = new ArrayList<>();
    public  static int musteri=2;
    public static int max_musteri = 20;



    // Film Bilgileri
    public static ArrayList<String> film_adlari = new ArrayList<>();
    public static ArrayList<String> film_turleri = new ArrayList<>();
    public static ArrayList<Integer> film_sureleri = new ArrayList<>();
    public static int film_sayisi=10;




    //Kaydolma Methodu
    public static void kaydol() {
       if(musteri>=max_musteri){
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

           if (ad_soyad.contains(tam_isim) && mailler.contains(mail_giris)) {
               System.out.println("Bu kullanıcı zaten kayıtlı Ad Soyad:"+tam_isim +" | "+" mail: "+mail_giris);
           } else {
               ad_soyad.add(tam_isim);
               mailler.add(mail_giris);
               System.out.println("Kaydınız başarıyla oluşturuldu Ad Soyad:"+tam_isim +" | "+" mail: "+mail_giris);
               musteri+=1;
           }
       }

    }
    //İzleyicileri Gösterme Methodu
    public static void izleyicileri_goruntule() {
        System.out.println("İşte izleyici listesi:");
        for (int i = 0; i < ad_soyad.size(); i++) {
            System.out.println("# " + ad_soyad.get(i) + " | " + mailler.get(i));
        }
    }
    //Filmleri Gösterme Methodu
    public static void filmleri_goruntule() {
        System.out.println("Vizyondakiler:");
        for (int i = 0; i < film_adlari.size(); i++) {
            System.out.println("# " + film_adlari.get(i) + " | Tür: " + film_turleri.get(i) + " | Uzunluk: " + film_sureleri.get(i) + " dakika");
        }
    }
    //Film Bileti Alma Methodu
    public static void filmbileti_al(){
        System.out.print("Vizyonda "+film_sayisi+" film var, film adları aşağıda verilmiştir.\n");
        for (int i = 0; i < film_adlari.size(); i++) {
            System.out.println((i+1) + "-" + film_adlari.get(i));
        }

        System.out.print("Seçin:");
        int filmsecim = scanner.nextInt();
        scanner.nextLine();

        if(filmsecim < 1 || filmsecim > film_adlari.size()){
            System.out.println("Geçersiz film seçimi yaptınız!");
        } else {
            String secilenFilm = film_adlari.get(filmsecim - 1);
            System.out.println(secilenFilm + " filmi için bilet aldınız.");
        }
    }







    public static void main(String[] args) {
        // Başlangıçta direkt iki kişi kayıtlı.
        ad_soyad.add("Ali Yılmaz");
        mailler.add("ali@mail.com");
        ad_soyad.add("Zeynep Kaya");
        mailler.add("zeynep@mail.com");

        // Vizyondaki filmler,türleri ve uzunlukları
        film_adlari.add("Hızlı ve Öfkeli");
        film_turleri.add("Aksiyon");
        film_sureleri.add(120);

        film_adlari.add("Daha Hızlı Daha Öfkeli");
        film_turleri.add("Aksiyon");
        film_sureleri.add(146);

        film_adlari.add("Hızlı ve Öfkeli: Tokyo Yarışı ");
        film_turleri.add("Aksiyon");
        film_sureleri.add(112);

        film_adlari.add("Hızlı ve Öfkeli 4 ");
        film_turleri.add("Aksiyon");
        film_sureleri.add(160);

        film_adlari.add("Hızlı ve Öfkeli 5: Rio Soygunu");
        film_turleri.add("Aksiyon");
        film_sureleri.add(160);

        film_adlari.add("Hızlı ve Öfkeli 6");
        film_turleri.add("Aksiyon");
        film_sureleri.add(175);

        film_adlari.add("Hızlı ve Öfkeli 7");
        film_turleri.add("Aksiyon");
        film_sureleri.add(180);

        film_adlari.add("Hızlı ve Öfkeli 8");
        film_turleri.add("Aksiyon");
        film_sureleri.add(180);

        film_adlari.add("Yüzüklerin Efendisi");
        film_turleri.add("Fantastik");
        film_sureleri.add(147);

        film_adlari.add("Oyuncak Hikayesi");
        film_turleri.add("Animasyon");
        film_sureleri.add(161);


        while (true) {
            System.out.print("\n***** AnkaSinemaya Hoşgeldiniz. *****\n");

            System.out.println("1-Kaydol\n2-İzleyicileri Göster\n3-Filmleri Göster\n4-Film Bileti Al\n5-Çıkış");

            System.out.print("Yapmak istediğiniz işlemin numarasını girin:");
            int islem = scanner.nextInt();
            scanner.nextLine();

            switch (islem){
                case 1:
                    kaydol();
                    break;
                case 2:
                    izleyicileri_goruntule();
                    break;
                case 3:
                    filmleri_goruntule();
                    break;
                case 4:
                    filmbileti_al();
                    break;
                case 5:
                    System.out.println("Çıkılıyor...\n");
                    System.exit(0);

                default:
                    System.out.println("Geçersiz seçim");

                return;
            }





        }


    }

}

