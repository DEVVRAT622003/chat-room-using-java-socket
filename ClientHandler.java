import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    // Class fields
    private Socket socket;
    private BufferedReader bufferedReader ;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    // Constructor
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER : "+clientUsername + "has entered the chat");
        }catch (IOException e){
            closeEverything(socket , bufferedReader , bufferedWriter);
        }

    }

    @Override
    public void run() {
        String msgFromClient ;
        while(socket.isConnected()){
            try {
                msgFromClient = bufferedReader.readLine();  // This is a blocking operation
                broadcastMessage(msgFromClient);
            }catch (IOException e){
                closeEverything(socket , bufferedReader , bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String msg){
        for(ClientHandler clientHandler : clientHandlers){
            try {
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(msg);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket , bufferedReader , bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER : "+ clientUsername + "has left the chat");
    }

    public void closeEverything(Socket socket  , BufferedReader bufferedReader , BufferedWriter bufferedWriter){
        removeClientHandler();
        try {
            if(socket!= null){
                socket.close();
            }
            if(bufferedReader!= null){
                bufferedReader.close();
            }
            if(bufferedWriter!= null){
                bufferedWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
