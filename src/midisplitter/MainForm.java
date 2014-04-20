/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midisplitter;

import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Törcsi
 */
public class MainForm extends javax.swing.JPanel {

    /**
     * Creates new form MainForm
     */
    final JFileChooser fileChooser = new JFileChooser();
    HashMap<String, ConfigPanel> runon = new HashMap<String, ConfigPanel>();
    Preferences prefs = Preferences.userNodeForPackage(MidiSplitter.class);
    public MainForm() {
        //this.setPreferredSize(new Dimension(300, 400));
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooseFileButton = new javax.swing.JButton();
        choosedFileLabel = new javax.swing.JLabel();
        prefixField = new javax.swing.JTextField();
        postfixField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        prepostLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        configAll = new javax.swing.JPanel();
        preprocessButton = new javax.swing.JButton();
        processButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        errorList = new javax.swing.JList();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        chooseFileButton.setText("ChooseFile");
        chooseFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseFile(evt);
            }
        });

        choosedFileLabel.setText("Please select a file..");

        prefixField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prepostChange(evt);
            }
        });
        prefixField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                prepostFocus(evt);
            }
        });
        prefixField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                prepostKey(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prepostKey(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prepostKey(evt);
            }
        });

        postfixField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prepostChange(evt);
            }
        });
        postfixField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                prepostFocus(evt);
            }
        });
        postfixField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                prepostKey(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prepostKey(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prepostKey(evt);
            }
        });

        jLabel1.setText("Prefix:");

        jLabel2.setText("Postfix:");

        prepostLabel.setText("Output will like: \"filename.mid\"");

        javax.swing.GroupLayout configAllLayout = new javax.swing.GroupLayout(configAll);
        configAll.setLayout(configAllLayout);
        configAllLayout.setHorizontalGroup(
            configAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        configAllLayout.setVerticalGroup(
            configAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Config all", configAll);

        preprocessButton.setText("Preprocess");
        preprocessButton.setEnabled(false);
        preprocessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preprocessFiles(evt);
            }
        });

        processButton.setText("Process");
        processButton.setEnabled(false);
        processButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processEvent(evt);
            }
        });

        errorList.setModel(new DefaultListModel());
        errorList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(errorList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(preprocessButton)
                                .addGap(18, 18, 18)
                                .addComponent(processButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chooseFileButton)
                                    .addComponent(jLabel1)
                                    .addComponent(prefixField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(postfixField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addComponent(prepostLabel))
                                    .addComponent(choosedFileLabel))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chooseFileButton)
                            .addComponent(choosedFileLabel))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prefixField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postfixField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prepostLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(preprocessButton)
                            .addComponent(processButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ChooseFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseFile
        String previouslyLoaded = prefs.get("lastDicPosition", null);
        if (previouslyLoaded != null) {
            fileChooser.setCurrentDirectory(new File(previouslyLoaded));
        }
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            prefs.put("lastDicPosition", fileChooser.getSelectedFile().getPath());
            loadFile(fileChooser.getSelectedFile());
        } else {
            //Cancelled
        }
    }//GEN-LAST:event_ChooseFile

    private void prepostAction() {
        prepostLabel.setText("Output will like: \"" + prefixField.getText() + "filename" + postfixField.getText() + ".mid\"");
    }
    private void prepostChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prepostChange
        prepostAction();
    }//GEN-LAST:event_prepostChange

    private void prepostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prepostFocus
        prepostAction();
    }//GEN-LAST:event_prepostFocus

    private void prepostKey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prepostKey
        prepostAction();
    }//GEN-LAST:event_prepostKey

    private void preprocessFiles(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preprocessFiles
        preprocess();
    }//GEN-LAST:event_preprocessFiles

    private void processEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processEvent
        process();
    }//GEN-LAST:event_processEvent

    private void process() {
        try {
            for (Map.Entry<String, ConfigPanel> entry : runon.entrySet()) {
                Splitter sp = new Splitter();
                Sequence sequence = MidiSystem.getSequence(new File(entry.getKey()));
                sp.splitit(sequence);
                ReOrganizer reo = new ReOrganizer(sp);
                if (!entry.getValue().isGlobal()) {
                    int chnum = reo.firstComeFirstServe(entry.getValue().getBooleanArray());
                    if (chnum > 16) {
                        ((DefaultListModel<String>)errorList.getModel()).addElement(entry.getValue().getOutFileName()+" not converted too much chennel");
                        continue;
                    }
                    Sequence oseq = new Sequence(sequence.getDivisionType(), sequence.getResolution());
                    OutputWriter opw = new OutputWriter();
                    opw.write(oseq, reo);
                    int[] allowedTypes = MidiSystem.getMidiFileTypes(oseq);
                    File tmpf=new File(entry.getValue().getOutFileName());
                    tmpf=tmpf.getParentFile();
                    tmpf.mkdirs();
                    MidiSystem.write(oseq, allowedTypes[0], new File(entry.getValue().getOutFileName()));
                } else {
                    int chnum = reo.firstComeFirstServe();
                    if (chnum > 16) {
                        ((DefaultListModel<String>)errorList.getModel()).addElement(entry.getValue().getOutFileName()+" not converted too much chennel");
                        continue;
                    }
                    Sequence oseq = new Sequence(sequence.getDivisionType(), sequence.getResolution());
                    OutputWriter opw = new OutputWriter();
                    opw.write(oseq, reo);
                    int[] allowedTypes = MidiSystem.getMidiFileTypes(oseq);
                    File tmpFile = new File(entry.getKey());
                    String outFName = tmpFile.getName();
                    outFName = outFName.replace(".mid", postfixField.getText() + ".mid");
                    String outPFName = tmpFile.getParentFile().getAbsolutePath();
                    outFName = outPFName + "\\" + prefixField.getText() + outFName;
                    File tmpf=new File(outFName);
                    tmpf=tmpf.getParentFile();
                    tmpf.mkdirs();
                    MidiSystem.write(oseq, allowedTypes[0], new File(outFName));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            e.printStackTrace();
        }
    }

    private void preprocess() {
        chooseFileButton.setEnabled(false);
        for (Map.Entry<String, ConfigPanel> me : runon.entrySet()) {
            if (me.getValue() == null) {
                File tmpFile = new File(me.getKey());
                String outFName = tmpFile.getName();
                outFName = outFName.replace(".mid", postfixField.getText() + ".mid");
                String outPFName = tmpFile.getParentFile().getAbsolutePath();
                outFName = outPFName + "\\" + prefixField.getText() + outFName;
                ConfigPanel tmpCP = new ConfigPanel(tmpFile, outFName);
                jTabbedPane1.addTab(tmpFile.getName(), tmpCP);
                runon.put(me.getKey(), tmpCP);
            }
        }
        preprocessButton.setEnabled(false);
        processButton.setEnabled(true);
    }

    private void loadFile(File selectedFile) {
        try {
            runon.clear();
            if (selectedFile.isDirectory()) {
                int n = JOptionPane.showConfirmDialog(
                        this,
                        "Its a directory. You want to process it recursevely?",
                        "Recursive?",
                        JOptionPane.YES_NO_OPTION);
                int midifilenum = processDirs(n == JOptionPane.YES_OPTION, selectedFile);
                choosedFileLabel.setText("\"" + selectedFile.getAbsolutePath() + "\"" + " number of midis: " + midifilenum);
                if (midifilenum > 0) {
                    preprocessButton.setEnabled(true);
                }
            } else {
                if (selectedFile.getAbsolutePath().endsWith(".mid")) {
                    runon.put(selectedFile.getAbsolutePath(), null);
                    choosedFileLabel.setText("\"" + selectedFile.getAbsolutePath() + "\"");
                    preprocessButton.setEnabled(true);
                } else {
                    choosedFileLabel.setText("The choosed file is incompatible!");
                    preprocessButton.setEnabled(false);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int processDirs(Boolean recursive, File homeDir) {
        int i = 0;
        for (File f : homeDir.listFiles()) {
            if (f.getAbsolutePath().endsWith(".mid")) {
                i++;
                runon.put(f.getAbsolutePath(), null);
            }
            if (recursive) {
                if (f.isDirectory()) {
                    i += processDirs(recursive, f);
                }
            }
        }
        return i;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseFileButton;
    private javax.swing.JLabel choosedFileLabel;
    private javax.swing.JPanel configAll;
    private javax.swing.JList errorList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField postfixField;
    private javax.swing.JTextField prefixField;
    private javax.swing.JLabel prepostLabel;
    private javax.swing.JButton preprocessButton;
    private javax.swing.JButton processButton;
    // End of variables declaration//GEN-END:variables
}
