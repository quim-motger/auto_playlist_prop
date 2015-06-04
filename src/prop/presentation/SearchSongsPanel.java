package prop.presentation;

import prop.PropException;

import javax.swing.*;
import java.util.Calendar;

/**
 * Created by quim_motger on 26/05/15.
 */
public class SearchSongsPanel extends JPanel{

    SongPController songPController;
    SongTabView songTabView;

    /**
     * Creates a new SearchSongsPanel
     * @param spc   the SongPController
     * @param stv   the SongTabView
     */
    public SearchSongsPanel(SongPController spc, SongTabView stv) {
        songPController = spc;
        songTabView = stv;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jCheckBox8 = new javax.swing.JCheckBox();

        Calendar cal = Calendar.getInstance();
        SpinnerNumberModel yearModel = new SpinnerNumberModel(cal.get(Calendar.YEAR),1,cal.get(Calendar.YEAR),1);
        jSpinner1 = new JSpinner(yearModel);

        SpinnerNumberModel durationModel = new SpinnerNumberModel(1,1,999999,1);
        jSpinner2 = new javax.swing.JSpinner(durationModel);

        jLabel1.setText("Search Songs");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(songPController.listGenres()));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(songPController.listGenres()));

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setText("Year");

        jLabel3.setText("Artist");

        jLabel2.setText("Title");

        jLabel7.setText("Subgenre");

        jLabel6.setText("Genre");

        jLabel8.setText("Duration");

        jLabel4.setText("Album");

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jSpinner1.setEnabled(false);
        jSpinner2.setEnabled(false);
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(30, 30, 30)
                                                                                .addComponent(jLabel3))
                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jCheckBox2)
                                                                        .addComponent(jCheckBox3)
                                                                        .addComponent(jCheckBox8)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(38, 38, 38)
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jCheckBox1)))
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField1)
                                                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTextField2)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel8)
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(jLabel6))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jCheckBox5)
                                                                        .addComponent(jCheckBox6)
                                                                        .addComponent(jCheckBox7))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jComboBox1, 0, 168, Short.MAX_VALUE)
                                                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 94, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel3)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jCheckBox2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox3)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jCheckBox8))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jCheckBox5))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel7))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel8)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jCheckBox6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jCheckBox7)))))
                                .addGap(8, 8, 8)
                                .addComponent(jButton1)
                                .addContainerGap(150, Short.MAX_VALUE))
        );
    }// </editor-fold>


    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox1.getModel().isSelected()) {
            jTextField1.setEnabled(true);
        } else {
            jTextField1.setText("");
            jTextField1.setEnabled(false);
        }
    }

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox2.getModel().isSelected()) {
            jTextField2.setEnabled(true);
        } else {
            jTextField2.setText("");
            jTextField2.setEnabled(false);
        }
    }

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox3.getModel().isSelected()) {
            jTextField3.setEnabled(true);
        } else {
            jTextField3.setText("");
            jTextField3.setEnabled(false);
        }
    }

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox5.getModel().isSelected()) {
            jComboBox1.setEnabled(true);
        } else {
            jComboBox1.setSelectedIndex(0);
            jComboBox1.setEnabled(false);
        }
    }

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox6.getModel().isSelected()) {
            jComboBox2.setEnabled(true);
        } else {
            jComboBox2.setSelectedIndex(0);
            jComboBox2.setEnabled(false);
        }    }

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox7.getModel().isSelected()) {
            jSpinner2.setEnabled(true);
        } else {
            jSpinner2.setValue(0);
            jSpinner2.setEnabled(false);
        }
    }

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox8.getModel().isSelected()) {
            jSpinner1.setEnabled(true);
        } else {
            jSpinner1.setValue(0);
            jSpinner1.setEnabled(false);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if(jCheckBox1.getModel().isSelected() && jTextField1.getText().length() > 0 ||
        jCheckBox2.getModel().isSelected() && jTextField2.getText().length() > 0 ||
        jCheckBox3.getModel().isSelected() && jTextField3.getText().length() > 0 ||
        jCheckBox8.getModel().isSelected() ||
        jCheckBox5.getModel().isSelected() ||
        jCheckBox6.getModel().isSelected() ||
        jCheckBox7.getModel().isSelected()) {
            String s = "";
            if (jCheckBox1.getModel().isSelected() && jTextField1.getText().length() > 0) {
                s += "song_title|" + jTextField1.getText() + "\n";
            }
            if (jCheckBox2.getModel().isSelected() && jTextField2.getText().length() > 0) {
                s += "song_artist|" + jTextField2.getText() + "\n";
            }
            if (jCheckBox3.getModel().isSelected() && jTextField3.getText().length() > 0) {
                s += "song_album|" + jTextField3.getText() + "\n";
            }
            if (jCheckBox8.getModel().isSelected()) {
                s += "song_year|" + jSpinner1.getValue() + "\n";
            }
            if (jCheckBox5.getModel().isSelected()) {
                s += "song_genre|" + jComboBox1.getSelectedIndex() + "\n";
            }
            if (jCheckBox6.getModel().isSelected()) {
                s += "song_subgenre|" + jComboBox2.getSelectedIndex() + "\n";
            }
            if (jCheckBox7.getModel().isSelected()) {
                s += "song_duration|" + jSpinner2.getValue() + "\n";
            }
            try {
                String songs = songPController.searchSongs(s);
                SongListPanel songListPanel = new SongListPanel(songs,songTabView);
                songTabView.setRightPanel(songListPanel);
            } catch (PropException e) {
                e.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration
}
