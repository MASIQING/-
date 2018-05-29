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
 * ���Ʋ������Ĳ�����Ϊ<br>
 * ��������������������������
 * @version 2018/5/27
 * 
 * @author ��˼��
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
	     * ���õ�Ӱ�ļ���URL
	     * **/
		public void setMediaURL(String mediaLocation) {
			this.mediaLocation = mediaLocation;
		}
		
		/**
		 * ֹͣ����,��ղ��������ݲ�ֹͣ��ʱ
		 * **/
		public void stop() {
			if(mediaPlayer!=null){
				mediaPlayer.dispose();
				timer.cancel();
			}
		}
		
		/**
		 * ��ȡ������״̬<br>
		 * @return 0�����������,1���ڲ���,2��ͣ,-1�������
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
		 * PlayerControl�ĳ�ʼ��<br>
		 * ������е���ʽ��Ӧ�����µ���ʽ
		 * **/
		@FXML
		public void initialize() {
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		}
		
		/**
		 * ��ʼ����<br>
		 * �Զ��������µ���Ƶ�ļ�����ʼ��������������������
		 * **/
		public void mediaPlay() {
		
			try{	
				/**����ӰƬ����Ƶ����������**/
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
						playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		            }
		        });
		 
			/**�������ƻ���������**/
			    volumeSlider.valueProperty().addListener((
			        ObservableValue<? extends Number> ov, 
			        Number old_val, Number new_val) -> {
			            /**����˥��ֵ**/
			            int damp = 50;
			            mediaPlayer.setVolume(new_val.doubleValue()/damp);
			     });
	        
		    /**���̴������**/
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
		 * ��ͣ�벥�Ű�ť�ļ���
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
		 * ��Ƶ���Ȼ������Ŀ���
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
		 * ʱ���ʽת��
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
