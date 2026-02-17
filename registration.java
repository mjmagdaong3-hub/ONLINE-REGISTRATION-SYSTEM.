import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class registration extends JFrame implements ActionListener {
    private CardLayout cards = new CardLayout();
    private JPanel mainPanel = new JPanel(cards);

    // Personal Info components
    private JTextField firstName = new JTextField(15);
    private JTextField middleName = new JTextField(15);
    private JTextField lastName = new JTextField(15);
    private JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(16, 16, 65, 1));
    private JTextArea address = new JTextArea(2, 15);
    private JTextField contactNumber = new JTextField(15);
    private JTextField studentEmail = new JTextField(15);
    private JTextField placeOfBirth = new JTextField(15);
    private JTextField lrn = new JTextField(15);
    private JTextField facebookName = new JTextField(15);
    private JSpinner dobSpinner;
    private JComboBox<String> civilStatus = new JComboBox<>(new String[]{"Single", "Married"});
    private JComboBox<String> sex = new JComboBox<>(new String[]{"Male", "Female"});

    // Family components
    private JTextField motherName = new JTextField(15);
    private JTextField fatherName = new JTextField(15);
    private JTextField guardianName = new JTextField(15);
    private JTextField guardianContact = new JTextField(15);

    // School attainment
    private JTextField primarySchool = new JTextField(15);
    private JTextField primaryAddress = new JTextField(15);
    private JTextField secondarySchool = new JTextField(15);
    private JTextField secondaryAddress = new JTextField(15);
    private JTextField previousSchool = new JTextField(15);
    private JTextField previousSchoolYear = new JTextField(15);

    // Preferred schedule
    private JComboBox<String> strand = new JComboBox<>(new String[]{"ICT"});
    private JComboBox<String> section = new JComboBox<>(new String[]{"ICT 3A"});
    private JComboBox<String> eChoice = new JComboBox<>(new String[]{"E2"});
    private JComboBox<String> modality = new JComboBox<>(new String[]{"Blended", "Modular"});

    // Navigation
    private JButton nextBtn = new JButton("Next");
    private JButton backBtn = new JButton("Back");
    private JButton submitBtn = new JButton("Submit");

    private JTextArea reviewArea = new JTextArea(18, 40);

    public registration() {
        super("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);


        Calendar minDate = Calendar.getInstance();
        minDate.set(1945, Calendar.JANUARY, 1);
        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2010, Calendar.DECEMBER, 31);
        Calendar defaultDate = Calendar.getInstance();
        defaultDate.set(1990, Calendar.JANUARY, 1);
        
        dobSpinner = new JSpinner(new SpinnerDateModel(defaultDate.getTime(), minDate.getTime(), maxDate.getTime(), Calendar.YEAR));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(dateEditor);

        // Apply letter-only filter to name fields
        ((AbstractDocument) firstName.getDocument()).setDocumentFilter(new LetterFilter());
        ((AbstractDocument) middleName.getDocument()).setDocumentFilter(new LetterFilter());
        ((AbstractDocument) lastName.getDocument()).setDocumentFilter(new LetterFilter());
        ((AbstractDocument) motherName.getDocument()).setDocumentFilter(new LetterFilter());
        ((AbstractDocument) fatherName.getDocument()).setDocumentFilter(new LetterFilter());
        ((AbstractDocument) guardianName.getDocument()).setDocumentFilter(new LetterFilter());
        ((AbstractDocument) placeOfBirth.getDocument()).setDocumentFilter(new LetterFilter());

        // Apply document filters
        ((AbstractDocument) contactNumber.getDocument()).setDocumentFilter(new DigitFilter(11));
        ((AbstractDocument) guardianContact.getDocument()).setDocumentFilter(new DigitFilter(11));
        ((AbstractDocument) previousSchoolYear.getDocument()).setDocumentFilter(new DigitFilter(4, true));


        
        // Panels
        mainPanel.add(personalPanel(), "personal");
        mainPanel.add(familyPanel(), "family");
        mainPanel.add(schoolPanel(), "school");
        mainPanel.add(preferredPanel(), "preferred");
        mainPanel.add(reviewPanel(), "review");
        mainPanel.add(schedulePanel(), "schedule"); 
        mainPanel.add(schedulePanelSecond(), "schedule_second"); 

        
        add(mainPanel);
        cards.show(mainPanel, "personal");
        setVisible(true);
    }






    private JPanel personalPanel() {
        
        JPanel p = new JPanel(new BorderLayout());
        p.setName("personal");
        JPanel center = new JPanel(new GridBagLayout());
        center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Personal Information",
                TitledBorder.CENTER, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;
        addRow(center, gbc, y++, "First name", firstName);
        addRow(center, gbc, y++, "Middle name", middleName);
        addRow(center, gbc, y++, "Last name", lastName);
        addRow(center, gbc, y++, "Age", ageSpinner);
        addRow(center, gbc, y++, "Complete Home Address", new JScrollPane(address));
        addRow(center, gbc, y++, "Contact Number", contactNumber);
        addRow(center, gbc, y++, "Student email", studentEmail);
        addRow(center, gbc, y++, "LRN", lrn);
        addRow(center, gbc, y++, "Facebook Name", facebookName);
        addRow(center, gbc, y++, "Place of birth", placeOfBirth);
        addRow(center, gbc, y++, "Date of birth", dobSpinner);
        addRow(center, gbc, y++, "Civil Status", civilStatus);
        addRow(center, gbc, y++, "Sex", sex);

        JPanel nav = navigationPanel("personal");

        p.add(center, BorderLayout.CENTER);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }






    private JPanel familyPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setName("family");
        JPanel center = new JPanel(new GridBagLayout());
        center.setPreferredSize(new Dimension(420, 100));
        center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Family Background",
                TitledBorder.CENTER, TitledBorder.TOP));
        ((TitledBorder) center.getBorder()).setTitleFont(new Font("Dialog", Font.BOLD, 12));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;
        addRow(center, gbc, y++, "Mother's name", motherName);
        addRow(center, gbc, y++, "Father's name", fatherName);
        addRow(center, gbc, y++, "Guardian's name", guardianName);
        addRow(center, gbc, y++, "Guardian's contact number", guardianContact);

        JPanel nav = navigationPanel("family");

        p.add(center, BorderLayout.CENTER);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }





    private JPanel schoolPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setName("school");
        JPanel center = new JPanel(new GridBagLayout());
        center.setPreferredSize(new Dimension(520, 180));
        center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "School Attainment",
                TitledBorder.CENTER, TitledBorder.TOP));
        ((TitledBorder) center.getBorder()).setTitleFont(new Font("Dialog", Font.BOLD, 12));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;
        addRow(center, gbc, y++, "Primary School", primarySchool);
        addRow(center, gbc, y++, "School address", primaryAddress);
        addRow(center, gbc, y++, "Secondary School", secondarySchool);
        addRow(center, gbc, y++, "School address", secondaryAddress);
        addRow(center, gbc, y++, "Previous school", previousSchool);
        addRow(center, gbc, y++, "Previous school year", previousSchoolYear);

        JPanel nav = navigationPanel("school");

        p.add(center, BorderLayout.CENTER);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }





    private JPanel preferredPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setName("preferred");
        JPanel center = new JPanel(new GridBagLayout());
        center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Preferred Schedule",
                TitledBorder.CENTER, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;
        addRow(center, gbc, y++, "Strand", strand);
        addRow(center, gbc, y++, "Section", section);
        addRow(center, gbc, y++, "E1/E2", eChoice);
        addRow(center, gbc, y++, "Modality", modality);

        JPanel nav = navigationPanel("preferred");

        p.add(center, BorderLayout.CENTER);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }




    private JPanel reviewPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setName("review");
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Review & Confirm",
                TitledBorder.CENTER, TitledBorder.TOP));

        reviewArea.setEditable(false);
        reviewArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        center.add(new JScrollPane(reviewArea), BorderLayout.CENTER);

        JPanel btns = new JPanel();
        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> cards.show(mainPanel, "personal"));
        submitBtn.addActionListener(e -> doSubmit());
        btns.add(edit);
        // Schedule view buttons added to the review panel
        JButton viewFirst = new JButton("View First Semester Schedule");
        viewFirst.addActionListener(e -> cards.show(mainPanel, "schedule"));
        btns.add(viewFirst);
        JButton viewSecond = new JButton("View Second Semester Schedule");
        viewSecond.addActionListener(e -> cards.show(mainPanel, "schedule_second"));
        btns.add(viewSecond);
        btns.add(submitBtn);

        p.add(center, BorderLayout.CENTER);
        p.add(btns, BorderLayout.SOUTH);
        return p;
    }








    // schedule panel First Semester
    private JPanel schedulePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setName("schedule");
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Class Schedule - First Semester",
                TitledBorder.CENTER, TitledBorder.TOP));

        JPanel header = new JPanel(new GridLayout(3,1));
        JLabel grade = new JLabel("Grade & Section: 11 - ICT 3A", SwingConstants.CENTER);
        JLabel adviser = new JLabel("Class Adviser: ", SwingConstants.CENTER);
        JLabel group = new JLabel("Group: E1 (MORNING SESSION)", SwingConstants.CENTER);
        grade.setFont(new Font("Dialog", Font.BOLD, 14));
        adviser.setFont(new Font("Dialog", Font.PLAIN, 12));
        group.setFont(new Font("Dialog", Font.PLAIN, 12));
        header.add(grade);
        header.add(adviser);
        header.add(group);
        p.add(header, BorderLayout.NORTH);





        
        // Table data
        String[] cols = {"Time", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
        String[][] data = {
            {"6:30 - 7:15","GEN.MATH","GEN.MATH","KOMUNIKASYON","KOMUNIKASYON","ORAL COMMUNICATION"},
            {"7:15 - 8:00","KOMUNIKASYON","COMPROG 2","ETECH","UCSP","ETECH"},
            {"8:00 - 8:30","BREAK TIME","BREAK TIME","BREAK TIME","BREAK TIME","BREAK TIME"},
            {"8:30 - 9:15","ORAL COMMUNICATION","COMPROG 2","COMPROG 1","ORAL COMMUNICATION","GEN.MATH"},
            {"9:15 - 10:00","USCP","ETECH","UCSP","PE 1","COMPROG 1"},
            {"10:00 - 10:45","EARTH & LIFE SCIENCE","EARTH & LIFE SCIENCE","EARTH & LIFE SCIENCE","COMPROG 2 ", "COMPROG 1"}
        };

        JTable table = new JTable(data, cols) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table.setRowHeight(48);
        table.setFont(new Font("Dialog", Font.PLAIN, 12));
        table.getColumnModel().getColumn(0).setMaxWidth(110);

       
        TableCellRenderer wrapRenderer = new TableCellRenderer() {
            private final JTextArea ta = new JTextArea();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                ta.setText(value == null ? "" : value.toString());
                ta.setLineWrap(true);
                ta.setWrapStyleWord(true);
                ta.setOpaque(true);
                ta.setFont(table.getFont());
                if (isSelected) ta.setBackground(table.getSelectionBackground()); else ta.setBackground(table.getBackground());
                return ta;
            }
        };
        TableColumnModel tcm = table.getColumnModel();
        for (int i = 0; i < tcm.getColumnCount(); i++) tcm.getColumn(i).setCellRenderer(wrapRenderer);

        p.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back to Review");
        back.addActionListener(e -> cards.show(mainPanel, "review"));
        btns.add(back);
        p.add(btns, BorderLayout.SOUTH);

        return p;
    }

    // schedule Second Semester
    private JPanel schedulePanelSecond() {
        JPanel p = new JPanel(new BorderLayout());
        p.setName("schedule_second");
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Class Schedule - Second Semester",
                TitledBorder.CENTER, TitledBorder.TOP));

        // headeR
        JPanel header = new JPanel(new GridLayout(3,1));
        JLabel grade = new JLabel("Grade & Section: 11 - ICT 3A", SwingConstants.CENTER);
        JLabel adviser = new JLabel("Class Adviser: ", SwingConstants.CENTER);
        JLabel group = new JLabel("Group: E1 (MORNING SESSION)", SwingConstants.CENTER);
        grade.setFont(new Font("Dialog", Font.BOLD, 14));
        adviser.setFont(new Font("Dialog", Font.PLAIN, 12));
        group.setFont(new Font("Dialog", Font.PLAIN, 12));
        header.add(grade);
        header.add(adviser);
        header.add(group);
        p.add(header, BorderLayout.NORTH);

        // Table data
        String[] cols = {"Time", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
        String[][] data = {
            {"6:30 - 7:15","STATS & PROB","RESEARCH 1","PAGBASA AT PAGSULAT","PAGBASA AT PAGSULAT","RESEARCH 1"},
            {"7:15 - 8:00","PAGBASA AT PAGSULAT","COMPROG 4","STATS & PROB","21ST Century","READING AND WRITING"},
            {"8:00 - 8:30","BREAK TIME","BREAK TIME","BREAK TIME","BREAK TIME","BREAK TIME"},
            {"8:30 - 9:15","READING AND WRITING","COMPROG 4","COMPROG 3","STATS & PROB","READING AND WRITING"},
            {"9:15 - 10:00","PER.DEV’T","21ST Century","PER.DEV’T","RESEARCH 1","COMPROG 3"},
            {"10:00 - 10:45","21ST Century","PER.DEV'T","COMPROG 4","PE 2","COMPROG 3"}
        };

        JTable table = new JTable(data, cols) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table.setRowHeight(48);
        table.setFont(new Font("Dialog", Font.PLAIN, 12));
        table.getColumnModel().getColumn(0).setMaxWidth(110);

        
        TableCellRenderer wrapRenderer = new TableCellRenderer() {
            private final JTextArea ta = new JTextArea();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                ta.setText(value == null ? "" : value.toString());
                ta.setLineWrap(true);
                ta.setWrapStyleWord(true);
                ta.setOpaque(true);
                ta.setFont(table.getFont());
                if (isSelected) ta.setBackground(table.getSelectionBackground()); else ta.setBackground(table.getBackground());
                return ta;
            }
        };
        TableColumnModel tcm = table.getColumnModel();
        for (int i = 0; i < tcm.getColumnCount(); i++) tcm.getColumn(i).setCellRenderer(wrapRenderer);

        p.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back to Review");
        back.addActionListener(e -> cards.show(mainPanel, "review"));
        btns.add(back);
        p.add(btns, BorderLayout.SOUTH);

        return p;
    }




























    



    private JPanel navigationPanel(String current) {
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back");
        JButton next = new JButton("Next");

        back.addActionListener(e -> {
            switch (current) {
                case "family": cards.show(mainPanel, "personal"); break;
                case "school": cards.show(mainPanel, "family"); break;
                case "preferred": cards.show(mainPanel, "school"); break;
                case "review": cards.show(mainPanel, "preferred"); break;
                default: break;
            }
        });

        next.addActionListener(e -> {
            switch (current) {
                case "personal": if (validatePersonal()) cards.show(mainPanel, "family"); break;
                case "family": if (validateFamily()) cards.show(mainPanel, "school"); break;
                case "school": if (validateSchool()) cards.show(mainPanel, "preferred"); break;
                case "preferred": if (validatePreferred()) { populateReview(); cards.show(mainPanel, "review"); } break;
                default: break;
            }
        });

        if (current.equals("personal")) back.setEnabled(false);
        nav.add(back);
        nav.add(next);
        return nav;
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int y, String label, Component comp) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(comp, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == nextBtn) {
            Container visible = getVisiblePanel();
            if (visible == null) return;
            String name = getPanelName(visible);
            boolean ok = false;
            switch (name) {
                case "personal": ok = validatePersonal(); if (ok) cards.show(mainPanel, "family"); break;
                case "family": ok = validateFamily(); if (ok) cards.show(mainPanel, "school"); break;
                case "school": ok = validateSchool(); if (ok) cards.show(mainPanel, "preferred"); break;
                case "preferred": ok = validatePreferred(); if (ok) { populateReview(); cards.show(mainPanel, "review"); } break;
            }
        } else if (src == backBtn) {
            Container visible = getVisiblePanel();
            if (visible == null) return;
            String name = getPanelName(visible);
            switch (name) {
                case "family": cards.show(mainPanel, "personal"); break;
                case "school": cards.show(mainPanel, "family"); break;
                case "preferred": cards.show(mainPanel, "school"); break;
                case "review": cards.show(mainPanel, "preferred"); break;
            }
        }
    }

    private Container getVisiblePanel() {
        for (Component c : mainPanel.getComponents()) {
            if (c.isVisible()) return (Container) c;
        }
        return null;
    }

    private String getPanelName(Container c) {
        BorderLayout bl = (BorderLayout) ((JPanel) c).getLayout();
     
        for (String name : new String[]{"personal", "family", "school", "preferred", "review"}) {
            if (mainPanel.isAncestorOf(c) && mainPanel.getComponentZOrder(c) >= 0) return name;
        }
        return "personal";
    }

    private boolean validatePersonal() {
        if (empty(lrn) || empty(firstName) || empty(middleName) || empty(lastName) || empty(address) || empty(contactNumber)
                || empty(studentEmail) || empty(facebookName) || empty(placeOfBirth)) {
            showErr("All personal fields are required.");
            return false;
        }
        String contact = contactNumber.getText().trim();
        if (contact.length() != 11) { showErr("Contact number must be exactly 11 digits."); return false; }
        if (!contact.matches("\\d{11}")) { showErr("Contact number must contain digits only."); return false; }
        String email = studentEmail.getText().trim();
        if (!email.contains("@")) { showErr("Student email must contain '@'."); return false; }
        
        // Validate date of birth is between 1945 and 2010
        Date dob = (Date) dobSpinner.getValue();
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(dob);
        int year = dobCal.get(Calendar.YEAR);
        if (year < 1945 || year > 2010) {
            showErr("Date of birth must be between 1945 and 2010.");
            return false;
        }
        return true;
    }

    private boolean validateFamily() {
        if (empty(motherName) || empty(fatherName) || empty(guardianName) || empty(guardianContact)) {
            showErr("All family background fields are required.");
            return false;
        }
        String g = guardianContact.getText().trim();
        if (g.length() != 11 || !g.matches("\\d{11}")) { showErr("Guardian contact must be exactly 11 digits."); return false; }
        return true;
    }

    private boolean validateSchool() {
        if (empty(primarySchool) || empty(primaryAddress) || empty(secondarySchool) || empty(secondaryAddress)
                || empty(previousSchool) || empty(previousSchoolYear)) {
            showErr("All school attainment fields are required.");
            return false;
        }
        String y = previousSchoolYear.getText().trim();
        if (!y.matches("\\d{4}")) { showErr("Previous school year must be 4 digits."); return false; }
        return true;
    }

    private boolean validatePreferred() {
        // COMBO BOXES
        if (strand.getSelectedItem() == null || section.getSelectedItem() == null
                || eChoice.getSelectedItem() == null || modality.getSelectedItem() == null) {
            showErr("Please complete your preferred schedule selection.");
            return false;
        }
        return true;
    }

    private void populateReview() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        sb.append("--- Personal Information ---\n");
        sb.append(String.format("LRN: %s\n", lrn.getText()));
        sb.append(String.format("Name: %s %s %s\n", firstName.getText(), middleName.getText(), lastName.getText()));
        sb.append(String.format("Age: %s\n", ageSpinner.getValue().toString()));
        sb.append(String.format("Complete Home Address: %s\n", address.getText()));
        sb.append(String.format("Contact: %s\n", contactNumber.getText()));
        sb.append(String.format("Email: %s\n", studentEmail.getText()));
        sb.append(String.format("Facebook Name: %s\n", facebookName.getText()));
        sb.append(String.format("Place of birth: %s\n", placeOfBirth.getText()));
        sb.append(String.format("Date of birth: %s\n", df.format((Date) dobSpinner.getValue())));
        sb.append(String.format("Civil Status: %s\n", civilStatus.getSelectedItem()));
        sb.append(String.format("Sex: %s\n\n", sex.getSelectedItem()));

        sb.append("--- Family Background ---\n");
        sb.append(String.format("Mother: %s\n", motherName.getText()));
        sb.append(String.format("Father: %s\n", fatherName.getText()));
        sb.append(String.format("Guardian: %s\n", guardianName.getText()));
        sb.append(String.format("Guardian Contact: %s\n\n", guardianContact.getText()));

        sb.append("--- School Attainment ---\n");
        sb.append(String.format("Primary: %s\nAddress: %s\n", primarySchool.getText(), primaryAddress.getText()));
        sb.append(String.format("Secondary: %s\nAddress: %s\n", secondarySchool.getText(), secondaryAddress.getText()));
        sb.append(String.format("Previous School: %s\nPrevious Year: %s\n\n", previousSchool.getText(), previousSchoolYear.getText()));

        sb.append("--- Preferred Schedule ---\n");
        sb.append(String.format("Strand: %s\nSection: %s\nE1/E2: %s\nModality: %s\n", strand.getSelectedItem(), section.getSelectedItem(), eChoice.getSelectedItem(), modality.getSelectedItem()));

        reviewArea.setText(sb.toString());
    }

    private void doSubmit() {
        JOptionPane.showMessageDialog(this, "Registration submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
   
        System.exit(0);
    }

    private boolean empty(JTextComponent comp) {
        return comp.getText().trim().isEmpty();
    }

    private void showErr(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

 
    static class LetterFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            for (char c : string.toCharArray()) if (Character.isLetter(c) || Character.isWhitespace(c)) sb.append(c);
            super.insertString(fb, offset, sb.toString(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            for (char c : text.toCharArray()) if (Character.isLetter(c) || Character.isWhitespace(c)) sb.append(c);
            super.replace(fb, offset, length, sb.toString(), attrs);
        }
    }

    static class DigitFilter extends DocumentFilter {
        private int maxLen;
        private boolean allowLess = false;
        public DigitFilter(int maxLen) { this(maxLen, false); }
        public DigitFilter(int maxLen, boolean allowLess) { this.maxLen = maxLen; this.allowLess = allowLess; }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            for (char c : string.toCharArray()) if (Character.isDigit(c)) sb.append(c);
            int newLen = fb.getDocument().getLength() + sb.length();
            if (newLen <= maxLen) super.insertString(fb, offset, sb.toString(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            for (char c : text.toCharArray()) if (Character.isDigit(c)) sb.append(c);
            int newLen = fb.getDocument().getLength() - length + sb.length();
            if (newLen <= maxLen) super.replace(fb, offset, length, sb.toString(), attrs);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new registration());
    }
}
