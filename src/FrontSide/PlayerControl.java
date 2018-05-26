package FrontSide;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PlayerControl implements KeyListener{
	    
	    //播放器界面 (FXML变量)
		@FXML private Button playPause;
		@FXML private Slider slider;
		@FXML private Slider volumeSlider;
		@FXML private MediaView mediaView;
		@FXML private Text showTime;
		@FXML private Button back;
		@FXML private AnchorPane settingPane;
		@FXML private AnchorPane mediaPane;
		@FXML private ImageView volumeImage;

		private String mediaLocation;  //视频文件地址  
	    private MediaPlayer mediaPlayer;//新建的MediaPlayer
	    Timer timer = new Timer();
	    
	   
		public String getMediaLocation() {
			return mediaLocation;
		}
		public void setMediaURL(String mediaLocation) {
			this.mediaLocation = mediaLocation;
		}
		
		//关闭影片
		public void stop() {
			if(mediaPlayer!=null) mediaPlayer.dispose();
		}
		
		//获取播放器状态
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
		
		
		@FXML//初始化代码（加载特定的css文件）
		public void initialize() {
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		}
		
		//开始播放视频
		public void mediaPlay() {
		
			try{	
				//获取视频URL 并创建媒体对象
				String mediaURL = getClass().getResource(mediaLocation).toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
				System.out.println("mediaURL  "+mediaURL);
				Media media = new Media(mediaURL);		
				//创建播放器对象
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setAutoPlay(true); //设置自动播放
                
				System.out.println("Width"+media.getWidth());
				
				//创建媒体播放视图，并开始播放
				mediaView.setMediaPlayer(mediaPlayer);
				mediaPlayer.play();
				volumeSlider.setValue(30);
				
			}catch(Exception e) {
				System.out.println("Error in loading movie url");
				e.printStackTrace();
				stop();
			}		
			    
			
			//进度条运动的定时器设置 
			    timer = new Timer();
	    		timer.schedule(new TimerTask() {
	    			public void run() {
	    				slideControl();	
	    			}
	    		}, 0, 100);
                
			
			//进度条的监听
			    slider.valueProperty().addListener((
		            ObservableValue<? extends Number> ov, 
		            Number old_val, Number new_val) -> {
		            if(slider.isPressed()==true) {
		            	double endTime = mediaPlayer.getStopTime().toMillis();
						double startTime = (new_val.doubleValue()/100)*endTime;
						mediaPlayer.stop();
						System.out.println("Stop");
						mediaPlayer.setStartTime(Duration.millis(startTime));
						//mediaPlayer.
						mediaPlayer.play();
						//playPause.setText("暂停");
						playPause.getStylesheets().clear();
						playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		            }
		        });
		 
			//音量控制监听
			    volumeSlider.valueProperty().addListener((
			            ObservableValue<? extends Number> ov, 
			            Number old_val, Number new_val) -> {
			            	 mediaPlayer.setVolume(new_val.doubleValue()/400);
			            });
	            
			    
		}
	    
		//暂停与继续播放视频
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
		
		//进度条跟随
		public void slideControl() {
			
			double endTime = mediaPlayer.getStopTime().toMillis();
			double nowTime = mediaPlayer.getCurrentTime().toMillis();
			double percentValue = (nowTime/endTime) * 100 ;
			
			//显示当前播放时间和视频总时长。
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
		
		//时间格式转换
		private String changeTime(double time) {
			String changed = "";
		    int newTime = (int) (time/1000);
		    String minutes = String.valueOf(newTime/60);
		    String seconds = String.valueOf(newTime - (newTime/60)*60 );
		    if(minutes.length() == 1)minutes = "0"+minutes;
		    if(seconds.length() == 1)seconds = "0"+seconds;
		    changed = minutes+ ":" +seconds;
			return changed;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getKeyCode());
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
}
