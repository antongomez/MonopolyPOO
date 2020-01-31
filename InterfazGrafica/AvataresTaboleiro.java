package InterfazGrafica;

import xogadores.Avatar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AvataresTaboleiro extends JPanel {

    private HashMap<Avatar, JLabel> avatares;

    public AvataresTaboleiro(JButton boton) {
        //this.setPreferredSize(new Dimension(boton.getWidth(), boton.getHeight() - 10));
        avatares = new HashMap<>();

        this.setVisible(true);
        this.setOpaque(false);
    }

    public void addAvater(Avatar avatar) {
        JLabel text = new JLabel("&" + avatar.getId());

        text.setFont(new Font("arial", 0, 15));
        text.setVisible(true);
        text.setOpaque(true);
        avatares.put(avatar, text);
        this.add(text);
    }

    public void elimAvatar(Avatar avatar) {
        avatares.remove(avatar);
    }

}
