package InterfazGrafica;

import xogadores.Avatar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AvataresTaboleiro extends JPanel {
    private ArrayList<JLabel> avatares;

    public AvataresTaboleiro(JButton boton)
    {
        this.setPreferredSize(new Dimension(boton.getWidth(), boton.getHeight()-10));
        avatares = new ArrayList<>();
    }

    public void addAvater(Avatar avatar)
    {
        JLabel text =  new JLabel();
        text.setText(avatar.getId() + " ");

        avatares.add(text);
    }

    public void elimAvatar(Avatar avatar)
    {
        for (int i = 0; i < avatares.size(); i++)
        {
            if (avatares.get(i).getText().equals(avatar.getId() + " "))
                avatares.remove(i);
        }
    }

}
