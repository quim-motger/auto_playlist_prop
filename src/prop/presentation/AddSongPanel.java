package prop.presentation;

import prop.PropException;
import prop.domain.Pair;
import prop.presentation.generator.FormGen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by quim_motger on 24/05/15.
 */
public class AddSongPanel extends FormGen {

    private SongPController songPController;
    private SongTabView songTabView;
    private JButton addButton;
    private JComboBox genreComboBox;
    private JComboBox subgenreComboBox;
    private JTextField titleField;
    private JTextField artistField;
    private JTextField albumField;
    private JSpinner yearSpinner;
    private JSpinner durationSpinner;

    /**
     * Creates new form AddSongPanel
     * @param spc   the SongPController
     * @param stv   the SongTabView
     */
    public AddSongPanel(SongPController spc, SongTabView stv) {
        songPController = spc;
        songTabView = stv;
        setTitleText("Add new song");
        addButton = new JButton("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAction(evt);
            }
        });
        addButton(addButton);

        String[] genres = songPController.listGenres();

        genreComboBox.setModel(new DefaultComboBoxModel(genres));

        subgenreComboBox.setModel(new DefaultComboBoxModel(genres));
    }


    private void submitAction(java.awt.event.ActionEvent evt) {
        try {
            String title = titleField.getText();
            String artist = artistField.getText();
            String album = albumField.getText();
            int y = (Integer) yearSpinner.getValue();
            String year = String.valueOf(y);
            String genre = String.valueOf(genreComboBox.getSelectedIndex());
            String subgenre = String.valueOf(subgenreComboBox.getSelectedIndex());
            int d = (Integer) durationSpinner.getValue();
            String duration = String.valueOf(d);
            songPController.addSong(title, artist, album, year, genre, subgenre, duration);
            songTabView.updateSongSetModel();
            songTabView.setShowSongPanel(title,artist);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

    @Override
    protected ArrayList<Pair<String, JComponent>> setFields() {
        ArrayList<Pair<String, JComponent>> fields = new ArrayList<>();
        titleField = new JTextField();
        artistField = new JTextField();
        albumField = new JTextField();
        genreComboBox = new JComboBox();
        subgenreComboBox = new JComboBox();
        addButton = new JButton();

        Calendar cal = Calendar.getInstance();
        SpinnerNumberModel yearModel = new SpinnerNumberModel(cal.get(Calendar.YEAR), 1, cal.get(Calendar.YEAR), 1);
        yearSpinner = new JSpinner(yearModel);

        SpinnerNumberModel durationModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        durationSpinner = new JSpinner(durationModel);

        titleField.setHorizontalAlignment(JTextField.LEFT);


        fields.add(new Pair<String, JComponent>("Title", titleField));
        fields.add(new Pair<String, JComponent>("Artist", artistField));
        fields.add(new Pair<String, JComponent>("Year", yearSpinner));
        fields.add(new Pair<String, JComponent>("Genre", genreComboBox));
        fields.add(new Pair<String, JComponent>("Subgenre", subgenreComboBox));
        fields.add(new Pair<String, JComponent>("Duration", durationSpinner));
        return fields;
    }
}