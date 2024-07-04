
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ThuVien library = ThuVien.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nQuan Ly Thu Vien");
            System.out.println("1. Quan ly sach");
            System.out.println("2. Quan ly thanh vien");
            System.out.println("3. Quan ly thu thu");
            System.out.println("4. Muon sach");
            System.out.println("5. Tra sach");
            System.out.println("6. Xem muon tra sach");
            System.out.println("0. Thoat");
            System.out.print("Chon di : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageBooksMenu(library, scanner);
                    break;
                case 2:
                    manageMembersMenu(library, scanner);
                    break;
                case 3:
                    manageLibrariansMenu(library, scanner);
                    break;
                case 4:
                    borrowBookMenu(library, scanner);
                    break;
                case 5:
                    returnBookMenu(library, scanner);
                    break;
                case 6:
                    viewBorrowingsMenu(library);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Chon lai");
            }
        }
    }

    private static void manageBooksMenu(ThuVien library, Scanner scanner) {
        while (true) {
            System.out.println("\nQuan ly sach");
            System.out.println("1. Them sach");
            System.out.println("2. Xoa sach");
            System.out.println("3. Cap nhat sach");
            System.out.println("4. Tim sach");
            System.out.println("0. Quay ve");
            System.out.print("Chon di : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Nhap ma sach: ");
                    String masach = scanner.nextLine();
                    System.out.print("Nhap ten sach: ");
                    String tensach = scanner.nextLine();
                    System.out.print("Nhap tac gia: ");
                    String tacgia = scanner.nextLine();
                    System.out.print("Nhap the loai: ");
                    String theloai = scanner.nextLine();
                    System.out.print("Nhap so luong: ");
                    int soluong = scanner.nextInt();
                    scanner.nextLine(); 
                    library.themsach(new Book(masach, tensach, tacgia, theloai, soluong));                    
                    break;
                case 2:
                    System.out.print("Nhap ma sach muon xoa: ");
                    String masachxoa = scanner.nextLine();
                    library.xoasach(masachxoa);
                    break;
                case 3:
                    System.out.print("Nhap ma sach muon cap nhat: ");
                    String masachcapnhat = scanner.nextLine();
                    System.out.print("Nhap ten : ");

                    String tensachmoi = scanner.nextLine();
                    System.out.print("Nhap tac gia: ");
                    String tacgiamoi = scanner.nextLine();
                    System.out.print("Nhap the loai: ");
                    String theloaimoi = scanner.nextLine();
                    System.out.print("Nhap so luong: ");
                    int soluongmoi = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    library.capnhatsach(masachcapnhat, tensachmoi.isEmpty() ? null : tensachmoi,
                            tacgiamoi.isEmpty() ? null : tacgiamoi,
                            theloaimoi.isEmpty() ? null : theloaimoi,
                            soluongmoi == 1 ? 1 : soluongmoi);

                  
                    break;
                case 4:
                    System.out.print("Nhap tu khoa: ");
                    String keyword = scanner.nextLine();
                    System.out.println("Ket qua:");
                    for (Book book : library.timsach(keyword)) {
                        System.out.println(book);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nhap lai.");
            }
        }
    }

    private static void manageMembersMenu(ThuVien library, Scanner scanner) {
        while (true) {
            System.out.println("\nQuan ly thanh vien");
            System.out.println("1.Them thanh vien");
            System.out.println("2. Xoa thanh vien");
            System.out.println("3. Cap nhat thanh vien");
            System.out.println("4. Tim thanh vien");
            System.out.println("0. Quay lai");
            System.out.print("Chon di: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Nhap ma thanh vien: ");
                    String mathanhvien = scanner.nextLine();
                    System.out.print("Nhap ten thanh vien: ");
                    String tenthanhvien = scanner.nextLine();
                    System.out.print("Nhap dia chi: ");
                    String diachi = scanner.nextLine();
                    library.themthanhvien(new Member(mathanhvien, tenthanhvien, diachi));                    
                    break;
                case 2:
                    System.out.print("Nhap ma thanh vien muon xoa: ");

                    String mathanhvienxoa = scanner.nextLine();
                    library.xoathanhvien(mathanhvienxoa);
                   
                    break;
                case 3:
                    System.out.print("Nhap ma tv muon cap nhat: ");
                    String matvcapnhat = scanner.nextLine();
                    System.out.print("nhap ten thanh vien moi: ");
                    String tentvmoi = scanner.nextLine();
                    System.out.print("Nhap dia chi moi: ");

                    String diachimoi = scanner.nextLine();
                    library.capnhatthanhvien(matvcapnhat,
                            tentvmoi.isEmpty() ? null : tentvmoi,
                            diachimoi.isEmpty() ? null : diachimoi);

                    break;
                case 4:
                    System.out.print("Nhap tu khoa: ");
                    String keyword = scanner.nextLine();
                    System.out.println("Ket qua:");
                    for (Member member : library.timthanhvien(keyword)) {
                        System.out.println(member);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nhap lai.");
            }
        }
    }

    private static void manageLibrariansMenu(ThuVien library, Scanner scanner) {
        while (true) {
            System.out.println("\nQuan ly thu thu");
            System.out.println("1. Them thu thu");
            System.out.println("2. Xoa thu thu");
            System.out.println("3. Cap nhat thu thu");
            System.out.println("4. Tim thu thu");
            System.out.println("0. Quay lai");
            System.out.print("Chon di: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:

                    System.out.print("Nhap ma thu thu: ");
                    String mathuthu = scanner.nextLine();
                    System.out.print("Ten thu thu: ");
                    String tenthuthu = scanner.nextLine();
                    System.out.print("Ca lam viec: ");
                    String calamviec = scanner.nextLine();
                    library.themthuthu(new Librarian(mathuthu, tenthuthu, calamviec));

                    
                    break;
                case 2:

                    System.out.print("Nhap ma thu thu muon xoa: ");
                    String mathuthuxoa = scanner.nextLine();
                    library.xoathuthu(mathuthuxoa);

                    
                    break;
                case 3:

                    System.out.print("Nhap ma thu thu muon cap nhat: ");
                    String mathuthucapnhat = scanner.nextLine();
                    System.out.print("Ten thu thu moi: ");
                    String tenthuthumoi = scanner.nextLine();
                    System.out.print("Ca lam moi: ");
                    String calamviecmoi = scanner.nextLine();
                    library.capnhatthuthu(mathuthucapnhat,
                            tenthuthumoi.isEmpty() ? null : tenthuthumoi,
                            calamviecmoi.isEmpty() ? null : calamviecmoi);

                    
                    break;
                case 4:
                    System.out.print("Nhap tu khoa: ");
                    String keyword = scanner.nextLine();
                    System.out.println("Ket qua:");
                    for (Librarian librarian : library.timthuthu(keyword)) {
                        System.out.println(librarian);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nhap lai");
            }
        }
    }

    private static void borrowBookMenu(ThuVien library, Scanner scanner) {
        System.out.print("Nhap ma thanh vien: ");
        String mathanhvien = scanner.nextLine();
        System.out.print("Nhap ma sach: ");
        String masach = scanner.nextLine();
        library.muonsach(mathanhvien, masach);
        
    }

    private static void returnBookMenu(ThuVien library, Scanner scanner) {
        System.out.print("Nhap ma thanh vien: ");
        String mathanhvien = scanner.nextLine();
        System.out.print("Nhap ma sach: ");
        String masach = scanner.nextLine();
        library.trasach(mathanhvien, masach);
        
    }

    private static void viewBorrowingsMenu(ThuVien library) {
        System.out.println("Thong tin muon,tra sach :");
        for (Borrowing view : library.muontrasach()) {
            System.out.println(view);

        }
    }
}
