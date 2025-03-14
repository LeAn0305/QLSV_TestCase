import java.util.Scanner;

// ========================== LỚP SINH VIÊN ==============================
class SinhVien {
    private String MSSV;
    private String Name;
    private float GPA;

    public SinhVien() {} // Constructor mặc định

    public SinhVien(String MSSV, String Name, float GPA) { // Constructor có tham số
        this.MSSV = MSSV;
        this.Name = Name;
        this.GPA = GPA;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public String getMSSV() {
        return MSSV;
    }

    public String getName() {
        return Name;
    }

    public float getGPA() {
        return GPA;
    }

    public void hienthiSV() {
        System.out.printf("MSSV : %s , Name : %s , GPA : %.2f\n", MSSV, Name, GPA);
    }
}

// ========================== LỚP QUẢN LÝ SINH VIÊN ==============================
public class QLSinhVien {
    private int size;
    private SinhVien sv[];

    public void InputSinhVien() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap So Luong Sinh Vien Can Nhap : ");
        size = sc.nextInt();
        sc.nextLine(); // bỏ dòng thừa sau nextInt()

        sv = new SinhVien[size];

        for (int i = 0; i < size; i++) {
            System.out.println("\nNhap Thong Tin Sinh Vien Thu :  " + (i + 1));

            System.out.print("Enter MSSV: ");
            String MSSV = sc.nextLine();

            System.out.print("Enter Name: ");
            String Name = sc.nextLine();

            System.out.print("Enter GPA: ");
            float GPA = sc.nextFloat();
            sc.nextLine(); // bỏ dòng thừa sau nextFloat()

            sv[i] = new SinhVien(MSSV, Name, GPA);
        }

    }

    public void OutputSinhVien() {
        System.out.println("\n==================== DANH SACH SINH VIEN ====================\n");
        for (int i = 0; i < size; i++) {
            sv[i].hienthiSV();
        }
    }

    public void SortArraySinhVien(){
        for(int i=0;i<size-1;i++)
        {
            for(int j=i+1;j<size;j++)
            {
                if(sv[i].getGPA() < sv[j].getGPA())
                {
                    SinhVien temp = sv[i];
                    sv[i] = sv[j];
                    sv[j] = temp;
                }
            }
        }
        System.out.println("Sinh Vien Sau Khi Sap Xep Theo GPA La");
        OutputSinhVien();

    }

    public void Menu(){
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do{
            System.out.println("\n====================Menu====================\n");
            System.out.println("1. Nhap Danh Sach Sinh Vien");
            System.out.println("2. Xuat Danh Sach Sinh Vien");
            System.out.println("3. Sap Xep Danh Sach Sinh Vien Theo GPA Giam Dan");
            System.out.println("0. Thoat");
            System.out.print("Moi Nhap Chuc Nang : ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    InputSinhVien();
                    break;
                case 2:
                    OutputSinhVien();
                    break;
                case 3:
                    SortArraySinhVien();
                    break;
                case 0:
                    System.out.println("Exit Chuong Trinh Thanh Cong !");
                    break;
                default:
                    System.out.println("Chuc Nang Khong Ton Tai");
            }
        }while(choice != 0);
    }

    public static void main(String[] args) {
        QLSinhVien qlsv = new QLSinhVien();
        qlsv.Menu();
    }
}
