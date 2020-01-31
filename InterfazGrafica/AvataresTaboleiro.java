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

        this.setVisible(true);
        this.setOpaque(false);
    }

    public void addAvater(Avatar avatar)
    {
        JLabel text =  new JLabel(avatar.getId() + "fsd ");

        text.setFont(new Font("arial",0, 5));
        text.setVisible(true);
        text.setOpaque(true);
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
