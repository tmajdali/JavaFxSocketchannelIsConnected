package application;
	
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application  { 
	Label myLabel ;
    private BooleanProperty booleanProperty = new SimpleBooleanProperty(); 
    Server02 myObject = new Server02();

	     
		  public static void main(String[] args) { 
		 
		    launch(args);   
		  } 
		 
		  
		  public void start(Stage myStage) { 
			 
			  
		    
		    myStage.setTitle("Use a JavaFX label."); 
		 
		 
		    FlowPane rootNode = new FlowPane(10, 10); 
		 
		    rootNode.setStyle("-fx-background-color: #000000");
		    Scene myScene = new Scene(rootNode, 300, 200); 
		 
		   
		    myStage.setScene(myScene); 
		 
		   
		    
		     myLabel = new Label("No Connection"); 
		    // myLabel.setStyle("-fx-background-color:  #00ff00");
		     myLabel.setTextFill(Color.web("#00ff00"));
		     myLabel.setTranslateY(180);
		     myLabel.setTranslateX(210);
		 
		   
		   booleanProperty.addListener(new ChangeListener<Boolean>() {

	            @Override
	            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	                System.out.println("changed " + oldValue + "->" + newValue);
	               
	                Platform.runLater(new Runnable() {
	                    @Override
	                    public void run() {
	                    	if(newValue.booleanValue() == true){
	                    	 myLabel.setText("Connected");
	                    }
	                   else
	                    {
	                    	 myLabel.setText("Lost Connecttion");
	                    }
	                    }
	                });
	                try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
	                
	            }
	        });
           
                  
		   
		    rootNode.getChildren().addAll(myLabel); 
		 
		    
		    myStage.show(); 
		  }
		  public class Server02 implements Runnable{
				Thread th;
				
				Main cl ;
				Server02()
				{
					th = new Thread(this);
					th.start();
				}

				  

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("Sender Start");

			        ServerSocketChannel ssChannel;
					try {
						ssChannel = ServerSocketChannel.open();
						 ssChannel.configureBlocking(true);
					        int port = 3003 ;
					        ssChannel.socket().bind(new InetSocketAddress(port));

					      
					        while (true) {
					            SocketChannel sChannel = ssChannel.accept();
					            if(sChannel.isConnected())
					            {
					            	booleanProperty.set(true);
					            }
					            else
					            {
					            	System.out.println("server : not connected");
					            	booleanProperty.set(false);
					            }
					            
					            
					        }
					} catch (IOException e) {
						
						e.printStackTrace();
					}
			       


			            System.out.println("Connection ended");
				}
//			  
		  }

		
		
		
}
