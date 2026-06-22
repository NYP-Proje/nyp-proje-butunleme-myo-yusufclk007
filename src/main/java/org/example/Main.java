package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Main {

    static DefaultTableModel tableModel;
    static JTable table;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kimlik Kartı Sistemi");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Yeni Kimlik Ekle"));
        formPanel.setBackground(new Color(240, 240, 240));

        JTextField adField = new JTextField();
        JTextField soyadField = new JTextField();
        JTextField tcField = new JTextField();
        JTextField dogumField = new JTextField();
        JComboBox<String> cinsiyetBox = new JComboBox<>(new String[]{"Erkek", "Kadın"});
        JTextField adresField = new JTextField();

        formPanel.add(new JLabel("  Ad:"));
        formPanel.add(adField);
        formPanel.add(new JLabel("  Soyad:"));
        formPanel.add(soyadField);
        formPanel.add(new JLabel("  TC No:"));
        formPanel.add(tcField);
        formPanel.add(new JLabel("  Doğum Tarihi:"));
        formPanel.add(dogumField);
        formPanel.add(new JLabel("  Cinsiyet:"));
        formPanel.add(cinsiyetBox);
        formPanel.add(new JLabel("  Adres:"));
        formPanel.add(adresField);

        JButton ekleBtn = new JButton("Kaydet");
        ekleBtn.setBackground(new Color(70, 130, 180));
        ekleBtn.setForeground(Color.WHITE);
        formPanel.add(new JLabel());
        formPanel.add(ekleBtn);

        String[] kolonlar = {"ID", "Ad", "Soyad", "TC No", "Doğum", "Cinsiyet", "Adres"};
        tableModel = new DefaultTableModel(kolonlar, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Kayıtlı Kimlikler"));

        JButton silBtn = new JButton("Seçili Kaydı Sil");
        silBtn.setBackground(new Color(178, 34, 34));
        silBtn.setForeground(Color.WHITE);

        JPanel altPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        altPanel.add(silBtn);

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(altPanel, BorderLayout.SOUTH);

        ekleBtn.addActionListener(e -> {
            String ad = adField.getText().trim();
            String soyad = soyadField.getText().trim();
            String tc = tcField.getText().trim();

            if (ad.isEmpty() || soyad.isEmpty() || tc.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ad, Soyad ve TC No zorunludur!");
                return;
            }
            if (tc.length() != 11) {
                JOptionPane.showMessageDialog(frame, "TC No 11 haneli olmalıdır!");
                return;
            }

            Kimlik k = new Kimlik();
            k.setAd(ad);
            k.setSoyad(soyad);
            k.setTcNo(tc);
            k.setDogumTarihi(dogumField.getText().trim());
            k.setCinsiyet((String) cinsiyetBox.getSelectedItem());
            k.setAdres(adresField.getText().trim());

            KimlikIslemleri.ekle(k);
            JOptionPane.showMessageDialog(frame, "Kimlik kaydedildi!");

            adField.setText("");
            soyadField.setText("");
            tcField.setText("");
            dogumField.setText("");
            adresField.setText("");

            tabloYukle();
        });

        silBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Lütfen bir kayıt seçin!");
                return;
            }
            String id = (String) tableModel.getValueAt(row, 0);
            int onay = JOptionPane.showConfirmDialog(frame, "Silmek istediğinize emin misiniz?");
            if (onay == 0) {
                KimlikIslemleri.sil(id);
                tabloYukle();
            }
        });

        tabloYukle();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void tabloYukle() {
        tableModel.setRowCount(0);
        List<Kimlik> liste = KimlikIslemleri.hepsiniGetir();
        for (Kimlik k : liste) {
            tableModel.addRow(new Object[]{
                    k.getId(), k.getAd(), k.getSoyad(),
                    k.getTcNo(), k.getDogumTarihi(),
                    k.getCinsiyet(), k.getAdres()
            });
        }
    }
}
