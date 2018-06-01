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
 * 控制播放器的播放行为<br>
 * 包括播放器进度条和音量控制
 * @version 2018/6/1
 * 
 * @author 马思清
 * @author 李晶晶
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
		 * 停止播放,清空播放器内容并停止计时
		 * **/
	    public void stop() {
	    	MediaPlayer mediaPlayer = backSide.getMediaPlayer();
			if(mediaPlayer!=null){
				mediaPlayer.dispose();
				timer.cancel();
			}
		}
	    
	    /**使聚焦**/
	    public void fatherPaneFocus() {
	    	settingPane.requestFocus();
	    	System.out.println("settingPane Focus "+settingPane.isFocused());
	    }
	    
		/**
		 * PlayerControl的初始化<br>
		 * 清除所有的样式并应用最新的样式
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
		 * 开始播放<br>
		 * 自动加载最新的视频文件并开始监听进度条和音量控制
		 * **/
		public void mediaPlay() {
		 
			MediaPlayer mediaPlayer = backSide.startMediaPlayer();
			
			mediaView.setMediaPlayer(mediaPlayer);
			volumeSlider.setValue(50);
			
			/**开始计时**/
		    timer = new Timer();
    		timer.schedule(new TimerTask() {
    			public void run() {
    				try {
    					slideControl();
    				}catch(Exception e) {}
    			}
    		}, 0, 100);
			/**视频播放进度条监听**/
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
		 
			/**音量控制滑动条监听**/
			    volumeSlider.valueProperty().addListener((
			        ObservableValue<? extends Number> ov, 
			        Number old_val, Number new_val) -> {
			            /**音量衰减值**/
			            int damp = 100;
			            mediaPlayer.setVolume(new_val.doubleValue()/damp);
			     });
		  
		}
	    
		/**
		 * 暂停与播放按钮的监听
		 * **/
		@FXML
		public void pauseAndPlay() {
             backSide.playPauseAction(playPause,play,paused);
		}
		
		/**
		 * 视频进度滑动条的控制
		 * **/
		private void slideControl() {
			 backSide.slideControl(slider, showTime);
	    }
		
		

		
		
}
