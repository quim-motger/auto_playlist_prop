package prop.presentation;

import prop.PropException;
import prop.domain.Pair;
import prop.presentation.generator.FormGen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by quim_motger on 24/05/15.
 */
public class ShowSongPanel extends FormGen {

    public static final String TITLE = "Song details";
    private SongPController songPController;
    private SongTabView songTabView;
    private String title;
    private String artist;
    private JButton editButton;
    private JButton saveButton;
    private JButton removeButton;
    private JButton cancelButton;
    private JComboBox genreComboBox;
    private JComboBox subgenreComboBox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel errorText;
    private JSeparator jSeparator1;
    private JTextField titleField;
    private JTextField artistField;
    private JTextField albumField;
    private JSpinner yearSpinner;
    private JSpinner durationSpinner;
    private JPanel emptyPanel;


    /**
     * Creates a new ShowSongPanel
     * @param scp   the SongPController
     * @param stv   the SongTabView
     * @param t     the title of the song
     * @param a     the artist of the song
     */
    public ShowSongPanel(SongPController scp, SongTabView stv, String t, String a) {
        songPController = scp;
        songTabView = stv;
        title = t;
        artist = a;
        setTitleText(TITLE);

        genreComboBox.setModel(new DefaultComboBoxModel(songPController.listGenres()));

        subgenreComboBox.setModel(new DefaultComboBoxModel(songPController.listGenres()));

        try {
            String[] attr = songPController.getSong(title,artist).split(Pattern.quote("|"));
            titleField.setText(attr[0]);
            artistField.setText(attr[1]);
            albumField.setText(attr[2]);
            int y = Integer.parseInt(attr[3]);
            yearSpinner.setValue(y);
            int d = Integer.parseInt(attr[6]);
            durationSpinner.setValue(d);
            genreComboBox.setSelectedIndex(Integer.parseInt(attr[4]));
            subgenreComboBox.setSelectedIndex(Integer.parseInt(attr[5]));
            setEditable(false);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }

        addButtons();
    }


    private void setEditable(boolean editable) {
        titleField.setEditable(editable);
        artistField.setEditable(editable);
        albumField.setEditable(editable);
        genreComboBox.setEnabled(editable);
        subgenreComboBox.setEnabled(editable);
        yearSpinner.setEnabled(editable);
        durationSpinner.setEnabled(editable);
        editButton.setEnabled(!editable);
        removeButton.setEnabled(!editable);
        saveButton.setEnabled(editable);
        cancelButton.setEnabled(editable);
    }

    private void saveButton(java.awt.event.ActionEvent evt) {
        try {
            int y = (Integer) yearSpinner.getValue();
            int d = (Integer) durationSpinner.getValue();
            if (title != titleField.getText().trim()) {
                songPController.edit(title, artist, "title", titleField.getText().trim());
                title = titleField.getText().trim();
            }
            if (artist != artistField.getText().trim()) {
                songPController.edit(title, artist, "artist", artistField.getText().trim());
                artist = artistField.getText().trim();
            }
            songPController.edit(title, artist, "album", albumField.getText());
            songPController.edit(title, artist, "year", String.valueOf(y));
            songPController.edit(title, artist, "genre", String.valueOf(genreComboBox.getSelectedIndex()));
            songPController.edit(title, artist, "subgenre", String.valueOf(subgenreComboBox.getSelectedIndex()));
            songPController.edit(title, artist, "duration", String.valueOf(d));
            setEditable(false);
            songTabView.updateSongSetModel();
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

    private void cancelButton(java.awt.event.ActionEvent evt) {
        try {
            String[] attr = songPController.getSong(title, artist).split(Pattern.quote("|"));
            titleField.setText(attr[0]);
            artistField.setText(attr[1]);
            albumField.setText(attr[2]);
            int y = Integer.parseInt(attr[3]);
            yearSpinner.setValue(y);
            int d = Integer.parseInt(attr[6]);
            durationSpinner.setValue(d);
            setEditable(false);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }


    private void removeButton(java.awt.event.ActionEvent evt) {
        try {
            songPController.removeSong(title, artist);
            songTabView.updateSongSetModel();
            songTabView.setRightPanel(emptyPanel);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }

    }

    @Override
    protected ArrayList<Pair<String, JComponent>> setFields() {
        ArrayList<Pair<String, JComponent>> fields = new ArrayList<>();

        jSeparator1 = new JSeparator();
        jLabel1 = new JLabel();
        errorText = new JLabel();
        jLabel8 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel2 = new JLabel();
        jLabel4 = new JLabel();
        artistField = new JTextField();
        jLabel3 = new JLabel();
        titleField = new JTextField();
        jLabel5 = new JLabel();
        albumField = new JTextField();
        editButton = new JButton();
        removeButton = new JButton();
        saveButton = new JButton();
        cancelButton = new JButton();
        genreComboBox = new JComboBox();
        subgenreComboBox = new JComboBox();
        emptyPanel = new JPanel();

        SpinnerNumberModel durationModel = new SpinnerNumberModel(1, 1, 999999, 1);
        durationSpinner = new JSpinner(durationModel);


        Calendar cal = Calendar.getInstance();
        SpinnerNumberModel yearModel = new SpinnerNumberModel(cal.get(Calendar.YEAR), 1, cal.get(Calendar.YEAR), 1);
        yearSpinner = new JSpinner(yearModel);


        jLabel8.setText("Duration");
        fields.add(new Pair<String, JComponent>("Title", titleField));
        fields.add(new Pair<String, JComponent>("Artist", artistField));
        fields.add(new Pair<String, JComponent>("Album", albumField));
        fields.add(new Pair<String, JComponent>("Year", yearSpinner));
        fields.add(new Pair<String, JComponent>("Duration", durationSpinner));
        fields.add(new Pair<String, JComponent>("Genre", genreComboBox));
        fields.add(new Pair<String, JComponent>("Subgenre", subgenreComboBox));

        return fields;
    }

    private void addButtons() {
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEditable(true);
            }
        });
        addButton(editButton);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton(evt);
            }
        });
        addButton(saveButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton(evt);
            }
        });
        addButton(cancelButton);

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButton(evt);
            }
        });
        addButton(removeButton);
    }
}

