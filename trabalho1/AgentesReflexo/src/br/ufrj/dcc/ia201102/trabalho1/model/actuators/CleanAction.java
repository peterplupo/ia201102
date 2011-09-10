package br.ufrj.dcc.ia201102.trabalho1.model.actuators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;


public class CleanAction implements Action {

	@Override
	public void execute(Room room) {
		room.setState(State.CLEAN);
		playVacuum();
	}

	@Override
	public int cost() {
		return 10;
	}
	
	public static synchronized void playVacuum() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(new FileInputStream(new File("vacuum_cleaner_short.wav")));
						clip.open(inputStream);
						clip.start();
						
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		try {
		Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
