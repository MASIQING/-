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
	    
	    //���������� (FXML����)
		@FXML private Button playPause;
		@FXML private Slider slider;
		@FXML private Slider volumeSlider;
		@FXML private MediaView mediaView;
		@FXML private Text showTime;
		@FXML private Button back;
		@FXML private AnchorPane settingPane;
		@FXML private AnchorPane mediaPane;
		@FXML private ImageView volumeImage;

		private String mediaLocation;  //��Ƶ�ļ���ַ  
	    private MediaPlayer mediaPlayer;//�½���MediaPlayer
	    Timer timer = new Timer();
	    
	   
		public String getMediaLocation() {
			return mediaLocation;
		}
		public void setMediaURL(String mediaLocation) {
			this.mediaLocation = mediaLocation;
		}
		
		//�ر�ӰƬ
		public void stop() {
			if(mediaPlayer!=null) mediaPlayer.dispose();
		}
		
		//��ȡ������״̬
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
		
		
		@FXML//��ʼ�����루�����ض���css�ļ���
		public void initialize() {
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		}
		
		//��ʼ������Ƶ
		public void mediaPlay() {
		
			try{	
				//��ȡ��ƵURL ������ý�����
				String mediaURL = getClass().getResource(mediaLocation).toString(); //��Ҫ�Ѷ�ý���ļ����õ�outĿ¼�ϵ�����classĿ¼����
				System.out.println("mediaURL  "+mediaURL);
				Media media = new Media(mediaURL);		
				//��������������
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setAutoPlay(true); //�����Զ�����
                
				System.out.println("Width"+media.getWidth());
				
				//����ý�岥����ͼ������ʼ����
				mediaView.setMediaPlayer(mediaPlayer);
				mediaPlayer.play();
				volumeSlider.setValue(30);
				
			}catch(Exception e) {
				System.out.println("Error in loading movie url");
				e.printStackTrace();
				stop();
			}		
			    
			
			//�������˶��Ķ�ʱ������ 
			    timer = new Timer();
	    		timer.schedule(new TimerTask() {
	    			public void run() {
	    				slideControl();	
	    			}
	    		}, 0, 100);
                
			
			//�������ļ���
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
						//playPause.setText("��ͣ");
						playPause.getStylesheets().clear();
						playPause.getStylesheets().addAll(getClass().getResource("css\\CSS Button Paused.css").toExternalForm());
		            }
		        });
		 
			//�������Ƽ���
			    volumeSlider.valueProperty().addListener((
			            ObservableValue<? extends Number> ov, 
			            Number old_val, Number new_val) -> {
			            	 mediaPlayer.setVolume(new_val.doubleValue()/400);
			            });
	            
			    
		}
	    
		//��ͣ�����������Ƶ
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
		
		//����������
		public void slideControl() {
			
			double endTime = mediaPlayer.getStopTime().toMillis();
			double nowTime = mediaPlayer.getCurrentTime().toMillis();
			double percentValue = (nowTime/endTime) * 100 ;
			
			//��ʾ��ǰ����ʱ�����Ƶ��ʱ����
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
		
		//ʱ���ʽת��
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
