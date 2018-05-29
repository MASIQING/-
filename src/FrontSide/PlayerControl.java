package FrontSide;

import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
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
 * @version 2018/5/27
 * 
 * @author 马思清
 *
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
		private String mediaLocation;
	    private MediaPlayer mediaPlayer;
	    private Timer timer = new Timer();
	   
	    /**
	     * 设置电影文件的URL
	     * **/
		public void setMediaURL(String mediaLocation) {
			this.mediaLocation = mediaLocation;
		}
		
		/**
		 * 停止播放,清空播放器内容并停止计时
		 * **/
		public void stop() {
			if(mediaPlayer!=null){
				mediaPlayer.dispose();
				timer.cancel();
			}
		}
		
		/**
		 * 获取播放器状态<br>
		 * @return 0播放器已清空,1正在播放,2暂停,-1其他情况
		 * **/
		public int getStatus() {
			
			if(mediaPlayer == null ||
			   mediaPlayer.getStatus().toString().equals("DIPOSED")) {
				return 0;
			}else if(mediaPlayer.getStatus().toString().equals("PLAYING")) {
				return 1;
			}else if(mediaPlayer.getStatus().toString().equals("PAUSED")) {
				return 2;
			}else {
				return -1;
			}
		
		}
		/**
		 * PlayerControl的初始化<br>
		 * 清除所有的样式并应用最新的样式
		 * **/
		@FXML
		public void initialize() {
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		}
		
		/**
		 * 开始播放<br>
		 * 自动加载最新的视频文件并开始监听进度条和音量控制
		 * **/
		public void mediaPlay() {
		
			try{	
				/**加载影片到视频播放器对象**/
				String mediaURL = getClass().getResource(mediaLocation).toString(); 
				Media media = new Media(mediaURL);		
				
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setAutoPlay(true);
				
				mediaView.setMediaPlayer(mediaPlayer);
				mediaPlayer.play();
				volumeSlider.setValue(30);
				
			}catch(Exception e) {
				System.out.println("Error in loading movie url");
				e.printStackTrace();
				stop();
			}		
			    
			
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
						playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		            }
		        });
		 
			/**音量控制滑动条监听**/
			    volumeSlider.valueProperty().addListener((
			        ObservableValue<? extends Number> ov, 
			        Number old_val, Number new_val) -> {
			            /**音量衰减值**/
			            int damp = 50;
			            mediaPlayer.setVolume(new_val.doubleValue()/damp);
			     });
	        
		    /**键盘处理监听**/
		        fatherPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						System.out.println(event.getCode());
						if(event.getCode() == KeyCode.SHIFT) {
		                    pauseAndPlay();
		                }	
						if(event.getCode() == KeyCode.UP) {
		                    pauseAndPlay();
		                }	
						if(event.getCode() == KeyCode.DOWN) {
		                   
		                }	
						if(event.getCode() == KeyCode.LEFT) {
		                   
		                }	
						if(event.getCode() == KeyCode.RIGHT) {
		                    
		                }	
				}});
			    
		}
	    
		/**
		 * 暂停与播放按钮的监听
		 * **/
		@FXML
		public void pauseAndPlay() {

			if(mediaPlayer.getStatus().toString().equals("PLAYING")) {
				mediaPlayer.pause();
				playPause.getStylesheets().clear();
				playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Play.css").toExternalForm());
			}
			
			if(mediaPlayer.getStatus().toString().equals("PAUSED")) {
				mediaPlayer.play();
				playPause.getStylesheets().clear();
				playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
			}
			
			if(mediaPlayer.getStatus().toString().equals("STOP")) {
				mediaPlayer.play();
				playPause.getStylesheets().clear();
				playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
			}
		}
		
		/**
		 * 视频进度滑动条的控制
		 * **/
		private void slideControl() {
			double endTime = mediaPlayer.getStopTime().toMillis();
			double nowTime = mediaPlayer.getCurrentTime().toMillis();
			double percentValue = (nowTime/endTime) * 100 ;
			
			if(slider.isPressed() == false) {
				try {
					slider.setValue(percentValue);
					String time = changeTime(nowTime)+"     "+changeTime(endTime);
					showTime.setText(time);
				}catch(Exception e) {
					System.out.println(" Error in Slider and show time  ");
					e.printStackTrace();
				}	
			}
	    }
		
		/**
		 * 时间格式转换
		 * **/
		private String changeTime(double time) {
			String changed = "";
		    int newTime = (int) (time/1000);
		    String minutes = String.valueOf(newTime/60);
		    String seconds = String.valueOf(newTime - (newTime/60)*60 );
		    if(minutes.length() == 1){
		    	minutes = "0"+minutes;
		    }
		    if(seconds.length() == 1){
		    	seconds = "0"+seconds;
		    }
		    changed = minutes+ ":" +seconds;
			return changed;
		}
		
}
