/**
 * @file LabirentProje
 * @description Duvarlar mayınlar ve bonuslar olmak üzere farklı fonksiyonlar
 * içeren 2d labirent oyunu.
 * @assignment 1.Proje
 * @date 09.12.2023
 * @author Emir Varol emir.varol@stu.fsm.edu.tr
 */
package labirentproje;

import java.util.Scanner;

public class LabirentProje {

    public static void main(String[] args) {
        int[] isinlanmabonusu = {0};
        int[] duvarbonusu = {0};
        int[] hamlebonusu = {0};
        int[] mayınbonusu = {0};
        int[] adimsayisi = {0};
        int[] hamlesayisi = {0};
        int[] baslangicyer = new int[2];
        int[] bitisyer = new int[2];
        boolean tp = false;
        boolean isinlanmakontrol;

        System.out.println("Labirent Oyunun Hoş Geldiniz...");
        System.out.println("Karakteri Harakete Geçirmek İçin Kullanılacak Komular Şu Şekildedir \nW: Yukarı S: Aşşağı  A: Sola D: Sağa");
        System.out.println("Bonuslarınızı Görüntülemek için + Basınız");
        System.out.println("Oyundan Çıkmak İçin 'exit' yazınız");

        baslangicbitis(baslangicyer, bitisyer);
        System.out.print("Girdi : ");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        while (!str.equals("exit")) {

            if (str.length() == 1) {
                char hareket = str.toUpperCase().charAt(0);

                if (hareket == '+') {
                    bonuskontrol(isinlanmabonusu, duvarbonusu, hamlebonusu, mayınbonusu, adimsayisi, baslangicyer, hareket, tp);
                    bonustoplama(duvarbonusu, hamlebonusu, isinlanmabonusu, mayınbonusu, baslangicyer);
                    konum(baslangicyer);
                    System.out.println("Adim Sayisi = " + adimsayisi[0]);
                    System.out.println("Satir: " + baslangicyer[0] + "  Sütun: " + baslangicyer[1]);
                    if(isinlanmakontrol=false){
                    str = scanner.next();}
                } else {
                    System.out.println("Karakteri Harakete Geçirmek İçin Kullanılacak Komular Şu Şekildedir \nW: Yukarı S: Aşşağı  A: Sola D: Sağa");
                    System.out.println("Bonuslarınızı Görüntülemek için + Basınız");
                    yerdegistirme(hareket, baslangicyer, adimsayisi, duvarbonusu, isinlanmabonusu, hamlebonusu, mayınbonusu, tp, hamlesayisi);
                    mayın(labirent, baslangicyer, mayınbonusu, adimsayisi);
                    bonustoplama(duvarbonusu, hamlebonusu, isinlanmabonusu, mayınbonusu, baslangicyer);
                    adimsayisi[0]++;

                    if (hamlesayisi[0] % 5 == 0 && hamlesayisi[0] != 0) {
                        System.out.println("Harita Başarıyla Güncellendi...");
                        System.out.println("Bonuslar yeniden rastgele yerleştirildi...");
                        haritareset(labirent);
                        bonusyerlestirme();
                        konum(baslangicyer);
                        hamlesayisi[0] = 0;
                    }
                    System.out.println("Adim Sayisi = " + adimsayisi[0]);
                    System.out.println("Satir: " + baslangicyer[0] + "  Sütun: " + baslangicyer[1]);
                    System.out.println("Girdi : ");

                    if (baslangicyer[0] == bitisyer[0] && baslangicyer[1] == bitisyer[1]) {
                        konum(baslangicyer);
                        break;
                    }
                }
                str = scanner.next();
            } else {
                str = scanner.next();
            }
        }
        System.out.println(adimsayisi[0] + " Adım İle Oyun Sonlandı");

    }

