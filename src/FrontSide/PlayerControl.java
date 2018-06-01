package FrontSide;

import java.util.Timer;
import java.util.TimerTask;

import Model.MediaPlay;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * ���Ʋ������Ĳ�����Ϊ<br>
 * ��������������������������
 * @version 2018/6/1
 * 
 * @author ��˼��
 * @author ���
 * **/
public class PlayerControl {
	   
		@FXML private Button playPause;
		@FXML private Slider slider;
		@FXML private Slider volumeSlider;
		@FXML private MediaView mediaView;
		@FXML private Text showTime;
		@FXML private Button back;
		@FXML private AnchorPane fatherPane;
		@FXML private AnchorPane settingPane;
		@FXML private AnchorPane mediaPane;
		@FXML private ImageView volumeImage;
		
		private String paused = getClass().getResource("css\\CSS Button Paused.css").toExternalForm();
		private String play = getClass().getResource("css\\CSS Button Play.css").toExternalForm();
		private MediaPlay backSide = new MediaPlay();
	    private Timer timer = new Timer();
	   
	    public void setMediaURL(String mediaLocation) {
	    	System.out.println("setMediaURL "+mediaLocation);
	    	String mediaURL = getClass().getResource(mediaLocation).toString(); 
			backSide.setMediaURL(mediaURL);
		}
	    
	    public MediaPlay getBackSide() {
	    	return backSide;
	    }
	    
	    /**
		 * ֹͣ����,��ղ��������ݲ�ֹͣ��ʱ
		 * **/
	    public void stop() {
	    	MediaPlayer mediaPlayer = backSide.getMediaPlayer();
			if(mediaPlayer!=null){
				mediaPlayer.dispose();
				timer.cancel();
			}
		}
	    
	    /**ʹ�۽�**/
	    public void fatherPaneFocus() {
	    	settingPane.requestFocus();
	    	System.out.println("settingPane Focus "+settingPane.isFocused());
	    }
	    
		/**
		 * PlayerControl�ĳ�ʼ��<br>
		 * ������е���ʽ��Ӧ�����µ���ʽ
		 * **/
		@FXML
		public void initialize() {
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(paused);
			
			settingPane.addEventFilter(KeyEvent.KEY_PRESSED, (key) ->{
				MediaPlayer mediaPlayer = backSide.getMediaPlayer();
				
				if(key.getCode() == KeyCode.ADD){
    				double oldVolume = mediaPlayer.getVolume();
    				mediaPlayer.setVolume(oldVolume+0.02);
    				double newVolume = mediaPlayer.getVolume();
    				volumeSlider.setValue(newVolume*100);
    				key.consume();
				}
				
				if(key.getCode() == KeyCode.SUBTRACT){
    				double oldVolume = mediaPlayer.getVolume();
    				mediaPlayer.setVolume(oldVolume-0.02);
    				double newVolume = mediaPlayer.getVolume();
    				volumeSlider.setValue(newVolume*100);
    				key.consume();
				}
				
				if(key.getCode() == KeyCode.LEFT){
					double endTime = mediaPlayer.getStopTime().toMillis();
					double nowTime = mediaPlayer.getCurrentTime().toMillis();
					double percentValue = (nowTime/endTime);
					double newPercentTime = percentValue-0.01;
					double newTime = newPercentTime*endTime;
					mediaPlayer.stop();
					mediaPlayer.setStartTime(Duration.millis(newTime));
					mediaPlayer.play();
					slider.setValue(newPercentTime*100);
					key.consume();
				}
				
				if(key.getCode() == KeyCode.RIGHT){
					double endTime = mediaPlayer.getStopTime().toMillis();
					double nowTime = mediaPlayer.getCurrentTime().toMillis();
					double percentValue = (nowTime/endTime);
					double newPercentTime = percentValue+0.01;
					double newTime = newPercentTime*endTime;
					mediaPlayer.stop();
					mediaPlayer.setStartTime(Duration.millis(newTime));
					mediaPlayer.play();
					slider.setValue(newPercentTime*100);
					key.consume();
				}
				
				if(key.getCode() == KeyCode.ENTER){
					pauseAndPlay();
					key.consume();
				}
				
				if(key.getCode() == KeyCode.BACK_SPACE){
					MenuControl menuControl = backSide.getMenuControl();
	                menuControl.leftPaneFocus(1);
					key.consume();
				}
				
				if(key.getCode() == KeyCode.UP){
					key.consume();
				}
				
				if(key.getCode() == KeyCode.DOWN){
					key.consume();
				}
			});
			
		}
		
	
		/**
		 * ��ʼ����<br>
		 * �Զ��������µ���Ƶ�ļ�����ʼ��������������������
		 * **/
		public void mediaPlay() {
		 
			MediaPlayer mediaPlayer = backSide.startMediaPlayer();
			
			mediaView.setMediaPlayer(mediaPlayer);
			volumeSlider.setValue(50);
			
			/**��ʼ��ʱ**/
		    timer = new Timer();
    		timer.schedule(new TimerTask() {
    			public void run() {
    				try {
    					slideControl();
    				}catch(Exception e) {}
    			}
    		}, 0, 100);
			/**��Ƶ���Ž���������**/
			    slider.valueProperty().addListener((
		            ObservableValue<? extends Number> ov, 
		            Number old_val, Number new_val) -> {
		            if(slider.isPressed()==true) {
		            	double endTime = mediaPlayer.getStopTime().toMillis();
						double startTime = (new_val.doubleValue()/100)*endTime;
						mediaPlayer.stop();
						mediaPlayer.setStartTime(Duration.millis(startTime));
						mediaPlayer.play();
						playPause.getStylesheets().clear();
						playPause.getStylesheets().addAll(paused);
		            }
		        });
		 
			/**�������ƻ���������**/
			    volumeSlider.valueProperty().addListener((
			        ObservableValue<? extends Number> ov, 
			        Number old_val, Number new_val) -> {
			            /**����˥��ֵ**/
			            int damp = 100;
			            mediaPlayer.setVolume(new_val.doubleValue()/damp);
			     });
		  
		}
	    
		/**
		 * ��ͣ�벥�Ű�ť�ļ���
		 * **/
		@FXML
		public void pauseAndPlay() {
             backSide.playPauseAction(playPause,play,paused);
		}
		
		/**
		 * ��Ƶ���Ȼ������Ŀ���
		 * **/
		private void slideControl() {
			 backSide.slideControl(slider, showTime);
	    }
		
		

		
		
}
