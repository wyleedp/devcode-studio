package com.github.devcode.studio.component;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashWithProgressWindow extends JWindow {

	private static final long serialVersionUID = 1L;
	
	private JProgressBar progressBar;

    public SplashWithProgressWindow() {
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 이미지
        JLabel imageLabel = new JLabel(new ImageIcon(
            getClass().getResource("/images/splash.png")
        ));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 프로그레스바
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("Loading...");

        add(imageLabel, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
    }

    public void setProgress(int value, String message) {
        progressBar.setValue(value);
        progressBar.setString(message);
    }
}
