import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

class SinhVien {
    private String MSSV;
    private String Name;
    private float GPA;

    public SinhVien(String MSSV, String Name, float GPA) {
        this.MSSV = MSSV;
        this.Name = Name;
        this.GPA = GPA;
    }

    public String getMSSV() { return MSSV; }
    public String getName() { return Name; }
    public float getGPA() { return GPA; }

    public void setName(String name) { this.Name = name; }
    public void setGPA(float GPA) { this.GPA = GPA; }
}

public class QLSinhVien_GUI extends JFrame {
    private ArrayList<SinhVien> dsSinhVien = new ArrayList<>();
    private DefaultTableModel tableModel;

    private JTextField txtMSSV = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtGPA = new JTextField();

    public QLSinhVien_GUI() {
        setTitle("Quản Lý Sinh Viên");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout chính
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblMSSV = new JLabel("MSSV:");
        lblMSSV.setBounds(20, 20, 100, 25);
        panel.add(lblMSSV);
        txtMSSV.setBounds(120, 20, 150, 25);
        panel.add(txtMSSV);

        JLabel lblName = new JLabel("Tên:");
        lblName.setBounds(20, 60, 100, 25);
        panel.add(lblName);
        txtName.setBounds(120, 60, 150, 25);
        panel.add(txtName);

        JLabel lblGPA = new JLabel("GPA:");
        lblGPA.setBounds(20, 100, 100, 25);
        panel.add(lblGPA);
        txtGPA.setBounds(120, 100, 150, 25);
        panel.add(txtGPA);

        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(300, 20, 100, 25);
        panel.add(btnThem);

        JButton btnSapXep = new JButton("Sắp Xếp");
        btnSapXep.setBounds(300, 60, 100, 25);
        panel.add(btnSapXep);

        JButton btnTimKiem = new JButton("Tìm MSSV");
        btnTimKiem.setBounds(300, 100, 100, 25);
        panel.add(btnTimKiem);

        // Table để hiển thị danh sách sinh viên
        tableModel = new DefaultTableModel();
        tableModel.addColumn("MSSV");
        tableModel.addColumn("Tên");
        tableModel.addColumn("GPA");

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 150, 540, 200);
        panel.add(scrollPane);

        // Sự kiện Thêm
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MSSV = txtMSSV.getText();
                String Name = txtName.getText();
                String gpaText = txtGPA.getText();

                if (MSSV.isEmpty() || Name.isEmpty() || gpaText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                try {
                    float GPA = Float.parseFloat(gpaText);
                    dsSinhVien.add(new SinhVien(MSSV, Name, GPA));
                    capNhatBang();
                    clearInput();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "GPA phải là số thực!");
                }
            }
        });

        // Sự kiện Sắp Xếp
        btnSapXep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dsSinhVien.sort((sv1, sv2) -> Float.compare(sv2.getGPA(), sv1.getGPA()));
                capNhatBang();
            }
        });

        // Sự kiện Tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchMSSV = JOptionPane.showInputDialog("Nhập MSSV cần tìm:");
                boolean found = false;

                for (SinhVien sv : dsSinhVien) {
                    if (sv.getMSSV().equalsIgnoreCase(searchMSSV)) {
                        JOptionPane.showMessageDialog(null,
                                "Tìm thấy:\nMSSV: " + sv.getMSSV() +
                                        "\nTên: " + sv.getName() +
                                        "\nGPA: " + sv.getGPA());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên có MSSV: " + searchMSSV);
                }
            }
        });
    }

    private void capNhatBang() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        for (SinhVien sv : dsSinhVien) {
            tableModel.addRow(new Object[]{sv.getMSSV(), sv.getName(), sv.getGPA()});
        }
    }

    private void clearInput() {
        txtMSSV.setText("");
        txtName.setText("");
        txtGPA.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QLSinhVien_GUI().setVisible(true);
        });
    }
}