    public static char[][] labirent = {
        {'#', '!', '.', '.', 'R', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
        {'.', '.', '#', '.', '.', '.', '#', '.', 'H', '.', '.', '.', '.', '.', '!'},
        {'F', '.', '.', '.', '#', '.', '!', '.', '.', 'R', '.', '.', '#', '#', '.'},
        {'.', '.', '#', '.', '.', '#', '.', '.', '.', '.', 'F', '.', '.', '.', '.'},
        {'.', '!', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
        {'.', '.', 'H', '.', '.', '!', '.', '.', 'H', '.', '.', 'F', '.', '.', 'R'},
        {'#', '#', '#', '#', '.', '.', '#', '.', '.', '.', 'T', '.', '.', '.', 'E'},
        {'.', '.', '#', '.', 'F', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
        {'.', '#', '.', '.', '.', '.', '!', '.', '#', '.', '.', '.', '#', '.', '.'},
        {'.', 'T', 'T', '.', '#', '#', '.', '.', '.', '.', 'T', '.', '.', '.', 'R'},
        {'.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', 'T', '.'},
        {'B', '.', '#', '.', '.', '!', '.', '!', '.', '.', '.', '.', '.', '.', '#'},
        {'.', '.', '.', 'F', '!', '.', '.', '.', 'H', '.', '.', 'R', '.', '.', '.'},
        {'.', '.', 'H', '.', '.', '.', '!', '.', '.', '.', '#', '.', '.', '#', '.'},
        {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '#'}};

    public static void baslangicbitis(int[] baslangicyer, int[] bitisyer) {

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                System.out.print(labirent[i][j] + "  ");
                if (labirent[i][j] == 'B') {
                    baslangicyer[0] = i;
                    baslangicyer[1] = j;
                } else if (labirent[i][j] == 'E') {
                    bitisyer[0] = i;
                    bitisyer[1] = j;
                }
            }
            System.out.println();
        }

        System.out.println("Baslangic konumunuz " + baslangicyer[0] + "," + baslangicyer[1]);
        System.out.println("Hedef konumunuz " + bitisyer[0] + "," + bitisyer[1]);
    }

    public static void mayın(char[][] labirent, int[] baslangicyer, int[] mayınbonusu, int[] adimsayisi) {
        if (labirent[baslangicyer[0]][baslangicyer[1]] == '!') {
            System.out.println("Mayına Bastınız ... Mayın Bonusu Sayiniz " + mayınbonusu[0]);
            Scanner scanner = new Scanner(System.in);
            if (mayınbonusu[0] > 0) {
                System.out.println("Mayın Patlarsa Adım Sayınız 5 Artacaktır...");
                System.out.println("Kullanmak istiyorsanız 'Y' Basınız");
                System.out.println("Kullanmak istemiyorsanız 'N' Basınız");
                char kontrol = scanner.next().toUpperCase().charAt(0);
                if (kontrol == 'Y') {
                    mayınbonusu[0]--;
                    labirent[baslangicyer[0]][baslangicyer[1]] = '.';
                    System.out.println("Mayın Bonusu kullanıldı!");
                    System.out.println("Bonus Sayınız 1 Azaldı Yeni Bonus Sayiniz " + mayınbonusu[0]);
                } else if (kontrol == 'N') {
                    System.out.println("Bonus Kullanılmadı Ve Mayın Patladı");
                    adimsayisi[0] += 5;
                    labirent[baslangicyer[0]][baslangicyer[1]] = '.';
                } else if (kontrol != 'N') {
                    System.out.println("Geçersiz seçim yapıldı ve bonus kullanılmadı. Adım Sayınız 5 Arttı");
                    adimsayisi[0] += 5;
                }
            } else {
                System.out.println("Yeterli Mayın Bonusunuz Bulunmadığı İçin Patladınız Ve Adım Sayınız 5 Arttı");
                adimsayisi[0] += 5;
                labirent[baslangicyer[0]][baslangicyer[1]] = '.';
            }
        }
    }

    public static void konum(int[] baslangicyer) {
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                if (i == baslangicyer[0] && j == baslangicyer[1]) {
                    System.out.print("X  ");
                } else {
                    System.out.print(labirent[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }

    public static void yerdegistirme(char hareket, int[] baslangicyer, int[] adimsayisi, int[] duvarbonusu, int[] isinlanmabonusu, int[] hamlebonusu, int[] mayınbonusu, boolean tp, int[] hamlesayisi) {
        Scanner scanner = new Scanner(System.in);

        switch (hareket) {
            case 'W' -> {
                if (baslangicyer[0] > 0) {
                    baslangicyer[0]--;
                    hamlesayisi[0]++;

                    if (labirent[baslangicyer[0]][baslangicyer[1]] == '#') {
                        System.out.println("Duvara Takıldınız. Duvar Bonus Sayınız = " + duvarbonusu[0]);
                        if (duvarbonusu[0] > 0) {
                            System.out.println("Kullanmak istiyorsanız 'Y' Basınız");
                            System.out.println("Kullanmak istemiyorsanız 'N' Basınız");
                        } else {
                            System.out.println("Yeterli Bonusunuz Bulunmadığı İçin Duvarı Geçemezsiniz");
                            baslangicyer[0]++;
                            adimsayisi[0]--;
                            hamlesayisi[0]--;
                            break;
                        }
                        char kontrol;
                        do {
                            kontrol = scanner.next().toUpperCase().charAt(0);
                            switch (kontrol) {
                                case 'Y':
                                    if (duvarbonusu[0] > 0) {
                                        duvarbonusu[0]--;
                                        labirent[baslangicyer[0]][baslangicyer[1]] = '.';
                                    }
                                    break;
                                case 'N':
                                    baslangicyer[0]++;
                                    adimsayisi[0]--;
                                    hamlesayisi[0]--;
                                    break;
                                case '+':
                                    adimsayisi[0]--;
                                    bonuskontrol(isinlanmabonusu, duvarbonusu, hamlebonusu, mayınbonusu, adimsayisi, baslangicyer, hareket, tp);
                                    baslangicyer[0]++;
                                default:
                                    System.out.println("Hatalı Seçim yapıldı... Tekrar deneyin.");
                                    break;
                            }
                        } while (!(kontrol == 'Y' || kontrol == 'N' || kontrol == '+'));

                    }
                } else {
                    System.out.println("Sınırlar İçerisinde Kalacak Şekilde Haraket Ediniz");
                    adimsayisi[0]--;

                }
            }
            case 'A' -> {
                if (baslangicyer[1] > 0) {
                    baslangicyer[1]--;
                    hamlesayisi[0]++;

                    if (labirent[baslangicyer[0]][baslangicyer[1]] == '#') {

                        System.out.println("Duvara Takıldınız. Duvar Bonus Sayınız = " + duvarbonusu[0]);
                        if (duvarbonusu[0] > 0) {
                            System.out.println("Kullanmak istiyorsanız 'Y' Basınız");
                            System.out.println("Kullanmak istemiyorsanız 'N' Basınız");
                        } else {
                            System.out.println("Yeterli Bonusunuz Bulunmadığı İçin Duvarı Geçemezsiniz");
                            baslangicyer[1]++;
                            adimsayisi[0]--;
                            hamlesayisi[0]--;
                            break;
                        }

                        char kontrol;
                        do {
                            kontrol = scanner.next().toUpperCase().charAt(0);
                            switch (kontrol) {
                                case 'Y':
                                    if (duvarbonusu[0] > 0) {
                                        duvarbonusu[0]--;
                                        labirent[baslangicyer[0]][baslangicyer[1]] = '.';
                                    }
                                    break;
                                case 'N':
                                    baslangicyer[1]++;
                                    adimsayisi[0]--;
                                    hamlesayisi[0]--;
                                    break;
                                case '+':
                                    adimsayisi[0]--;
                                    bonuskontrol(isinlanmabonusu, duvarbonusu, hamlebonusu, mayınbonusu, adimsayisi, baslangicyer, hareket, tp);
                                    baslangicyer[1]++;
                                default:
                                    System.out.println("Hatalı Seçim yapıldı... Tekrar deneyin.");
                                    break;
                            }
                        } while (!(kontrol == 'Y' || kontrol == 'N' || kontrol == '+'));
                    }
                } else {
                    System.out.println("Sınırlar İçerisinde Kalacak Şekilde Haraket Ediniz");
                    adimsayisi[0]--;

                }
            }

            case 'S' -> {
                if (baslangicyer[0] < labirent.length - 1) {
                    baslangicyer[0]++;
                    hamlesayisi[0]++;
                    if (labirent[baslangicyer[0]][baslangicyer[1]] == '#') {
                        System.out.println("Duvara Takıldınız. Duvar Bonus Sayınız = " + duvarbonusu[0]);
                        if (duvarbonusu[0] > 0) {
                            System.out.println("Kullanmak istiyorsanız 'Y' Basınız");
                            System.out.println("Kullanmak istemiyorsanız 'N' Basınız");
                        } else {
                            System.out.println("Yeterli Bonusunuz Bulunmadığı İçin Duvarı Geçemezsiniz");
                            baslangicyer[0]--;
                            adimsayisi[0]--;
                            hamlesayisi[0]--;
                            break;
                        }
                        char kontrol;
                        do {
                            kontrol = scanner.next().toUpperCase().charAt(0);
                            switch (kontrol) {
                                case 'Y':
                                    if (duvarbonusu[0] > 0) {
                                        duvarbonusu[0]--;
                                        labirent[baslangicyer[0]][baslangicyer[1]] = '.';
                                    }
                                    break;
                                case 'N':
                                    baslangicyer[0]--;
                                    adimsayisi[0]--;
                                    hamlesayisi[0]--;
                                    break;
                                case '+':
                                    adimsayisi[0]--;
                                    bonuskontrol(isinlanmabonusu, duvarbonusu, hamlebonusu, mayınbonusu, adimsayisi, baslangicyer, hareket, tp);
                                    baslangicyer[0]--;
                                default:
                                    System.out.println("Hatalı Seçim yapıldı... Tekrar deneyin.");
                                    break;
                            }
                        } while (!(kontrol == 'Y' || kontrol == 'N' || kontrol == '+'));

                    }
                } else {
                    System.out.println("Sınırlar İçerisinde Kalacak Şekilde Haraket Ediniz");
                    adimsayisi[0]--;
                }
            }
            case 'D' -> {
                if (baslangicyer[1] < labirent[0].length - 1) {
                    baslangicyer[1]++;
                    hamlesayisi[0]++;

                    if (labirent[baslangicyer[0]][baslangicyer[1]] == '#') {
                        System.out.println("Duvara Takıldınız. Duvar Bonus Sayınız = " + duvarbonusu[0]);
                        if (duvarbonusu[0] > 0) {
                            System.out.println("Kullanmak istiyorsanız 'Y' Basınız");
                            System.out.println("Kullanmak istemiyorsanız 'N' Basınız");
                        } else {
                            System.out.println("Yeterli Bonusunuz Bulunmadığı İçin Duvarı Geçemezsiniz");
                            baslangicyer[1]--;
                            adimsayisi[0]--;
                            hamlesayisi[0]--;

                            break;
                        }

                        char kontrol;
                        do {
                            kontrol = scanner.next().toUpperCase().charAt(0);
                            switch (kontrol) {
                                case 'Y':
                                    if (duvarbonusu[0] > 0) {
                                        duvarbonusu[0]--;
                                        labirent[baslangicyer[0]][baslangicyer[1]] = '.';
                                    }
                                    break;
                                case 'N':
                                    baslangicyer[1]--;
                                    adimsayisi[0]--;
                                    hamlesayisi[0]--;
                                    break;
                                case '+':
                                    adimsayisi[0]--;
                                    bonuskontrol(isinlanmabonusu, duvarbonusu, hamlebonusu, mayınbonusu, adimsayisi, baslangicyer, hareket, tp);
                                    baslangicyer[1]--;
                                default:
                                    System.out.println("Hatalı Seçim yapıldı... Tekrar deneyin.");
                                    break;
                            }
                        } while (!(kontrol == 'Y' || kontrol == 'N' || kontrol == '+'));

                    }
                } else {
                    System.out.println("Sınırlar İçerisinde Kalacak Şekilde Hareket Ediniz");
                    adimsayisi[0]--;
                }
            }

            case '+' -> {
                adimsayisi[0]--;
                bonuskontrol(isinlanmabonusu, duvarbonusu, hamlebonusu, mayınbonusu, adimsayisi, baslangicyer, hareket, tp);
            }
            default -> {
                System.out.println("Hatalı Komut...");
                adimsayisi[0]--;
                hamlesayisi[0]--;
            }
        }
        konum(baslangicyer);
    }

    public static void bonustoplama(int[] duvarbonusu, int[] hamlebonusu, int[] isinlanmabonusu, int[] mayınbonusu, int[] baslangicyer) {
        if (labirent[baslangicyer[0]][baslangicyer[1]] == 'R') {
            labirent[baslangicyer[0]][baslangicyer[1]] = '.';
            duvarbonusu[0]++;
            System.out.println("R Bonusu Envantere Eklendi...");
        }
        if (labirent[baslangicyer[0]][baslangicyer[1]] == 'H') {
            labirent[baslangicyer[0]][baslangicyer[1]] = '.';
            hamlebonusu[0]++;
            System.out.println("H Bonusu Envantere Eklendi...");
        }
        if (labirent[baslangicyer[0]][baslangicyer[1]] == 'T') {
            labirent[baslangicyer[0]][baslangicyer[1]] = '.';
            isinlanmabonusu[0]++;
            System.out.println("T Bonusu Envantere Eklendi...");
        }
        if (labirent[baslangicyer[0]][baslangicyer[1]] == 'F') {
            labirent[baslangicyer[0]][baslangicyer[1]] = '.';
            mayınbonusu[0]++;
            System.out.println("F Bonusu Envantere Eklendi...");
        }
    }

    public static void bonuskontrol(int[] isinlanmabonusu, int[] duvarbonusu, int[] hamlebonusu, int[] mayınbonusu, int[] adimsayisi, int[] baslangicyer, char haraket, boolean tp) {
        System.out.println("Duvar Bonusu Sayısı = " + duvarbonusu[0]);
        System.out.println("Işınlanma Bonusu Sayısı = " + isinlanmabonusu[0]);
        System.out.println("Hamle Bonusu Sayısı = " + hamlebonusu[0]);
        System.out.println("Mayın Bonusu Sayınız = " + mayınbonusu[0]);
        System.out.println("---------------------");
        System.out.println("Hamle Bonusunu Kullanmak İçin 'H' Basınız...");
        System.out.println("Isınlanma Bonusunu Kullanmak İçin 'T' Basınız...");
        System.out.println("Kullanmak İstemiyorsanız 'Q' Basınız...");

        Scanner input = new Scanner(System.in);
        char secim = input.next().toUpperCase().charAt(0);

        switch (secim) {
            case 'H' -> {
                if (hamlebonusu[0] > 0) {
                    hamlebonusu[0]--;
                    adimsayisi[0] -= 2;
                    System.out.println("Hamle Bonusu kullanıldı... Adım sayınız 2 azaldı.");
                } else {
                    System.out.println("Yeterli Hamle Bonusunuz Bulunmamakta");
                }

                break;
            }
            case 'T' -> {
                boolean isinlanmakontrol = false;
                while (isinlanmakontrol == false) {
                    System.out.println("Isınlanmak istediğiniz Satiri Giriniz...");
                    int isinlanmasatir = input.nextInt();
                    System.out.println("Isınlanmak istediğiniz Sutunu Giriniz...");
                    int isinlanmasutun = input.nextInt();

                    if (isinlanmasatir >= 0 && isinlanmasatir <= labirent.length - 1
                            && isinlanmasutun >= 0 && isinlanmasutun <= labirent[0].length - 1
                            && labirent[isinlanmasatir][isinlanmasutun] != '!' && labirent[isinlanmasatir][isinlanmasutun] != '#') {
                        baslangicyer[0] = isinlanmasatir;
                        baslangicyer[1] = isinlanmasutun;
                        isinlanmakontrol = true;
                        break;
                    } else {
                        System.out.println("Hatalı Bir Yere Işınlanmak İstediniz Lütfen Tekrar Deneyiniz...");
                    }
                }
                break;
            }
            case 'Q' ->
                System.out.println("Bonus Kullanılmadı...");

            default ->
                System.out.println("Geçersiz seçim yapıldı.");
        }
        System.out.println("Girdi :");
    }

    public static void haritareset(char[][] labirent) {
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'T' || labirent[i][j] == 'R' || labirent[i][j] == 'H' || labirent[i][j] == 'F' || labirent[i][j] == '!') {
                    labirent[i][j] = '.';
                }
            }
        }
    }

    public static void bonusyerlestirme() {
        for (int i = 0; i < 5; i++) {
            sembolyerlestirme(labirent, 'R', 5);
        }
        for (int i = 0; i < 5; i++) {
            sembolyerlestirme(labirent, 'T', 5);
        }
        for (int i = 0; i < 5; i++) {
            sembolyerlestirme(labirent, 'H', 5);
        }
        for (int i = 0; i < 5; i++) {
            sembolyerlestirme(labirent, 'F', 5);
        }
        for (int i = 0; i < 10; i++) {
            sembolyerlestirme(labirent, '!', 10);
        }
    }

    public static void sembolyerlestirme(char[][] labirent, char sembol, int bonusadet) {
        int x, y;
        do {
            x = (int) (Math.random() * labirent.length);
            y = (int) (Math.random() * labirent[0].length);
        } while (labirent[x][y] != '.');
        labirent[x][y] = sembol;
    }
}
