package Model;

import java.util.Timer;
import java.util.TimerTask;

import FrontSide.MenuControl;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class MediaPlay {

	private MenuControl mcControl;
	private String mediaURL;
	private MediaPlayer mediaPlayer;
   
    /**
     * 设置电影文件的URL
     * **/
	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}
    
    public void setMenuControl(MenuControl mcControl) {
    	this.mcControl = mcControl;
    }
    
    public MediaPlayer startMediaPlayer() {
    	
    	try{	
			/**加载影片到视频播放器对象**/
    		//System.out.println("theMediaUrl "+mediaLocation);
			
			Media media = new Media(mediaURL);		
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setVolume(0.5);
			
			mediaPlayer.play();
			
		}catch(Exception e) {
			System.out.println("Error in loading movie url");
			e.printStackTrace();
			//stop();
		}		
		    
		return mediaPlayer;
            
    }
    
    public MediaPlayer getMediaPlayer() {
    	return mediaPlayer;
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
    
    public void playPauseAction(Button playPause, String play, String paused) {
    	
    	if(mediaPlayer.getStatus().toString().equals("PLAYING")) {
			mediaPlayer.pause();
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(play);
		}
		
		if(mediaPlayer.getStatus().toString().equals("PAUSED")) {
			mediaPlayer.play();
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(paused);
		}
		
		if(mediaPlayer.getStatus().toString().equals("STOP")) {
			mediaPlayer.play();
			playPause.getStylesheets().clear();
			playPause.getStylesheets().addAll(paused);
		}
    }

    public void slideControl(Slider slider,Text showTime) {
    	
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
	public String changeTime(double time) {
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
